package com.gelerion.unsafe.risks;

import com.gelerion.unsafe.TheUnsafe;
import sun.misc.Unsafe;

public class CrashingTheVM {

    /**
     * A quick way to crash the VM is to free memory that
     * is in a protected address range, for example by calling
     * freeMemory as follows.
     *
     * In Java, the normal behavior of a method to deal with such
     * situations is to throw an exception. Being unsafe, instead
     * of throwing an exception, this invocation of freeMemory
     * crashes the VM.
     */
    public static void main(String[] args) throws Exception {
        Unsafe unsafe = TheUnsafe.get();
        unsafe.freeMemory(1);
    }
}
