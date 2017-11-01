package com.lanou.hr.service.impl;

import com.lanou.hr.dao.StaffDao;
import com.lanou.hr.domain.Staff;
import com.lanou.hr.service.StaffService;
import com.lanou.base_utils.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/25.
 */
@Service("staffService")
public class StaffServiceImpl implements StaffService {

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
        return staffDao.findSingle(hql, params);
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
        return staffDao.get(tClass, id);
    }

    @Override
    public PageBean<Staff> findByPage(int pageNum, int pageSize) {
        String hql = "select count(*) from Staff";
        String hql1 = "from Staff where 1=1";
        int totalRecord = staffDao.getTotalRecord(hql);
        PageBean<Staff> pageBean = new PageBean<>(pageNum, pageSize, totalRecord);
        List<Staff> data = staffDao.findALL(hql1, pageBean.getStartIndex(), pageBean.getPageSize());
        pageBean.setData(data);
        return pageBean;
    }

    /**
     * 高级查询
     *
     * @param params
     * @return
     */
    @Override
    public PageBean<Staff> findByCD(List<Object> params, int pageNum, int pageSize) {
        String hql = "select count(*) from Staff where 1=1";
        String hql1 = "from Staff where 1=1";
        StringBuffer stringBuffer = new StringBuffer(hql);
        StringBuffer stringBuffer1 = new StringBuffer(hql1);
        Object staffName = params.get(0);
        Object depId = params.get(1);
        Object postId = params.get(2);
        List<Object> lists = new ArrayList<>();
        if (staffName.toString().trim().length() > 0) {
            stringBuffer.append(" and staffName like ?");
            stringBuffer1.append(" and staffName like ?");
            lists.add("%" + staffName + "%");
        }
        if (!depId.equals("-1")) {
            stringBuffer.append(" and depId like ?");
            stringBuffer1.append(" and depId like ?");
            lists.add(depId);
        }
        if (!postId.equals("-1")) {
            stringBuffer.append(" and postId like ?");
            stringBuffer1.append(" and postId like ?");
            lists.add(postId);
        }
        int totalRecord = staffDao.getTotalRecordT(stringBuffer.toString(), lists);
        PageBean<Staff> pageBean = new PageBean<>(pageNum, pageSize, totalRecord);
        List<Staff> data = staffDao.findByCD(stringBuffer1.toString(), lists, pageBean.getStartIndex(), pageBean.getPageSize());
        pageBean.setData(data);
        return pageBean;
    }

    @Override
    public Staff login(Map<String, Object> params) {
        String hql = "from Staff where loginName =:loginName and loginPwd =:loginPwd";
        return staffDao.findSingle(hql, params);
    }


}
