package linear.array;

public class Array
{
    private int count;  //number of items currently in the array
    private int[] arr;

    //constructor
    public Array(int size){
        this.count = 0;
        this.arr = new int[size];
    }

    //O(1) if there is space, O(n) if newArr is required
    public void insert(int item){
        if(arr.length == count){
            int[] newArr = new int[count*2];
            for (int i=0; i<count; i++){
                newArr[i] = arr[i];
            }
            //set array to newArr (garbage collection so dont worry about deallocating old array)
            arr = newArr;
        }

        arr[count++] = item;   //count++ inside (since ++ is after rather than ++count, to save having to write count++ on next line)
    }

    //O(n)
    public void insertAt(int item, int index){
        //since we always need to iterate over the entire array and build a temp array anyways, we can increase count by 1 rather than double
        int[] newArr = new int[++count];

        for (int i=0, j=0; i<count; i++, j++){
            if(i == index){
                newArr[i] = item;
                j--;
            }else{
                newArr[i] = arr[j];
            }
        }
        arr = newArr;
    }

    //O(n) worst case (1st elem), O(1) best case (last elem)
    public void removeAt(int index){
        if(index < 0 || index >= count)
            throw new IllegalArgumentException(); //crashes program so dev knows something is wrong. Dont just println

        //shift items to left to fill the hole
        for(int i=index; i<count; i++){
            if(i != count-1)
                arr[i]=arr[i+1];
            else
                arr[i]=0;
        }
        count--;
    }

    //O(n)
    public int max(){
        int max = 0;
        for(int i=0; i<count; i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }
        return max;
    }

    //O(n^2)
    public void intersects(int[] compareArr){
        //return the common items in this array and another array (both unsorted)
        Array common = new Array(count);

        for(int i=0; i<count; i++){
            for(int j=0; j<compareArr.length; j++){
                if(compareArr[j] == arr[i]){
                    common.insert(arr[i]);
                }
            }
        }
        common.print();
    }

    //O(n)
    public void reverse(){
        int[] newArr = new int[count];

        for(int i=0; i<count; i++){
            newArr[i] = arr[count-1-i];
        }
        arr = newArr;
        print();
    }

    //O(n) worst case, O(1) best case (first elem matches)
    public int indexOf(int item){
        for(int i=0; i<count; i++){
            if(arr[i]==item)
                return i;
        }
        return -1;
    }

    //O(n)
    public void print(){
        if(count !=0){
            String arrayString = "[";
            for (int i=0; i<count; i++){
                arrayString += arr[i];  //string concatenation is bad in java (reallocates memory)
                if(i<count-1){
                    arrayString += ", ";
                }
            }
            arrayString += "]";
            System.out.println(arrayString);
        }else{
            System.out.println("Empty Linear.Array.Array!");
        }

    }
}