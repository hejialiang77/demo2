package com.chris.demo.core.concurrent;

class Scratch {
    public static void main(String[] args) {
        int n = 33;
        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toBinaryString(n >>> 1));
        n |= n >>> 1;
        System.out.println(Integer.toBinaryString(n));
        System.out.println( "--------------------------");
        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toBinaryString(n >>>2));
        n |= n >>> (1<<1);
        System.out.println(Integer.toBinaryString(n));
        System.out.println( "--------------------------");
        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toBinaryString(n >>> (1<<2)));
        n |= n >>> (1<<2);
        System.out.println(Integer.toBinaryString(n));
        System.out.println( "--------------------------");
        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toBinaryString(n >>> (1<<3)));
        n |= n >>> (1<<3);
        System.out.println(Integer.toBinaryString(n));
        System.out.println( "--------------------------");
        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toBinaryString(n >>> (1<<4)));
        n |= n >>> (1<<4);
        System.out.println(Integer.toBinaryString(n));
    }
}