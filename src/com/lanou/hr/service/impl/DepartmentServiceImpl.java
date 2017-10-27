package com.lanou.hr.service.impl;

import com.lanou.hr.dao.DepartmentDao;
import com.lanou.hr.domain.Department;

import com.lanou.hr.service.DepartmentService;
import com.lanou.hr.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;



/**
 * Created by dllo on 17/10/24.
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
    @Qualifier("departmentDao")
    private DepartmentDao departmentDao;

    @Override
    public List<Department> findAll() {
        String hql = "from Department";
        return departmentDao.findAll(hql);
    }

    @Override
    public void update(Department department) {
        departmentDao.update(department);
    }

    @Override
    public void save(Department department) {
        departmentDao.save(department);
    }

    @Override
    public Department get(Class<Department> tClass, Serializable id) {
        return departmentDao.get(tClass,id);
    }

    @Override
    public PageBean<Department> findByPage(int pageNum, int pageSize) {
        String hql = "select count(*) from Department";
        String hql1 = "from Department where 1=1";
        int totalRecord = departmentDao.getTotalRecord(hql);
        PageBean<Department> pageBean = new PageBean<>(pageNum,pageSize,totalRecord);
        List<Department> data = departmentDao.findALL(hql1,pageBean.getStartIndex(),pageBean.getPageSize());
        pageBean.setData(data);
        return pageBean;
    }

}
