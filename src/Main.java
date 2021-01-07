import java.util.*;


public class Main
{
    public static void main(String[] args) {
        //callArray();
        //callLinkedList();
        //callStack();
        //callQueues();
        callHashTables();
    }

    public static void callArray() {
        //Building a dynamic array class
        Array nums = new Array(3);
        nums.insert(10);
        nums.insert(20);
        nums.insert(50);
        nums.insert(40);    //grows dynamically even though original is 3 length
        nums.removeAt(1);
        System.out.println(nums.max());
        System.out.println(nums.indexOf(40));
        int[] compareArr = {1, 2, 10, 50, 2, 7};
        nums.intersects(compareArr);
        nums.insertAt(30, 2);
        nums.print();   //you typically dont want to implement print in array class since its only concern should be storing the values
        nums.reverse();

        // Built-in dynamic arrays: java.util.Vector and java.util.ArrayList
        // Vector: 100% size change (2x) - synchronized
        // ArrayList: 50% size change (1.5x) - async
        ArrayList<Integer> list = new ArrayList<>(); //angled brackets = generic parameter where we specify type of element
        list.add(15);
        list.add(20);
        list.remove(1); //index 1;
        list.indexOf(15);
        list.toArray(); //converts to regular ArrayList
        list.contains(15);
        list.size();
        System.out.println(list);   //no need to loop
    }

    public static void callLinkedList(){
        //custom singly LinkedList
        var ls = new LinkedListCustom();
        ls.addLast(5);
        ls.addLast(10);
        ls.addLast(20);
        ls.addLast(30);
        ls.addFirst(3);
        System.out.println(ls.size());
        ls.reverse();
        var array = ls.toArray();
        System.out.println(Arrays.toString(array));
        ls.printMiddle();
        ls.printMiddle_V2();
        System.out.println(ls.getKthFromEnd(4));
        System.out.println(ls.getKthFromTheEndWithoutSize(2));
        ls.removeLast();
        ls.removeFirst();
        System.out.println(ls.size());
        System.out.println(ls.indexOf(1));

        //Loops
        var lsLoop = LinkedListCustom.createWithLoop();
        System.out.println(lsLoop.hasLoop());

        //java.util.LinkedList: implemented as doubly linked list
        LinkedList list = new LinkedList();
        list.addLast(10);
        list.addLast(20);
        list.addFirst(5);
        System.out.println(list.contains(10));
        list.removeLast();
        list.remove(1);
        System.out.println(list);
        System.out.println(list.size());

        //circular linked list, example: last node points to first
        //uses: loop back to first song in song playlist, tab between input objects to change focus and looping back to first obj from last
    }

    //LAST IN FIRST OUT
    public static void callStack(){
        //java.util.Stack: pop, push, peek, isEmpty are all O(1). You almost never search through a stack, but .search is also a method
        //Can be implemented using arrays / linked lists

        Stack<Integer> stack = new Stack<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.pop();
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack);

        //Stack exercises (done within the class methods)
        var stackExercises = new StackExercise();
        stackExercises.reverseString("abcd");
        stackExercises.reverseStringWithoutStack("abcd");
        var str = "[()]{}{[()()]()}";
        System.out.println(str + " is balanced: " + stackExercises.balancedBracketsCheck(str));
        str = "[(])";
        System.out.println(str + " is balanced: " + stackExercises.balancedBracketsCheck(str));
        str = "[()]{}{[()()]()}";
        System.out.println(str + " is balanced: " + stackExercises.balancedBracketsCheck_V2(str));
        str = "[(])";
        System.out.println(str + " is balanced: " + stackExercises.balancedBracketsCheck_V2(str));

        //Stack implementation using Arrays
        var customStack = new StackViaArray();
        customStack.print();
        customStack.push(1);
        customStack.push(2);
        customStack.print();
        System.out.println(customStack.pop());
        System.out.println(customStack);    //println calls the toString method of the Object class which we have overridden
        System.out.println(customStack.peek());

        //Implement two stacks in one array.
        var twoStacks = new TwoStacks();
        twoStacks.push1(5);
        twoStacks.push2(3);
        twoStacks.push1(2);
        twoStacks.push2(8);
        twoStacks.push1(2);
        twoStacks.push2(8);
        twoStacks.push1(2);
        twoStacks.push2(8);
        twoStacks.push2(8);
        twoStacks.push2(8);
        twoStacks.print();
        twoStacks.pop1();
        twoStacks.pop2();
        twoStacks.print();
        twoStacks.push1(2);
        twoStacks.print();


