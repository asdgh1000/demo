package Hash;

/**
 * Created by Jo on 12/9/16.
 */
public class Test {
	public static void main(String[] args){
		HashMethod hashMethod  = new HashMethod();
		Runnable runnable = new Runnable() {
			public void run() {
				System.out.println(HashMethod.testConsumerHash("127.0.0.1"));
			}
		};
		for(int i =0;i<100;i++){
			Thread thread = new Thread(runnable);
			thread.run();
		}
	}
}
