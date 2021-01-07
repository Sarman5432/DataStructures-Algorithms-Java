package linear.queue;

import java.util.Arrays;

public class ArrayQueue {
    //[10, 20, 30, 40, 0]
    // F               R
    //Instead of shifting the array, we move the pointers to increase performance (fixed size queue)
    private int[] queue;
    private int size;
    private int front = 0;
    private int rear = 0;

    public ArrayQueue(int capacity){
        queue = new int[capacity];
        size = capacity;
    }

    public void enqueue(int val){
        if(isFull())
            throw new IllegalStateException();

        if(rear < size)
            queue[rear++] = val;
        else{
            rear = 0;
            queue[rear++] = val;
        }
    }

    public int dequeue(){
        if(isEmpty())
            throw new IllegalStateException();

        int val;
        if(front < size)
            val = queue[front++];
        else{
            front = 0;
            val = queue[front];
        }
        return val;
    }

    private boolean isEmpty(){
        return front==rear;
    }

    private boolean isFull(){
        return Math.abs(rear - front) == size;
    }

    //[10, 20, 30, 40, 0]
    // F   R     F       R
    public void print(){
        System.out.print("[");
        if(front < rear)
            for (int i=front; i<rear; i++){
                System.out.print(queue[i]);
                if(i < rear-1)
                    System.out.print(", ");
            }
        else{
            for (int i=front; i<size; i++){
                System.out.print(queue[i]);
                if(i <  size-1)
                    System.out.print(", ");
            }
            System.out.print(", ");
            for (int i=0; i<rear; i++){
                System.out.print(queue[i]);
                if(i < rear-1)
                    System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
