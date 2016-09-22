package user.withouttm;

/**
 * Created by Jo on 8/22/16.
 */
public class BigObj20ld {
	public static void main(String[] args){

		BigObj20ld bigObj20ld = new BigObj20ld();
		try {
			bigObj20ld.test();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	public synchronized void test()throws Exception{
		System.out.print(
				"时间：2010.05.30\n地址：中信附近、地铁附近\n活动：会员交流、聚餐\n费用：AA制，MM除外\n建议：欢迎围观");
	}
}