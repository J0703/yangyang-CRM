package com.lanou.hr.action;

import com.lanou.hr.domain.Department;
import com.lanou.hr.service.DepartmentService;
import com.lanou.hr.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import java.util.List;


/**
 * Created by dllo on 17/10/24.
 */
@Controller("departmentAction")
@Scope("prototype")
public class DepartmentAction extends ActionSupport implements ModelDriven<Department> {

    @Autowired
    @Qualifier("departmentService")
    private DepartmentService departmentService;
    private List<Department> departments;
    private Department departmentDriven;
    private Department department;
    private int pageNum;
    private int pageSize = 3;

    /**
     * 条件查询获得department集合
     */
    public String findByPage() {
        if (pageNum == 0) {
            pageNum = 1;
        }
        PageBean<Department> pageBean = departmentService.findByPage(pageNum, pageSize);
        ActionContext.getContext().put("pageBean", pageBean);
        return SUCCESS;
    }

    /**
     * 获取所有部门
     *
     * @return
     */
    public String findDepartment() {
        departments = departmentService.findAll();
        return SUCCESS;
    }

    /**
     * 通过id获取
     */
    public String findSingle() {
        department = departmentService.get(Department.class, departmentDriven.getDepId());
        return SUCCESS;
    }

    /**
     * 更新或添加
     */
    public String update() {
        if (StringUtils.isBlank(departmentDriven.getDepId())) {
            Department department1 = new Department(departmentDriven.getDepName());
            departmentService.save(department1);
        } else {
            Department department2 = new Department(departmentDriven.getDepId(), departmentDriven.getDepName());
            departmentService.update(department2);
        }
        return SUCCESS;
    }

    /**
     * 添加部门表单校验
     */
    public void validateUpdate() {
        if (StringUtils.isBlank(departmentDriven.getDepName())) {
            addActionError("输入的部门名称不能为空");
        }
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public Department getModel() {
        departmentDriven = new Department();
        return departmentDriven;
    }
}
