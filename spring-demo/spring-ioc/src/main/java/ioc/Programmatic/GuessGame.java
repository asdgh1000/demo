package ioc.Programmatic;

/**
 * Created by Jo on 10/13/16.
 */
public class GuessGame {
	public int guess(int n){
		if(n==4){
			return 0;
		}
		else if(n>4){
			return 1;
		}
		else if(n<4){
			return -1;
		}
		return 1000;
	}
}
