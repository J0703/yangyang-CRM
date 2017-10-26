package com.lanou.hr.service.impl;

import com.lanou.hr.dao.StaffDao;
import com.lanou.hr.domain.Staff;
import com.lanou.hr.service.StaffService;
import com.lanou.hr.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/25.
 */
@Service("staffService")
public class StaffServiceImpl extends BaseServiceImpl<Staff> implements StaffService{

    @Autowired
    @Qualifier("staffDao")
    private StaffDao staffDao;

    /**
     * 高级查询
     * @param params
     * @return
     */
    @Override
    public PageBean<Staff> findByCD(String hql, String hql1, List<Object> params, int pageNum, int pageSize) {

        int totalRecord = staffDao.getTotalRecordStaff(hql,params);
        PageBean<Staff> pageBean = new PageBean<>(pageNum,pageSize,totalRecord);
        List<Staff> data = staffDao.findByCD(hql1,params,pageBean.getStartIndex(),pageBean.getPageSize());
        pageBean.setData(data);
        return pageBean;
    }


}
