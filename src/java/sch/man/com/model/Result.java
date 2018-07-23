package sch.man.com.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author LEOGOLD
 */

@Entity 
@Table(name = "result",uniqueConstraints ={@UniqueConstraint(columnNames ={"student_Id", "subject_Id"})})
class Result implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id 
    @Column(name = "result_Id", nullable = false, unique = true)
    private String resultId;
    
    @Column (name = "CA", nullable = false)
    private double test1 ;
    
    @Column (name = "test2", nullable = false)
    private double test2 ;
    
    @Column (name = "exam", nullable = false)
    private double exam ;
    
    @ManyToOne (fetch = FetchType.LAZY,targetEntity = Student.class)
    @JoinColumn(name = "student_Id", nullable=false,foreignKey = @ForeignKey(name="FK_Result_Student"))
    private Student studentId;
      
    @ManyToOne (targetEntity = Subjects.class) 
    @JoinColumn(name = "subject_Id",
            foreignKey = @ForeignKey(name="FK_Result_Subject"), nullable=false) 
    private Subjects subjectId;

    public Result() {
    }

    public Result(String resultId, double test1, double test2, double exam, Student studentId, Subjects subjectId) {
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

    public double getExam() {
        return exam;
    }

    public void setExam(double exam) {
        this.exam = exam;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    public Subjects getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subjects subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public String toString() {
        return "Result{" + "resultId=" + resultId + ", test1=" + test1 + ", test2=" + test2 + ", exam=" + exam + ", studentId=" + studentId + ", subjectId=" + subjectId + '}';
    } 
    
}
