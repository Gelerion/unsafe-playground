package com.gelerion.unsafe.risks;

import com.gelerion.unsafe.TheUnsafe;
import sun.misc.Unsafe;

public class MonitorDeadlock {

    private boolean c = true;

    /**
     * Java provides synchronized methods and synchronized blocks.
     * These constructs guarantee that monitors entered at the beginning of a section of code are exited at the end. Unsafe
     * can violate that contract: it can be used to asymmetrically
     * enter or exit a monitor, and that asymmetry might be not
     * immediately obvious.
     */
    public static void main(String[] args) throws Exception {
    }

    private final Object o = new Object();

    void m() {
        Unsafe unsafe = TheUnsafe.get();

        unsafe.monitorEnter(o);
        if (c) return;
        unsafe.monitorExit(o);
    }

}
