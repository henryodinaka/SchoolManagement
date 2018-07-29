package sch.man.com.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import sch.man.com.model.Department;
import sch.man.com.model.Subjects;
import sch.man.com.service.SubjectService;

/**
 *
 * @author LEOGOLD
 */
@Named(value = "subjectBean")
@RequestScoped
public class SubjectBean {

    private String subjectId;
    private String subjectName;
    private String duration;
    private String resultId;
    private String studentId;
    private String teacherId;
    private String departmentId;
    private String report;

    @Autowired
    SubjectService subjectService;
    Subjects subject;

    public SubjectBean() {
    }

    public SubjectBean(String subjectId, String subjectName, String duration, String resultId, String studentId, String teacherId, String departmentId) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.duration = duration;
        this.resultId = resultId;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.departmentId = departmentId;
    }

    public String save() {
        Department dept = new Department();
        dept.setDepartmentId(departmentId);
        subject = new Subjects(subjectId, subjectName, duration, dept);
        subjectService.save(subject);

        return "addSubject";
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

    public String getResultId() {
        return resultId;
    }

    public void setResultId(String resultId) {
        this.resultId = resultId;
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

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

}
