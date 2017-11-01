package com.lanou.hr.action;

import com.lanou.hr.domain.Department;
import com.lanou.hr.domain.Post;
import com.lanou.hr.domain.Staff;
import com.lanou.hr.service.DepartmentService;
import com.lanou.hr.service.PostService;
import com.lanou.hr.service.StaffService;
import com.lanou.base_utils.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by dllo on 17/10/25.
 */
@Controller("staffAction")
@Scope("prototype")
public class StaffAction extends ActionSupport implements ModelDriven<Staff> {
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
    private Staff staff;
    private Staff staffDriven;
    private List<Department> departments;
    private List<Post> posts;

    private int pageNum;
    private int pageSize = 3;

    /**
     * 获得staff集合
     */

    public String findByPage() {
        if (pageNum == 0) {
            pageNum = 1;
        }
        PageBean<Staff> pageBean = staffService.findByPage(pageNum, pageSize);
        ActionContext.getContext().put("pageBean", pageBean);
        return SUCCESS;
    }

    /**
     * 高级查询
     */
    public String find() {
        List<Object> params = new ArrayList<>();
        params.add(staffDriven.getStaffName());
        params.add(depId);
        params.add(postId);
        if (pageNum == 0) {
            pageNum = 1;
        }
        PageBean<Staff> pageBean = staffService.findByCD(params, pageNum, pageSize);
        ActionContext.getContext().put("pageBean", pageBean);
        return SUCCESS;
    }

    /**
     * 表单回显
     */
    public String findSingle() {
        staff = staffService.get(Staff.class, staffDriven.getStaffId());
        departments = departmentService.findAll();
        Map<String, Object> params = new HashMap<>();
        params.put("depId", staff.getDepartment().getDepId());
        posts = postService.find(params);
        return SUCCESS;
    }

    /**
     * 更新staff
     */
    public String update() {
        Date date = staffDriven.getOnDutyDate();
        Staff staff1 = new Staff(staffDriven.getStaffId(), staffDriven.getLoginName(),
                staffDriven.getLoginPwd(), staffDriven.getStaffName(), staffDriven.getGender(), date);
        if (!depId.equals("-1")) {
            Department department = departmentService.get(Department.class, depId);
            staff1.setDepartment(department);
        }
        if (!postId.equals("-1")) {
            staff1.setDepartment(null);
            Post post = postService.get(Post.class, postId);
            staff1.setPost(post);
            staff1.setDepartment(post.getDepartment());
        }
        staffService.update(staff1);
        return SUCCESS;
    }

    /**
     * 添加staff
     */
    public String save() {
        Date date = staffDriven.getOnDutyDate();
        Post post = postService.get(Post.class, postId);
        String loginPwd1 = EncoderByMd5(staffDriven.getLoginPwd());
        Staff staff1 = new Staff(staffDriven.getLoginName(), loginPwd1,
                staffDriven.getStaffName(), staffDriven.getGender(), date);
        staff1.setPost(post);
        staff1.setDepartment(post.getDepartment());

        staffService.save(staff1);
        return SUCCESS;
    }

    /**
     * 加密
     *
     * @param str 要加密的参数
     * @return 加密后的参数
     */
    public String EncoderByMd5(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BASE64Encoder base64Encoder = new BASE64Encoder();

        String newStr = null;
        try {
            newStr = base64Encoder.encode(md5.digest(str.getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return newStr;
    }

    /**
     * 修改员工表单校验
     */
    public void validateUpdate() {
        if (StringUtils.isBlank(staffDriven.getLoginName())) {
            addActionError("登录名不能为空");
        }
        if (StringUtils.isBlank(staffDriven.getLoginPwd())) {
            addActionError("登录密码不能为空");
        }
        if (StringUtils.isBlank(staffDriven.getStaffName())) {
            addActionError("员工姓名不能为空");
        }
        if (StringUtils.isBlank(staffDriven.getGender())) {
            addActionError("员工性别不能为空");
        }
    }

    /**
     * 添加员工表单校验
     */
    public void validateSave() {
        if (StringUtils.isBlank(staffDriven.getLoginName())) {
            addActionError("登录名不能为空");
        }
        if (StringUtils.isBlank(staffDriven.getLoginPwd())) {
            addActionError("登录密码不能为空");
        }
        if (StringUtils.isBlank(staffDriven.getStaffName())) {
            addActionError("员工姓名不能为空");
        }
        if (StringUtils.isBlank(staffDriven.getGender())) {
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


    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
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

    @Override
    public Staff getModel() {
        staffDriven = new Staff();
        return staffDriven;
    }
}
