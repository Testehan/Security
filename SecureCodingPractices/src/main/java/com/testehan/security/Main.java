package com.testehan.security;

import java.util.ArrayList;
import java.util.Collection;

public class Main
{
    public static void main( String[] args ) throws CloneNotSupportedException {
        Collection<String> shirts = new ArrayList<>();
        shirts.add("blue");
        shirts.add("yellow");
        Person p = new Person("Dan",shirts);
        System.out.println(p);

        shirts.add("Red");
        System.out.println(p);

        System.out.println("Shirts outside :" + shirts);


        Person p2 = p.clone();
        System.out.println(p2);
    }
}
