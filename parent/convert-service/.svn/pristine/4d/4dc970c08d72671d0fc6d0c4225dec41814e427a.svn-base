package com.soholy.utils;

import java.io.Serializable;
import java.util.*;

/**
 * returning
 */
public class R extends LinkedHashMap<String, Object> implements Serializable {

    private static final long serialVersionUID = 1L;

    private static List<String> KEYS = null;

    static {
        KEYS = new ArrayList<>();
        /**
         * 返回码
         */
        KEYS.add("code");
        /**
         * 返回消息
         */
        KEYS.add("msg");
        /**
         * 返回数据
         */
        KEYS.add("obj");


        /**
         * 返回数据-其他
         */
        KEYS.add("ext");

        /**
         * 返回结果
         */
        KEYS.add("success");
    }


    public R() {
        put("code", 0);
        put("msg", "success");
    }


    public static R error() {
        return error(1, "failure", null);
    }

    public static R error(String msg) {
        return error(500, msg);
    }

    public static R error(int code, String msg) {
        return error(code, msg, null);
    }

    public static R error(int code, String msg, Object obj) {
        return error(code, msg, obj, null);
    }

    public static R error(int code, String msg, Object obj, Object extData) {
        return result(code, msg, obj, extData, false);
    }

    public static R result(int code, String msg, Object obj, Object extData, boolean success) {
        List<Object> values = Arrays.asList(code, msg, obj, extData, success);
        R r = new R();
        for (int i = 0; i < KEYS.size(); i++) {
            r.put(KEYS.get(i), values.get(i));
        }
        return r;
    }


    public static R ok(Map<String, Object> map) {
        return result(200, null, map, null, true);
    }

    public static R ok() {
        return ok("success");
    }

    public static R ok(Object obj) {
        return ok(200, "success", obj, null);
    }

    public static R ok(int code, Object obj) {
        return ok(code, "success", obj, null);
    }

    public static R ok(String msg, Object obj) {
        return ok(200, msg, obj, null);
    }


    public static R ok(String msg) {
        return ok(200, msg, null, null);
    }

    public static R ok(int code, String msg) {
        return ok(code, msg, null, null);
    }

    public static R ok(int code, String msg, Object obj) {
        return ok(code, msg, obj, null);
    }

    public static R ok(int code, String msg, Object obj, Object extData) {
        return result(code, msg, obj, extData, true);
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
