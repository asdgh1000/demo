package ioc.Programmatic;

import java.util.Stack;

/**
 * Created by Jo on 10/16/16.
 */
class MyQueue {
	Stack<Integer> stack = new Stack();
	Stack<Integer> stackTmp = new Stack();
	// Push element x to the back of queue.
	public void push(int x) {
		stack.push(x);
	}

	// Removes the element from in front of queue.
	public void pop() {
		if(stackTmp.isEmpty()){
			while(!stack.isEmpty()){
				int tmp = stack.peek();
				stackTmp.push(tmp);
				stack.pop();
			}
		}
		else{
			stackTmp.pop();
		}
	}

	// Get the front element.
	public int peek() {
		if(!stackTmp.isEmpty()){
			return stackTmp.peek();
		}
		else{
			while(!stack.isEmpty()){
				int tmp = stack.peek();
				stackTmp.push(tmp);
			}
			return stackTmp.peek();
		}
	}

	// Return whether the queue is empty.
	public boolean empty() {
		if(stackTmp.isEmpty() && stack.isEmpty()){
			return true;
		}
		else{
			return false;
		}
	}
}
