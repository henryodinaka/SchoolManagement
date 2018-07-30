package sch.man.com.service;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sch.man.com.controller.ResultBean;
import sch.man.com.model.Person;
import sch.man.com.model.Result;
import sch.man.com.model.Subjects;

/**
 *
 * @author LEOGOLD
 */
@Service
@Transactional
public class ResultService {

    private Session session;

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private PersonService personService;
    HttpSession httpSession;
    private String hql;
    private Query query;
    Person person;

    @Autowired
    ResultBean resultBean;

    Result result = null;
    List<Result> resultList = null;

    public String save(ResultBean studentResult) {
        session = sessionFactory.getCurrentSession();

        Result result = new Result();

        result.setResultId(studentResult.getResultId());
        result.setTest1(studentResult.getTest1());
        result.setTest2(studentResult.getTest2());
        result.setExam(studentResult.getExam());
        result.setStudentId(personService.getPerson1(studentResult.getStudentId()));
        Subjects subject = new Subjects();
        result.setSubjectId(subject);

        session.save(result);
        resultBean.setReport("Result Was Added Successfully");
        return "success";
    }

    public List<Result> getStudentResults() {
        List<Result> studentResult = null;
        person = (Person) httpSession.getAttribute("loggedPerson");
        String personId = person.getPersonId();
        try {
            session = sessionFactory.getCurrentSession();

            studentResult = session.createQuery("FROM Result "
                    + "WHERE studentId = :student")
                    .setParameter("student", personId)
                    .list();
        } catch (EmptyResultDataAccessException ex) {
            ex.printStackTrace();
        }
        return studentResult;
    }

    public List<Result> getAllResult(String resultId) {

        try {
            session = sessionFactory.getCurrentSession();

            query = session.createQuery("FROM Result");
            resultList = query.list();
        } catch (EmptyResultDataAccessException ex) {
            ex.printStackTrace();
        }
        return resultList;
    }

    public Result getSingleResult(String resultId) {
        try {
            session = sessionFactory.getCurrentSession();

            result = (Result) session.createQuery("FROM Result "
                    + "WHERE result_Id = :result")
                    .setParameter("result", resultId)
                    .uniqueResult();
        } catch (EmptyResultDataAccessException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
