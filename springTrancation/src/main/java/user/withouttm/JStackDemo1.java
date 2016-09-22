package user.withouttm;

/**
 * Created by Jo on 8/23/16.
 */
public class JStackDemo1 {
	public static void main(String[] args) {
		Thread thread = new Thread(new Thread1());
		thread.start();
	}
}
class Thread1 implements Runnable{
	public void run() {
		while(true){
//			System.out.println(1);
		}
	}
}
