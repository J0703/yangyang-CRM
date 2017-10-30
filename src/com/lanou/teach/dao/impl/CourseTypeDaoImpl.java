package com.lanou.teach.dao.impl;

import com.lanou.hr.dao.impl.BaseDaoImpl;
import com.lanou.hr.util.PageHibernateCallback;
import com.lanou.teach.dao.CourseTypeDao;
import com.lanou.teach.domain.CourseType;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/10/27.
 */
public class CourseTypeDaoImpl extends BaseDaoImpl<CourseType> implements CourseTypeDao {
    @Override
    public int getTotalRecordStaff(String hql, List<Object> params) {
        StringBuffer stringBuffer = new StringBuffer(hql);
        String courseName = (String) params.get(0);
        String remark = (String) params.get(1);
        int totalStart = StringUtils.isBlank(params.get(2).toString()) ? 0 : Integer.parseInt(params.get(2).toString());
        int totalEnd = StringUtils.isBlank(params.get(3).toString()) ? 0 : Integer.parseInt(params.get(3).toString());
        double lessonCostStart = StringUtils.isBlank(params.get(4).toString()) ? 0 : Double.parseDouble(params.get(4).toString());
        double lessonCostEnd = StringUtils.isBlank(params.get(5).toString()) ? 0 : Double.parseDouble(params.get(5).toString());
        List<Object> lists = new ArrayList<>();
        if (!StringUtils.isBlank(courseName)) {
            stringBuffer.append(" and courseName like ?");
            lists.add("%" + courseName + "%");
        }
        if (!StringUtils.isBlank(remark)) {
            stringBuffer.append(" and remark like ? ");
            lists.add("%" + remark + "%");
        }
        stringBuffer.append(" and total > " + totalStart + "");
        if (totalEnd > 0) {
            stringBuffer.append(" and total < " + totalEnd + "");
        }
        stringBuffer.append(" and courseCost > " + lessonCostStart + "");
        if (lessonCostEnd > 0) {
            stringBuffer.append(" and courseCost < " + lessonCostEnd + "");
        }
        List<Long> find = (List<Long>) this.getHibernateTemplate().find(stringBuffer.toString(), lists.toArray());
        if (find != null) {
            return find.get(0).intValue();
        }
        return 0;
    }

    @Override
    public List<CourseType> findByCD(String hql1, List<Object> params, int startIndex, int pageSize) {
        StringBuffer stringBuffer = new StringBuffer(hql1);
        String courseName = (String) params.get(0);
        String remark = (String) params.get(1);
        int totalStart = StringUtils.isBlank(params.get(2).toString()) ? 0 : Integer.parseInt(params.get(2).toString());
        int totalEnd = StringUtils.isBlank(params.get(3).toString()) ? 0 : Integer.parseInt(params.get(3).toString());
        double lessonCostStart = StringUtils.isBlank(params.get(4).toString()) ? 0 : Double.parseDouble(params.get(4).toString());
        double lessonCostEnd = StringUtils.isBlank(params.get(5).toString()) ? 0 : Double.parseDouble(params.get(5).toString());
        List<Object> lists = new ArrayList<>();
        if (!StringUtils.isBlank(courseName)) {
            stringBuffer.append(" and courseName like ?");
            lists.add("%" + courseName + "%");
        }
        if (!StringUtils.isBlank(remark)) {
            stringBuffer.append(" and remark like ? ");
            lists.add("%" + remark + "%");
        }
        stringBuffer.append(" and total > " + totalStart + "");
        if (totalEnd > 0) {
            stringBuffer.append(" and total < " + totalEnd + "");
        }
        stringBuffer.append(" and courseCost > " + lessonCostStart + "");
        if (lessonCostEnd > 0) {
            stringBuffer.append(" and courseCost < " + lessonCostEnd + "");
        }
        return this.getHibernateTemplate().execute(
                new PageHibernateCallback<CourseType>(stringBuffer.toString(), lists.toArray(), startIndex, pageSize));
    }
}


