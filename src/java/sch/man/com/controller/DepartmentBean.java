package sch.man.com.controller;

/**
 *
 * @author LEOGOLD
 */
public class DepartmentBean {
    
    private String departmentId;
    private String departmentName; 
    private String hod;
    private String studentId;
    private String teacherId;

    public DepartmentBean() {
    }

    public DepartmentBean(String departmentId, String departmentName, String hod, String studentId, String teacherId) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
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

    public String getHod() {
        return hod;
    }

    public void setHod(String hod) {
        this.hod = hod;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
    
}
