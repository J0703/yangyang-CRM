package com.lanou.career_plan.domain;

import com.lanou.hr.domain.Staff;
import com.lanou.teach.domain.Classes;

import java.util.Date;

/**
 * Created by dllo on 17/10/30.
 */
public class Station {
    private String stationId;
    private Student student;
    private String flag;
    private Staff staff;
    private Date createDate;
    private Classes beforeClass;
    private Classes afterClass;

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Classes getBeforeClass() {
        return beforeClass;
    }

    public void setBeforeClass(Classes beforeClass) {
        this.beforeClass = beforeClass;
    }

    public Classes getAfterClass() {
        return afterClass;
    }

    public void setAfterClass(Classes afterClass) {
        this.afterClass = afterClass;
    }
}
