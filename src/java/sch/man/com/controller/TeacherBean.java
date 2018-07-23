package sch.man.com.controller;

/**
 *
 * @author LEOGOLD
 */
public class TeacherBean {
   private String teacherId;
   private String position;
   private String specialFunction;
   private String level;
   private String personId;
   private String departmentId;
   private String subjectId;

    public TeacherBean() {
    }

    public TeacherBean(String teacherId, String position, String specialFunction, String level, String personId, String departmentId, String subjectId) {
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
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

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }
   
}
