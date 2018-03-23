package com.graduation.project.util;

import java.util.UUID;

/**
 * UUIDGenerator
 * @author leejianhao
 * @since 2017-12-18 16:00
 */
public final class UUIDGenerator {

	/**
	 * 生成UUID
	 * @return
	 */
	public static String uuid() {
		String uuid = UUID.randomUUID().toString();
		return uuid.replaceAll("-", "").toUpperCase();
	}

	/**
	 * 生成限定长度的UUID
	 * @param length
	 * @return
	 */
	public static String uuid(int length) {
		String uuid = uuid();
		return uuid.substring(0, length);
	}

}
