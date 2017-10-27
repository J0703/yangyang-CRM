package com.lanou.career_plan.domain;

/**
 * Created by dllo on 17/10/27.
 */
public class Student {
    private String studentId; //学员主键ID
    private String name; //姓名(从咨询表中带过来)
    private String telephone; //电话(从咨询表中带过来)
    private String identity; //身份证号
    private String qq; //QQ(从咨询表中带过来)
    private String gender; //性别
    private int mustTuition; //应付学费
    private int actualTuition;//实付学费
    private String email; //Email
    private String school; //毕业学校
    private String education;//学历
    private String professional;//专业
    private String identityAddress; //身份证地址
    private String address;//在京地址
    private String remark;//学员描述
    private String homeTelephone;//家庭联系电话
    private String homeContact;//家庭联系人
    private String status;//状态(1.新生、2.转班、3.升级、4.退费、5. 毕业)
    private String classId; //班级表的外键classId(一个学生对应 一个班级,一个班级对应多个学生)
    private String referId; //学员咨询ID(正式学习的学员,与学 生咨询表对应)
}
