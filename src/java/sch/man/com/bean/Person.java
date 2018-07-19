/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sch.man.com.bean;

import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Juliet
 */
@Named(value = "student")
@Dependent
public class Person {

    
    private String firstName;
    private String lastName;
    private String parentName;
    private String parentPhone;
    private Date dob;
    
    public Person() {
    }

    public Person(String firstName, String lastName, String parentName, String parentPhone, Date dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.parentName = parentName;
        this.parentPhone = parentPhone;
        this.dob = dob;
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

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
    
}
