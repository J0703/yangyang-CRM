package com.lanou.hr.service.impl;

import com.lanou.hr.dao.DepartmentDao;
import com.lanou.hr.domain.Department;

import com.lanou.hr.service.DepartmentService;
import com.lanou.hr.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by dllo on 17/10/24.
 */
@Service("departmentService")
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements DepartmentService{
    @Autowired
    @Qualifier("departmentDao")
    private DepartmentDao departmentDao;

}
