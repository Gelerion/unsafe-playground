package com.gelerion.unsafe.risks;

import com.gelerion.unsafe.TheUnsafe;
import sun.misc.Unsafe;

public class ViolatingMethodContracts {

    /**
     * In Java, a method that does not declare an exception cannot
     * throw any checked exceptions. Unsafe can violate that contract: it can be used to throw a checked exception that the
     * surrounding method does not declare or catch.
     */
    public static void main(String[] args) {
        new ViolatingMethodContracts().m();
    }

    void m() {
        Unsafe unsafe = TheUnsafe.get();
        unsafe.throwException(new Exception());
    }
}
