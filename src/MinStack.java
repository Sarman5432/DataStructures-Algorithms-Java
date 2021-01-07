import java.util.Arrays;

public class MinStack {
    private int[] arr = new int[1];
    private int count = 0;
    private int size = 1;
    private int minValue = 0;   //doesn't include the last value
    private int lastInValue = 0;

    //[] -> throw error
    //[10] -> minValue=10, lastInValue=10
    //[10, 9] -> minValue=10, lastInValue=9
    //[10, 9, 11] -> minValue=9, lastInValue=11
    //[10, 9, 11, 7] -> minValue=9, lastInValue=7
    //[10, 9, 11] -> minValue=9, lastInValue=11

    public void push(int value){
        if(isEmpty())
            minValue = value;
        else
            if((minValue != lastInValue) && (minValue > lastInValue))
                minValue = lastInValue;

        lastInValue = value;

        if(count >= size)
            doubleCapacity();
        arr[count++] = value;
    }

    public int pop(){
        if(isEmpty())
            throw new IllegalStateException();
        else
            if((minValue != lastInValue) && (minValue < lastInValue))
                minValue = lastInValue;

        int val = arr[count - 1];
        arr[--count] = 0;
        return val;
    }

    public int getMinValue(){
        if(isEmpty())
            throw new IllegalStateException();

        if(arr[count-1] > minValue)
            return minValue;
        else
            return arr[count-1];
    }

    @Override
    public String toString(){
        var content = Arrays.copyOfRange(arr, 0, count);
        return Arrays.toString(content);
    }

    public boolean isEmpty(){
        return count==0;
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
