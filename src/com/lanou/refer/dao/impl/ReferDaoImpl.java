package com.lanou.refer.dao.impl;

import com.lanou.hr.dao.impl.BaseDaoImpl;
import com.lanou.hr.domain.Staff;
import com.lanou.hr.util.PageHibernateCallback;
import com.lanou.refer.dao.ReferDao;
import com.lanou.refer.domain.Refer;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/10/28.
 */
public class ReferDaoImpl extends BaseDaoImpl<Refer> implements ReferDao {
    @Override
    public int getTotalRecordRefer(String hql, List<Object> params) {
        StringBuffer stringBuffer = new StringBuffer(hql);
        Object param = params.get(0);
        List<Object> lists = new ArrayList<>();
        if (param.toString().trim().length() > 0) {
            if (!NumberUtils.isNumber(param.toString())) {
                stringBuffer.append(" and name like ?");
                lists.add("%" + param + "%");
            } else {
                if (param.toString().trim().length() == 11) {
                    stringBuffer.append(" and telephone like ?");
                    lists.add("%" + param + "%");
                }else {
                    stringBuffer.append(" and qq like ?");
                    lists.add("%" + param + "%");
                }
            }
        }
        List<Long> find = (List<Long>) this.getHibernateTemplate().find(stringBuffer.toString(), lists.toArray());
        if (find != null) {
            return find.get(0).intValue();
        }
        return 0;
    }

    @Override
    public List<Refer> findByCD(String hql1, List<Object> params, int startIndex, int pageSize) {
        StringBuffer stringBuffer = new StringBuffer(hql1);
        Object param = params.get(0);
        List<Object> lists = new ArrayList<>();
        if (param.toString().trim().length() > 0) {
            if (!NumberUtils.isNumber(param.toString())) {
                stringBuffer.append(" and name like ?");
                lists.add("%" + param + "%");
            } else {
                if (param.toString().trim().length() == 11) {
                    stringBuffer.append(" and telephone like ?");
                    lists.add("%" + param + "%");
                }else {
                    stringBuffer.append(" and qq like ?");
                    lists.add("%" + param + "%");
                }
            }
        }
        return this.getHibernateTemplate().execute(
                new PageHibernateCallback<Refer>(stringBuffer.toString(), lists.toArray(), startIndex, pageSize));
    }
}
