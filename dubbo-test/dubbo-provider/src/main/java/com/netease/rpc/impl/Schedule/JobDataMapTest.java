package com.netease.rpc.impl.Schedule;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.ArrayList;

/**
 * Created by Jo on 2/12/17.
 */
public class JobDataMapTest implements Job {

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		//从context中获取instName，groupName以及dataMap
//		String instName = context.getJobDetail().getName();
//		String groupName = context.getJobDetail().getGroup();
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		//从dataMap中获取myDescription，myValue以及myArray
		String myDescription = dataMap.getString("myDescription");
		int myValue = dataMap.getInt("myValue");
		ArrayList<String> myArray = (ArrayList<String>) dataMap.get("myArray");
		System.out.println("description = " + myDescription + ", value =" + myValue
						+ ", array item0 = " + myArray.get(0));
	}
}