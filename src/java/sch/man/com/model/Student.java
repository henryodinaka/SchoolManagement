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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author LEOGOLD
 */
@Entity
@Table(name = "student")
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
   
   
    @Column (name = "parent_name", nullable = false) 
    private String parentName;
    
    
    @Column (name = "parent_phone", nullable = false)
    private String parentPhone;
    
    @Column (name = "parent_address", nullable = false)
    private String parentAddress;
    
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Subjects.class)
    @JoinColumn(name = "subject_Id", 
            nullable = false, 
            foreignKey = @ForeignKey(name = "FK_Student_Subject"))
    private List<Subjects> subjectId;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Person.class)
    @JoinColumn(name = "person_Id", 
            nullable = false, 
            foreignKey = @ForeignKey(name = "FK_Student_Person"))
    private Person personId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Department.class)
    @JoinColumn(name = "department_Id", 
            nullable = false, 
            foreignKey = @ForeignKey(name = "FK_Student_Department"))
    private Department departmentId;

    @OneToMany(mappedBy = "studentId",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            targetEntity = Result.class)
    private List<Result> resultId;

    public Student() {
    }

    public Student(String studentId, String currentClass, String classPosition, String remark, String parentName, String parentPhone, String parentAddress, List<Subjects> subjectId, Person personId, Department departmentId, List<Result> resultId) {
        this.studentId = studentId;
        this.currentClass = currentClass;
        this.classPosition = classPosition;
        this.remark = remark;
        this.parentName = parentName;
        this.parentPhone = parentPhone;
        this.parentAddress = parentAddress;
        this.subjectId = subjectId;
        this.personId = personId;
        this.departmentId = departmentId;
        this.resultId = resultId;
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
        return resultId;
    }

    public void setResultId(List<Result> resultId) {
        this.resultId = resultId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }

    public String getParentAddress() {
        return parentAddress;
    }

    public void setParentAddress(String parentAddress) {
        this.parentAddress = parentAddress;
    }

    @Override
    public String toString() {
        return "Student{" + "studentId=" + studentId + ", currentClass=" + currentClass + ", classPosition=" + classPosition + ", remark=" + remark + ", parentName=" + parentName + ", parentPhone=" + parentPhone + ", parentAddress=" + parentAddress + ", subjectId=" + subjectId + ", personId=" + personId + ", departmentId=" + departmentId + ", resultId=" + resultId + '}';
    }

}
