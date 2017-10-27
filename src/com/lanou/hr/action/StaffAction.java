package com.lanou.hr.action;

import com.lanou.hr.domain.Department;
import com.lanou.hr.domain.Post;
import com.lanou.hr.domain.Staff;
import com.lanou.hr.service.DepartmentService;
import com.lanou.hr.service.PostService;
import com.lanou.hr.service.StaffService;
import com.lanou.hr.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
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
    @Autowired
    @Qualifier("departmentService")
    private DepartmentService departmentService;

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
    private List<Department> departments;
    private List<Post> posts;

    private int pageNum;
    private int pageSize = 3;

    /**
     * 获得department集合
     */

    public String findByPage() {
        if (pageNum == 0) {
            pageNum = 1;
        }
        String hql = "select count(*) from Staff";
        String hql1 = "from Staff where 1=1";
        PageBean<Staff> pageBean = staffService.findByPage(hql, hql1, pageNum, pageSize);
        ActionContext.getContext().put("pageBean", pageBean);
        return SUCCESS;
    }

    /**
     * 获取员工集合
     */
    public String findStaff() {
        String hql = "from Staff";
        staffs = staffService.findAll(hql);
        return SUCCESS;
    }

    /**
     * 高级查询
     */
    public String find() {
        List<Object> params = new ArrayList<>();
        params.add(staffName);
        params.add(depId);
        params.add(postId);
        if (pageNum == 0) {
            pageNum = 1;
        }
        String hql = "select count(*) from Staff where 1=1";
        String hql1 = "from Staff where 1=1";
        PageBean<Staff> pageBean = staffService.findByCD(hql, hql1, params, pageNum, pageSize);
        ActionContext.getContext().put("pageBean", pageBean);
        return SUCCESS;
    }


    /**
     * 表单回显
     */
    public String findSingle() {
        staff = staffService.get(Staff.class, staffId);
        departments = departmentService.findAll("from Department");
        String hql = "from Post where depId=:depId";
        Map<String, Object> params = new HashMap<>();
        params.put("depId", staff.getDepartment().getDepId());
        posts = postService.find(hql, params);
        return SUCCESS;
    }

    /**
     * 更新staff
     */
    public String update() {
        Date date = onDutyDate;
        Staff staff = new Staff(staffId, loginName, loginPwd, staffName, gender, date);
        if (!depId.equals("-1")){
            Department department = departmentService.get(Department.class, depId);
            staff.setDepartment(department);
        }
        if (!postId.equals("-1")) {
            staff.setDepartment(null);
            Post post = postService.get(Post.class, postId);
            staff.setPost(post);
            staff.setDepartment(post.getDepartment());
        }
        staffService.update(staff);
        return SUCCESS;
    }

    /**
     * 添加staff
     */
    public String save() {
        Date date = onDutyDate;
        Post post = postService.get(Post.class, postId);
        Staff staff = new Staff(loginName, loginPwd, staffName, gender, date);
        staff.setPost(post);
        staff.setDepartment(post.getDepartment());
        staffService.save(staff);
        return SUCCESS;
    }

    /**
     * 修改员工表单校验
     */
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

    /**
     * 添加员工表单校验
     */
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

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
