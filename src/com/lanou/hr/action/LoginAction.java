package com.lanou.hr.action;

import com.lanou.hr.domain.Staff;
import com.lanou.hr.service.StaffService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dllo on 17/10/25.
 */
@Controller("loginAction")
public class LoginAction extends ActionSupport {

    @Autowired
    @Qualifier("staffService")
    private StaffService staffService;
    private String loginName;
    private String loginPwd;
    private String newPassword;
    private String reNewPassword;


    /**
     * 登录
     */
    public String login() {
        String hql = "from Staff where loginName =:loginName";
        Map<String, Object> params = new HashMap<>();
        params.put("loginName", loginName);
        if (staffService.findSingle(hql, params) == null) {
            addActionError("用户不存在");
            return INPUT;
        }
        String hql1 = "from Staff where loginName =:loginName and loginPwd =:loginPwd";
        Map<String, Object> params1 = new HashMap<>();
        params1.put("loginName", loginName);
        params1.put("loginPwd", loginPwd);
        Staff staff = staffService.findSingle(hql1, params1);
        ServletActionContext.getRequest().getServletContext().setAttribute("adminStaff", staff);
        if (staff == null) {
            addActionError("您输入的密码有误");
            return INPUT;
        }
        return SUCCESS;
    }

    /**
     * 修改密码
     */
    public String updatePwd() {
        Staff staff = (Staff) ServletActionContext.getRequest().getServletContext().getAttribute("staff");
        staff.setLoginPwd(newPassword);
        staffService.update(staff);
        addFieldError("msg", "密码修改成功, 请重新登录");
        return SUCCESS;
    }

    /**
     * 修改密码表单校验
     */
    public void validateUpdatePwd() {
        Staff staff = (Staff) ServletActionContext.getRequest().getServletContext().getAttribute("staff");
        if (StringUtils.isBlank(newPassword) || StringUtils.isBlank(reNewPassword) || StringUtils.isBlank(loginPwd)) {
            addActionError("输入的密码不能为空");
        } else {
            if (!staff.getLoginPwd().equals(loginPwd)) {
                addActionError("输入的原始密码有误, 请重新输入");
            } else {
                if (newPassword.equals(loginPwd)) {
                    addActionError("不能与原密码重复, 请重新输入");
                }
            }
            if (!newPassword.equals(reNewPassword)) {
                addActionError("两次输入的密码不一致,请重新输入");
            }
        }

    }

    /**
     * 登录表单校验
     */
    public void validateLogin() {
        if (StringUtils.isBlank(loginName) || StringUtils.isBlank(loginPwd)) {
            addActionError("用户名或密码不能为空");
        }
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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getReNewPassword() {
        return reNewPassword;
    }

    public void setReNewPassword(String reNewPassword) {
        this.reNewPassword = reNewPassword;
    }
}
