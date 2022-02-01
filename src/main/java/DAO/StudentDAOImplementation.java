package DAO;

import Entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class StudentDAOImplementation implements StudentRepository{
    private EntityManager entityManager;

    @Autowired
    public StudentDAOImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Student> findAll() {
        Session session = entityManager.unwrap(Session.class);

        Query<Student> query = session.createQuery("from Student inner JOIN studentDetails", Student.class);

        List<Student> students = query.getResultList();

        return students;
    }

    @Override
    public Student findById(long id) {
        Session session = entityManager.unwrap(Session.class);

        Student student = session.get(Student.class, id);

        return student;
    }

    @Override
    public void save(Student student) {
        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(student);
    }

    @Override
    public void deleteById(long id) {
        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery("delete from Student where id=:id");

        query.setParameter("id", id);

        query.executeUpdate();
    }
}
