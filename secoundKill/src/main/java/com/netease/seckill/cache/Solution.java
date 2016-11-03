package com.netease.seckill.cache;

/**
 * Created by Jo on 10/29/16.
 */
public class Solution {
	public ListNode deleteDuplicates(ListNode head) {
		if(head == null){
			return null;
		}
		if(head.next == null){
			return head;
		}
		ListNode tmp = new ListNode(0);
		tmp.next = head;
		while(head.next != null){
			if(head.next.val == head.val){
				head.next = head.next.next;
			}
			else {
				head = head.next;
			}
		}
		return tmp.next;
	}



	public static void main(String[] args){
		ListNode listNode = new ListNode(1);
		listNode.next = new ListNode(1);
		listNode.next.next = new ListNode(1);
		Solution solution = new Solution();
		System.out.println(solution.deleteDuplicates(listNode));
	}
}