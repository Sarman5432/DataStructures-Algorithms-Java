import java.util.Arrays;

public class ArrayQueueV2 {
    private int[] items;
    private int rear;
    private int front;
    private int count;

    //NOTE FOR CIRCULAR ARRAY

    // [60, 70, 30, 40, 50]
    //                  R
    // Length = 5
    // Max Rear = 4
    // 5 -> 0       Left % Length
    // 6 -> 1
    // 7 -> 2
    // 8 -> 3
    // 9 -> 4
    // 10 -> 0
    // 11 -> 1

    // (rear + 1) % length

    public ArrayQueueV2(int capacity) {
        items = new int[capacity];
    }

    public void enqueue(int item) {
        if (isFull())
            throw new IllegalStateException();

        items[rear] = item;
        rear = (rear + 1) % items.length;
        count++;
    }

    public int dequeue() {
        if (isEmpty())
            throw new IllegalStateException();

        var item = items[front];
        items[front] = 0;
        front = (front + 1) % items.length;
        count--;

        return item;
    }

    public int peek() {
        if (isEmpty())
            throw new IllegalStateException();

        return items[front];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == items.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }
}