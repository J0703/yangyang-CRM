package com.lanou.hr.service;

import com.lanou.hr.domain.Post;
import com.lanou.hr.util.PageBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/25.
 */
public interface PostService {

    List<Post> find(Map<String, Object> params);

    void update(Post post);

    void save(Post post);

    Post get(Class<Post> postClass, Serializable id);

    PageBean<Post> findByPage(int pageNum, int pageSize);
}
