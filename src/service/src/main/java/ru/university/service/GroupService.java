package ru.university.service;


import com.sun.org.apache.regexp.internal.RE;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.university.entity.Group;
import ru.university.entity.Teacher;
import ru.university.service.security.Session;

import java.util.List;

@Service
public class GroupService {


    @Autowired SessionFactory sessionFactory;

    @Autowired Session session;

    @Transactional
    public Group findById(Long id) {
        Group group = sessionFactory.openSession().get(Group.class, id);
        if (group != null) {
            group.getStudents();
        }
        return group;
    }



    @Transactional
    public List<Group> findByTeacher(){
        long teacherId = session.getLoggedUserId();
        Teacher teacher = (Teacher) sessionFactory.openSession().get(Teacher.class, teacherId);
        List<Group> groupList = null;
        if (teacher != null){
            groupList = teacher.getGroups();
            for (Group group : groupList){
                group.getStudents();
            }
        }
        return groupList;
    }

    @Transactional
    public List<Group> getGroupFromCurrentTeacher(){
        long teacherId = session.getLoggedUserId();
        Teacher teacher = sessionFactory.openSession().get(Teacher.class, teacherId);
        List<Group> groupList = teacher.getGroups();
        for (Group group : groupList){
            group.getStudents();
        }
        return  groupList;
    }
}
