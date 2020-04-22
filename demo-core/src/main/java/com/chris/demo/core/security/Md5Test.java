package com.chris.demo.core.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * todo
 *
 * @ClassName Md5Test
 * @Author jlhe
 * @Date 2019/7/24
 * @Version 1.0
 */
public class Md5Test {

    public static String string2MD5(String str) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        char[] charArray = str.toCharArray();
        byte[] bytes = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            bytes[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(bytes);
        StringBuilder hexValue = new StringBuilder();
        for (int i = 0; i < md5Bytes.length; i++) {
            System.out.println("num= " + md5Bytes[i] + " 字节码: " + Integer.toBinaryString(md5Bytes[i]));
            int val = (int) md5Bytes[i] & 0xff;
            System.out.println("&计算后 num= " + val + " 字节码: " + Integer.toBinaryString(val));

            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
            System.out.println(Integer.toHexString(val));
        }


        return hexValue.toString();

    }

    public static void main(String[] args) throws NoSuchAlgorithmException {


        String md5 = Md5Test.string2MD5("你好中国");
        System.out.println(md5);

    }
}
