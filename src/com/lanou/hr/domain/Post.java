package com.lanou.hr.domain;

import java.util.Set;

/**
 * Created by dllo on 17/10/24.
 * 职务
 */
public class Post {
    private String postId;
    private String postName;
    private Department department;
    public Post() {
    }

    public Post(String postId, String postName, Department department) {
        this.postId = postId;
        this.postName = postName;
        this.department = department;
    }


    public Post(String postName) {
        this.postName = postName;

    }

    @Override
    public String toString() {
        return "Post{" +
                "postId='" + postId + '\'' +
                ", postName='" + postName + '\'' +
                '}';
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
