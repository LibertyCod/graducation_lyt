package com.graduation.project.dao;

import com.graduation.project.core.Mapper;
import com.graduation.project.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends Mapper<User> {

    /**
     * 根据邮箱和密码查询用户
     * @param email
     * @param password
     * @return
     */
    User selectByEmailAndPsd(@Param("email") String email, @Param("password") String password);

    /**
     * 根据UUID更新用户
     * @param user
     * @return
     */
    int updateByUUID(User user);
}