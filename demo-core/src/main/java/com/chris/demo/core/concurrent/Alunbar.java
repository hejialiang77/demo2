package com.chris.demo.core.concurrent;

import java.lang.reflect.Modifier;

public class Alunbar {
    public static void  main(String arts[]){
        Class alunbarClass = Alunbar.class;
        System.out.println(alunbarClass.getModifiers());
        System.out.println(Modifier.isPublic(alunbarClass.getModifiers()));
        System.out.println(alunbarClass.toGenericString());

        Class birdClass = Bird.class;
        System.out.println(birdClass.getModifiers());
        System.out.println(Modifier.isPublic(birdClass.getModifiers()));

    }

    private class Bird{

    }
}
