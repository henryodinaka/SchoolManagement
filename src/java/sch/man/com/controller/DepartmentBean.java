package sch.man.com.controller;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import sch.man.com.model.Department;
import sch.man.com.model.Teacher;
import sch.man.com.service.DepartmentService;

/**
 *
 * @author LEOGOLD
 */
@Named(value = "departmentBean")
@RequestScoped
public class DepartmentBean {
    
    private String departmentId;
    private String departmentName; 
    private String hod;
    private int numOfteacher;
    private int numOfStudent;

    @Autowired
    private DepartmentService depService;
    private Department department;
    private List<Department> departmentList;
    
    private String report;
    
    public DepartmentBean() {
    }

    public DepartmentBean(String departmentId, String departmentName, String hod, int numOfteacher, int numOfStudent) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.hod = hod;
        this.numOfteacher = numOfteacher;
        this.numOfStudent = numOfStudent;
    }

    public String save(){
        int numTeacher = Integer.valueOf(numOfteacher);
        int numStudent = Integer.valueOf(numOfStudent);
        Teacher teacherId = new Teacher();
        teacherId.setTeacherId(hod);
        department = new Department(departmentId, departmentName, numTeacher, numStudent, teacherId);
        depService.save(department);
        return "addDepartment";
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

    public int getNumOfteacher() {
        return numOfteacher;
    }

    public void setNumOfteacher(int numOfteacher) {
        this.numOfteacher = numOfteacher;
    }

    public int getNumOfStudent() {
        return numOfStudent;
    }

    public void setNumOfStudent(int numOfStudent) {
        this.numOfStudent = numOfStudent;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    
}
