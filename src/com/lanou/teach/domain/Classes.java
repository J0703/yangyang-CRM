package com.lanou.teach.domain;

import java.sql.Date;

/**
 * Created by dllo on 17/10/27.
 */
public class Classes {
    private String classId; //主键 ID
    private Course_Type course_type; //课程
    private String name; //班级名称
    private Date beginTime; //开班时间
    private Date endTime; //毕业时间
    private String status; //状态(未开课/已开课/已结束) 数据库不需要保存,页面通过计算获 得,此字段可以删除
    private int totalCount; //学生总数
    private int upgradeCount; //升级数
    private int changeCount; //转班数(转来)
    private int runoffCount; //退费数(流失)
    private String remark; //其他说明
    private String uploadPath; //课表路径
    private String uploadFileName; //课表名称
    private Date uploadTime; //上传时间

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public Course_Type getCourse_type() {
        return course_type;
    }

    public void setCourse_type(Course_Type course_type) {
        this.course_type = course_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getUpgradeCount() {
        return upgradeCount;
    }

    public void setUpgradeCount(int upgradeCount) {
        this.upgradeCount = upgradeCount;
    }

    public int getChangeCount() {
        return changeCount;
    }

    public void setChangeCount(int changeCount) {
        this.changeCount = changeCount;
    }

    public int getRunoffCount() {
        return runoffCount;
    }

    public void setRunoffCount(int runoffCount) {
        this.runoffCount = runoffCount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
}
