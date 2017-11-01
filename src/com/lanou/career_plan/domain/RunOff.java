package com.lanou.career_plan.domain;

import com.lanou.hr.domain.Staff;

import java.util.Date;

/**
 * Created by dllo on 17/10/30.
 */
public class RunOff {
    private String runOffId;
    private Student student;
    private Staff staff;
    private Date createDate;
    private String isRefund;
    private String refundAmount;
    private String remark;

    public String getRunOffId() {
        return runOffId;
    }

    public void setRunOffId(String runOffId) {
        this.runOffId = runOffId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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

    public String getIsRefund() {
        return isRefund;
    }

    public void setIsRefund(String isRefund) {
        this.isRefund = isRefund;
    }

    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
