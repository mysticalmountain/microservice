package com.andx.micro.core.util;


import com.andx.micro.api.core.dto.Response;

/**
 * Created by andongxu on 17-2-3.
 */
public class Constant {

    public static final String CODE_SUCCESS = "000000";
    public static final String CODE_UNKNOWN = "999999";

    public static final String MSG_SUCCESS = "success";
    public static final String MSG_FAIL = "fail";
    public static final String MSG_UNKNOWN = "unknown error";

    public static Response RESPONSE_SUCCESS = new Response(CODE_SUCCESS, MSG_SUCCESS, true);
    public static Response RESPONSE_FAIL = new Response(CODE_UNKNOWN, MSG_UNKNOWN, false);

    public static final String KEY_REQUEST_ID = "requestId";
    public static final String KEY_EXECUTE_ID = "executeId";
    public static final String KEY_CHANNEL_ID = "channelId";
    public static final String KEY_HTTP_SERVLET_REQUEST = "httpServletRequest";
    public static final String KEY_PRAMS = "prams";


}
