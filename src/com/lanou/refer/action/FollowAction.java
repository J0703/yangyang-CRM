package com.lanou.refer.action;

import com.lanou.hr.domain.Staff;
import com.lanou.refer.domain.Follow;
import com.lanou.refer.domain.Refer;
import com.lanou.refer.service.FollowService;
import com.lanou.refer.service.ReferService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.Date;

/**
 * Created by dllo on 17/10/28.
 */
@Controller("followAction")
@Scope("prototype")
public class FollowAction extends ActionSupport implements ModelDriven<Follow> {

    @Autowired
    @Qualifier("followService")
    private FollowService followService;
    @Autowired
    @Qualifier("referService")
    private ReferService referService;

    private String referId;
    private Follow follow;
    private Follow followDriven;
    private Refer refers;

    /**
     * 追踪信息表单回显
     */
    public String findSingle() {
        refers = referService.findSingle(Refer.class, referId);
        follow = followService.findSingle(referId);
        return SUCCESS;
    }

    /**
     * 添加咨询学生追踪信息
     */
    public String addFollow() {
        if (StringUtils.isBlank(followDriven.getFollowId())) {
            followDriven.setFollowTime(new Date());
            Staff staff = (Staff) ServletActionContext.getRequest().getServletContext().getAttribute("adminStaff");
            followDriven.setStaff(staff);
            Refer refer1 = referService.findSingle(Refer.class, referId);
            followDriven.setRefer(refer1);
            followService.save(followDriven);
        } else {
            Follow follow1 = followService.get(Follow.class, followDriven.getFollowId());
            follow1.setContent(followDriven.getContent());
            followService.update(follow1);
        }
        return SUCCESS;
    }

    public String getReferId() {
        return referId;
    }

    public void setReferId(String referId) {
        this.referId = referId;
    }

    public Follow getFollow() {
        return follow;
    }

    public void setFollow(Follow follow) {
        this.follow = follow;
    }

    public Refer getRefers() {
        return refers;
    }

    public void setRefers(Refer refers) {
        this.refers = refers;
    }

    @Override
    public Follow getModel() {
        followDriven = new Follow();
        return followDriven;
    }
}
