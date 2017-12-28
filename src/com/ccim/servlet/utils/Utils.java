package com.ccim.servlet.utils;

public class Utils {
	/**
     * 如果这个字符串为空 返回:true
     */
    public static boolean isNull(String str) {
        if ("".equals(str) || str == null || "".equals("null")) {
            return true;
        }
        return false;
    }
}
