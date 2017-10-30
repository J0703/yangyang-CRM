package com.lanou.refer.domain;

import com.lanou.hr.domain.Staff;

import java.util.Date;

/**
 * Created by dllo on 17/10/27.
 */
public class Follow {
    private String followId; //主键 ID
    private Date followTime; //跟踪时间
    private String content; //内容
    private Staff staff; //营销人员(从 Session 中获取),当前 登录人的 ID
    private Refer refer; //学生咨询表的主键 ID

    public Follow(String followId, Date followTime, String content, Staff staff, Refer refer) {
        this.followId = followId;
        this.followTime = followTime;
        this.content = content;
        this.staff = staff;
        this.refer = refer;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "followId='" + followId + '\'' +
                ", followTime=" + followTime +
                ", content='" + content + '\'' +
                ", refer=" + refer +
                '}';
    }

    public Follow() {
    }

    public String getFollowId() {
        return followId;
    }

    public void setFollowId(String followId) {
        this.followId = followId;
    }

    public Date getFollowTime() {
        return followTime;
    }

    public void setFollowTime(Date followTime) {
        this.followTime = followTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Refer getRefer() {
        return refer;
    }

    public void setRefer(Refer refer) {
        this.refer = refer;
    }


}
