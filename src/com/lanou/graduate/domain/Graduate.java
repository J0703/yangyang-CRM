package com.lanou.graduate.domain;

import com.lanou.career_plan.domain.Student;
import com.lanou.teach.domain.Classes;

import java.sql.Date;

/**
 * Created by dllo on 17/10/27.
 */
public class Graduate {

    private String graduateId; // 学生就业ID
    private Student student; //学员
    private String companyName; //就业公司名称
    private String salary; //薪资
    private String post; //岗位
    private Date entryTime; //入职时间
    private String remark; //备注
    private Classes classes; //班级

    public Graduate(String graduateId, Student student, String companyName, String salary, String post, Date entryTime, String remark, Classes classes) {
        this.graduateId = graduateId;
        this.student = student;
        this.companyName = companyName;
        this.salary = salary;
        this.post = post;
        this.entryTime = entryTime;
        this.remark = remark;
        this.classes = classes;
    }

    public Graduate() {
    }

    public String getGraduateId() {
        return graduateId;
    }

    public void setGraduateId(String graduateId) {
        this.graduateId = graduateId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }
}
