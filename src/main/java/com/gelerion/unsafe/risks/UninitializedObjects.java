package com.gelerion.unsafe.risks;

import com.gelerion.unsafe.TheUnsafe;
import sun.misc.Unsafe;

public class UninitializedObjects {

    public static void main(String[] args) throws Exception {
        Unsafe unsafe = TheUnsafe.get();

        D d = (D) unsafe.allocateInstance(D.class);
        System.out.println(d.f); //0

    }

    static class D {
        private int f;
        public D() {
            f = 5;
        }

        public int getF() {
            return f;
        }
    }
}
