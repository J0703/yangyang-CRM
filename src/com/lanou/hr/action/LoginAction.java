package com.lanou.hr.action;

import com.lanou.hr.domain.Staff;
import com.lanou.hr.service.StaffService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by dllo on 17/10/25.
 */
@Controller("loginAction")
@Scope("prototype")
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
        Map<String, Object> params = new HashMap<>();
        params.put("loginName", loginName);
        if (staffService.findSingle(params) == null) {
            addActionError("用户不存在");
            return INPUT;
        }
        params.put("loginPwd", new StaffAction().EncoderByMd5(loginPwd));
        Staff staff = staffService.login(params);
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
        Staff staff = (Staff) ServletActionContext.getRequest().getServletContext().getAttribute("adminStaff");
        staff.setLoginPwd(new StaffAction().EncoderByMd5(newPassword));
        staffService.update(staff);
        ActionContext.getContext().getSession().put("msg", "密码修改成功, 请重新登录");
        ServletActionContext.getRequest().getServletContext().removeAttribute("adminStaff");
        return LOGIN;
    }

    /**
     * 修改密码表单校验
     */
    public void validateUpdatePwd() {
        Staff staff = (Staff) ServletActionContext.getRequest().getServletContext().getAttribute("adminStaff");
        if (StringUtils.isBlank(newPassword) || StringUtils.isBlank(reNewPassword) || StringUtils.isBlank(loginPwd)) {
            addActionError("输入的密码不能为空");
        } else {
            if (newPassword.length() < 5 || reNewPassword.length() < 5) {
                addActionError("密码长度不小于6位,请重新输入");
            } else {
                if (!staff.getLoginPwd().equals(new StaffAction().EncoderByMd5(loginPwd))) {
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
