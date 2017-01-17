//package NewGeneration.TooSmall;
//
//import javafx.concurrent.Task;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.ThreadPoolExecutor;
//
///**
// * Created by Jo on 1/4/17.
// */
//
//// jstat -gcutil 17908
//public class NewGenerationTooSmallDemo extends Task {
//	ThreadPoolExecutor
//	public static void main(String[] args) throws Exception{
//		System.out.println("ready to start");
//		Thread.sleep(10000);
//		List<GCDataObject> oldGenObjects = new ArrayList<GCDataObject>();
//		for(int i =0;i<51200;i++){
//			oldGenObjects.add(new GCDataObject(2)); //2KB * 51200 = 102.4MB
//		}
//		System.gc();
//		oldGenObjects.size();
//		oldGenObjects=null;
//		Thread.sleep(5000);
//		List<GCDataObject> tmpGenObjects = new ArrayList<GCDataObject>();
//		for(int i=0;i<3200;i++){
//			tmpGenObjects.add(new GCDataObject(5));  //5kb * 3200 = 16MB
//		}
//		tmpGenObjects.size();
//		tmpGenObjects=null;
//	}
//}
