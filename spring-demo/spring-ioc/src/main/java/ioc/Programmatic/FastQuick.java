package ioc.Programmatic;

/**
 * Created by Jo on 10/12/16.
 */
public class FastQuick {
	public int[] sort(int[] array,int low,int high){
		int pviot = low;
		int i = low;
		int j = high;
		while(j > i){
			j--;
		}
		if(j>i){
			int tmp = 0;
			tmp = array[high];
			array[high] = array[pviot];
			array[pviot] = tmp;
			pviot = high;
		}
		while(j>i){
			i++;
		}
		if(j>i){
			int tmp = 0;

		}
		return array;
	}
	public static void main(String[] args){

	}
}
