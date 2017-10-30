package com.lanou.hr.Interceptor;

import com.lanou.hr.domain.Staff;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

/**
 * Created by dllo on 17/10/25.
 */
public class hrInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        Staff staff = (Staff) ServletActionContext.getRequest().getServletContext().getAttribute("adminStaff");
        if (!staff.getDepartment().getDepName().equals("人事部")){
            return "permissions";
        }
        return actionInvocation.invoke();
    }
}
