package sch.man.com.DTO;
//08095267180 08181326516


import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sch.man.com.config.HibernateUtil;
import sch.man.com.controller.RegistrationForm;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LEOGOLD
 */

public class PersonDto {
    
    Session session = null;

    public PersonDto() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }
    
    public List <RegistrationForm> getStudent(){
        
        List <RegistrationForm> student = null;
        try{
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Person");
            student = (List<RegistrationForm>) query.list();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return student;
    }
}
