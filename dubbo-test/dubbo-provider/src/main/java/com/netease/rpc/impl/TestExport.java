package com.netease.rpc.impl;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.netease.rpc.TestService;

/**
 * Created by Jo on 1/12/17.
 */
public class TestExport {

	public static void main(String[] args){
		ServiceConfig serviceConfig = new ServiceConfig();
		serviceConfig.export();
		System.out.println(System.getProperties());
		TestExport testExport = new TestExport();
		testExport.exposeService();
	}

	public void exposeService() {
		TestService testService = new TestServiceImpl();

		ApplicationConfig application = new ApplicationConfig();
		application.setName("dubbo-api-provider");

		RegistryConfig registry = new RegistryConfig();
		registry.setAddress("zookeeper://127.0.0.1:2181");

		ProtocolConfig protocol = new ProtocolConfig();
		protocol.setName("dubbo");
		protocol.setPort(20888);

		ServiceConfig<TestService> service = new ServiceConfig<TestService>();
		service.setApplication(application);
		service.setRegistry(registry);
		service.setProtocol(protocol);
		service.setInterface(TestService.class);
		service.setRef(testService);
		service.export();
	}
}
