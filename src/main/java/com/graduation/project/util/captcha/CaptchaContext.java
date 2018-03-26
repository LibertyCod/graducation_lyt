package com.graduation.project.util.captcha;

import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Binbin Wang
 * @date 2018/3/9
 */
public class CaptchaContext {

    private static final ConcurrentHashMap<String, Object> captchaM = new ConcurrentHashMap<>();

    private static final Queue<String> captchaQ = new LinkedBlockingQueue<>();

    private static final Long EXIST_MILLS_TIME = 1 * 60 * 1000L;

    public static Object get(String key) {
        return captchaM.get(key);
    }

    public static void put(String key, Object o) {
        captchaM.putIfAbsent(key, o);
        Long expiredTime = System.currentTimeMillis() + EXIST_MILLS_TIME;
        captchaQ.add(key + "|" + String.valueOf(expiredTime));
    }

    public static void remove(String key) {
        captchaM.remove(key);
    }

    public static ConcurrentHashMap<String, Object> getCaptchaM() {
        return captchaM;
    }

    public static Queue<String> getCaptchaQ() {
        return captchaQ;
    }
}
