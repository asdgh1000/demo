import javax.annotation.Resource;
import java.io.Serializable;
import java.io.SyncFailedException;

/**
 * Created by Jo on 2/27/17.
 */
//consumer
public class Controller implements Serializable{

	@Resource(name="/weidai/login")
	public boolean test(){
		System.out.println("123");
	}

	public static void main(String[] args){
		Class.forName()
		Controller controller = new Controller();
		controller.test();
	}



}


