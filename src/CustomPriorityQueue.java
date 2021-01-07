import java.util.Arrays;

public class CustomPriorityQueue {
    private int[] arr;
    private int count;

    public CustomPriorityQueue(int capacity){
        arr = new int[capacity];
    }

    //[1, 3, 5, 7] -> insert(2)
    //[1, 3, 3, 5, 7]
    //[7]
    public void insert(int val){
        if(isFull())
            throw new IllegalStateException();

        var i = shiftItemsToInsert(val);
        arr[i] = val;
        count++;
    }

    //traverse from back in order to shift values to the right
    private int shiftItemsToInsert(int val){
        int i;
        for(i=count-1; i>=0; i--){
            if(arr[i] > val)
                arr[i+1] = arr[i];
            else
                break;
        }
        return i+1;
    }

    //[1, 3, 5, 7] we assumed 1 was front in a normal queue,
    //but if we assume that the end is the from (higher num = greater priority), we can simply decrement the count
    public int remove(){
        if(isEmpty())
            throw new IllegalStateException();
        return arr[--count];
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public boolean isFull(){
        return count == arr.length;
    }

    @Override
    public String toString(){
        return Arrays.toString(arr);
    }

}
