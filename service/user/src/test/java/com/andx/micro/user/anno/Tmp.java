package com.andx.micro.user.anno;

import com.andx.micro.api.core.Service;
import org.junit.Before;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URLDecoder;

/**
 * Created by andongxu on 17-6-12.
 */
@Service
public class Tmp {
@Service public void fun1() {

    }

    @Deprecated
    @Transactional
    public void fun2() {

    }

    public void fun3() {

    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "123";
        String [] ss = s.split(",");
        for (String a : ss) {
            System.out.println(a);
        }

    }
}
