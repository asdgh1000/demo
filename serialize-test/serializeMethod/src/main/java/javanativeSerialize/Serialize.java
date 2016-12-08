package javanativeSerialize;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by Jo on 12/8/16.
 */
public class Serialize {
	public byte[] ser(Object a){
		//定义字节数组输出流
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		//定义对象输出流
		try {
			ObjectOutputStream out = new ObjectOutputStream(os);
			out.writeObject(a);
			return os.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
