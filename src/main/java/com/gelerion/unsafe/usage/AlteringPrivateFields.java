package com.gelerion.unsafe.usage;

import com.gelerion.unsafe.TheUnsafe;
import sun.misc.Unsafe;

@SuppressWarnings("all")
public class AlteringPrivateFields {

    public static void main(String[] args) throws Exception {
        Unsafe unsafe = TheUnsafe.get();

        SecretHolder secretHolder = new SecretHolder();

        unsafe.putInt(secretHolder,
                unsafe.objectFieldOffset(SecretHolder.class.getDeclaredField("secret_value")),
                1);

        System.out.println(secretHolder.secretIsDisclosed());
    }

    static class SecretHolder {
        private int secret_value = 0;

        public boolean secretIsDisclosed() {
            return secret_value == 1;
        }
    }
}
