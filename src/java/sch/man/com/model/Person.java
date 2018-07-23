package sch.man.com.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author LEOGOLD
 */
@Entity
@Table (name= "person")
public class Person implements Serializable {
 
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "personId", nullable = false, unique = true)
    private String personId;
    
    @Column (name = "password",nullable = false)
    private String password;
     
    @Column (name = "email",nullable = false,unique = true)
    private String emailId;
        
    @Column(name = "first_name",nullable = false)
    private String firstName;
    
    @Column (name = "last_name",nullable = false)
    private String lastName;
    
    @Column (name = "phone_number",nullable = false, unique = true)
    private String phone;
    
    @Column(name = "Gender")
     private String gender;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "Date_Of_Birth",nullable = false)
     private Date dateOfBirth;
    
    @Column(name = "Address",nullable = false)
     private String address; 
        
    @Column (name = "status",nullable = false, length = 3) //this defines user's status: blk = blocked, act = active , ina = inactive
    private String status;
    
    @Column (name = "role",nullable = false) // Admin role is 1, teacher 2, student 3
    private int role;
    
    @CreationTimestamp
    @Temporal (TemporalType.TIMESTAMP)
    @Column (name = "date_created")
    private Date created;
     
    @UpdateTimestamp
    @Temporal (TemporalType.TIMESTAMP)
    @Column (name = "last_modified")
    private Date updated;  
    
    @OneToOne(mappedBy ="personId",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            targetEntity = Student.class)
    private Student studentId ;
    
    @OneToOne(mappedBy ="personId",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            targetEntity = Teacher.class)
    private Teacher teacherId ;
    
    public Person() {
    }

    public Person(String personId, String firstName, String lastName, String emailId, String phone, String password, int role, String status, String gender, Date dateOfBirth, String address) {
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

    @Override
    public String toString() {
        return "Person{" + "personId=" + personId + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId + ", phone=" + phone + ", password=" + password + ", role=" + role + ", status=" + status + ", gender=" + gender + ", dob=" + dateOfBirth + ", address=" + address + ", created=" + created + ", updated=" + updated + '}';
    }

}
