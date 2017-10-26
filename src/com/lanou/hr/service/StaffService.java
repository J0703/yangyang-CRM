package com.lanou.hr.service;

import com.lanou.hr.domain.Staff;

import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/25.
 */
public interface StaffService {
    List<Staff> findAll(String hql);

    List<Staff> find(Map<String, Object> params);

    Staff get(Class<Staff> staffClass, String staffId);

    void update(Staff staff);

    void save(Staff staff);

    Staff findSingle(String hql, Map<String, Object> params);
}
