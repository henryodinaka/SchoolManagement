package sch.man.com.unused;


import java.util.Iterator;
import java.util.List; 
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author LEOGOLD
 */
public class RegistrationDto {
    Session session = null;
    boolean validate = true;
    
    public RegistrationDto (){
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    public String register(Studentdetail student){
        Transaction transaction = session.beginTransaction();
        String userId = student.getUserid();
        System.out.println(userId);
        Query query = session.createQuery("FROM Person");
        List <Studentdetail> studentList = query.list();
        
        for(Iterator iterate = studentList.iterator(); iterate.hasNext();){
            Studentdetail regForm = (Studentdetail)iterate.next();
            if(regForm.getUserid().equals(userId)){
                validate = false;
            }
        }
        if(validate==false){
            return "error";
        }
        else{
            session.save(studentList);
            transaction.commit();
            return "true";
        }

//    session.save(student);
//    session.getTransaction().commit();
//    
//    return "true";
   }


}
