package linear.linkedlist;

import java.util.NoSuchElementException;

public class LinkedListCustom
{
    //Node class internalized, "Under the hood" of LinkedList class
    private class Node {
        private int value;  //value stored in node
        private Node next;  //reference to next node

        public Node(int value){
            this.value = value;
        }
    }
    private Node first;
    private Node last;
    private int size;

    public LinkedListCustom(){}

    public void addFirst(int value){
        Node node = new Node(value);
        if(isEmpty())
            first = last = node;
        else {
            node.next = first;
            first = node;
        }
        size++;
    }

    public void addLast(int value){
        Node node = new Node(value);

        if(isEmpty())
            first = last = node;
        else {
            last.next = node;
            last = node;
        }
        size++;
    }

    public void removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();

        if (first == last)
            first = last = null;
        else{
            var secondNode = first.next;
            first.next = null; //remove pointer so garbage collection will not think its being used
            first = secondNode;
        }
        size--;
    }

    public void removeLast(){
        if (isEmpty())
            throw new NoSuchElementException();

        if (first == last)
            first = last = null;
        else{
            var secondLastNode = getPrevious(last);
            secondLastNode.next = null;
            last = secondLastNode;
        }
        size--;
    }

    private Node getPrevious(Node node){
        var currentNode = first;
        while(currentNode != null){
            if(currentNode.next == node) return currentNode;
            currentNode = currentNode.next;
        }
        return null;
    }

    public boolean contains(int value){
        return indexOf(value) != -1;
    }

    public int indexOf(int value){
        var currentNode = first;
        int index=0;
        while(currentNode != null){
            if(currentNode.value == value) return index;
            currentNode = currentNode.next;
            index++;
        }
        return -1;
    }

    //O(1)
    public int size(){
        return size;
    }

    //O(n)
    public int[] toArray(){
        int[] array = new int[size];
        var currentNode = first;
        int index=0;
        while(currentNode != null){
            array[index++] = currentNode.value;
            currentNode = currentNode.next;
        }
        return array;
    }

    public void reverse(){
        if (isEmpty()) return;

        //[10 -> 20 -> 30 -> 40] to  [10 <- 20 <- 30 <- 40]
        var currentNode = first.next;
        var prevNode = first;

        while(currentNode != null){
            var nextNode = currentNode.next;
            currentNode.next = prevNode;
            prevNode = currentNode;
            currentNode = nextNode;
        }

        //swap first/last and set [10 <-> 20] to [10 <- 20]
        last = first;
        last.next = null;
        first = prevNode;
    }

    public int getKthFromEnd(int K){
        //Find Kth node from the end
        var currentNode = first;
        var index = 0;
        while(currentNode != null){
            if(index == (size - K)) return currentNode.value;
            index++;
            currentNode = currentNode.next;
        }
        return -1;
    }

    public int getKthFromTheEndWithoutSize(int K){
        // Find Kth node from the end in one pass, without knowing size
        // NOTE: Most linked list problems can be solved using 2 pointers
        // [10 -> 20 -> 30 -> 40 -> 50]
        //             *           *
        // K=3 (30) (distance = 2) (k-1 apart)

        if(K <= 0)
            throw new IllegalArgumentException();
        if(isEmpty())
            throw new IllegalStateException();

        var firstNode = first;
        var secondNode = first;
        var index=0;

        while(secondNode != null){
            if (index >= K)
                firstNode = firstNode.next;
            if(secondNode.next == null && index < K-1)
                throw new IllegalArgumentException();    //K is too high

            secondNode = secondNode.next;
            index++;
        }
        return firstNode.value;
    }

    public int getKthFromTheEndWithoutSize_V2(int k){
        if(isEmpty())
            throw new IllegalStateException();

        var firstNode = first;
        var secondNode = first;
        for(int i=0; i < k-1; i++){
            secondNode = secondNode.next;
            if(secondNode == null)
                throw new IllegalArgumentException();
        }
        while(secondNode != last){
            firstNode = firstNode.next;
            secondNode = secondNode.next;
        }
        return firstNode.value;
    }

    //Return middle node(s): Assume unknown size, 2 nodes if even
    //[30, 20, 10, 5, 30, 20, 10, 5]
    //[30, 20]
    public void printMiddle(){
        if(isEmpty())
            throw new IllegalStateException();
        if(first == last)
            System.out.println(first.value);

        var firstNode = first;
        var secondNode = first.next;
        int size = 2;

        while(secondNode != null){
            if(size==2 && secondNode.next==null) {
                System.out.println("[" + firstNode.value + ", " + secondNode.value + "]");
                break;
            }

            secondNode = secondNode.next;
            size++;
            if(size % 2 != 0 && secondNode != null){
                firstNode = firstNode.next;
            }
            if(secondNode.next == null)
                if(size % 2 == 0) {
                    System.out.print("[" + firstNode.value + ", ");
                    firstNode = firstNode.next;
                    System.out.println(firstNode.value + "]");
                    return;
                }else{
                    System.out.println("[" + firstNode.value + "]");
                    return;
                }
        }
    }

    /*
        1. The number of nodes is increasing by two where as the position of the middle node is increasing by one. The moment the second pointer hits the tail node, the first node is pointing the middle node.
        2. If the list has an even number of nodes, at the end of the last iteration, the second pointer will reference the tail node; otherwise, it’ll be null.
    */
    public void printMiddle_V2(){
        if(isEmpty())
            throw new IllegalStateException();
        var firstNode = first;
        var secondNode = first;

        while(secondNode != last && secondNode.next != last){
            secondNode = secondNode.next.next;  //twice per 1 increase in firstNode
            firstNode = firstNode.next;
        }

        if(secondNode == last)
            System.out.println("[" + firstNode.value + "]");
        else
            System.out.println("[" + firstNode.value + ", " + firstNode.next.value + "]");
    }

    //TODO: Check to see if a linked list has a loop
    /*
        Hint: use two pointers (slow and fast) to traverse the list. Move the slow
        pointer one step forward and the fast pointer two steps forward. If
        there’s a loop, at some point, the fast pointer will meet the slow pointer
        and overtake it. Draw this on a paper and see it for yourself. This
        algorithm is called Floyd’s Cycle-finding Algorithm.
    */
    public boolean hasLoop(){
        var firstNode = first;
        var secondNode = first;
        while(secondNode != null){
            secondNode = secondNode.next.next;  //twice per 1 increase in firstNode
            firstNode = firstNode.next;
            if(firstNode == secondNode){
                return true;
            }
        }
        return false;
    }

    public static LinkedListCustom createWithLoop() {
        var list = new LinkedListCustom();
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        // Get a reference to 30
        var node = list.last;

        list.addLast(40);
        list.addLast(50);

        // Create the loop
        list.last.next = node;

        return list;
    }

    private boolean isEmpty(){
        return first==null;
    }
}