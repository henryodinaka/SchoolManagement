package sch.man.com.controller;

/**
 *
 * @author LEOGOLD
 */
public class ResultBean {
    private String resultId;
    private double test1;
    private double test2;
    private double exam;
    private String studentId;
    private String subjectId;

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
    
}
