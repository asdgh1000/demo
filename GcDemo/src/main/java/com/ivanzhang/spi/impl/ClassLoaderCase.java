package com.ivanzhang.spi.impl;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Jo on 2/17/17.
 */
public class ClassLoaderCase {
	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException,
			InstantiationException {
		ArrayList arrayList = new ArrayList();
		Iterator it = arrayList.iterator();
		while (it.hasNext()){
			it.next();
			it.remove();
		}
//		System.out.println(ClassLoaderCase.class.getClassLoader());
//		Class clazz = ClassLoaderCase.class.getClassLoader().loadClass("com.ivanzhang.spi.impl.Test");
//		Test test = (Test)clazz.newInstance();
//		test.say();
	}
}
