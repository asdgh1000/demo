import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jo on 3/7/17.
 */
public class MyString implements Comparable<String> {
	public void inspect(List<? extends Object> list) {
		for (Object obj : list) {
			System.out.println(obj);
		}
		list.add(1); //这个操作在当前方法的上下文是合法的。
	}
	public void test() {
		List<String> strs = new ArrayList<String>();
		inspect(strs); //编译错误
	}

	public int compareTo(String o) {
		return 0;
	}
}