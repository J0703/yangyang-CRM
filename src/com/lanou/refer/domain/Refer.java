package com.lanou.refer.domain;

import java.sql.Date;

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
    private String courseTypeId; //意向学科
    private String classId; //意向班级
    private String source; //来源
    private String status; //状态(1.咨询中、2.已报名)
    private String remark; //备注(手动填写)
    private String staffId; //营销人员(从 Session 中获取),当前 登录人的 ID
}
