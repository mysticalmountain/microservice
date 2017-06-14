package com.andx.micro.core.util;


import com.andx.micro.api.core.dto.Response;

/**
 * Created by andongxu on 17-2-3.
 */
public class Constant {

    public static String CODE_SUCCESS = "000000";
    public static String CODE_UNKNOWN = "999999";

    public static String MSG_SUCCESS = "success";
    public static String MSG_FAIL = "fail";
    public static String MSG_UNKNOWN = "unknown error";

    public static Response RESPONSE_SUCCESS = new Response(CODE_SUCCESS, MSG_SUCCESS, true);
    public static Response RESPONSE_FAIL = new Response(CODE_UNKNOWN, MSG_UNKNOWN, false);




}
