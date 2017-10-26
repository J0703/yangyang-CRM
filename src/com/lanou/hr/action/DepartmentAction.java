package com.lanou.hr.action;

import com.lanou.hr.domain.Department;
import com.lanou.hr.service.DepartmentService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.util.List;


/**
 * Created by dllo on 17/10/24.
 */
@Controller("departmentAction")
public class DepartmentAction extends ActionSupport {

    @Autowired
    @Qualifier("departmentService")
    private DepartmentService departmentService;
    private List<Department> departments;
    private Department department;
    private String depName;
    private String depId;

    /**
     * 获得department集合
     *
     * @return
     */
    public String findDepartment() {
        String hql = "from Department";
        departments = departmentService.findAll(hql);
        return SUCCESS;
    }

    /**
     * 通过id获取
     *
     * @return
     */
    public String findSingle() {
        Serializable id = depId;
        department = departmentService.get(Department.class, id);
        return SUCCESS;
    }

    /**
     * 更新或添加
     *
     * @return
     */
    public String update() {
        if (StringUtils.isBlank(depId)) {
            Department department = new Department(depName);
            departmentService.save(department);
        }else {
            Department department = new Department(depId, depName);
            departmentService.update(department);
        }
        return SUCCESS;
    }


    public void validateUpdate() {
        if (StringUtils.isBlank(depName)) {
            addActionError("输入的部门名称不能为空");
        }
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }
}
