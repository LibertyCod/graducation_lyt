package com.graduation.project.schedule;

import com.graduation.project.util.captcha.CaptchaContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Binbin Wang
 * @date 2018/3/9
 */
@Component
@EnableScheduling
public class CaptchaContextSchedule {

    @Scheduled(cron = "0 0/1 * * * ?")
    public void cleanContext() {
        Long expiredTime;
        Long currentTime = System.currentTimeMillis();
        for (String keyAndTime : CaptchaContext.getCaptchaQ()){
            expiredTime = Long.parseLong(keyAndTime.split("\\|")[1]);
            if (expiredTime <= currentTime) {
                CaptchaContext.remove(keyAndTime.split("\\|")[0]);
                CaptchaContext.getCaptchaQ().remove(keyAndTime);
            }
        }
    }

}
