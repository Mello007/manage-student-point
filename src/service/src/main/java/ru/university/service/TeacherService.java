package ru.university.service; //Указываем расположение нашего класса

//Подключаем необходимые библиотеки
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.university.entity.Teacher;
import java.util.List;

//Класс TeacherService
@Service
public class TeacherService {
    @Autowired SessionFactory sessionFactory;

    /**
     * Метод который создает преподавателя
     * @param teacher принимает преподавателя
     * @return
     */
    @Transactional
    public Teacher createTeacher(Teacher teacher) {
        sessionFactory.getCurrentSession().save(teacher);//сохраняет полученного преподавателя в БД
        return teacher;
    }

    /**
     * Метод, который ищет преподавателя по Логину
     * @param login
     * @return
     */
    public Teacher findByName(String login){
        Query query = sessionFactory.getCurrentSession().createQuery("from Teacher where login = :login"); //Делаем запрос в БД с помощью HQL
        query.setParameter("login", login); //Указываем что в запросе login будет принимаемый login
        Teacher teacher = (Teacher)query.uniqueResult(); //Создаем нового преподавателя, который будет получен из БД
        return teacher; //возвращаем преподавателя
    }

    /**
     * Метод, который выводит список всех преподавателей
     * @return
     */
    @Transactional
    public List<Teacher> getAll(){
        List<Teacher> teachers = sessionFactory.getCurrentSession().createCriteria(Teacher.class).list(); //делаем запрос в БД по получению всех преподавателей
        return teachers;
    }

    /**
     * Метод, который удаляет преподавателя
     * @param fullname принимает имя преподавателя
     * @return
     */
    @Transactional
    public int deleteTeaher (String fullname){
        Query query1 = sessionFactory.getCurrentSession().createQuery("delete from Teacher WHERE fullName = :fullname");
        query1.setParameter("fullname", fullname);
        return query1.executeUpdate();
    }
}
