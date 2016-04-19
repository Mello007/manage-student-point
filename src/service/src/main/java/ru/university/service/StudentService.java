package ru.university.service;


import ru.university.entity.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private static final List<Student> ALL_STUDENTS = new ArrayList<>();

    private Connection conn;

    public StudentService() {
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/manage-student-point", "postgres", "55164937");
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

    public boolean add(Student student) {
        try {
            String formatInsertStudentSQL = "INSERT INTO student (name, age, estimate)\n" +
                    "VALUES ('%1',  %2 , %3 );"
                            .replace("%1", student.getFullName())
                            .replace("%2", String.valueOf(student.getAge()))
                            .replace("%3", String.valueOf(student.getEstimate()));
            Statement statement = conn.createStatement();
            statement.execute(formatInsertStudentSQL);
            return true;
        } catch (SQLException e) {
            return false;
        }

    }

    public Student find(Student student) {
            try {
                String formatInsertStudentSQL = "SELECT * FROM student WHERE NAME=" +
                        "'%';".replace("%", student.getFullName());
                Statement statement = conn.createStatement();
                statement.execute(formatInsertStudentSQL);
                // выбираем данные с БД
                ResultSet result = statement.executeQuery(formatInsertStudentSQL);
                // И если что то было получено то цикл while сработает
                if  (result.next()){
                    return getStudentFromResultSet(result);
                } else {
                    return null;
                }
            } catch (SQLException e) {
                return null;
            }
    }


    public boolean delete(Student student) {
        {
            try {
                String formatInsertStudentSQL = "DELETE FROM student WHERE NAME=" +
                        "'%';".replace("%", student.getFullName());
                Statement statement = conn.createStatement();
                statement.execute(formatInsertStudentSQL);
                // выбираем данные с БД
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }

    public List<Student> getAll() {
            List<Student> students = new ArrayList<>();
            try {
                String formatInsertStudentSQL = "SELECT * FROM student";
                Statement statement = conn.createStatement();
                statement.execute(formatInsertStudentSQL);
                // выбираем данные с БД
                ResultSet result = statement.executeQuery(formatInsertStudentSQL);
                // И если что то было получено то цикл while сработает
                while (result.next()) {
                    students.add(getStudentFromResultSet(result));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        return students;
    }

    public boolean addEstimate(Student student) {

            try {
                String formatInsertStudentSQL = "UPDATE student SET age = age +%1 WHERE name = '%2';"
                                .replace("%2", student.getFullName())
                                .replace("%1", String.valueOf(student.getEstimate()));
                Statement statement = conn.createStatement();
                statement.execute(formatInsertStudentSQL);
                // выбираем данные с БД
                statement.executeQuery(formatInsertStudentSQL);
                return true;
            } catch (SQLException e) {
                return false;
            }
    }

    public Student getStudentFromResultSet (ResultSet result) throws SQLException {
        Long userid = Long.valueOf(result.getString("id"));
        String username = result.getString("name");
        Integer age = Integer.valueOf(result.getString("age"));
        Integer estimate = Integer.valueOf(result.getString("estimate"));
        Student student = new Student(username, age, estimate);
        student.setId(userid);
        return student;
    }
}


