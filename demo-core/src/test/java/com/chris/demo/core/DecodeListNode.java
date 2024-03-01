package com.chris.demo.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author lihh
 */
public class DecodeListNode {



    /*
    有一个排序的链表，删除所有的重复元素，使得每个元素只出现一次，例如
输入：1->1->2，输出： 1->2
输入：1->1->2->3->3，输出：1->2->3
     */

    public String testListNode(ListNode listNode) {

//        ListNode listNode4 = new ListNode("3", null);
//        ListNode listNode3 = new ListNode("2", listNode4);
//        ListNode listNode2 = new ListNode("2", listNode3);
//        ListNode listNode = new ListNode("1", listNode2);
//        ListNode prevNode = new ListNode();
//        prevNode.next = listNode;'
        StringBuffer sb = new StringBuffer();
        while (listNode.getNext() != null) {
            sb.append(listNode.val);
            listNode = compare(listNode, listNode.next);
        }
        return sb.toString();
    }

    private ListNode compare(ListNode curr, ListNode next) {
        if (curr.val.equals(next.val)) {
            curr = next;
            if (curr.next == null) {
                return curr;
            }
            return compare(curr, curr.next);
        }
        return next;
    }

    private ListNode initList(int nums) {
        ListNode curr = null;
        for (int i = nums; i > 0; i--) {
            ListNode tmp = new ListNode(String.valueOf(i), curr);
            curr = tmp;
            int j = (int) (new Date().getTime() % 3);
            for (int k = 0; k < j; k++) {
                tmp = new ListNode(String.valueOf(i), curr);
                curr = tmp;
            }
        }
        return curr;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ListNode {

        String val;

        ListNode next;

    }

}
