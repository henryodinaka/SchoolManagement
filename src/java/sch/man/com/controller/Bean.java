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
public class Bean {

    private String ID;
    private String PASS;
    private String firstName;
    private String lastName;
    private String gender;
    private String dob;
    private String address;
    private String phoneNumber;
    private String emailID;
    
    private RegistrationForm regForm = new RegistrationForm();
    
    public Bean() {
    }

    public Bean(String ID, String PASS, String firstName, String lastName, String gender, String dob, String address, String phoneNumber, String emailID) {
        this.ID = ID;
        this.PASS = PASS;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailID = emailID;
    }

   
    
    public String action(){
        
        StringBuilder fName = new StringBuilder();
        StringBuilder lName = new StringBuilder();
        StringBuilder gen = new StringBuilder();
        StringBuilder dOB = new StringBuilder();
        StringBuilder homeAddress = new StringBuilder();
        StringBuilder phone = new StringBuilder();
        StringBuilder email = new StringBuilder();
        
        PersonDto beanHelper = new PersonDto();
        String uName = getID();
        String pass = getPASS();
        
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
        setDob(dOB.toString());
        setAddress(homeAddress.toString());
        setPhoneNumber(phone.toString());
        setEmailID(email.toString());
    }
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPASS() {
        return PASS;
    }

    public void setPASS(String PASS) {
        this.PASS = PASS;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
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

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }
    
}
