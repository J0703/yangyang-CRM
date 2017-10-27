package com.lanou.refer.domain;

import java.sql.Date;

/**
 * Created by dllo on 17/10/27.
 */
public class Follow {
    private String followId; //主键 ID
    private Date followTime; //跟踪时间
    private String content; //内容
    private String StaffId; //营销人员(从 Session 中获取),当前 登录人的 ID
    private String referId; //学生咨询表的主键 ID
}