        //Stack that supports push, pop and retrieving the MINIMUM value in constant time.
        var minStack = new MinStack();
        minStack.push(5);
        System.out.println(minStack + " min value: " + minStack.getMinValue());
        minStack.push(2);
        System.out.println(minStack + " min value: " + minStack.getMinValue());
        minStack.push(10);
        System.out.println(minStack + " min value: " + minStack.getMinValue());
        minStack.push(1);
        System.out.println(minStack + " min value: " + minStack.getMinValue());
        minStack.pop();
        System.out.println(minStack + " min value: " + minStack.getMinValue());
    }

    // Used by printers, operating systems, web servers: Anywhere you want to process jobs based on the order you receive them
    // Sharing a resource amongst many consumers
    // FIRST IN, FIRST OUT
    public static void callQueues(){
        //java.util.Queue: enqueue/add, dequeue/remove, peek, isEmpty, isFull are all O(1)
        //Queue<Integer> q = new Queue<>();   //DOESN"T WORK: Queue is an INTERFACE not a class (green icon when hovering), so you cannot instantiate as a new class
        //ArrayDeque (double ended queue) and LinkedList are one of many classes that implement the Queue Interface (but these 2 are most used)

        Queue<Integer> queue = new ArrayDeque<>();  //Interface (Contract on methods/method names to include) = new Implementation (ArrayDeque has methods with those names/return types)
        queue.add(10); // [10]
        queue.add(20); // [10, 20]
        queue.add(30); // [10, 20, 30]
        queue.remove(); // [20, 30]
        queue.add(15); // [20, 30, 15]
        System.out.println(queue);

        //Custom Queue Class implemented using Arrays
        ArrayQueue arrayQueue = new ArrayQueue(5);
        arrayQueue.print();
        arrayQueue.enqueue(5);
        arrayQueue.enqueue(4);
        arrayQueue.enqueue(3);
        arrayQueue.enqueue(2);
        arrayQueue.enqueue(1);
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        arrayQueue.enqueue(7);
        arrayQueue.enqueue(8);
        arrayQueue.print();

        //TODO: Build a queue with 2 stacks
        QueueWithTwoStacks qwts = new QueueWithTwoStacks(5);
        qwts.enqueue(10);
        qwts.enqueue(20);
        qwts.enqueue(30);
        qwts.dequeue();

        //Priority Queue: adds to queue based on priority rather than order in which received. ie. SORTED, adds to correct index
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(5);
        priorityQueue.add(1);
        priorityQueue.add(3);
        priorityQueue.add(2);
        System.out.println(priorityQueue);  //ordered from lowest(low priority) to greatest(high priority) [1, 2, 3, 5] rather than [5, 1, 3, 2]
        while (!priorityQueue.isEmpty())
            System.out.println(priorityQueue.remove());

        //Custom Priority Queue: Build using Arrays or Heaps.
        //When shifting an array to the right, you cannot do items[i+1] = item[i] as values will be overwritten. Instead, we need to iterate over the array from the back
        //ex. [1, 3, 5, 7] insert(2) -> [1, 2, 3, 3, 3] if you do items[i+1] = items[i]
        //When we iterate from the back (i=length, i--), we are not losing values
        CustomPriorityQueue customPriorityQueue = new CustomPriorityQueue(5);
        System.out.println(customPriorityQueue);
        customPriorityQueue.insert(7);
        customPriorityQueue.insert(3);
        customPriorityQueue.insert(1);
        customPriorityQueue.insert(2);
        customPriorityQueue.insert(1);
        System.out.println(customPriorityQueue);


        //Queue Exercises
        Queue<Integer> queueTwo = new ArrayDeque<>();  //Interface (Contract on methods/method names to include) = new Implementation (ArrayDeque has methods with those names/return types)
        queueTwo.add(10); // [10]
        queueTwo.add(20); // [10, 20]
        queueTwo.add(30); // [10, 20, 30]
        queueTwo.add(15); // [10, 20, 30, 15]
        System.out.println(queueTwo);

        QueueExercise queueExercise = new QueueExercise(queueTwo);
        //queueExercise.reverse();
        queueExercise.reverseK(3); // [30, 20, 10, 15]
        queueExercise.print();
        //TODO: StackWithTwoQueues
        //TODO: LinkedListQueue
    }

    //Hash tables give us super fast look ups: using key: value pairs
    // Java (HashMap), Javascript (Object), Python/C# (Dictionary)
    /* Uses:
        - Spellcheckers (look up a word among thousands of words),
        - Dictionaries (look up a word and find its translations into other languages),
        - Compilers (Look up addresses of functions)
        - Code Editors
     */
    //"Deterministic" : Ever time we give it the same input, it will return the same value: Therefore used for both storing/retrieving objects
    // Insert, Lookup, delete are all O(1), but can sometimes be O(n)
    public static void callHashTables(){
        // Map is an "INTERFACE" of <Key, Value> where HashMap is an Implementation. (Check out others like ConcurrentHashMap for multithreaded use, HashTable is Deprecated)
        // Key: Employee Number (Integer)
        // Value: Name (String)
        /*
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Ross");
        map.put(2, "Bob");
        map.put(3, "John");
        map.put(3, "Johnny");   //John gets overwritten, no duplicate keys in HashMaps
        map.put(4, null); //works!
        map.put(null, null); //also works!
        map.remove(2);
        System.out.println(map.get(1));
        System.out.println(map.containsKey(1)); //O(1): goes straight to "address" = 1
        System.out.println(map.containsValue("Ross")); //O(n): has to iterate over values, not an "address"
        System.out.println(map);
        //entrySet (list of key value pair), keySet (list of keys only), both iterable
        for(var item : map.keySet())
            System.out.println(item);
        for (var item : map.entrySet())
            System.out.println(item + ": key=" + item.getKey() + ", value=" + item.getValue());

        ///////    SETS     //////
        // Maps: k => v  (key maps to value)
        // Sets: k       (like Maps but only have keys. Also doesn't allow duplicate values)
        // Application of Sets: Get unique values from array [1, 1, 5, 5, 10] to Set is [1, 5, 10]
        // HashSet (implementation of Set interface)

        Set<Integer> set = new HashSet<>();
        int[] numbers = { 1, 2, 3, 3, 2, 1, 4 };
        for(int num : numbers)
            set.add(num);
        System.out.println(set);    //only unique values [1, 2, 3, 4]
        */


        //HashTable exercises
        //1. Find the first Non-Repeated character in a string (Map-HashMap)
        HashTableExercises hashExercise = new HashTableExercises();
        System.out.println(hashExercise.firstNonRepeatedCharacter("a green apple"));    //prints g

        //2. Find the first Repeated character in a string (Set-HashSet)
        System.out.println(hashExercise.firstRepeatedCharacter("a green apple"));

        /////////// HASH FUNCTIONS //////////
        // a function that gets a value and maps it to another kind of value (called Hash Value, Hash Code, digest, or just Hash)
        /*
            - In Data structure context, a hash function maps a "key" value to an "index" value (Array behind the scene)
            - used in cryptography such as password hashing (to store in database)
            - ex. map.put(1, "Sarman")   =>     items[1] = "Sarman"
            - ex2. map.put(123456, "Sarman"), but max employees (array size) of 100. Instead of wasting space we need to map values from 0 - 99 (ex. by using modulus)
        */

        //3. Simplest implementation of a hash function using modulo.
        //Note even if passed a string, it will work since each char is represented be a number so the param being passed will be the sum of each char's value
        System.out.println(hashExercise.hash(123456));  //returns 56
        System.out.println(hashExercise.hash("Testing-123")); //returns 29

        // Each obj in java has a hashCode method (outside context of data structures, just an algorithm that turns something to a hashed representation)
        String str = "orange";
        System.out.println(str.hashCode()); //returns -1008851410. This is used by hashmaps, but does extra work to map it to an index value

        /////////// Collisions //////////
        /*
            -collision when identical hash keys generated to point to same address
            -hash can give the same value (ex. modulo function above giving same result: 56 for inputs of 56, 123456, 356...)

            2 Ways to handle collisions:
                - CHAINING: Have each cell in array (mapped key address, ex Key=11: 11%5 = index 1 in array) point to a linked list holding the value. If collision occurs, add new value to end of the linked list
                    -pro: no need to worry about hashmap array overflowing (OPEN ADDRESSING con) since linked lists can continue to expand

                - OPEN ADDRESSING: store key-value pair directly in array spots, if collision find another spot to store the second value (finding a new address)
                    *LINEAR PROBING: start for collision spot and go forward in array until there is an empty spot and add key-value pair there
                        -formula: ( hash(key)+i ) % table_size
                        -CON: creates clusters (full cells next to each other) which makes it take longer to find an empty spot (PROBE)
                        -PRO: no need to worry about infinite loop since linear

                    *QUADRATIC PROBING: instead of i being 1,2,3,4... which makes clusters easy to form, i^2 being 1,4,9,16 make it more spread out (harder to form)
                        -formula: ( hash(key)+i^2 ) % table_size
                        -CON: big jumps, may end up in an infinite loop (jumping for full spots and missing empty spots in-between for a long/infinite time)
                        -PRO: spread out, clusters issue is better (no solved)

                    *DOUBLE HASHING: separate, Independent, hash function to calculate the number of steps
                        -popular function: hash2(key) = prime - (key % prime)       -> prime = any prime number smaller than array/table size
                        -formula: ( hash1(key) + [i * hash2(key)] ) % table_size
                        -PRO: fixes quadratic probings jumping/infinite loop problem, while also being good with clusters
        */
    }




}