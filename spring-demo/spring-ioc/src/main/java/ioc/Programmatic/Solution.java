package ioc.Programmatic;

/**
 * Created by Jo on 10/9/16.
 */
public class Solution {
	public int climbStairs(int n) {
		if(n != 1) {
			int n1 = 1;
			int n2 = 2;
			int a = n - 2;
			while (a-- > 0) {
				int tmp = n1;
				n1 = n2;
				n2 = n2 + tmp;
			}
			return n2;
		}
		else {
			return 1;
		}
	}
	public static void main(String[] args){
		Solution solution = new Solution();
		System.out.println(solution.climbStairs(10));
	}
}
