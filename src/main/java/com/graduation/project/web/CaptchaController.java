package com.graduation.project.web;

import com.alibaba.fastjson.JSONObject;
import com.graduation.project.core.ApiResponse;
import com.graduation.project.exception.ServiceException;
import com.graduation.project.exception.errorcode.BizErrorCode;
import com.graduation.project.util.UUIDUtil;
import com.graduation.project.util.captcha.CaptchaContext;
import com.graduation.project.util.captcha.CaptchaUtil;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Binbin Wang
 * @date 2018/3/9
 */
@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    @GetMapping
    public ApiResponse getCaptcha() {
        String[] captchaArray = null;
        Map<String, Object> captchaMap = null;
        try {
            captchaArray = CaptchaUtil.genCaptcha();
            captchaMap = new HashMap<>(3);
            captchaMap.put("value", captchaArray[0]);
            captchaMap.put("src", captchaArray[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String uuid = UUIDUtil.generateUUID();
        CaptchaContext.put(uuid, captchaArray[0]);
        captchaMap.put("captchaUUID", uuid);
        JSONObject jsonObject = new JSONObject(captchaMap);
        return ApiResponse.getSuccResponse("获取验证码成功", jsonObject);
    }

    @PostMapping
    public ApiResponse validateCaptcha(@RequestParam("captchaUUID") String captchaUUID, @RequestParam("captchaValue") String captchaValue) {
        validCaptcha(captchaUUID, captchaValue);
        return ApiResponse.getSuccResponse("验证成功");
    }

    private void validCaptcha(String captchaUUID, String captchaValue) {
        if (captchaUUID == null) {
            throw new ServiceException(BizErrorCode.CAPTCHA_IS_WRONG);
        }
        Object o = CaptchaContext.getCaptchaM().get(captchaUUID);
        if (o == null) {
            throw new ServiceException(BizErrorCode.CAPTCHA_IS_EXPIRED);
        }
        String value = String.valueOf(o);

        if (!captchaValue.equals(value)) {
            throw new ServiceException(BizErrorCode.CAPTCHA_IS_WRONG);
        }

    }

}
