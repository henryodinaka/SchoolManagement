/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sch.man.com.bean;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Juliet
 */
@Named(value = "department")
@Dependent
public class Result {

   private String subjectId;
   private String studentId;
   private String testScore;
   private String examScore;
   private String continuosAssessment;
   
    public Result() {
    }

    public Result(String subjectId, String studentId, String testScore, String examScore, String continuosAssessment) {
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.testScore = testScore;
        this.examScore = examScore;
        this.continuosAssessment = continuosAssessment;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getTestScore() {
        return testScore;
    }

    public void setTestScore(String testScore) {
        this.testScore = testScore;
    }

    public String getExamScore() {
        return examScore;
    }

    public void setExamScore(String examScore) {
        this.examScore = examScore;
    }

    public String getContinuosAssessment() {
        return continuosAssessment;
    }

    public void setContinuosAssessment(String continuosAssessment) {
        this.continuosAssessment = continuosAssessment;
    }
    
    public String submitResult(){
        return "success";
    }
}
