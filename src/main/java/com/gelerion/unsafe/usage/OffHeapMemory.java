package com.gelerion.unsafe.usage;

import static com.gelerion.unsafe.TheUnsafe.unsafe;

@SuppressWarnings("all")
public class OffHeapMemory {
    private final static int BYTE = 1;
    private long size;
    private long address;

    public OffHeapMemory(long size) {
        this.size = size;
        address = unsafe().allocateMemory(size * BYTE);
    }

    public void set(long idx, byte value) {
        unsafe().putByte(address + idx * BYTE, value);
    }

    public int get(long idx) {
        return unsafe().getByte(address + idx * BYTE);
    }

    public long size() {
        return size;
    }

    public void freeMemory() {
        unsafe().freeMemory(address);
    }

    public static void main(String[] args) {
        /*
        In the constructor of the OffHeapArray, we're initializing the array that is of a given size.
        We are storing the beginning address of the array in the address field.
        The set() method is taking the index and the given value that will be stored in the array.
        The get() method is retrieving the byte value using its index that is an offset from the start address of the array.
        */

        //long superSize = (long) Integer.MAX_VALUE * 2;
        OffHeapMemory memory = new OffHeapMemory(50);

        //we can put N numbers of byte values into this array and then retrieve those values,
        //summing them up to test if our addressing works correctly
        int sum = 0;
        for (int idx = 0; idx < 100; idx++) {
            memory.set(idx, (byte) 3);
            sum += memory.get(idx);
        }

        System.out.println("sum = " + sum);
    }
}
