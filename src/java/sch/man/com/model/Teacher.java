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
@Table(name = "teacher")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "teacher_Id", nullable = false, unique = true)
    private String teacherId; 
    @Column(name = "special_function", nullable = false)
    private String specialFunction;

    @Column(name = "level", nullable = false)
    private String level;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Person.class)
    @JoinColumn(name = "person_Id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_Teacher_Person"))
    private Person personId;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Department.class)
    @JoinColumn(name = "department_Id", 
            nullable = false, 
            foreignKey = @ForeignKey(name = "FK_Teacher_Department"))
    private Department departmentId;
    
    @OneToOne(mappedBy ="hod",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            targetEntity = Department.class)
    private Department position ;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Student.class)
    @JoinColumn(name = "subject_Id", 
            nullable = false, 
            foreignKey = @ForeignKey(name = "FK_Teacher_Subject"))
    private List<Subjects> subjectId;

    public Teacher() {
    }

    public Teacher(String teacherId, Department position, String specialFunction, String level, Person personId, Department departmentId, List<Subjects> subjectId) {
        this.teacherId = teacherId;
        this.position = position;
        this.specialFunction = specialFunction;
        this.level = level;
        this.personId = personId;
        this.departmentId = departmentId;
        this.subjectId = subjectId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Department getPosition() {
        return position;
    }

    public void setPosition(Department position) {
        this.position = position;
    }

    public String getSpecialFunction() {
        return specialFunction;
    }

    public void setSpecialFunction(String specialFunction) {
        this.specialFunction = specialFunction;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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

    public List<Subjects> getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(List<Subjects> subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public String toString() {
        return "Teacher{" + "teacherId=" + teacherId + ", position=" + position + ", specialFunction=" + specialFunction + ", level=" + level + ", personId=" + personId + ", departmentId=" + departmentId + ", subjectId=" + subjectId + '}';
    }
}
