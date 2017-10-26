package com.lanou.hr.service.impl;

import com.lanou.hr.dao.DepartmentDao;
import com.lanou.hr.domain.Department;
import com.lanou.hr.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/24.
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
    @Qualifier("departmentDao")
    private DepartmentDao departmentDao;
    @Override
    public List<Department> findAll(String hql) {
       return departmentDao.findAll(hql);
    }

    @Override
    public Department findSingle(String hql, Map<String, Object> params) {
        return departmentDao.findSingle(hql,params);
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
    public Department get(Class<Department> departmentClass, Serializable id) {
        return departmentDao.get(departmentClass,id);
    }
}
