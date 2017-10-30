package com.lanou.refer.service.impl;

import com.lanou.refer.dao.FollowDao;
import com.lanou.refer.domain.Follow;
import com.lanou.refer.service.FollowService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dllo on 17/10/28.
 */
@Service("followService")
public class FollowServiceImpl implements FollowService{

    @Autowired
    @Qualifier("followDao")
    private FollowDao followDao;

    @Override
    public Follow findSingle(String referId) {
        String hql = "from Follow where referId =:referId";
        Map<String, Object> params = new HashMap<>();
        params.put("referId",referId);
        return followDao.findSingle(hql,params);
    }

    @Override
    public void save(Follow followDriven) {
        followDao.save(followDriven);
    }

    @Override
    public Follow get(Class<Follow> followClass, String followId) {
        return followDao.get(followClass,followId);
    }

    @Override
    public void update(Follow follow1) {
        followDao.update(follow1);
    }
}
