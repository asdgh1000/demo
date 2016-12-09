package TCP.RPC.Provider.Impl;

import TCP.RPC.Comsumer.Interface.SayHelloService;

/**
 * Created by Jo on 12/8/16.
 */
public class SayHelloServiceImpl implements SayHelloService {
	public String sayHello(String helloArg) {
		if(helloArg.equals("hello")){
			return "hello";
		}
		else{
			return "bye bye";
		}
	}
}
