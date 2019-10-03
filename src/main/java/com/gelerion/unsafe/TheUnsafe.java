package com.gelerion.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class TheUnsafe {

    private static Unsafe theUnsafe;
    static {
        try {
            Constructor<Unsafe> constructor = Unsafe.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            theUnsafe = constructor.newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Unsafe unsafe = TheUnsafe.get();
        System.out.println("unsafe = " + unsafe);

        System.out.println(unsafe.arrayBaseOffset(byte[].class));
    }


    public static Unsafe get() {
        return theUnsafe;
    }

    public static Unsafe unsafe() {
        return theUnsafe;
    }
}
