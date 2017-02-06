package com.netease.rpc.impl;

import java.util.Arrays;
import java.util.Set;

/**
 * Created by Jo on 2/4/17.
 */
public class Test4 {
	public static void main(String[] args){
		Class<?> cl = null;
		try{
			cl = Class.forName(args[0]);

		}catch(ClassNotFoundException e){
			System.out.println("Class not found");
			System.exit(1);
			e.printStackTrace();
		}
		Set<String> s = null;
		try {
			s = (Set<String>)cl.newInstance();

		} catch (InstantiationException e) {
			System.out.println("class not accessiable");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("class not initital");
			e.printStackTrace();
		}
		s.addAll(Arrays.asList(args).subList(0,args.length));
		System.out.println(s);
	}
}
