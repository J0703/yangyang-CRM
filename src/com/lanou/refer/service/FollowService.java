package com.lanou.refer.service;

import com.lanou.refer.domain.Follow;

/**
 * Created by dllo on 17/10/28.
 */
public interface FollowService {
    Follow findSingle(String referId);

    void save(Follow followDriven);

    Follow get(Class<Follow> followClass, String followId);

    void update(Follow follow1);

    Follow findByReferId(String referId);
}
