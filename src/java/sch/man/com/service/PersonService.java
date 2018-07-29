package sch.man.com.service;
//08095267180 08181326516

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sch.man.com.controller.PersonBean;
import sch.man.com.model.Person;
import sch.man.com.utility.SessionUtils;

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
    int role;

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;
    HttpSession httpSession;
    private String hql;
    private Query query;

    Person person;

    @Autowired
    PersonBean personBean;
    List<Person> personList = null;

    public PersonService() {

    }

    public String save(Person person) {
        session = sessionFactory.getCurrentSession();
        session.save(person);
        return "success";
    }

    public String login(String personId, String password) {
        try {
            session = sessionFactory.getCurrentSession();

            person = (Person) session.createQuery("FROM Person u "
                    + "WHERE u.personId = :person "
                    + "AND u.password = :password")
                    .setParameter("person", personId)
                    .setParameter("password", password)
                    .uniqueResult();
            setPersonBean(person);
        } catch (EmptyResultDataAccessException ex) {
            ex.printStackTrace();
        }

        if (person != null) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("personId", person.getPersonId());
            session.setAttribute("loggedUser", person);

            personBean.setLogName(person.getPersonId());
            personBean.setLogBtn("Log Out");
            setPersonBean(person);
            return "success";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid login", "Please enter correct Username and Password"));
            personBean.setReport("wrong username or password");
            return "failed";
        }
    }

    public String logout() {
        httpSession = SessionUtils.getSession();
        httpSession.invalidate();
        personBean.setReport("Log out successful");
        personBean.setLogBtn(null);
        personBean.setLogName(null);
        return "index";
    }


    public String loggedUser() {
        httpSession = SessionUtils.getSession();
        person = (Person) httpSession.getAttribute("loggedUser");
        role = person.getRole();
        setPersonBean(person);
        switch (role) {
            case ROLE_ADMIN:
                return "admin";
            case ROLE_USER:
                return "user";
            default:
                return "user";
        }
    }

    public void setPersonBean(Person person) {
        personBean.setAddress(person.getAddress());
        personBean.setDateOfBirth(person.getDateOfBirth());
        personBean.setEmailId(person.getEmailId());
        personBean.setFirstName(person.getFirstName());
        personBean.setGender(person.getGender());
        personBean.setLastName(person.getLastName());
        personBean.setPersonId(person.getPersonId());
        personBean.setPhone(person.getPhone());
        personBean.setRole(person.getRole());
        personBean.setStatus(person.getStatus());
        personBean.setCreated(person.getCreated());
        personBean.setUpdated(person.getUpdated());
    }

    public List<Person> getAllPerson() {
        try {
            session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Person");
            personList = (List<Person>) query.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return personList;
    }

    public Person getPerson(String username) {
        session = sessionFactory.getCurrentSession();

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
