package javanativeSerialize;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by Jo on 12/8/16.
 */
public class AntiSerialize {
	public Person antiSer(byte[] person){
		ByteArrayInputStream is = new ByteArrayInputStream(person);
		try {
			ObjectInputStream in = new ObjectInputStream(is);
			try {
				Person person1 = (Person)in.readObject();
				return person1;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
