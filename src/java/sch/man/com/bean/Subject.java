/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sch.man.com.bean;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Juliet
 */
@Named(value = "subject")
@Dependent
public class Subject {

    private String subjectName;
    private String teacher;
    private String duration;
    
    public Subject() {
    }

    public Subject(String subjectName, String teacher, String duration) {
        this.subjectName = subjectName;
        this.teacher = teacher;
        this.duration = duration;
    }
    


    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
