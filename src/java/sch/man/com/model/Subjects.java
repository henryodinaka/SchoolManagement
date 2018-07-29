/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sch.man.com.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author LEOGOLD
 */
@Entity 
@Table(name = "subject")
public class Subjects implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id 
    @Column(name = "subject_Id", nullable = false, unique = true)
    private String subjectId;
    
    @Column (name = "subject_name", nullable = false)
    private String subjectName ;
    
    @Column (name = "duration", nullable = false)
    private String duration ;    
    
    @OneToMany(mappedBy ="subjectId",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            targetEntity = Result.class)
    private List<Result> resultId ;
    
    @ManyToMany(mappedBy ="subjectId",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            targetEntity = Student.class)
    private List<Student> studentId ;
    
    
    @ManyToMany(mappedBy ="subjectId",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            targetEntity = Teacher.class)
    private List<Teacher> teacherId ;
    
    @ManyToOne (fetch = FetchType.LAZY,targetEntity = Department.class)
    @JoinColumn(name = "department_Id", nullable=false,foreignKey = @ForeignKey(name="FK_Subject_Department"))
    private Department departmentId;
    
    @CreationTimestamp
    @Temporal (TemporalType.TIMESTAMP)
    @Column (name = "created")
    private Date created;

    @CreationTimestamp
    @Temporal (TemporalType.TIMESTAMP)
    @Column (name = "updated")
    private Date updated;

    public Subjects() {
    }

    public Subjects(String subjectId, String subjectName, String duration, List<Result> resultId, List<Student> studentId, List<Teacher> teacherId, Department departmentId, Date created, Date updated) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.duration = duration;
        this.resultId = resultId;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.departmentId = departmentId;
        this.created = created;
        this.updated = updated;
    }

    public Subjects(String subjectId, String subjectName, String duration, Department departmentId) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.duration = duration;
        this.departmentId = departmentId;
    }
    

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<Result> getResultId() {
        return resultId;
    }

    public void setResultId(List<Result> resultId) {
        this.resultId = resultId;
    }

    public List<Student> getStudentId() {
        return studentId;
    }

    public void setStudentId(List<Student> studentId) {
        this.studentId = studentId;
    }

    public List<Teacher> getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(List<Teacher> teacherId) {
        this.teacherId = teacherId;
    }

    public Department getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Department departmentId) {
        this.departmentId = departmentId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

}
