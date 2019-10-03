package com.gelerion.unsafe.usage;

import com.gelerion.unsafe.domain.SampleClass;

import static com.gelerion.unsafe.TheUnsafe.unsafe;

public class GettingMemoryAddressOfClass {

    public static void main(String[] args) {

    }

    //https://jrebel.com/rebellabs/dangerous-code-how-to-be-unsafe-with-java-classes-objects-in-memory/3/
    public void firstMethod() {
        /*
        In the JVM, every object has a pointer to its class, but only to its concrete class and not
        to its interface or abstract class. If we get the memory address of an object,
        we can get the address of its class easily.

        For 32 bit JVM:
            _mark	: 4 byte constant
            _klass	: 4 byte pointer to class

        For 64 bit JVM:
            _mark	: 8 byte constant
            _klass	: 8 byte pointer to class

        For 64 bit JVM with compressed-oops:
            _mark	: 8 byte constant
            _klass	: 4 byte pointer to class

         The second field in the object layout in memory (for a 32-bit JVM, the offset is 4,
         for a 64-bit JVM offset is 8 from the address of an object in memory) points to the class definition
         of object in memory. For getting a value at this offset, you can use “sun.misc.Unsafe” class.
         The SampleClass used here is listed in the section above.
         */

        SampleClass sampleClassObject = new SampleClass();
        //32-bit JVM:
        int addressOfSampleClass_32 = unsafe().getInt(sampleClassObject, 4L);
        //64-bit JVM
        int addressOfSampleClass_64 = unsafe().getInt(sampleClassObject, 8L);
        //64 bit JVM with compressed-oops
        int addressOfSampleClass_64_comp = unsafe().getInt(sampleClassObject, 8L);
    }

}
