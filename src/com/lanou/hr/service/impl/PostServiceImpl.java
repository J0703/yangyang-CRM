package com.lanou.hr.service.impl;

import com.lanou.hr.dao.PostDao;
import com.lanou.hr.domain.Post;
import com.lanou.hr.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


/**
 * Created by dllo on 17/10/25.
 */
@Service("postService")
public class PostServiceImpl extends BaseServiceImpl<Post> implements PostService {
    @Autowired
    @Qualifier("postDao")
    private PostDao postDao;

}
