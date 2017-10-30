package com.lanou.refer.domain;

import com.lanou.hr.domain.Staff;
import com.lanou.teach.domain.Classes;
import com.lanou.teach.domain.CourseType;

import java.util.Date;

/**
 * Created by dllo on 17/10/27.
 */
public class Refer {
    private String referId; //主键 ID
    private String name; //姓名
    private String telephone; //电话
    private String qq; //QQ(手动填写)
    private Date createDate; //建档日期,新增时系统时间,编辑时不能编辑
    private String intentionLevel; //意向级别

    private CourseType courseType; //意向学科
    private Classes classes; //意向班级
    private String source; //来源

    private String status; //状态(1.咨询中、2.已报名)
    private String remark; //备注(手动填写)
    private Staff staff; //营销人员(从 Session 中获取),当前 登录人的 ID

    public Refer(String referId, String name, String telephone, String qq, Date createDate, String intentionLevel, CourseType courseType, Classes classes, String source, String status, String remark, Staff staff) {
        this.referId = referId;
        this.name = name;
        this.telephone = telephone;
        this.qq = qq;
        this.createDate = createDate;
        this.intentionLevel = intentionLevel;
        this.courseType = courseType;
        this.classes = classes;
        this.source = source;
        this.status = status;
        this.remark = remark;
        this.staff = staff;
    }

    public Refer() {
    }

    @Override
    public String toString() {
        return "Refer{" +
                "referId='" + referId + '\'' +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", qq='" + qq + '\'' +
                ", createDate=" + createDate +
                ", intentionLevel='" + intentionLevel + '\'' +
                ", source='" + source + '\'' +
                ", status='" + status + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public String getReferId() {
        return referId;
    }

    public void setReferId(String referId) {
        this.referId = referId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getIntentionLevel() {
        return intentionLevel;
    }

    public void setIntentionLevel(String intentionLevel) {
        this.intentionLevel = intentionLevel;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}
