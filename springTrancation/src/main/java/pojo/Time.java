package pojo;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Jo on 8/15/16.
 */
public class Time {
	public static void main(String[] args){
		Time time = new Time();
		System.out.print(time.judge(new Date(), "yyyy-MM-dd"));

	}

	public boolean judge(Date time,String format){
		String s = (new java.text.SimpleDateFormat(format)).format(time);
		System.out.println(time);
		System.out.println(s);
		return time.toString().equals(s);
	}
}
