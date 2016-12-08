package HessianSerialize;

import com.caucho.hessian.io.HessianInput;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by Jo on 12/8/16.
 */
public class AntiSerialize {
	public Person antiSer(byte[] person){
		ByteArrayInputStream is = new ByteArrayInputStream(person);
		try {
			HessianInput in = new HessianInput(is);
				Person person1 = (Person)in.readObject();
				return person1;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
