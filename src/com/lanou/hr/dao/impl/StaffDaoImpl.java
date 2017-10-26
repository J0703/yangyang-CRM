package com.lanou.hr.dao.impl;

import com.lanou.hr.dao.StaffDao;
import com.lanou.hr.domain.Staff;

import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/25.
 */
public class StaffDaoImpl extends BaseDaoImpl<Staff> implements StaffDao{
    @Override
    public List<Staff> findByCd(Map<String, Object> params) {
        String hql = "from Staff where 1=1";
        StringBuffer stringBuffer = new StringBuffer(hql);
        Object postId = params.get("postId");
        Object staffName = params.get("staffName");
        if (staffName != null) {
            stringBuffer.append(" and staffName like :staffName");
        }
        if (!postId.equals("-1")) {
            stringBuffer.append(" and postId like :postId");
        } else {
            params.remove("postId");
        }

        return super.find(stringBuffer.toString(), params);
    }
}
