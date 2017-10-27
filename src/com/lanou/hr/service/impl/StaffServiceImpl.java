package com.lanou.hr.service.impl;

import com.lanou.hr.dao.StaffDao;
import com.lanou.hr.domain.Staff;
import com.lanou.hr.service.StaffService;
import com.lanou.hr.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.Serializable;
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
    public List<Staff> findAll() {
        String hql = "from Staff";
        return staffDao.findAll(hql);
    }

    @Override
    public Staff findSingle(Map<String, Object> params) {
        String hql = "from Staff where loginName =:loginName";
        return staffDao.findSingle(hql,params);
    }

    @Override
    public List<Staff> find(String hql, Map<String, Object> params) {

        return staffDao.find(hql, params);
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
    public Staff get(Class<Staff> tClass, Serializable id) {
        return staffDao.get(tClass,id);
    }

    @Override
    public PageBean<Staff> findByPage(int pageNum, int pageSize) {
        String hql = "select count(*) from Staff";
        String hql1 = "from Staff where 1=1";
        int totalRecord = staffDao.getTotalRecord(hql);
        PageBean<Staff> pageBean = new PageBean<>(pageNum,pageSize,totalRecord);
        List<Staff> data = staffDao.findALL(hql1,pageBean.getStartIndex(),pageBean.getPageSize());
        pageBean.setData(data);
        return pageBean;
    }


    /**
     * 高级查询
     * @param params
     * @return
     */
    @Override
    public PageBean<Staff> findByCD(List<Object> params, int pageNum, int pageSize) {
        String hql = "select count(*) from Staff where 1=1";
        String hql1 = "from Staff where 1=1";
        int totalRecord = staffDao.getTotalRecordStaff(hql,params);
        PageBean<Staff> pageBean = new PageBean<>(pageNum,pageSize,totalRecord);
        List<Staff> data = staffDao.findByCD(hql1,params,pageBean.getStartIndex(),pageBean.getPageSize());
        pageBean.setData(data);
        return pageBean;
    }

    @Override
    public Staff login(Map<String, Object> params) {
        String hql = "from Staff where loginName =:loginName and loginPwd =:loginPwd";
        return staffDao.findSingle(hql,params);
    }


}
