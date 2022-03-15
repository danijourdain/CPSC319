/* Note that this is a limited implementation of a Queue. Only the features necessary for
 * this assignment were included */

public class Queue {
    /* Queue implementation using arrays adapted from March 3 tutorial example */

    private Node[] array;
    private int capacity, size, front, back;


    /**
     * Constructor for the Queue class.
     * @param capacity the maximum capacity of the queue.
     */
    public Queue(int capacity) {
        this.array = new Node[capacity];
        this.capacity = capacity;
        this.size = 0;
        this.front = 0;
        this.back = -1;
    }

    /**
     * This method adds a new element to the end of the queue
     * @param item The element to add to the queue
     */
    public void enqueue(Node item) {
        this.back = (this.back + 1) % this.capacity;
        this.array[this.back] = item;
        size++;
    }

    /**
     * This method removes the element at the front of the queue
     * @return the element removed from the queue
     */
    public Node dequeue() {
        Node result = this.array[this.front];
        this.front = (this.front + 1) % this.capacity;
        this.size--;
        return result;
    }

    /**
     * This method checks if the queue is empty by checking the number of elements contained within it
     * @return a boolean - true if the queue is empty, false if it is not.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }
}
