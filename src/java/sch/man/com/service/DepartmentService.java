package sch.man.com.service;

import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sch.man.com.controller.DepartmentBean;
import sch.man.com.model.Department;

/**
 *
 * @author LEOGOLD
 */
@Service
@Transactional
public class DepartmentService {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    DepartmentBean departmentBean;

    private Session session;
    HttpSession httpSession;
    private String hql;
    private Query query;

    public String save(Department dep) {
        session = sessionFactory.getCurrentSession();
        session.save(dep);
        departmentBean.setReport("New Subject added");
        return "success";
    }

}
