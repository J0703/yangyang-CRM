package com.lanou.hr.dao.impl;

import com.lanou.hr.dao.StaffDao;
import com.lanou.hr.domain.Staff;
import com.lanou.hr.util.PageHibernateCallback;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/25.
 */
public class StaffDaoImpl extends BaseDaoImpl<Staff> implements StaffDao {
    @Override
    public int getTotalRecordStaff(String hql, List<Object> params) {
        StringBuffer stringBuffer = new StringBuffer(hql);
        Object staffName = params.get(0);
        Object depId = params.get(1);
        Object postId = params.get(2);
        List<Object> lists = new ArrayList<>();
        if (staffName.toString().trim().length() > 0) {
            stringBuffer.append(" and staffName like ?");
            lists.add("%" + staffName + "%");
        }
        if (!depId.equals("-1")) {
            stringBuffer.append(" and depId like ?");
            lists.add(depId);
        }
        if (!postId.equals("-1")) {
            stringBuffer.append(" and postId like ?");
            lists.add(postId);
        }
        List<Long> find = (List<Long>) this.getHibernateTemplate().find(stringBuffer.toString(), lists.toArray());
        if (find != null) {
            return find.get(0).intValue();
        }
        return 0;
    }

    @Override
    public List<Staff> findByCD(String hql, List<Object> params, int startIndex, int pageSize) {
        StringBuffer stringBuffer = new StringBuffer(hql);
        Object staffName = params.get(0);
        Object depId = params.get(1);
        Object postId = params.get(2);
        List<Object> lists = new ArrayList<>();
        if (staffName.toString().trim().length() > 0) {
            stringBuffer.append(" and staffName like ?");
            lists.add("%" + staffName + "%");
        }
        if (!depId.equals("-1")) {
            stringBuffer.append(" and depId like ?");
            lists.add(depId);
        }
        if (!postId.equals("-1")) {
            stringBuffer.append(" and postId like ?");
            lists.add(postId);
        }
        return this.getHibernateTemplate().execute(
                new PageHibernateCallback<Staff>(stringBuffer.toString(), lists.toArray(), startIndex, pageSize));
    }
}
