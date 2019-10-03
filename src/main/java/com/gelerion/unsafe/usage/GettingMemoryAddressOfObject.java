package com.gelerion.unsafe.usage;

import com.gelerion.unsafe.domain.SampleClass;

import static com.gelerion.unsafe.TheUnsafe.unsafe;

public class GettingMemoryAddressOfObject {

    //https://jrebel.com/rebellabs/dangerous-code-how-to-be-unsafe-with-java-classes-objects-in-memory/4/
    public static void main(String[] args) {
        SampleClass targetObject = new SampleClass();
        Object[] helperArray = new Object[1];
        helperArray[0] = targetObject;
        int baseOffset = unsafe().arrayBaseOffset(Object[].class);
        int addressOfObject = unsafe().getInt(helperArray, baseOffset);
        System.out.println(addressOfObject);
    }

    /*
    Class Memory Layout

    32-bit JVM

	[header                ] 4  byte
	[klass pointer         ] 4  byte (pointer)
	[C++ vtbl ptr          ] 4  byte (pointer)
	[layout_helper         ] 4  byte
	[super check offset    ] 4  byte
	[name                  ] 4  byte (pointer)
	[secondary super cache ] 4  byte (pointer)
	[secondary supers      ] 4  byte (pointer)
	[primary supers        ] 32 byte (8 length array of pointer)
	[java mirror           ] 4  byte (pointer)
	[super                 ] 4  byte (pointer)
	[first subklass        ] 4  byte (pointer)
	[next sibling          ] 4  byte (pointer)
	[modifier flags        ] 4  byte
	[access flags          ] 4  byte


    64-bit JVM

	[header                ] 8  byte
	[klass pointer         ] 8  byte (4 byte for compressed-oops)
	[C++ vtbl ptr          ] 8  byte (4 byte for compressed-oops)
	[layout_helper         ] 4  byte
	[super check offset    ] 4  byte
	[name                  ] 8  byte (4 byte for compressed-oops)
	[secondary super cache ] 8  byte (4 byte for compressed-oops)
	[secondary supers      ] 8  byte (4 byte for compressed-oops)
	[primary supers        ] 64 byte (32 byte for compressed-oops)
                                         {8 length array of pointer}
	[java mirror           ] 8  byte (4 byte for compressed-oops)
	[super                 ] 8  byte (4 byte for compressed-oops)
	[first subklass        ] 8  byte (4 byte for compressed-oops)
	[next sibling          ] 8  byte (4 byte for compressed-oops)
	[modifier flags        ] 4  byte
	[access flags          ] 4  byte
     */

    /*
    header always has the constant value of “0x00000001”.

    klass pointer
    is the pointer (0x38970v8a8 for both of classes) to definition of java.lang.Class class in memory,
    since this memory structure refers to a class.

    C++ vtbl ptr is the pointer to the virtual table definition structure of a defined class.
    A virtual table is a mechanism used to support dynamic dispatch, which is the process of selecting which
    implementation of a polymorphic method to call at runtime.

    layout helper
    refers to the shallow size of the instance. This size is calculated by considering the field layout alignment
    mechanism of the JVM. In our environment, object alignment size is 8 bytes.

    super is the pointer to cthe lass definition of a super class, which is SampleBaseClass in our example.
    In this example, the value of this field is 0x34104b70 , which is address of the class definition of
    SampleBaseClass as you can see. For the SampleBaseClass class, this field value is 0x38970000 which
    is the address of java.lang.Object class. Because in Java, every class is a subclass of the Object class ☺.

    modifier flags
    refer to the Java class modifiers which could be
    “public”, “protected”, “private”, “abstract”, “abstract”, “static”, “final” and “strictfp”.
    The “modifier flags” value of class is calculated by operating “Bitwise OR” operation on modifier values of target class.
    In our example, SampleClass class is “public” and “final” class. Hence “modifier flags” value for this
    class is calculated as “0x00000001 | 0x00000010 = 0x00000011 ”. SampleBaseClass is only “public” class
    and so its “modifier flags” value is “ 0x00000001 ”. The values for the modifiers are listed below.
     */
}
