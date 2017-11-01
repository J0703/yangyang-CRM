package com.lanou.hr.domain;


import java.util.Set;

/**
 * Created by dllo on 17/10/24.
 * 部门
 */
public class Department {
    private String depId;
    private String depName;
    private Set<Post> posts;

    public Department() {
    }


    public Department(String depId, String depName) {
        this.depId = depId;
        this.depName = depName;
    }

    public Department(String depName) {
        this.depName = depName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "depId='" + depId + '\'' +
                ", depName='" + depName + '\'' +
                '}';
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
