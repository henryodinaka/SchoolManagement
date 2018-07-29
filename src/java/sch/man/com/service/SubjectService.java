/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sch.man.com.service;

import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sch.man.com.controller.SubjectBean;
import sch.man.com.model.Subjects;

/**
 *
 * @author LEOGOLD
 */
@Service
@Transactional
public class SubjectService {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    SubjectBean subjectBean;

    private Session session;
    HttpSession httpSession;
    private String hql;
    private Query query;

    public String save(Subjects sub) {
        session = sessionFactory.getCurrentSession();
        session.save(sub);
        
        subjectBean.setReport("New Subject added");
        return "success";

    }
}
