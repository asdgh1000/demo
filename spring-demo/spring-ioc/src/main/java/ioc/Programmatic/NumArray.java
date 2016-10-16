package ioc.Programmatic;

/**
 * Created by Jo on 10/11/16.
 */
public class NumArray {
	private int[] nums;
	public NumArray(int[] nums) {
		this.nums = nums;
		int length = nums.length;
		for (int i = 1; i < length; i++) {
			nums[i] += nums[i - 1];
		}
	}

	public int sumRange(int i, int j) {
		if (i == 0) return nums[j];

		return nums[j] - nums[i - 1];
	}
	public static void main(String[] args){
		int nums[] = {-2,0,3,-5,2,-1};
		NumArray numArray = new NumArray(nums);
		System.out.println(numArray.sumRange(0,5));
		System.out.println(numArray.sumRange(2,5));
		System.out.println(numArray.sumRange(0,2));

	}
}