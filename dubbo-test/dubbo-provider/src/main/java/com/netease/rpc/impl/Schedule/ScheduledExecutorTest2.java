package com.netease.rpc.impl.Schedule;

import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jo on 2/12/17.
 */
public class ScheduledExecutorTest2 extends TimerTask{
	private String jobName = "";

	public ScheduledExecutorTest2(String jobName) {
		this.jobName = jobName;
	}
	public Calendar getEarliesDate(Calendar currentDate,int dayOfWeek,int hourOfDay,int minuteOfHour,int secondOfMinite){
		int currentWeekOfYear = currentDate.get(Calendar.WEEK_OF_YEAR);
		int currentDayOfWeek = currentDate.get(Calendar.DAY_OF_WEEK);
		int currentHour = currentDate.get(Calendar.HOUR_OF_DAY);
		int currentMinute = currentDate.get(Calendar.MINUTE);
		int currentSecond = currentDate.get(Calendar.SECOND);

		boolean weekLater = false;
		if(dayOfWeek<currentDayOfWeek){
			weekLater = true;
		}else if(dayOfWeek == currentDayOfWeek){
			if(hourOfDay<currentHour){
				weekLater = true;
			}else if(hourOfDay == currentHour){
				if(minuteOfHour < currentMinute){
					weekLater = true;
				}else if(minuteOfHour == currentSecond){
					if(secondOfMinite < currentSecond){
						weekLater = true;
					}
				}
			}
		}
		if(weekLater){
			currentDate.set(Calendar.WEEK_OF_YEAR,currentWeekOfYear + 1);
		}
		currentDate.set(Calendar.DAY_OF_WEEK,dayOfWeek);
		currentDate.set(Calendar.HOUR_OF_DAY,hourOfDay);
		currentDate.set(Calendar.MINUTE,minuteOfHour);
		currentDate.set(Calendar.SECOND,secondOfMinite);
		return currentDate;
	}

	@Override public void run() {
		System.out.println("Date = "+new Date()+", execute "+jobName);
	}
	public static void main(String[] args){
		ScheduledExecutorTest2 test = new ScheduledExecutorTest2("job1");
		Calendar currentDate = Calendar.getInstance();
		long currentDateLong = currentDate.getTime().getTime();
		System.out.println("Current Date "+currentDate.getTime().toString());
		Calendar eliestDate = test.getEarliesDate(currentDate,3,16,38,10);
		long earliesDateLong = eliestDate.getTime().getTime();
		System.out.println("Earliest Date ="+eliestDate.getTime().toString());
		long delay = earliesDateLong - currentDateLong;
		long period = 7*24*60*60*1000;
		ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
		service.scheduleAtFixedRate(test,delay,period, TimeUnit.MILLISECONDS);
	}
}
