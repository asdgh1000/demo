package ioc.Programmatic;

import java.util.Stack;

/**
 * Created by Jo on 10/16/16.
 */
public class MyStack {
	Stack<Integer> stack;
	int min = 0;
	int single;
	/** initialize your data structure here. */
	public MyStack() {
		stack = new Stack();
		single = 0;
	}

	public void push(int x) {
		single++;
		if(single==1) min = x;
		if (x<min) min = x;
		stack.push(x);
	}

	public void pop() {
		single--;
		stack.pop();
	}

	public int top() {
		return stack.peek();
	}

	public int getMin() {
		return min;
	}
}
