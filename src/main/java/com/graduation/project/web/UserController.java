package com.graduation.project.web;
import com.graduation.project.core.ApiResponse;
import com.graduation.project.dto.LoginResponseDTO;
import com.graduation.project.model.User;
import com.graduation.project.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.graduation.project.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/03/23.
*/
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/add")
    public ApiResponse add(User user) {
        userService.saveUser(user);
        return ApiResponse.getSuccResponse("用户创建成功");
    }

    @PostMapping("/delete")
    public ApiResponse delete(@RequestParam Integer id) {
        userService.deleteById(id);
        return ApiResponse.getSuccResponse("用户删除成功");
    }

    @PostMapping("/update")
    public ApiResponse update(User user) {
        userService.update(user);
        return ApiResponse.getSuccResponse("用户资料完善成功");
    }

    @PostMapping("/detail")
    public ApiResponse detail(@RequestParam Integer id) {
        User user = userService.findById(id);
        return ApiResponse.getSuccResponse(user);
    }

    @PostMapping("/list")
    public ApiResponse list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<User> list = userService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ApiResponse.getSuccResponse(pageInfo);
    }

    @PostMapping("/login")
    public ApiResponse login(@RequestParam String email, @RequestParam String password) {
        LoginResponseDTO resDTO = userService.login(email, password);
        if (resDTO != null) {
            return ApiResponse.getSuccResponse("登陆成功", resDTO);
        } else {
            return ApiResponse.getErrResponse("账号密码有误");
        }
    }
}
