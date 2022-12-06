package com.testehan.security;


import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class PersonTest {

    @Test
    void encapsulationSerialization() throws IOException, ClassNotFoundException {
        Collection<String> shirts = new ArrayList<>();
        shirts.add("blue");
        shirts.add("yellow");
        Person p = new Person("Dan",shirts);
        System.out.println(p);

        ByteArrayOutputStream boas = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(boas)){
            oos.writeObject(p);
        }

        byte[] bytes = boas.toByteArray();
        bytes[190] = 12; // one could modify the age to be a restricted/unwanted one

        try(ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes))){
            Person person = (Person)ois.readObject();

            System.out.println(person); // as you can see the 2 shirt was altered to be "ellow" instead of "yellow"
        }

    }


    @Test
    void ObjectStreamField() throws IOException, ClassNotFoundException {
        Collection<String> shirts = new ArrayList<>();
        shirts.add("blue");
        shirts.add("yellow");
        Person p = new Person("Dan",shirts);
        System.out.println(p);

        ByteArrayOutputStream boas = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(boas)){
            oos.writeObject(p);
        }

        byte[] bytes = boas.toByteArray();

        try(ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes))){
            Person person = (Person)ois.readObject();

            System.out.println(person); // as you can see since only the "name" field was specified in the Person in
            // ObjectStreamField ...only that fields gets serialized...all others are left outside
        }

    }
}
