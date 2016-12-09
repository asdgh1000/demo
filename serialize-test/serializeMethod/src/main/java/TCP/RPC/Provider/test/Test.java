package TCP.RPC.Provider.test;

import TCP.RPC.Provider.Impl.SayHelloServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * Created by Jo on 12/8/16.
 */
public class Test {
	public static void main(String[] args){
		try {
			SayHelloServiceImpl sayHelloServiceImpl = new SayHelloServiceImpl();
			HashMap services = new HashMap<String,Object>();
			services.put(SayHelloServiceImpl.class.getInterfaces()[0].toString(),sayHelloServiceImpl);
			ServerSocket serverSocket = new ServerSocket(1234);
			while(true){
				Socket socket = serverSocket.accept();

				ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
				String interfaceName = input.readUTF();
				String methodName = input.readUTF();
				try {
					Class<?>[] parameterTypes = (Class<?> [])input.readObject();
					Object[] arguments = (Object[]) input.readObject();
					Class serviceInterfaceClass = Class.forName(interfaceName);
					Object service = services.get(interfaceName);
					try {
						Method method = serviceInterfaceClass.getMethod(methodName,parameterTypes);
						try {
							Object result = method.invoke(service,arguments);
							ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
							output.writeObject(result);
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
