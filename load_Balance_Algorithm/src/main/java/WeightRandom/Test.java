package WeightRandom;

/**
 * Created by Jo on 12/9/16.
 */
public class Test {
	public static void main(String[] args){
		Runnable runnable = new Runnable() {
			public void run() {
				System.out.println(WeightRandom.testWeightRandom());
			}
		};
		for(int i =0;i<100;i++){
			Thread thread = new Thread(runnable);
			thread.run();
		}
	}
}
