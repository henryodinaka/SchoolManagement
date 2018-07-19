package sch.man.com.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author LEOGOLD
 */

@Entity 
@Table(name = "Department")
class Department implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id 
    @Column(name = "department_Id", nullable = false, unique = true)
    private String departmentId;
    
    @Column (name = "department_name", nullable = false)
    private String departmentName ;
   
    @Column (name = "num_of_teacher", nullable = false)
    private int numOfTeacher ;
   
    @Column (name = "num_of_student", nullable = false)
    private int numOfStudent ;
    
    @OneToOne(fetch = FetchType.LAZY, targetEntity = Department.class)
    @JoinColumn(name = "teacher_Id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_Department_Teacher"))
    private Teacher hod;
    
    @OneToOne(mappedBy ="studentId",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            targetEntity = Student.class)
    private Student studentId ;
    
    @OneToOne(mappedBy ="teacherId",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            targetEntity = Teacher.class)
    private Teacher teacherId ;

    public Department() {
    }

    public Department(String departmentId, String departmentName, int numOfTeacher, int numOfStudent, Teacher hod, Student studentId, Teacher teacherId) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.numOfTeacher = numOfTeacher;
        this.numOfStudent = numOfStudent;
        this.hod = hod;
        this.studentId = studentId;
        this.teacherId = teacherId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getNumOfTeacher() {
        return numOfTeacher;
    }

    public void setNumOfTeacher(int numOfTeacher) {
        this.numOfTeacher = numOfTeacher;
    }

    public int getNumOfStudent() {
        return numOfStudent;
    }

    public void setNumOfStudent(int numOfStudent) {
        this.numOfStudent = numOfStudent;
    }

    public Teacher getHod() {
        return hod;
    }

    public void setHod(Teacher hod) {
        this.hod = hod;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    public Teacher getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Teacher teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "Department{" + "departmentId=" + departmentId + ", departmentName=" + departmentName + ", numOfTeacher=" + numOfTeacher + ", numOfStudent=" + numOfStudent + ", hod=" + hod + ", studentId=" + studentId + ", teacherId=" + teacherId + '}';
    }
    
    
}
