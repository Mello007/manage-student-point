package ru.university.service;


import com.couchbase.client.deps.com.fasterxml.jackson.databind.JsonNode;
import com.couchbase.client.deps.com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.university.dto.TeacherDTO;
import ru.university.entity.Teacher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@Service
@Data
public class OauthService {

    @Autowired SessionFactory sessionFactory;
    @Autowired TeacherService teacherService;

    private final String USER_AGENT = "Mozilla/5.0";

    private final String API_VERSION = "5.21";

    private final String AUTH_URL = "https://oauth.vk.com/authorize"
            + "?client_id={APP_ID}"
            + "&scope={PERMISSIONS}"
            + "&redirect_uri={REDIRECT_URI}"
            + "&display={DISPLAY}"
            + "&v={API_VERSION}"
            + "&response_type=token";

    private  final String API_REQUEST = "https://api.vk.com/method/users.isAppUser?user_id=122269742&access_token=e39603a7395c775ebacbad374fa702912274ee087af64065adcf61459ecb3fbee66c7eee991390c6226cb&v=5.21"
            + "?{PARAMETERS}"
            + "&access_token={ACCESS_TOKEN}"
            + "&v=" + API_VERSION;

    private final String API_REQUEST1 = "https://api.vk.com/method/{METHOD_NAME}?access_token={ACCESS_TOKEN}&v={API_VERSION}";

    @Transactional
    private StringBuffer apiRequestToUser(String reqUrl) throws IOException {
        URL obj = new URL(reqUrl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response;
    }

    @Transactional
    public void requestToUserForManageToken(String accessToken, String userId) throws Exception {
        String userName, userLastName, userCity;
        String method = "user.get";
        String reqUrl = API_REQUEST1
                .replace("{METHOD_NAME}", method)
                .replace("{ACCESS_TOKEN}", accessToken)
                .replace("{API_VERSION}", API_VERSION);
        System.out.println(reqUrl);
        StringBuffer response = apiRequestToUser(reqUrl);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(response.toString());
        if (actualObj.get("response") == null){
            throw new Exception("This token isn't right!");
        }
        userName = String.valueOf(actualObj.get("response").findValue("first_name")).replace("\"", "");
        userLastName = String.valueOf(actualObj.get("response").findValue("last_name")).replace("\"", "");
        userCity = String.valueOf(actualObj.get("response").findValue("home_town")).replace("\"", "");
        System.out.println(userName + userLastName + userCity);
    }

    @Transactional
    public void requestToUserForFindName(String userId) throws Exception {
        String userName, userLastName, userCity;
        String method = "users.get";
        String reqUrl = "https://api.vk.com/method/{METHOD_NAME}?user_id={userID}"
                .replace("{METHOD_NAME}", method)
                .replace("{userID}", userId);
        System.out.println(reqUrl);
        StringBuffer response = apiRequestToUser(reqUrl);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(response.toString());
        if (actualObj.get("response") == null){
            throw new Exception("Ошибка авторизации!!!");
        }
        userName = String.valueOf(actualObj.get("response").findValue("first_name")).replace("\"", "");
        userLastName = String.valueOf(actualObj.get("response").findValue("last_name")).replace("\"", "");
        String fullName = userName + " " + userLastName;
//        userCity = String.valueOf(actualObj.get("response").findValue("home_town")).replace("\"", "");
        System.out.println(userName + userLastName);
        Teacher teacher = new Teacher();
        teacher.setFullName(fullName);
        teacherService.createTeacher(teacher);
    }
}
