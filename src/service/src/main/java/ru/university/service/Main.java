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
                    showResultByBoolen(STUDENT_SERVICE.find(enterParameters(scanner)));
                    break;
                case UserMenu.LOOK_STUDENT:
                    showStudent(STUDENT_SERVICE.look());
                    break;
                case UserMenu.EXIT:

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
        while (result == null || result > 4 || result < 1) {
            System.out.println("Меню: \r\n " +
                    "1. Добавить студента \r\n 2. Удалить студента \r\n " +
                    "3. Просмотреть список всех студенов \r\n 4. Найти студента \r\n 5. Выйти" );
            String inputString = scanner.next();
            boolean inputStinrgIsNumber = NumberUtils.isNumber(inputString);
            if (inputStinrgIsNumber) {
                result = Integer.valueOf(inputString);
            }
        }
        return result;
    }

    private static void showStudent(List<Student> students){

        for(Student student : students) {
            System.out.println(student);
        }
        System.out.println("Работа выполнена!");
    }

    private static void showResultByBoolen(boolean result){
        System.out.println(result);
        System.out.println("Работа выполнена");
    }
}


