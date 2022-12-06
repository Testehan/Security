package com.testehan.security;

import java.io.ObjectStreamField;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Person implements Cloneable, Serializable {

    private final Collection<String> shirts;
    private final String name;

    private int age;

    private static final ObjectStreamField[] serialPersistentFields = {
            new ObjectStreamField("name", String.class)
    };

    public Person(String name, Collection<String> shirts){
        this.name = name;
//        this.shirts = shirts; // this will make our shirts collection modifiable from outside...which is not what we want
        this.shirts = new ArrayList<>(shirts);

        this.age = 10;
        System.out.println("In the constructor");
    }

    @Override
    public String toString() {
        return "Person{" +
                "shirts=" + shirts +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    @Override
    protected Person clone() throws CloneNotSupportedException {
//        return (Person)super.clone(); // this does not call the constructor..hence the object might not get initialized as expected
        return new Person(name, shirts); // better solution is to use a copy constructor than Cloneable or a copy method
    }
}
