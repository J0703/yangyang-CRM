package com.lanou.hr.service;

import com.lanou.hr.domain.Post;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/25.
 */
public interface PostService {
    List<Post> findAll(String hql);

    List<Post> find(String hql, Map<String, Object> params);

    Post findSingle(String hql, Map<String, Object> params);

    Post get(Class<Post> postClass, Serializable id);

    void update(Post post);

    void save(Post post);
}
