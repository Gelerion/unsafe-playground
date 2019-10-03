package com.gelerion.unsafe.risks;

import com.gelerion.unsafe.TheUnsafe;
import sun.misc.Unsafe;

public class ViolatingTypeSafety {

    /**
     * In Java, variables are strongly typed. For example, it is impossible to store
     * an int value inside a variable of a reference type. Unsafe can violate that guarantee:
     *  it can be used to store a value of any type in a field or array element.
     */
    public static void main(String[] args) throws Exception {
        Unsafe unsafe = TheUnsafe.get();
        long fieldOffset = unsafe.objectFieldOffset(C.class.getDeclaredField("f"));
        C o = new C();
        unsafe.putInt(o, fieldOffset, 123456);

    }

    static class C {
        private Object f = new Object();
    }

}
