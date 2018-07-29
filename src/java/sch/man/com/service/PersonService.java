package sch.man.com.service;
//08095267180 08181326516


import java.util.List;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sch.man.com.model.Person;

/**
 *
 * @author LEOGOLD
 */

@Service
@Transactional
public class PersonService { 
    
    public static final String LOGIN_ACTIVE = "act";
    public static final String LOGIN_BLOCKED = "blk";
    public static final String LOGIN_INACTIVE = "ina";

    public static final int ROLE_ADMIN = 1;
    public static final int ROLE_USER = 2;

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;
    HttpSession httpSession;
    private String hql;
    private Query query;

    public PersonService() {
        
    }
    
    public String save(Person person){
       session = sessionFactory.getCurrentSession();
       session.save(person);
        return "success";
    }
    
    public Person login(String personId, String password) {
        Person person = null;
        try {
            session = sessionFactory.getCurrentSession();

            person = (Person) session.createQuery("FROM Person u "
                    + "WHERE u.personId = :person "
                    + "AND u.password = :password")
                    .setParameter("person", personId)
                    .setParameter("password", password)
                    .uniqueResult();
            return person;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    public List <Person> getAllPerson(){
        
        List <Person> person = null;
        try{
            session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Person");
            person = (List<Person>) query.list();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return person;
    }
    
    public Person getPerson(String username) {
        session = sessionFactory.getCurrentSession();
        Person person = null;
        try {
            session = sessionFactory.getCurrentSession();

            person = (Person) session.createQuery("FROM Person u "
                    + "WHERE u.username = :username")
                    .setParameter("username", username)
                    .uniqueResult();
            return person;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    public int updatePerson(Person user, String username) {
        hql = "UPDATE Person u SET u.firstName=:fName, u.lastName=:lName, u.phone=:phone,u.emailId=:email WHERE u.username =:uName";
        session = sessionFactory.getCurrentSession();
        query = session.createQuery(this.hql);
        query.setParameter("uName", username);
        query.setParameter("fName", user.getFirstName());
        query.setParameter("lName", user.getLastName());
        query.setParameter("phone", user.getPhone());
        query.setParameter("email", user.getEmailId());

        return query.executeUpdate();

    }

    public void deletePerson(Object username) {
        session = sessionFactory.getCurrentSession();
        hql = "DELETE FROM Person u WHERE u.username=:username";
        Query query = session.createQuery(hql);
        
        query.setParameter("username", username);
        query.executeUpdate();
    }

}
