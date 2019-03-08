package com.chris.demo.core;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * todo
 *
 * @ClassName StringTests
 * @Author jlhe
 * @Date 2019/2/23
 * @Version 1.0
 */
public class StringTests {

    @Test
    public void test() {
        int[] ints = twoSum(new int[]{0,4, 3, 0}, 0);
        System.out.println(ints[0]);
        System.out.println(ints[1]);

    }

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if (n < 2)
            return n;
        String currStr = "";
        int record = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int index = currStr.indexOf(c);
            if (index >= 0) {
                currStr = currStr.substring(index + 1) + c;
            } else {
                currStr = currStr + c;
            }
            record = Math.max(currStr.length(), record);
        }
        return record;
    }

    public int[] twoSum(int[] nums, int target) {
        int[] map = new int[ Math.max(target,nums.length)+1];
        int index;
        for (int i = 0; i < nums.length; i++) {
            index = nums[i]; //
            if (index > target) {
                continue;
            }
            if (map[index] != 0) {
                return new int[]{map[index] - 1, i};
            } else {
                map[(target - index)] = i + 1;
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}




