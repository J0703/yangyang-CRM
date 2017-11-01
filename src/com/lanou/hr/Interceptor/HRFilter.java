package com.lanou.hr.Interceptor;

import com.lanou.hr.domain.Staff;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by dllo on 17/10/31.
 */
public class HRFilter implements Filter {
    public HRFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        Staff adminStaff = (Staff) request.getServletContext().getAttribute("adminStaff");
        if (!adminStaff.getDepartment().getDepName().equals("人事部")) {
            request.getRequestDispatcher("/pages/error.jsp").forward(request, response);
        }
        filterChain.doFilter(request,response);

    }

    @Override
    public void destroy() {

    }
}
