https://lucaponzanelli.gitlab.io/assets/publications/Mast2015a.pdf

## How can we get the memory address of an Object?
https://jrebel.com/rebellabs/dangerous-code-how-to-be-unsafe-with-java-classes-objects-in-memory/4/

## how-to-write-portable-and-production
https://dzone.com/articles/safe-unsafe-how-to-write-portable-and-production-q

We know that there is a variable called _mark that is the first variable in the instance of 
an object and it is followed by a pointer to the class instance itself which is called  _metadata
  
The comments section of klass.hpp shows  
Klass layout:  
    [header        ] klassOop  
    [klass pointer ] klassOop  
    [C++ vtbl ptr  ] (contained in Klass_vtbl)  
    [layout_helper ]  
    [super_check_offset] for fast subtype checks  
    
The field we are most interested in here is the fourth field, layout_helper, which stores the size 
of an object if it’s not an array. For an array, it stores structural information about the array. 
The JVM reads this field to know the amount of memory to allocate for new object instances, 
and we will do so too.