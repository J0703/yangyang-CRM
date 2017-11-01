package com.lanou.refer.service.impl;

import com.lanou.base_utils.util.PageBean;
import com.lanou.refer.dao.ReferDao;
import com.lanou.refer.domain.Refer;
import com.lanou.refer.service.ReferService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        StringBuilder sb1 = new StringBuilder(hql);
        StringBuilder sb2 = new StringBuilder(hql1);
        StringBuilder sb3 = new StringBuilder(hql);
        StringBuilder sb4 = new StringBuilder(hql1);
        Object param = params.get(0);
        Object status = params.get(1);
        List<Object> lists = new ArrayList<>();
        sb1.append(" and status = " + status + "");
        sb2.append(" and status = " + status + "");
        sb3.append(" and status = " + status + "");
        sb4.append(" and status = " + status + "");
        if (param.toString().trim().length() > 0) {
            if (!NumberUtils.isNumber(param.toString())) {
                sb1.append(" and name like ?");
                sb2.append(" and name like ?");
                sb3.append(" and name like ?");
                sb4.append(" and name like ?");
                lists.add("%" + param + "%");
            } else {
                sb1.append(" and qq like ?");
                sb3.append(" and telephone like ?");
                sb2.append(" and qq like ?");
                sb4.append(" and telephone like ?");
                lists.add("%" + param + "%");
            }
        }

        int totalRecord1 = referDao.getTotalRecordT(sb1.toString(), lists);
        PageBean<Refer> pageBean1 = new PageBean<>(pageNum, pageSize, totalRecord1);
        List<Refer> data1 = referDao.findByCD(sb2.toString(), lists, pageBean1.getStartIndex(), totalRecord1);

        int totalRecord2 = referDao.getTotalRecordT(sb3.toString(), lists);
        PageBean<Refer> pageBean2 = new PageBean<>(pageNum, pageSize, totalRecord2);
        List<Refer> data2 = referDao.findByCD(sb4.toString(), lists, pageBean2.getStartIndex(), totalRecord2);

        for (Refer refer : data2) {
            if (!data1.contains(refer)) {
                data1.add(refer);
            }
        }
        pageBean1.setData(data1);
        return pageBean1;
    }
}
