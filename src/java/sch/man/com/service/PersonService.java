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
    public static final int ROLE_TEACHER = 2;
    public static final int ROLE_STUDENT = 3;
    int role;

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;
    HttpSession httpSession;
    private String hql;
    private Query query;

    Person person;
    String username;

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

            person = (Person) session.createQuery("FROM Person p "
                    + "WHERE p.personId = :person "
                    + "AND p.password = :password")
                    .setParameter("person", personId)
                    .setParameter("password", password)
                    .uniqueResult();
            setPersonBean(person);
        } catch (EmptyResultDataAccessException ex) {
            ex.printStackTrace();
        }

        if (person != null) {

            role = person.getRole();
            int serial = person.getSerial();
            httpSession = SessionUtils.getSession();

            if (serial == 1) {

                //This is statement will only run once at the first Login of the admin
                if (role != 1) {
                    httpSession.setAttribute("personView", person);
                    assignRole(ROLE_ADMIN);
                    role = ROLE_ADMIN;
                    httpSession.removeAttribute("personView");
                }
                httpSession.setAttribute("personId", person.getPersonId());
                httpSession.setAttribute("loggedUser", person);

                personBean.setLogName(person.getPersonId());
                personBean.setLogBtn("Log Out");
                personBean.setReport(null);
                setPersonBean(person);
                return "admin";
            } else {
                String status = person.getStatus();
                switch (status) {
                    case LOGIN_ACTIVE:

                        httpSession = SessionUtils.getSession();
                        httpSession.setAttribute("personId", person.getPersonId());
                        httpSession.setAttribute("loggedUser", person);

                        personBean.setLogName(person.getPersonId());
                        personBean.setLogBtn("Log Out");
                        personBean.setReport(null);
                        setPersonBean(person);

                        switch (role) {

                            case ROLE_ADMIN:
                                httpSession.setAttribute("adminRole", role);
                                return "admin";

                            case ROLE_TEACHER:
                                return "teacher";

                            case ROLE_STUDENT:
                                return "student";

                            default:
                                return "student";
                        }
                    case LOGIN_BLOCKED:
                        personBean.setReport("Sorry  " + person.getFirstName() + "! your Account is Currently blocked, please contact admin for further clearifications");
                        personBean.setPersonId(person.getPersonId());
                        return "blocked";

                    case LOGIN_INACTIVE:
                        personBean.setReport("Sorry  " + person.getFirstName() + "! We noticed that your Account is Currently inactive, this may be due to an unresovled issues,  please contact admin for further clearifications");
                        personBean.setPersonId(person.getPersonId());
                        return "inactive";

                    default:
                        personBean.setReport("Sorry  " + person.getFirstName() + "! you can't currently access your account now , please try again later or contact admin for further clearifications");
                        personBean.setPersonId(person.getPersonId());
                        return "issue";
                }
            }
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
            case ROLE_STUDENT:
                return "student";
            case ROLE_TEACHER:
                return "teacher";
            default:
                return "person";
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
        httpSession.removeAttribute("currentList");
        try {
            session = sessionFactory.getCurrentSession();
            query = session.createQuery("from Person");
            personList = query.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (personList != null) {
            personBean.setServiceReport("success");

            return personList;
        } else {
            personBean.setServiceReport("failed");
            return null;
        }
    }

    public List<Person> getAllTeacher() {
        httpSession.removeAttribute("currentList");
        try {
            session = sessionFactory.getCurrentSession();
            hql = "FROM Person p WHERE p.role=:role";
            query = session.createQuery(hql);
            query.setParameter("role", ROLE_TEACHER);
            personList = query.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (personList != null) {
            httpSession.setAttribute("currentList", personList);
            personBean.setServiceReport("success");

            return personList;
        } else {
            personBean.setServiceReport("failed");
            return null;
        }
    }

    public List<Person> getAllStudent() {
        httpSession.removeAttribute("currentList");
        try {
            session = sessionFactory.getCurrentSession();
            hql = "FROM Person p WHERE p.role=:role";
            query = session.createQuery(hql);
            query.setParameter("role", ROLE_STUDENT);
            personList = query.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (personList != null) {
            httpSession.setAttribute("currentList", personList);
            personBean.setServiceReport("success");

            return personList;
        } else {
            personBean.setServiceReport("failed");
            return null;
        }
    }

    public String getPerson(String personId) {
        session = sessionFactory.getCurrentSession();

        try {
            session = sessionFactory.getCurrentSession();

            person = (Person) session.createQuery("FROM Person p "
                    + "WHERE p.personId=:id")
                    .setParameter("id", personId)
                    .uniqueResult();

        } catch (EmptyResultDataAccessException ex) {
            ex.printStackTrace();
        }
        httpSession = SessionUtils.getSession();
        httpSession.setAttribute("personView", person);
        personBean.setPerson(person);

        role = person.getRole();
        switch (role) {

            case ROLE_ADMIN:
                personBean.setPosition("Admin");
                return "admin";

            case ROLE_STUDENT:
                personBean.setPosition("Student");
                return "student";
            case ROLE_TEACHER:
                personBean.setPosition("Teacher");
                return "teacher";
            default:
                return "student";

        }
    }

    public Person getPerson1(String personId) {
        session = sessionFactory.getCurrentSession();
        try {
            session = sessionFactory.getCurrentSession();

            person = (Person) session.createQuery("FROM Person u "
                    + "WHERE u.personId = :username")
                    .setParameter("username", personId)
                    .uniqueResult();

        } catch (EmptyResultDataAccessException ex) {
            ex.printStackTrace();
        }
        return person;
    }

    public int assignRole(int roleToAssign) {
//        if(roleToAssign == ROLE_ADMIN){
//            roleToAssign = ROLE_ADMIN;
//        }else{
//            roleToAssign =ROLE_TEACHER;
//        }
        Person personView = (Person) httpSession.getAttribute("personView");

        hql = "UPDATE Person p SET p.role=:role WHERE p.personId =:id";
        session = sessionFactory.getCurrentSession();
        query = session.createQuery(this.hql);
        query.setParameter("id", personView.getPersonId());
        query.setParameter("role", roleToAssign);

        return query.executeUpdate();

    }

    public int blockUser() {
        Person personView = (Person) httpSession.getAttribute("personView");

        hql = "UPDATE Person p SET p.status=:status WHERE p.personId =:id";
        session = sessionFactory.getCurrentSession();
        query = session.createQuery(this.hql);
        query.setParameter("id", personView.getPersonId());
        query.setParameter("status", personBean.getStatus());

        return query.executeUpdate();

    }

    public int suspendUser() {
        Person personView = (Person) httpSession.getAttribute("personView");

        hql = "UPDATE Person p SET p.status=:status WHERE p.personId =:id";
        session = sessionFactory.getCurrentSession();
        query = session.createQuery(this.hql);
        query.setParameter("id", personView.getPersonId());
        query.setParameter("status", personBean.getStatus());

        return query.executeUpdate();

    }

    public String updateLoggedPerson() {
        username = SessionUtils.getPersonId();
        hql = "UPDATE Person p SET "
                + "p.firstName=:fName,"
                + "p.lastName=:lName, "
                + "p.phone=:phone,"
                + "p.emailId=:email,"
                + "p.gender=:gender,"
                + "p.dateOfBirth=:dob,"
                + "p.address=:address "
                + "WHERE p.personId =:id";

        session = sessionFactory.getCurrentSession();
        query = session.createQuery(this.hql);
        query.setParameter("id", username);
        query.setParameter("fName", personBean.getFirstName());
        query.setParameter("lName", personBean.getLastName());
        query.setParameter("phone", personBean.getPhone());
        query.setParameter("email", personBean.getEmailId());
        query.setParameter("gender", personBean.getGender());
        query.setParameter("dob", personBean.getDateOfBirth());
        query.setParameter("address", personBean.getAddress());
        int quryResult = query.executeUpdate();

        if (quryResult != 0) {
            personBean.setMessage("Your details have been updated successfully");
            return "success";
        } else {
            personBean.setMessage("Update was not succesful");
            return "failed";
        }
    }

    public String deletePerson() {
        session = sessionFactory.getCurrentSession();
        person = (Person) httpSession.getAttribute("personView");
        role = person.getRole();
        String personId = person.getPersonId();

        hql = "DELETE FROM Person p WHERE p.personId=:id";
        query = session.createQuery(hql);

        query.setParameter("id", personId);
        query.executeUpdate();

        switch (role) {
            case ROLE_STUDENT:
                return "student";
            case ROLE_TEACHER:
                return "teacher";
            default:
                return "allList";

        }

    }

    public int getNumberOfStudents() {
        if (getAllStudent() != null) {
            return getAllStudent().size();
        } else {
            return 0;
        }
    }

    public int getNumberOfTeachers() {
        if (getAllTeacher() != null) {
            return getAllTeacher().size();
        } else {
            return 0;
        }
    }
}
