package linear.queue;

import jdk.jshell.Snippet;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class QueueExercise {
    private Queue<Integer> queue;

    public QueueExercise(Queue<Integer> q){
        this.queue = q;
    }

    //O(n)
    public void reverse(){
        Stack<Integer> stack = new Stack<>();
        while (!queue.isEmpty())
            stack.add(queue.remove());

        while(!stack.isEmpty())
            queue.add(stack.pop());
    }

    //Input: Q = [10, 20, 30, 40, 50], K = 3 (first K elems)
    //Output: Q = [30, 20, 10, 40, 50]
    public void reverseK(int K){
        Stack<Integer> stack = new Stack<>();    //for K items to be reversed
        Queue<Integer> queueTwo = new ArrayDeque<>();    //For items not to be reversed
        int count = 0;
        while(!queue.isEmpty()){
            if(count < K)
                stack.add(queue.remove());
            else
                queueTwo.add(queue.remove());
            count++;
        }
        while(!stack.isEmpty())
            queue.add(stack.pop());

        while(!queueTwo.isEmpty())
            queue.add(queueTwo.remove());
    }

    public void print(){
        System.out.println(queue);
    }
}
