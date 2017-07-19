package com.andx.micro.permission.tmp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andongxu on 17-7-6.
 */
public class Tmp {

    public static void main(String[] args) {
        List<String> a = new ArrayList<String>();
        a.add("1");
        a.add("2");
        a.add("3");
        for (String s : a) {
//            a.remove(s);
        }
        a.remove("1");
    }
}
