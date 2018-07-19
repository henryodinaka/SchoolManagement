package sch.man.com.DTO;
//08095267180 08181326516


import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import sch.man.com.bean.Person;
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
    

    @Autowired
    private SessionFactory sessionFactory;
    private Session session; 
    private Query query;
    public PersonDto() {
        
    }
    
    public void save(Person person){
       session = sessionFactory.getCurrentSession();
       session.save(person);
        
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
    public List <Person> getPerson(){
        
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
}
