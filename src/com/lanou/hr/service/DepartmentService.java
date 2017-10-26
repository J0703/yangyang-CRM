package com.lanou.hr.service;

import com.lanou.hr.domain.Department;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/24.
 */
public interface DepartmentService {
    List<Department> findAll(String hql);

    Department findSingle(String hql, Map<String, Object> params);

    void update(Department department);

    void save(Department department);

    Department get(Class<Department> departmentClass, Serializable id);
}
