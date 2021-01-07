package linear.hashtable;

// put(k, v)
// get(k): v
// remove(k)
// k: int
// value: string
// Collisions: Chaining Strategy (Array, maps to Linked list)
// LinkedList<Entry>[] ("LinkedList" Array each elem in array holds a linked list)
// Entry (Class that wrap key-value pair)

import java.util.LinkedList;

public class CustomHashTable {
    private class Entry {
        private int key;
        private String value;

        public Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Entry>[] entries = new LinkedList[5];

    private int hash(int key){
        return Math.abs(key) % entries.length;
    }

    //Size=100
    //key=50, value=1 => index=50
    //key=50, value=2 => index=50    (value gets updated from 1 to 2 through for each loop)
    //key=150, value=3 => index=50   (adds entry to end of index=50 linked list chain w/ var entry = new Entry)
    public void put(int key, String value){
        int index = hash(key);

        if(entries[index] == null)
            entries[index] = new LinkedList<>();

        var bucket = entries[index];
        //find entry with same key and update value
        for(var entry : bucket){
            if(entry.key == key){
                entry.value = value;
                return;
            }
        }

        //entry with new key added to end on linked list
        var entry = new Entry(key, value);
        bucket.addLast(entry);
    }

    public String get(int key){
        int index = hash(key);

        var bucket = entries[index];
        if(bucket != null) {
            for (var entry : bucket)
                if (entry.key == key) {
                    return entry.value;
                }
        }
        return null;
    }

    public void remove(int key){
        int index = hash(key);

        var bucket = entries[index];
        if(bucket == null)
            throw new IllegalStateException();

        for (var entry : bucket) {
            if (entry.key == key) {
                bucket.remove(entry);
                return;
            }
        }

        throw new IllegalStateException();
    }


}
