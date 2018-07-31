package sch.man.com.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import sch.man.com.service.PersonService;
import sch.man.com.model.Person;

/**
 *
 * @author Juliet
 */
@Named(value = "personBean")
@RequestScoped
public class PersonBean {

    private String personId;
    private String firstName;
    private String lastName;
    private String emailId;
    private String phone;
    private String password;
    private String password2;
    private int role;
    private String status;
    private String gender;
    private Date dateOfBirth;
    private String address;
    private Date created;
    private Date updated;

    private static String report;
    private String logName;
    private String logBtn;
    private String servicereport;
    private String message;
    private String position;

    @Autowired
    private PersonService personService;
    private Person person;
    private List<Person> personList;

    public PersonBean() {
    }

    public PersonBean(String personId, String firstName, String lastName, String emailId, String phone, String password, int role, String status, String gender, Date dateOfBirth, String address) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.phone = phone;
        this.password = password;
        this.role = role;
        this.status = status;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public String save() throws ParseException {
        boolean feedBack = false;
        if (!personId.isEmpty() && !firstName.isEmpty() && !lastName.isEmpty() && !emailId.isEmpty() && !phone.isEmpty() && !password.isEmpty() && !gender.isEmpty() && !password.isEmpty()) {

            //   Date dob = new SimpleDateFormat("MM/dd/yy").parse(dateOfBirth);
            person = new Person(personId, firstName, lastName, emailId, phone, password, PersonService.ROLE_STUDENT, PersonService.LOGIN_ACTIVE, gender, dateOfBirth, address);
            personService.save(person);
            feedBack = true;
        } else {
            setReport("Complete all fields");
            return "personRegForm";

        }
        if (feedBack) {
            setReport("Sign up was successful");
            return "index?faces-redirect=true";
        } else {
            setReport("Sign up Failed");
            return "personRegForm?faces-redirect=true";
        }
    }

    public String allPerson() {
        personList = personService.getAllPerson();
        return "personList?faces-redirect=true";
    }

    public String allStudent() {
        personList = personService.getAllStudent();
        return "studentList?faces-redirect=true";

    }

    public String allTeacher() {
        personList = personService.getAllTeacher();
        return "teacherList?faces-redirect=true";
    }

    public String moreInfo(String personId) {
        servicereport = personService.getPerson(personId);
        switch (servicereport) {

            case "admin":
                return "adminInfo?faces-redirect=true";

            case "student":
                return "studentInfo?faces-redirect=true";
            case "teacher":
                return "teacherInfo?faces-redirect=true";
            default:
                return "student";

        }
    }

    public String login() {//throws HandlingExeption
        if (!personId.isEmpty() || !password.isEmpty()) {

            servicereport = personService.login(personId, password);
            switch (servicereport) {
                case "admin":
                    System.out.println("printing from controller");
                    return "admin_dashboard?faces-redirect=true";
                case "teacher":
                    return "teacher_dashboard?faces-redirect=true";
                case "student":
                    return "student_dashboard?faces-redirect=true";
                case "failed":
                    return "index?faces-redirect=true";
                default:
                    return "index";
            }

        } else {
            setReport("Please enter valid username and password");
            return "index";
        }
    }

    public String logout() {
        personService.logout();
        return "index?faces-redirect=true";
    }

    public String loggedUser() {
        servicereport = personService.loggedUser();
        switch (servicereport) {
            case "admin": 
                return "admin_dashboard?faces-redirect=true";
            case "teacher":
                return "teacher_dashboard?faces-redirect=true";
            case "student":
                return "student_dashboard?faces-redirect=true";
            default:
                return "personProfile?faces-redirect=true";
        }

    
    }
    public String updateLoggedPerson() {
        personService.updateLoggedPerson();
        return "personProfile?faces-redirect=true";
    }

    public String assignRole() {
        personService.assignRole(role);
        return "personProfileAdmin?faces-redirect=true";
    }

    public String suspendUser() {
        personService.suspendUser();
        return "personProfileAdmin?faces-redirect=true";
    }

    public String blockUser() {
        personService.blockUser();
        return "personProfileAdmin?faces-redirect=true";
    }

    public String delete() {
        return "";
    }

    public void reset() {
        setAddress(null);
        setDateOfBirth(null);
        setEmailId(null);
        setFirstName(null);
        setGender(null);
        setLastName(null);
        setPersonId(null);
        setPhone(null);
        setRole(0);
        setStatus(null);
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getLogBtn() {
        return logBtn;
    }

    public void setLogBtn(String logBtn) {
        this.logBtn = logBtn;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getServicereport() {
        return servicereport;
    }

    public void setServiceReport(String servicereport) {
        this.servicereport = servicereport;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
