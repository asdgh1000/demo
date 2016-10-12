package ioc.Programmatic;

import com.sun.tools.hat.internal.util.ArraySorter;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Jo on 10/9/16.
 */
public class Solution {
    public String addStrings(String num1, String num2) {
		if(num1.length()==1 && num2.length()==1){
			return (num1.toCharArray()[0]-'0' + num2.toCharArray()[0]-'0')+"";
		}
		int x = num1.length()-num2.length();
		String a2 = "";
		String b2 = "";
		if(x>0){
			StringBuffer a1 = new StringBuffer();
			for(int i=0;i<x;i++) {
				a1.append("0");
			}
			a2 = a1.toString()+num2;
			b2 = num1;
		}
		else {
			StringBuffer b1 = new StringBuffer();
			for(int i=0;i<-x;i++){
				b1.append("0");
			}
			b2 = b1.toString()+num1;
			a2 = num2;
		}
		char[] a = a2.toCharArray();
		char[] b = b2.toCharArray();
		StringBuffer z = new StringBuffer();
		int[] sum = new int[a.length+1];
		for(int i =a.length-1;i>=0;i--){
			if(sum[i+1]+(a[i]-'0')+(b[i]-'0')>9){
				sum[i+1]+=(a[i]-'0')+(b[i]-'0');
				sum[i]++;
				sum[i+1]=sum[i+1]%10;
			}
			else{
				sum[i+1] = sum[i+1]+(a[i]-'0')+(b[i]-'0');
			}
		}
		for(int f : sum){
			z.append(f);
		}
		String back = z.toString().replaceFirst("^0*","");
		return back;
    }
	public static void main(String[] args){
		Solution solution = new Solution();
		System.out.println(solution.addStrings("99", "9"));
	}
}
