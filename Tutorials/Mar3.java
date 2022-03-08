import java.util.*;

public class Mar3 {
	public static void main(String[] args) {
		Queue q = new Queue(5);
		q.enqueue(0);
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		q.enqueue(4);
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.showQueue();
		
		q.enqueue(5);
		q.showQueue();
	}
}	

class Queue {
	int theArray[], capacity, size, front, back;
	
	Queue(int c) {
		capacity = c;
		theArray = new int[c];
		size = 0;
		front = 0;
		back = -1;
	}
	
	void showQueue() {
		System.out.println("front = " + front + ", back = " + back + ", size = " + size);
		for(int i = 0; i < size; i++) {
			System.out.println(theArray[i]);
		}
	}
	
	int getFront() {
		if(isEmpty()) {
			System.out.println("Queue is empty. Front failed!");
			return -1;
		}
		return theArray[front];
	}
	
	int getBack() {
		if(isEmpty()) {
			System.out.println("Queue is empty. Back failed!");
			return -1;
		}
		return theArray[back];
	}
	
	void enqueue(int data) {
		if(isFull()) {
			System.out.println("Queue is full. Enqueue failed!");
			return;
		}
		
		back = (back + 1) % capacity;
		theArray[back] = data;
		size++;
	}
	
	int dequeue() {
		if(isEmpty()) {
			System.out.println("Queue is empty. Dequeue failed!");
			return -1;
		}
		int res = theArray[front];
		front = (front + 1) % capacity;
		size--;
		return res;
	}
	
	
	
	boolean isFull() {
		if(size == capacity) {
			return true;
		}
		else {
			return false;
		}
	}
	
	boolean isEmpty() {
		return size == 0;
	}
}

class LinkedQueue{
	LinkedList<Integer> queue = new LinkedList<Integer>();
	int front, back, size;
	
	LinkedQueue() {
		front = -1;
		back = -1;
		size = 0;
	}
	
	void enqueue(int c) {
		queue.addLast(c);
	}
	
	int dequeue() {
		return queue.pop();
	}
}