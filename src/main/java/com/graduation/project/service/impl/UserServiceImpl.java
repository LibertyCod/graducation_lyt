package com.graduation.project.service.impl;

import com.graduation.project.dao.UserMapper;
import com.graduation.project.dto.LoginResponseDTO;
import com.graduation.project.exception.ServiceException;
import com.graduation.project.exception.errorcode.BizErrorCode;
import com.graduation.project.model.User;
import com.graduation.project.service.UserService;
import com.graduation.project.core.AbstractService;
import com.graduation.project.util.JwtTokenUtil;
import com.graduation.project.util.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


/**
 * Created by CodeGenerator on 2018/03/23.
 */
@Service("userService")
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public LoginResponseDTO login(String email, String password) {
        User user = userMapper.selectByEmailAndPsd(email, password);
        LoginResponseDTO responseDTO = new LoginResponseDTO();
        if (user == null) {
            return null;
        }
        responseDTO.setUser(user);
        responseDTO.setToken(jwtTokenUtil.generateToken(String.valueOf(user.getId())));
        return responseDTO;
    }

    @Override
    public String saveUser(User user) {
        User query = new User();
        query.setEmail(user.getEmail());
        String uuid;
        if (userMapper.select(query) != null) {
            throw new ServiceException(BizErrorCode.USER_HAS_EXISTED);
        } else {
            uuid = UUIDGenerator.uuid();
            user.setUuid(uuid);
            user.setCreateDatetime(new Date());
            super.save(user);
        }
        return uuid;
    }

    @Override
    public void update(User model) {
        model.setUpdateDatetime(new Date());
        userMapper.updateByUUID(model);
    }
}
