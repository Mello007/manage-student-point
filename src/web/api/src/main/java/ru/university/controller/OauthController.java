package ru.university.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.university.service.OauthService;

import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController //Указываем, что это будет контроллером
@RequestMapping("oauth") //Указываем URL для данного класса

public class OauthController {

    @Autowired OauthService oauthService;

    @RequestMapping(value = "vk", method = RequestMethod.GET)
    public String sendLink(){
        String link = "https://oauth.vk.com/authorize?client_id=5499487&display=page&redirect_uri=http://localhost:8080/oauth/token&scope=offline&response_type=code&v=5.52";
        System.out.println(link);
        return link;
    }

    @RequestMapping(value = "token", method = RequestMethod.GET)
    public void getToken(@RequestParam String code, HttpServletResponse responseUrl) throws Exception{
        String reqUrl = "https://oauth.vk.com/{METHOD_NAME}?client_id={USER_ID}&client_secret={CLIENT_SECRET}&redirect_uri={REDIRECT_URI}&code={CODE}"
                .replace("{METHOD_NAME}", "access_token")
                .replace("{USER_ID}", "5499487")
                .replace("{CLIENT_SECRET}", "bMTTeUDFad7H95I8LiIt")
                .replace("{REDIRECT_URI}", "http://localhost:8080/oauth/token")
                .replace("{CODE}", code);
        System.out.println(reqUrl);
        URL obj = new URL(reqUrl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        int responseCode = con.getResponseCode();
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(response.toString());
        System.out.println(actualObj.findValue("access_token"));
        String token = String.valueOf(actualObj.findValue("access_token")).replace("\"", "");
        String userId = String.valueOf(actualObj.findValue("user_id")).replace("\"", "");
        System.out.println(token);
        oauthService.requestToUserForFindName(userId);
        responseUrl.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        responseUrl.setHeader("Location", "http://localhost:8080/resources/manage.html");
    }
}