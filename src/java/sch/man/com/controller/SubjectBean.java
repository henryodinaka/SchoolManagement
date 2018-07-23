package sch.man.com.controller;

/**
 *
 * @author LEOGOLD
 */
public class SubjectBean {
    private String subjectId;
    private String subjectName;
    private String duration;
    private String resultId;
    private String studentId;
    private String teacherId;
    private String departmentId;

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
    
}
