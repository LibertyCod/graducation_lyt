package com.graduation.project.service;
import com.graduation.project.dto.LoginResponseDTO;
import com.graduation.project.model.User;
import com.graduation.project.core.Service;


/**
 * Created by CodeGenerator on 2018/03/23.
 */
public interface UserService extends Service<User> {

    /**
     * 根据邮箱密码登陆
     * @param email
     * @param password
     * @return
     */
    LoginResponseDTO login(String email, String password);

    /**
     * 新建用户,返回用户的uuid
     * @param user
     * @return
     */
    String saveUser(User user);

}
