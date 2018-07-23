package sch.man.com.unused;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException; 
import sch.man.com.config.Studentdetail;

/**
 *
 * @author LEOGOLD
 */
@ManagedBean
@Named(value = "registrationForm")
@RequestScoped
public class RegistrationForm {
    
    private String firstName;
    private String lastName;
    private String gender;
    private String address;
    private String contactNumber;
    private String emailId;
    private String userId;
    private String password;
    private String confirmPassword;
    private String dateOfBirth;
    private String finalPassword;
    private String userIdError;
    
    public RegistrationForm() {
    }
    
    public RegistrationForm(String firstName, String lastName, String gender, String address, String contactNumber, String emailId, String userId, String fPassword, String dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.address = address;
        this.contactNumber = contactNumber;
        this.emailId = emailId;
        this.userId = userId;        
        this.finalPassword = fPassword;
        this.dateOfBirth = dob;
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
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getContactNumber() {
        return contactNumber;
    }
    
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    
    public String getEmailId() {
        return emailId;
    }
    
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getConfirmPassword() {
        return confirmPassword;
    }
    
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    
    public void setDateOfBirth(String d) {
        this.dateOfBirth = d;
    }
    
    public String getFinalPassword() {
        return finalPassword;
    }
    
    public void setFinalPassword(String finalPassword) {
        this.finalPassword = finalPassword;
    }

    public String getUserIdError() {
        return userIdError;
    }
    
    public void setUserIdError(String userIdError) {
        this.userIdError = userIdError;
    }
    
    public void validateEmail(FacesContext fc, UIComponent c, Object value) throws ValidatorException {
        String email = (String) value;
        Pattern mask = null;
        mask = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher matcher = mask.matcher(email);
        if (!matcher.matches()) {
            FacesMessage message = new FacesMessage();
            message.setDetail("Invalid e-mail ID");
            message.setSummary("Input a valid e-mail");
            throw new ValidatorException(message);
        }
    }
    
    public void validateFinalPassword(FacesContext fc, UIComponent c, Object value) throws ValidatorException {
        setFinalPassword((String) value);
        getFinalPassword();
    }
    
    public void validateCPassword(FacesContext fc, UIComponent c, Object value) throws ValidatorException {
        String cPassword = (String) value;
        //  RegistrationForm regForm = new RegistrationForm(); 
        if (cPassword.compareTo(getFinalPassword()) != 0) {
            FacesMessage message = new FacesMessage();
            message.setSummary("Password does not match");
            throw new ValidatorException(message);
        }
        
    }

    public void validateInput(FacesContext fc, UIComponent c, Object value) {
        if (((String) value).contains("!") || ((String) value).contains("@") || ((String) value).contains("#") || ((String) value).contains("$") || ((String) value).contains("%") || ((String) value).contains("^") || ((String) value).contains("&") || ((String) value).contains("*") || ((String) value).contains("(") || ((String) value).contains(")") || ((String) value).contains("<") || ((String) value).contains(">") || ((String) value).contains("/") || ((String) value).contains("?") || ((String) value).contains(";") || ((String) value).contains(":") || ((String) value).contains("|") || ((String) value).contains("\\")) {
            throw new ValidatorException(new FacesMessage("Cannot contain special character"));
        }
        
    }
    
    //This method is being called on the registration form page when a user submits form
    public String submitAction() {
        
        RegistrationDto regOperation = new RegistrationDto();
        
        //Calling the Studentdetail Entity class mapped to DB and passing the values from the registrationForm bean
        //through its constructor
        Studentdetail student = new Studentdetail(userId, firstName, lastName, gender, dateOfBirth, address, dateOfBirth, emailId, password);
        
//        student.setFirstname(getFirstName());
//        student.setLastname(getLastName());
//        student.setGender(getGender());
//        student.setDob(getDOB());
//        student.setAddress(getAddress());
//        student.setPhone(getContactNumber());yy
//        student.setEmail(getEmailID());
//        student.setUserid(getUserID());
//        student.setPassword(getPassword());
        

    //calling the register() method in RegistrationDBOperation and returns a String value either "true" or "error"
        String message = regOperation.register(student);
        
        //This uses the retured value of register method notifies the user of the existence of the chosing detail
        if(message.equals("error")){
            setUserIdError("User Already exist. Please select another user");
            setUserId("");
            return "Registration";
        }
        else {
            return "Success";
        }
    }
}
