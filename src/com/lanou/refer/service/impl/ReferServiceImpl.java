package com.lanou.refer.service.impl;

import com.lanou.hr.util.PageBean;
import com.lanou.refer.dao.ReferDao;
import com.lanou.refer.domain.Refer;
import com.lanou.refer.service.ReferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/28.
 */
@Service("referService")
public class ReferServiceImpl implements ReferService {

    @Autowired
    @Qualifier("referDao")
    private ReferDao referDao;

    @Override
    public List<Refer> findAll() {
        String hql = "from Refer";
        return referDao.findAll(hql);
    }

    @Override
    public List<Refer> find(Map<String, Object> params) {
        String hql = "from Refer where status =:status";
        return referDao.find(hql, params);
    }

    @Override
    public void save(Refer referDriven) {
        referDao.save(referDriven);
    }

    @Override
    public Refer findSingle(Class<Refer> referClass, String referId) {
        return referDao.get(referClass, referId);
    }

    @Override
    public void update(Refer referDriven) {
        referDao.update(referDriven);
    }

    @Override
    public PageBean<Refer> findByPage(String status, int pageNum, int pageSize) {
        String hql = "select count(*) from Refer where status =" + status + "";
        String hql1 = "from Refer where 1=1 and status =" + status + "";
        int totalRecord = referDao.getTotalRecord(hql);
        PageBean<Refer> pageBean = new PageBean<>(pageNum, pageSize, totalRecord);
        List<Refer> data = referDao.findALL(hql1, pageBean.getStartIndex(), pageBean.getPageSize());
        pageBean.setData(data);
        return pageBean;
    }

    @Override
    public PageBean<Refer> findByCD(List<Object> params, int pageNum, int pageSize) {
        String hql = "select count(*) from Refer where 1=1";
        String hql1 = "from Refer where 1=1";
        int totalRecord = referDao.getTotalRecordRefer(hql,params);
        PageBean<Refer> pageBean = new PageBean<>(pageNum,pageSize,totalRecord);
        List<Refer> data = referDao.findByCD(hql1,params,pageBean.getStartIndex(),pageBean.getPageSize());
        pageBean.setData(data);
        return pageBean;
    }
}
