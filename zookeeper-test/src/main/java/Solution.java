/**
 * Created by Jo on 11/22/16.
 */
public class Solution {
	public int minMoves(int[] nums) {
		int times =0;

		return times(nums,times);

	}
	private int times(int[] nums,int times){
		int max = 0;
		int tmp = nums[0];
		int index = 0;
		int count =0;
		for(int i=0;i<nums.length;i++){
			if(nums[i]>max){
				max = nums[i];
				index = i;
			}
			//表示有不同的元素
			if(tmp!=nums[i]){
				count++;
			}
		}
		if(count>0){
			times++;
			for(int i=0;i<nums.length;i++){
				if(i!=index){
					nums[i]++;
				}
			}
			return times(nums,times);
		}
		else{
			return times;
		}
	}

	public static void main(String[] args){
		Solution solution = new Solution();
		int[] a = {1,21474836};
		System.out.println(solution.minMoves(a));
	}
}