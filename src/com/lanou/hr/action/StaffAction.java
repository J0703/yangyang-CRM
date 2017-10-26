package com.lanou.hr.action;

import com.lanou.hr.domain.Department;
import com.lanou.hr.domain.Post;
import com.lanou.hr.domain.Staff;
import com.lanou.hr.service.DepartmentService;
import com.lanou.hr.service.PostService;
import com.lanou.hr.service.StaffService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by dllo on 17/10/25.
 */
@Controller("staffAction")
public class StaffAction extends ActionSupport {
    @Autowired
    @Qualifier("staffService")
    private StaffService staffService;
    @Autowired
    @Qualifier("postService")
    private PostService postService;


    private List<Staff> staffs;

    private String depId;
    private String postId;
    private String staffName;
    private String staffId;
    private String gender;
    private String loginName;
    private String loginPwd;
    private Date onDutyDate;
    private Staff staff;

    /**
     * 获取员工集合
     *
     * @return
     */
    public String findStaff() {
        String hql = "from Staff";
        staffs = staffService.findAll(hql);
        return SUCCESS;
    }

    /**
     * 高级查询
     *
     * @return
     */
    public String find() {
        if (postId.equals("-1") && depId.equals("-1")) {
            findStaff();
            return SUCCESS;
        }
        if (postId.equals("-1")) {
            Map<String, Object> params = new HashMap<>();
            params.put("depId", depId);
            List<Post> posts = postService.find("from Post where depId like :depId", params);
            staffs = new ArrayList<>();
            for (Post post : posts) {
                String postId = post.getPostId();
                params.remove("depId");
                params.put("postId", postId);
                List<Staff> staffList = staffService.find(params);
                staffs.addAll(staffList);
            }
        } else {
            Map<String, Object> params = new HashMap<>();
            params.put("postId", postId);
            if (staffName.trim().length() > 0) {
                params.put("staffName", staffName);
            }
            staffs = staffService.find(params);
        }
        return SUCCESS;
    }

    /**
     * 表单回显
     *
     * @return
     */
    public String findSingle() {
        staff = staffService.get(Staff.class, staffId);
        return SUCCESS;
    }

    /**
     * 更新staff
     *
     * @return
     */
    public String update() {
        Date date = onDutyDate;
        Staff staff = new Staff(staffId, loginName, loginPwd, staffName, gender, date);
        if (!postId.equals("-1")) {
            Post post = postService.get(Post.class, postId);
            staff.setPost(post);
        }
        staffService.update(staff);
        System.out.println();
        return SUCCESS;
    }

    public String save() {
        Date date = onDutyDate;
        Post post = postService.get(Post.class, postId);
        Staff staff = new Staff(loginName, loginPwd, staffName, gender, date);
        staff.setPost(post);
        staffService.save(staff);
        return SUCCESS;
    }

    public void validateUpdate() {
        if (StringUtils.isBlank(loginName)) {
            addActionError("登录名不能为空");
        }
        if (StringUtils.isBlank(loginPwd)) {
            addActionError("登录密码不能为空");
        }
        if (StringUtils.isBlank(staffName)) {
            addActionError("员工姓名不能为空");
        }
        if (StringUtils.isBlank(gender)) {
            addActionError("员工性别不能为空");
        }
    }


    public void validateSave() {
        if (StringUtils.isBlank(loginName)) {
            addActionError("登录名不能为空");
        }
        if (StringUtils.isBlank(loginPwd)) {
            addActionError("登录密码不能为空");
        }
        if (StringUtils.isBlank(staffName)) {
            addActionError("员工姓名不能为空");
        }
        if (StringUtils.isBlank(gender)) {
            addActionError("员工性别不能为空");
        }


    }

    public List<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<Staff> staffs) {
        this.staffs = staffs;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public Date getOnDutyDate() {
        return onDutyDate;
    }

    public void setOnDutyDate(Date onDutyDate) {
        this.onDutyDate = onDutyDate;
    }
}
