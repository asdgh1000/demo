//package com.netease.rpc.impl;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.concurrent.*;
//
///**
// * Created by Jo on 2/8/17.
// */
//public class Test20 extends RecursiveTask<Long> {
//
//	private static final int THERSHOLD = 10000;
//	private long start;
//	private long end;
//	public Test20(long start,long end){
//		this.start = start;
//		this.end = end;
//	}
//
//	@Override
//	protected Long compute() {
//		HashMap map = new HashMap();
//		ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
//		concurrentHashMap.put("a","b");
//		long sum=0;
//		boolean canCompute = (end-start)<THERSHOLD;
//		if(canCompute){
//			for(long i=start;i<=end;i++){
//				sum+=i;
//			}
//		}else{
//			long step = (start+end)/100;
//			ArrayList<Test20> test20s = new ArrayList<>();
//			long pos = start;
//			for(int i=0;i<100;i++){
//				long lastOne=pos+step;
//				if(lastOne>end)
//					lastOne=end;
//				Test20 test20 = new Test20(pos,lastOne);
//				pos+=step+1;
//				test20s.add(test20);
////				test20.fork();
//			}
//			for(Test20 t:test20s){
////				sum+=t.join();
//			}
//		}
//		return sum;
//	}
//	public static void main(String[] args){
//		ForkJoinPool forkJoinPool = new ForkJoinPool();
//		Test20 test20 = new Test20(0,20000l);
//		ForkJoinTask<Long> result = forkJoinPool.submit(test20);
//		try{
//			long res = result.get();
//			System.out.println("sum="+res);
//		}catch (InterruptedException e){
//			e.printStackTrace();
//		}catch (ExecutionException e){
//			e.printStackTrace();
//		}
//	}
//}
