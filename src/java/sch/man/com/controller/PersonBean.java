package sch.man.com.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sch.man.com.DTO.PersonDto;

/**
 *
 * @author LEOGOLD
 */
@Named(value = "bean")
@RequestScoped
public class PersonBean {

    private String personId;
    private String password;
    private String firstName;
    private String lastName;
    private String gender;
    private String dateOfBirth;
    private String address;
    private String phoneNumber;
    private String emailId;
    
    private RegistrationForm regForm = new RegistrationForm();
    
    public PersonBean() {
    }

    public PersonBean(String personId, String password, String firstName, String lastName, String gender, String dateOfBirth, String address, String phoneNumber, String emailId) {
        this.personId = personId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
    }

   
    
    public String retrieveAllPerson(){
        
        StringBuilder fName = new StringBuilder();
        StringBuilder lName = new StringBuilder();
        StringBuilder gen = new StringBuilder();
        StringBuilder dOB = new StringBuilder();
        StringBuilder homeAddress = new StringBuilder();
        StringBuilder phone = new StringBuilder();
        StringBuilder email = new StringBuilder();
        
        PersonDto beanHelper = new PersonDto();
        String uName = getPersonId();
        String pass = getPassword();
        
        boolean validate = false;
        List<RegistrationForm> registrationForm = new ArrayList<RegistrationForm>();
        registrationForm = beanHelper.getStudent();
        
        for(Iterator iterate  = registrationForm.iterator(); iterate.hasNext();){
            regForm  = (RegistrationForm)iterate.next();
          if (regForm.getUserId().toString().equals(uName) && regForm.getPassword().toString().equals(pass)){
              
              validate = true;
              fName.append(regForm.getFirstName() );
              lName.append(regForm.getLastName() );
              gen.append(regForm.getGender() );
              dOB.append(regForm.getDateOfBirth() );
              homeAddress.append(regForm.getAddress() );
              email.append(regForm.getEmailId() );
              phone.append(regForm.getContactNumber() );
          }  
          
          else {
              validate =false;
            }
        }
        if(validate){
           return "student"; 
        }
        else{
            return "error";
        }
    }

    public void setValues (StringBuilder fName, StringBuilder lName, StringBuilder gen, StringBuilder dOB, StringBuilder homeAddress,StringBuilder phone, StringBuilder email){
        setFirstName(fName.toString());
        setLastName(lName.toString());
        setGender(gen.toString());
        setDateOfBirth(dOB.toString());
        setAddress(homeAddress.toString());
        setPhoneNumber(phone.toString());
        setEmailId(email.toString());
    }
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RegistrationForm getRegForm() {
        return regForm;
    }

    public void setRegForm(RegistrationForm regForm) {
        this.regForm = regForm;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    
}
