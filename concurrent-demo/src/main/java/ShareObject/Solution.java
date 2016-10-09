package ShareObject;

/**
 * Created by Jo on 10/8/16.
 */
public class Solution {
	public int lengthOfLastWord(String s) {
		String[] a = s.split(" ");
		if(a.length>0) {
			String b = a[a.length - 1];
			return b.length();
		}
		else{
			return 0;
		}
	}
	public static void main(String[] args){
		Solution solution = new Solution();
		System.out.println(solution.lengthOfLastWord("   "));
	}
}
