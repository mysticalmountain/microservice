package com.andx.micro.core.util;

import java.io.*;

/**
 * Created by andongxu on 9/23/16.
 */
public final class ObjectSerializeUtil {

    public static byte[] serializeObject(Object object) throws IOException {
        ByteArrayOutputStream saos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(saos);
        oos.writeObject(object);
        oos.flush();
        return saos.toByteArray();
    }

    public static <C> C deserializeObject(byte[]buf, Class<C> c) throws IOException, ClassNotFoundException{
        ByteArrayInputStream sais=new ByteArrayInputStream(buf);
        ObjectInputStream ois = new ObjectInputStream(sais);
        return (C) ois.readObject();
    }
}
