package linear.stack;

import java.util.Arrays;

public class StackViaArray {

    private int[] arr = new int[0];
    private int count = 0;
    private int size = 0;

    public void push(int value){
        if(count >= size)
            doubleCapacity();
        arr[count++] = value;
    }

    public int pop(){
        if(isEmpty())
            throw new IllegalStateException();

        int val = arr[count - 1];
        arr[--count] = 0;
        return val;
    }

    public int peek(){
        if(isEmpty())
            throw new IllegalStateException();

        return arr[count - 1];
    }

    public boolean isEmpty(){
        return count==0;
    }

    public void print(){
        System.out.print("[");
        for (int i=0; i<count; i++){
            System.out.print(arr[i]);
            if(i < count-1)
                System.out.print(", ");
        }
        System.out.println("]");
    }

    //Every class has {@code Object} as a superclass (which has a toString method). So override is needed to change it
    @Override
    public String toString(){
        var content = Arrays.copyOfRange(arr, 0, count);
        return Arrays.toString(content);
    }

    private void doubleCapacity(){
        if(isEmpty()){
            arr = new int[1];
            size = 1;
        }else{
            int[] newArr = new int[size * 2];
            size = size * 2;

            for (int i=0; i<arr.length; i++){
                newArr[i] = arr[i];
            }
            arr = newArr;
        }
    }
}
