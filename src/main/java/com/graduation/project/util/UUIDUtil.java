package com.graduation.project.util;

import java.util.UUID;

/**
 * @author Binbin Wang
 * @date 2018/3/9
 */
public class UUIDUtil {

    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
