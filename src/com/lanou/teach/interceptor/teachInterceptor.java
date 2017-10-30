package com.lanou.teach.interceptor;

import com.lanou.hr.domain.Staff;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

/**
 * Created by dllo on 17/10/28.
 */
public class teachInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        Staff staff = (Staff) ServletActionContext.getRequest().getServletContext().getAttribute("adminStaff");
        if (!staff.getDepartment().getDepName().equals("教研部")) {
            return "permissions";
        }
        return actionInvocation.invoke();
    }
}
