package com.andx.micro.user.anno;

import com.andx.micro.api.core.Service;
import org.junit.Before;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by andongxu on 17-6-12.
 */
@Service
public class Tmp {

    @Service
    public void fun1() {

    }

    @Deprecated
    @Transactional
    public void fun2() {

    }

    public void fun3() {

    }

    public static void main(String [] args) {
        Tmp tmp = new Tmp();
        Annotation [] annotations = tmp.getClass().getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation.toString());
        }
        Method [] methods = tmp.getClass().getMethods();
        for (Method method : methods) {
            Annotation [] ans = method.getAnnotations();
            for (Annotation an : ans) {
                System.out.println("method " + method.getName() + "\t" + an.toString());
            }
        }
        System.out.println(tmp);
    }
}
