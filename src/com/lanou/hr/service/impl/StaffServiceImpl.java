package com.lanou.hr.service.impl;

import com.lanou.hr.dao.StaffDao;
import com.lanou.hr.domain.Staff;
import com.lanou.hr.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/25.
 */
@Service("staffService")
public class StaffServiceImpl implements StaffService{
    @Autowired
    @Qualifier("staffDao")
    private StaffDao staffDao;

    @Override
    public List<Staff> findAll(String hql) {
        return staffDao.findAll(hql);
    }

    @Override
    public List<Staff> find(Map<String, Object> params) {
        return staffDao.findByCd(params);
    }

    @Override
    public Staff get(Class<Staff> staffClass, String staffId) {
        return staffDao.get(staffClass,staffId);
    }

    @Override
    public void update(Staff staff) {
        staffDao.update(staff);
    }

    @Override
    public void save(Staff staff) {
        staffDao.save(staff);
    }

    @Override
    public Staff findSingle(String hql, Map<String, Object> params) {
        return staffDao.findSingle(hql,params);
    }
}
