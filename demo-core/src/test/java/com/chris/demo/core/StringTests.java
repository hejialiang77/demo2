package com.chris.demo.core;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
		int[] ints = twoSum(new int[]{0, 4, 3, 0}, 0);
		System.out.println(ints[0]);
		System.out.println(ints[1]);

	}

	public int[] twoSum(int[] nums, int target) {
		int[] map = new int[Math.max(target, nums.length) + 1];
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

	@Test
	public void testLengthOfLongestSubstring() {
		String s = "123123412345";
		int result = lengthOfLongestSubstring(s);
		System.out.println(result);
	}

	/**
	 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
	 *
	 * @param s
	 * @return
	 */
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


	/**
	 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
	 * <p>
	 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
	 * <p>
	 * 您可以假设除了数字 0 之外，这两个数都不会以 0开头。
	 * <p>
	 * 示例：
	 * <p>
	 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
	 * 输出：7 -> 0 -> 8
	 * 原因：342 + 465 = 807
	 *
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode dummyHead = new ListNode(0);
		int carry = 0;
		while (l1 != null || l2 != null) {
			int x = (l1 != null) ? l1.val : 0;
			int y = (l2 != null) ? l2.val : 0;
			int sum = carry + x + y;
			carry = sum / 10;
            dummyHead.next = new ListNode(sum % 10);
            dummyHead = dummyHead.next;
			if (l1 != null) l1 = l1.next;
			if (l2 != null) l2 = l2.next;
		}
		if (carry > 0) {
            dummyHead.next = new ListNode(carry);
		}
		return dummyHead.next;
	}

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}


	@Test
	public void findMedianSortedArraysTest() {
		int[] nums2 = {1, 2, 3, 4, 5, 6};
		int[] nums1 = {7, 8, 9, 12};
		System.out.println(findMedianSortedArrays(nums1, nums2));
	}

	/**
	 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
	 * <p>
	 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
	 * <p>
	 * 你可以假设 nums1 和 nums2 不会同时为空。
	 *
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int n = nums1.length;
		int m = nums2.length;
		int left = (n + m + 1) / 2;
		int right = (n + m + 2) / 2;
		//将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
		return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
	}

	private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
		int len1 = end1 - start1 + 1;
		int len2 = end2 - start2 + 1;
		//让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
		if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
		if (len1 == 0) return nums2[start2 + k - 1];

		if (k == 1) return Math.min(nums1[start1], nums2[start2]);

		int i = start1 + Math.min(len1, k / 2) - 1;
		int j = start2 + Math.min(len2, k / 2) - 1;

		if (nums1[i] > nums2[j]) {
			return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
		} else {
			return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
		}
	}


	@Test
	public void longestPalindromeTest() {
		String res = longestPalindrome("asdfaseeeesrwqerqwsdafca");
		System.out.println(res);
	}

	/**
	 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
	 *
	 * @param s
	 * @return
	 */
	public String longestPalindrome(String s) {
		String result = "";
		String curr;
		if (s.length() < 2) {
			return s;
		}
		for (int i = 1; i < s.length(); i++) {
			curr = extendStr(s, i - 1, i + 1);
			if (s.charAt(i - 1) == s.charAt(i)) {
				if (extendStr(s, i - 1, i).length() > curr.length()) {
					curr = extendStr(s, i - 1, i);
				}
			}
			if (curr.length() > result.length()) {
				result = curr;
			}
		}
		return result;
	}

	public String extendStr(String s, int begin, int end) {
		if (begin < 0 || end == s.length()) {
			return s.substring(begin + 1, end);
		}
		if (s.charAt(begin) == s.charAt(end)) {
			//扩大范围
			return extendStr(s, begin - 1, end + 1);
		} else {
			return s.substring(begin + 1, end);
		}
	}


	@Test
	public void convertTest() {
		System.out.println(Integer.MAX_VALUE);

//		String res = convert("123456789", 3);
//		String res = convert2("PAYPALISHIRING", 3);
		String res = convert2("123456789", 4);
		System.out.println(res);
	}

	/**
	 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
	 *
	 * @param s
	 * @param numRows
	 * @return
	 */
	public String convert(String s, int numRows) {
		if (numRows == 1 || s.length() < 3) {
			return s;
		}
		List<StringBuilder> list = new ArrayList<>();
		for (int i = 0; i < Math.min(numRows, s.length()); i++)
			list.add(new StringBuilder());
		for (int i = 0; i < s.length(); i++) {
			int key = i % (2 * numRows - 2);
			if (key >= numRows) {
				key = numRows - 1 - (key + 1 - numRows);
			}
			list.get(key).append(s.charAt(i));
		}
		StringBuilder sb = new StringBuilder();
		for (StringBuilder stringBuilder : list) {
			sb.append(stringBuilder);
		}
		return sb.toString();

	}

	/**
	 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
	 *
	 * @param s
	 * @param numRows
	 * @return
	 */
	public String convert2(String s, int numRows) {
        //长度
        if (s.length() <= 2 || numRows == 1) {
            return s;
        }
        int offset = 0;
        int tmp = (numRows - 1) * 2;
        List<StringBuilder> target = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            offset = i % tmp;
            //初始化  4<= 4 < 6
            if (offset >= numRows) {
                offset = tmp - offset;
            }
            System.out.println(offset);
            //这里初始化
            if (offset > target.size() - 1) {
                target.add(new StringBuilder());
            }
            target.get(offset).append(s.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        for (StringBuilder stringBuilder : target) {
            sb.append(stringBuilder);
        }
        return sb.toString();

	}

	@Test
	public void findTargetTest() {

		findTarget(7, new int[][]{{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}});
	}

	public boolean findTarget(int target, int[][] array) {
		int h = array.length;
		if (h == 0) {
			return false;
		}
		int w = array[0].length;
		if (w == 0) {
			return false;
		}
		int x, y;
		x = h - 1;
		y = 0;
		while (y < w && x >= 0) {
			if (array[x][y] == target) {
				return true;
			} else if (array[x][y] > target) {
				x--;
			} else {
				y++;
			}
		}
		return false;
	}


	@Test
	public void printListFromTailToHeadTest() {
		ListNode listNode = new ListNode(1);

		printListFromTailToHead(listNode);
	}


	public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
		ArrayList<Integer> result = new ArrayList<>();
		while (listNode != null) {
			result.add(0,listNode.val);
			listNode = listNode.next;
		}
		return result;
	}



	@Test
	public void testFindKthToTail() {
		reOrderArray(new int[]{1, 2, 4, 3, 6, 5, 7});
		ListNode head = new ListNode(1);
		ListNode curr=head;
		curr.next = new ListNode(2);
		curr = curr.next;
		curr.next = new ListNode(3);
		curr = curr.next;
		curr.next = new ListNode(4);
		curr = curr.next;
		curr.next = new ListNode(5);
		findKthToTail(head,1);
	}


	public void reOrderArray(int [] array) {
		if(array==null || array.length ==0){
			return ;
		}
		int m = 0;
		for(int i=0;i<array.length;i++){
			if((Math.abs(array[i])&1)!= 0){
				//如果是奇数 尝试往前走
				int tmp = array[i];
				int j = i;
				while (j > m) {
					array[j] = array[j-1];
					j--;
				}
				//记录下位置
				m = j+1;
				array[j] = tmp;

			}
		}
	}

	public ListNode findKthToTail(ListNode head,int k) {

		int i =0;
		ListNode curr = head;
		if (curr == null) {
			return new ListNode(0);
		}
		java.util.ArrayList<ListNode> list = new java.util.ArrayList<>();
		list.add(curr);
		while(curr.next !=null){
			curr=curr.next;
			list.add(curr);
		}
		return list.get(list.size()-k);
	}



	@Test
	public void test1() {
		ListNode node = new ListNode(0);
		int i = 0;
		add(node,i);
		System.out.println(node.val);
		System.out.println(i);
	}

	private void add(ListNode node,int i) {

		node.val ++;
		i++;
		System.out.println(node.val);
		System.out.println(i);

	}
}




