package HessianSerialize;

import com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Jo on 12/8/16.
 */
public class Serialize {
	public byte[] ser(Object a){
		//定义字节数组输出流
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		//定义对象输出流
		try {
			HessianOutput ho = new HessianOutput(os);
			ho.writeObject(a);
			return os.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
