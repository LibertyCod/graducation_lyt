package com.graduation.project.web;

import com.graduation.project.core.ApiResponse;
import com.graduation.project.util.EmailUtil;
import com.xiaoleilu.hutool.lang.Base64;
import com.xiaoleilu.hutool.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Binbin Wang
 * @author Nick Chen
 * @date 2017/10/30
 */
@Controller
@Api(value = "/email", description = "发送邮件接口")
@RequestMapping("/email")
public class EmailController {

    @Value("${email.register.redirect.url}")
    private String registerRedirectUrl;

    /**
     * 有效时间 3小时
     */
    private static final long VALID_TIME = 3 * 60 * 60 * 1000L;

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w{2,3}){1,3})$");

    private static CharSequence emailTemplate = null;

    /**
     * 读取html模板文件
     */
    static {
        InputStream in =  EmailController.class.getClassLoader().getResourceAsStream("email/index.html");
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = null;
        String line;
        try {
           bufferedReader = new BufferedReader(new InputStreamReader(in));
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            emailTemplate = stringBuffer;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发送激活邮件
     * @return
     */
    @ApiOperation("发送激活邮件")
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ApiResponse postRegisterMethod(
            @ApiParam(value = "邮箱", required = true)
            @RequestParam String email) {
        ApiResponse apiResponse;

        final String subject = "用户账号激活邮件";
        final boolean isHtml = true;

        long time = System.currentTimeMillis() + VALID_TIME;
        String redirectUrl = registerRedirectUrl + "?email=" + Base64.encode(email) + "&expired=" + Base64.encode(time + "");
        final Map<String, String> templateValueMap = new HashMap<>();
        templateValueMap.put("title", "感谢你注册本平台");
        templateValueMap.put("content", "请点击以下链接激活帐号：");
        templateValueMap.put("redirectUrl", redirectUrl);
        String emailContent = StrUtil.format(emailTemplate, templateValueMap);
        try {
            EmailUtil.sendHtmlMail(email, subject, emailContent, isHtml);
            apiResponse = ApiResponse.getSuccResponse("发送邮件成功");
        } catch (Exception e) {
            apiResponse = ApiResponse.getErrResponse("发送邮件失败！");
        }
        return apiResponse;
    }

    /**
     * 用户激活 邮箱确认
     * @param email
     * @param expired
     * @return
     */
    @ApiOperation("用户激活 邮箱确认")
    @ResponseBody
    @RequestMapping(value = "/register/active", method = RequestMethod.POST)
    public ApiResponse validateRegisterActive(
            @ApiParam(value = "邮箱", required = true)
            @RequestParam String email,
            @ApiParam(value = "过期时间", required = true)
            @RequestParam String expired) {
        Long expiredTime = Long.parseLong(Base64.decodeStr(expired));
        ApiResponse apiResponse;
        if (expiredTime < System.currentTimeMillis()) {
            apiResponse = ApiResponse.getErrResponse("链接已过期，请重新生成");
        } else {
            email = Base64.decodeStr(email);
            if (!StringUtils.isEmpty(email)) {
                apiResponse = ApiResponse.getSuccResponse("恭喜你，激活成功!");
            } else {
                apiResponse = ApiResponse.getErrResponse("该邮箱没有被注册， 请不要修改邮件中的参数！");
            }
        }
        return apiResponse;
    }


    public static void main(String[] args) {
//        EmailController.class.getClassLoader().getResourceAsStream("/WEB-INF/email/index.html");
    }
}
