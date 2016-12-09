package TCP.RPC.Comsumer.test;

import TCP.RPC.Comsumer.Interface.SayHelloService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * Created by Jo on 12/8/16.
 */
public class Test {
	public static void main(String[] args){
		String interfaceName = SayHelloService.class.getName();

		try {
			Method method = SayHelloService.class.getMethod("sayHello",String.class);
			Object[] arguments = {"hello"};
			try {
				Socket socket = new Socket("127.0.0.1",1234);
				ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
				output.writeUTF(interfaceName);
				output.writeUTF(method.getName());
				output.writeObject(method.getParameterTypes());
				output.writeObject(arguments);
				ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
				try {
					System.out.println((String)input.readObject());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
}
