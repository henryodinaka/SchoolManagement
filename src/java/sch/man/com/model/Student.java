/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sch.man.com.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author LEOGOLD
 */
@Entity
@Table(name = "Subject")
class Student implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id 
    @Column(name = "student_Id", nullable = false, unique = true)
    private String studentId;
    
    @Column (name = "curent_Class", nullable = false)
    private String currentClass;
    
    @Column (name = "class_Position", nullable = false)
    private String classPosition;
   
    @Column (name = "remark", nullable = false)
    private String remark;
   
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Student.class)
    @JoinColumn(name = "subject_Id", 
            nullable = false, 
            foreignKey = @ForeignKey(name = "FK_Student_Subject"))
    private List<Subjects> subjectId;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Department.class)
    @JoinColumn(name = "person_Id", 
            nullable = false, 
            foreignKey = @ForeignKey(name = "FK_Student_Person"))
    private Person personId;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Department.class)
    @JoinColumn(name = "department_Id", 
            nullable = false, 
            foreignKey = @ForeignKey(name = "FK_Student_Department"))
    private Department departmentId;

    @OneToMany(mappedBy = "resultId",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            targetEntity = Result.class)
    private List<Result> ResultId;

    public Student() {
    }

    public Student(String studentId, String currentClass, String classPosition, String remark, List<Subjects> subjectId, Person personId, Department departmentId, List<Result> ResultId) {
        this.studentId = studentId;
        this.currentClass = currentClass;
        this.classPosition = classPosition;
        this.remark = remark;
        this.subjectId = subjectId;
        this.personId = personId;
        this.departmentId = departmentId;
        this.ResultId = ResultId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCurrentClass() {
        return currentClass;
    }

    public void setCurrentClass(String currentClass) {
        this.currentClass = currentClass;
    }

    public String getClassPosition() {
        return classPosition;
    }

    public void setClassPosition(String classPosition) {
        this.classPosition = classPosition;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Subjects> getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(List<Subjects> subjectId) {
        this.subjectId = subjectId;
    }

    public Person getPersonId() {
        return personId;
    }

    public void setPersonId(Person personId) {
        this.personId = personId;
    }

    public Department getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Department departmentId) {
        this.departmentId = departmentId;
    }

    public List<Result> getResultId() {
        return ResultId;
    }

    public void setResultId(List<Result> ResultId) {
        this.ResultId = ResultId;
    }

    @Override
    public String toString() {
        return "Student{" + "studentId=" + studentId + ", currentClass=" + currentClass + ", classPosition=" + classPosition + ", remark=" + remark + ", subjectId=" + subjectId + ", personId=" + personId + ", departmentId=" + departmentId + ", ResultId=" + ResultId + '}';
    }

}
