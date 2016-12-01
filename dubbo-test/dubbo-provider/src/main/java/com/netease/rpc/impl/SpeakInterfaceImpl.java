package com.netease.rpc.impl;

import com.netease.rpc.People;
import com.netease.rpc.SpeakInterface;

/**
 * Created by Jo on 11/29/16.
 */
public class SpeakInterfaceImpl implements SpeakInterface {
	public String speak(People people) {
		return "dubbo return: " + people.getAge();
	}

}
