package com.chris.demo.core.serialize;

/**
 * todo
 *
 * @ClassName StringTest
 * @Author jlhe
 * @Date 2020/9/23
 * @Version 1.0
 */
public class StringTest {


    public static void main(String[] args) {
        String s1 = "Chris";
        String s2 = new String("Chris");
        String s3 = new String("Chris").intern();

        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        
    }
}
