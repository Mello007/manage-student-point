package ru.university.service;


import ru.university.entity.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private static final List<Student> ALL_STUDENTS = new ArrayList<>();

    private Connection conn;

    public StudentService(){
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/manage-student-point","postgres", "55164937");
        } catch (SQLException e) {
            System.out.println("Соединиться с базой данных не удалось! ");
        }
        try {
            createDbUserTable(conn);
        } catch (SQLException e) {
            System.out.println("Ошибка! Не удалось создать таблицу!");
        }
    }

    private static void createDbUserTable(Connection dbConnection) throws SQLException {
        Statement statement = null;

        String createTableSQL = String.format("CREATE SEQUENCE if not exists student_id_seq;" +
                " CREATE TABLE if not exists student" +
                "(" +
                "id bigint DEFAULT NEXTVAL('student_id_seq')," +
                "name varchar(100)," +
                "age int," +
                "estimate int," +
                "PRIMARY KEY(id)" +
                ");");

        try {
            statement = dbConnection.createStatement();
            // выполнить SQL запрос
            statement.execute(createTableSQL);
            System.out.println("Метод инициализации выполнен!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public boolean add(Student student){
        try {
            String formatInsertStudentSQL =  "INSERT INTO student (name, age, estimate)\n" +
                    "VALUES (' %1 ',  %2 , %3 );"
                            .replace("%1",student.getFullName())
                            .replace("%2",String.valueOf(student.getAge()))
                            .replace("%3",String.valueOf(student.getEstimate()));
            Statement statement = conn.createStatement();
            statement.execute(formatInsertStudentSQL);
        } catch (SQLException e) {
            System.out.println("Не удалось создать какуюто фигню");
        }
        return true;
    }

    public boolean find(Student student) {
        {
            try {
                String formatInsertStudentSQL =  "SELECT * FROM student where id=\n" +
                        "(' %1 ');"
                                .replace("%1",student.getFullName());
                Statement statement = conn.createStatement();
                statement.execute(formatInsertStudentSQL);
            } catch (SQLException e) {
                System.out.println("Не удалось создать какуюто фигню");
            }
            return true;
        }
    }

    public boolean delete(Student student){
        return ALL_STUDENTS.remove(student);
    }

    public List<Student> look(){
        return ALL_STUDENTS;
    }

}

