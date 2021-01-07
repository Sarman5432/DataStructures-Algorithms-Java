package linear.stack;

import java.util.Arrays;

public class TwoStacks {
    private int[] arr = new int[2];
    private int stackOneCount = 0;
    private int stackTwoCount = 0;
    private int stackOneSize = 1;
    private int stackTwoSize = 1;

    public void push1(int val){
        if(stackOneCount >= stackOneSize)
            doubleStackOne();

        arr[stackOneCount++] = val;
    }

    public void push2(int val){
        if(stackTwoCount >= stackTwoSize)
            doubleStackTwo();

        arr[stackOneSize + stackTwoCount++] = val;
    }

    public int pop1(){
        int val = arr[stackOneCount-1];
        arr[--stackOneCount] = 0;
        return val;
    }

    public int pop2(){
        int val = arr[stackOneSize + stackTwoCount - 1];
        arr[stackOneSize + --stackTwoCount] = 0;
        return val;
    }

    private void doubleStackOne(){
        stackOneSize = stackOneSize * 2;
        int[] newArr = new int[totalSize()];

        for(int i=0; i<stackOneSize; i++)
            if(i<stackOneSize/2)
                newArr[i] = arr[i];
        for(int i=stackOneSize, j=stackOneCount; i<totalSize(); i++, j++)
            newArr[i] = arr[j];

        arr = newArr;
    }
    private void doubleStackTwo(){
        stackTwoSize = stackTwoSize * 2;
        int[] newArr = new int[totalSize()];

        for(int i=0; i<stackOneSize; i++)
            newArr[i] = arr[i];
        for(int i=stackOneSize, j=stackOneSize; i<totalSize(); i++, j++)
            if(i<(totalSize()-stackTwoSize/2))
                newArr[i] = arr[j];

        arr = newArr;
    }

    public void print(){
        boolean stackOneExists = false;
        System.out.print("[");
        for (int i=0; i<stackOneCount; i++){
            System.out.print(arr[i]);
            if(i < stackOneCount-1)
                System.out.print(", ");
            stackOneExists = true;
        }
        if(stackOneExists)
            System.out.print(", ");
        for (int i=stackOneSize; i<stackOneSize+stackTwoCount; i++){
            System.out.print(arr[i]);
            if(i < (stackOneSize+stackTwoCount)-1)
                System.out.print(", ");
        }
        System.out.println("]");
    }

    @Override
    public String toString(){
        var content = Arrays.copyOfRange(arr, 0, totalSize());
        return Arrays.toString(content);
    }

    public boolean isEmpty1(){
        return stackOneCount==0;
    }

    public boolean isEmpty2(){
        return stackTwoCount==0;
    }

    private int totalSize(){
        return stackOneSize + stackTwoSize;
    }
}
