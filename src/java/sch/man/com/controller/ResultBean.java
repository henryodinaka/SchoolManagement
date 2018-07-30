package sch.man.com.controller;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import sch.man.com.model.Result;
import sch.man.com.service.ResultService;

/**
 *
 * @author LEOGOLD
 */
@Named(value = "resultBean")
@RequestScoped
public class ResultBean {

    private String resultId;
    private double test1;
    private double test2;
    private double exam;
    private String studentId;
    private String subjectId;

    @Autowired
    private ResultService resultService;
    List<Result> studentResultList;

    public ResultBean() {
    }

    public ResultBean(String resultId, double test1, double test2, double exam, String studentId, String subjectId) {
        this.resultId = resultId;
        this.test1 = test1;
        this.test2 = test2;
        this.exam = exam;
        this.studentId = studentId;
        this.subjectId = subjectId;
    }

    public String addResult() {
        if (!resultId.isEmpty() || test1 != 0 || test2 != 0 || exam != 0 || !studentId.isEmpty() || subjectId.isEmpty()) {
            ResultBean result = new ResultBean(resultId, test1, test2, exam, studentId, subjectId);
            resultService.save(result);
            return "successPage";
        } else {
            return "resultPage";
        }
    }

    public String sngleResult() {
        Result result = resultService.getSingleResult(resultId);
        setBean(result);
        return "viewResult?faces-redirect=true";
    }

    public String studentResult() {
        studentResultList = resultService.getStudentResults();
        
        return "resultList?faces-redirect=true";
    }

    public void setBean(Result result) {
        this.setResultId(result.getResultId());
        this.setStudentId(result.getStudentId().getPersonId());
        this.setSubjectId(result.getSubjectId().getSubjectId());
        this.setTest1(result.getTest1());
        this.setTest2(result.getTest2());
        this.setExam(result.getExam());
    }

    public String getResultId() {
        return resultId;
    }

    public void setResultId(String resultId) {
        this.resultId = resultId;
    }

    public double getTest1() {
        return test1;
    }

    public void setTest1(double test1) {
        this.test1 = test1;
    }

    public double getTest2() {
        return test2;
    }

    public void setTest2(double test2) {
        this.test2 = test2;
    }

    public double getExam() {
        return exam;
    }

    public void setExam(double exam) {
        this.exam = exam;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public List<Result> getStudentResultList() {
        return studentResultList;
    }

    public void setStudentResultList(List<Result> studentResultList) {
        this.studentResultList = studentResultList;
    }

}
