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

    @Transactional
    public void add(Estimate estimate, long userId) {
        Student student = sessionFactory.getCurrentSession().get(Student.class, userId);
        List<Estimate> estimates = student.getEstimate();
        estimateAdding(estimates, estimate, student);
    }

    @Transactional
    public void addExtension(Estimate estimate, long userId) {
        Student student = sessionFactory.getCurrentSession().get(Student.class, userId);
        List<Estimate> estimates = student.getExtensionEstimate();
        estimateAdding(estimates, estimate, student);
    }

    private void estimateAdding(List<Estimate> estimates, Estimate estimate, Student student){
        TimeIgnoringComparator comparator = new TimeIgnoringComparator();
        boolean finded = false;
        for (Estimate estimate1 : estimates) {
            if (comparator.compare(estimate1.getDate(), estimate.getDate())) {
                estimate1.setEstimate(estimate.getEstimate());
                finded = true;
            }
        }
        if (!finded) {
            estimates.add(estimate);
        }
        sessionFactory.getCurrentSession().merge(student);
    }
}
