package com.andx.micro.api.core;

import java.lang.annotation.*;

/**
 * Created by andongxu on 16-8-31.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Service {

    /**
     * 资源路径
     * @return
     */
    String path() default "";

    /**
     * GET
     * POST
     * PUT
     * DELETE
     * PATCH
     * @return
     */
    MethodType method() default MethodType.GET;

    /**
     * 代码
     * @return
     */
    String code() default "";

    /**
     * 名称
     */
    String name() default "";

    /**
     * 系统
     * @return
     */
    String system() default "";

    /**
     * 模块
     * @return
     */
    String module() default "";

    /**
     * 服务所属组
     * @return
     */
    String group() default "";

}
