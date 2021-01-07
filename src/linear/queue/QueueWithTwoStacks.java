package linear.queue;

import java.util.Arrays;
import java.util.Stack;

public class QueueWithTwoStacks {
    private Stack<Integer> queueStack;
    private Stack<Integer> tempStack;

    // S1: [60, 70, 30]     -> used for enqueue
    // S2: []               -> used for dequeue

    //Reverse by pop/add, then remove from top of S2

    // S1: []
    // S2: [30, 70, 60]

    //Add new items to S1

    public QueueWithTwoStacks(int capacity){
        queueStack = new Stack<>();
        tempStack = new Stack<>();
    }

    public void enqueue(int item) {
        queueStack.push(item);
    }

    public int dequeue() {
        if (queueStack.isEmpty() && tempStack.isEmpty())
            throw new IllegalStateException();

        if(tempStack.isEmpty()){
            while(!queueStack.isEmpty())
                tempStack.push(queueStack.pop());
        }
        return tempStack.pop();
    }

    public int peek() {
        if (queueStack.isEmpty() && tempStack.isEmpty())
            throw new IllegalStateException();

        if(tempStack.isEmpty()){
            while(!queueStack.isEmpty())
                tempStack.push(queueStack.pop());
        }
        return tempStack.peek();
    }

    public void reverseStack(){
        while (!queueStack.isEmpty()){
            int top = queueStack.pop();
            tempStack.add(top);
        }
    }

    public void print(){
        System.out.println(queueStack);
    }
}
