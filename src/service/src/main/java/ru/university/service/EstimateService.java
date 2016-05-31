package ru.university.service;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.university.entity.Estimate;
import ru.university.entity.Student;
import ru.university.util.TimeIgnoringComparator;

@Service
public class EstimateService {
    @Autowired
    SessionFactory sessionFactory;


    /**
     * Метод, который добавляет оценку за основные задания студенту
     * @param estimate //принимает оценку
     * @param userId //принимает Id студента
     */
    @Transactional
    public void add(Estimate estimate, long userId) {
        Student student = sessionFactory.getCurrentSession().get(Student.class, userId); //Достаем студента из БД по userId
        List<Estimate> estimates = student.getEstimate(); //Получаем список оценок студента
        estimateAdding(estimates, estimate, student); //передаем данные в estimateAdding
    }


    /**
     * Метод, который добавляет оценку за доп задания студенту
     * @param estimate //принимает оценку
     * @param userId //принимает Id студента
     */
    @Transactional
    public void addExtension(Estimate estimate, long userId) {
        Student student = sessionFactory.getCurrentSession().get(Student.class, userId);
        List<Estimate> estimates = student.getExtensionEstimate();
        estimateAdding(estimates, estimate, student);
    }


    /**
     * Метож, который записывает оценку студента в БД
     * @param estimates //принимает все оценки студента
     * @param estimate //принимает оценку студента
     * @param student //принимает студента
     */
    private void estimateAdding(List<Estimate> estimates, Estimate estimate, Student student){
        TimeIgnoringComparator comparator = new TimeIgnoringComparator(); //создаем объект класса TimeIgnoringComparator для сравнения даты в БД и выбранной даты
        boolean finded = false; //заводим флаг
        for (Estimate estimate1 : estimates) { //цикл
            if (comparator.compare(estimate1.getDate(), estimate.getDate())) { //сравниваем нашлась ли выбранная дата в БД
                estimate1.setEstimate(estimate.getEstimate()); //обновляем оценку в БД
                finded = true;
            }
        }
        if (!finded) {
            estimates.add(estimate); // добавляем оценку в БД
        }
        sessionFactory.getCurrentSession().merge(student); //обновляем студента
    }
}
