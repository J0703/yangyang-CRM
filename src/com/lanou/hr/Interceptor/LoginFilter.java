package com.lanou.hr.Interceptor;

import com.lanou.hr.domain.Staff;
import org.apache.struts2.ServletActionContext;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by dllo on 17/10/31.
 */
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        Staff staff = (Staff) ServletActionContext.getRequest().getServletContext().getAttribute("adminStaff");
        if (staff == null){
            request.setAttribute("msg","请登录");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
