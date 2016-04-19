package ru.university.service;

import org.apache.commons.lang3.math.NumberUtils;
import ru.university.entity.Student;

import java.io.IOException;
import java.util.*;


public class Main {

    private static final StudentService STUDENT_SERVICE = new StudentService();

    public static void main (String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int userChoice = showMenu(scanner);
            switch (userChoice) {
                case UserMenu.ADD_STUDENT:
                    showResultByBoolen(STUDENT_SERVICE.add(enterParameters(scanner)));
                    break;
                case UserMenu.DELETE_STUDENT:
                    showResultByBoolen(STUDENT_SERVICE.delete(enterParameters(scanner)));
                    break;
                case UserMenu.FIND_STUDENT:
                    showStudent(STUDENT_SERVICE.find(enterParameters(scanner)));
                    break;
                case UserMenu.LOOK_STUDENT:
                    showAllStudents(STUDENT_SERVICE.getAll());
                    break;
                case UserMenu.ADD_ESTIMATE:
                    showResultByBoolen(STUDENT_SERVICE.addEstimate(enterParameters(scanner)));
                    break;
                case UserMenu.EXIT:
                    System.exit(0);
                    break;
            }
        }
    }

    private static Student enterParameters(Scanner scanner){
        System.out.println("Введите ФИО студента: ");
        String fullName = scanner.next();
        System.out.println("Введите возраст студента: ");
        Integer studentAge = scanner.nextInt();
        System.out.println("Введите оценку студента: ");
        Integer studentEstimate = scanner.nextInt();
        return new Student(fullName, studentAge, studentEstimate);
    }

    private static int showMenu(Scanner scanner){
        Integer result = null;
        while (result == null || result > 6 || result < 1) {
            System.out.println("Меню: \r\n " +
                    "1. Добавить студента \r\n 2. Удалить студента \r\n " +
                    "3. Найти студента \r\n 4. Посмотреть всех студентов \r\n" +
                    "5. Добавить балл студенту \r\n 6. Выйти" );
            String inputString = scanner.next();
            boolean inputStinrgIsNumber = NumberUtils.isNumber(inputString);
            if (inputStinrgIsNumber) {
                result = Integer.valueOf(inputString);
            }
        }
        return result;
    }

    private static void showResultByBoolen(boolean result){
        if (result) {
            System.out.println("Работа выполнена успешно");
        } else {
            System.out.println("Работа не выполнена!");
        }
    }

    private static void showAllStudents(List<Student> students){
        for (Student student : students){
            System.out.println(student);
        }
    }

    private static void showStudent(Student student){
        if (student != null) {
            System.out.println(student);
        }
    }

}


