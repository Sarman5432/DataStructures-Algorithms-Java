package linear.hashtable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HashTableExercises {

    // a green apple: print 'g'
    public char firstNonRepeatedCharacter(String str){
        Map<Character, Integer> map = new HashMap<>();

        char[] charArray = str.toLowerCase().replaceAll("\\s+","").toCharArray();   //removed blank spaces/hidden characters (ex tab)

        for(char ch : charArray){
            var count = map.containsKey(ch) ? map.get(ch) : 0;
            map.put(ch, count + 1);
        }

        char character = ' ';
        int count = 0;

        //DOESNT WORK SINCE HASHMAP DOESN'T KEEP/GUARANTEE ORDER, so key=g will not be the second pair, not inserted sequentially in memory
        /*
        for(var item : map.entrySet()){
            if(count == 0){
                character = item.getKey();
                count = item.getValue();
            } else if(item.getValue() < count){
                character = item.getKey();
                count = item.getValue();
            }
        }
        */
        //Instead, iterate over the string again and LOOKUP the character rather than looping over hashtable like above
        for(var ch : charArray){
            if(map.get(ch) == 1)
                return ch;

            //TODO: this will give us the least frequent appearing character (also works but if we look only for first "Non-Repeated" Character" then not needed
            /*if((count == 0) || (count > map.get(ch))) {
                count = map.get(ch);
                character = ch;
            }*/
        }

        return Character.MIN_VALUE; //Kind of like null character (non existent first non-repeated, all are repeated)
    }

    // a green apple: print 'e'
    // Can use HashMap but we don't need values (repeat times) so we can just use a Set (less memory with no value)
    public char firstRepeatedCharacter(String str){
        Set<Character> set = new HashSet<>();

        char[] charArray = str.toLowerCase().replaceAll("\\s+","").toCharArray();   //removed blank spaces/hidden characters (ex tab)

        for(char ch : charArray){
            if(set.contains(ch))
                return ch;
            set.add(ch);
        }

        return Character.MIN_VALUE; //Kind of like null character (non existent first repeated, all are unique)
    }

    //simplest implementation of a hash function
    public int hash(int number){
        return number % 100;    //assuming 100 is the size of the hashtable/size of internal array
    }
    public int hash(String key){
        int hash = 0;
        for(char ch : key.toCharArray())
            hash += ch; //implicit type casting (char to int using += to int)

        return hash % 100;    //assuming 100 is the size of the hashtable/size of internal array
    }

    //Most repeated elements
    //Input: [1, 2, 2, 3, 3, 3, 4]   =>  Output: 3
    public int mostFrequent(int arr[]){
        Map<Integer, Integer> map = new HashMap<>(); // <Value, Repeats>

        for(int i=0; i<arr.length; i++){
            if(map.containsKey(arr[i]))
                map.put(arr[i], map.get(arr[i])+1);
            else
                map.put(arr[i], 1);
        }

        int count = 0, val=0;
        for(var elem : arr){
            if(map.get(elem) > count){
                val=elem;
                count++;
            }
        }

        if(count == 0)
            throw new IllegalStateException();

        return val;
    }

    // Given an array of integers, count the number of unique pairs of integers that have difference k
    // Input: [1, 7, 5, 9, 2, 12, 3],  K=2    =>   Output: 4
    // We have four pairs with difference 2: (1, 3), (3, 5), (5, 7), (7, 9). Note that we only want the number of these pairs, not the pairs themselves
    public int countPairsWithDiff(int arr[], int k){
        Map<Integer, Integer> map = new HashMap<>(); // <Value, Value+K>

        //pair each key with value of key+k
        for(int i : arr)
            map.put(i, i+k);

        //for each key, see if its value (i+k) exists in the map, if so add to count
        int count = 0;
        for(int i : arr)
            if(map.containsKey(map.get(i)))
                count++;

        return count;
    }

    //More optimized (memory wise) by using Set instead of Hash table
    public int countPairsWithDiff_V2(int[] numbers, int difference) {
        // For a given number (a) and difference (diff), number (b) can be: [b = a + diff]  or   [b = a - diff]
        //
        // We can iterate over our array of numbers, and for each number,
        // check to see if we have (current + diff) or (current - diff).
        // But looking up items in an array is an O(n) operation. With this
        // algorithm, we need two nested loops (one to pick a,
        // and the other to find b). This will be an O(n^2) operation.
        //
        // We can optimize this by using a set. Sets are like hash tables
        // but they only store keys. We can look up a number in constant time.
        // No need to iterate the array to find it.

        // So, we start by adding all the numbers to a set for quick look up.
        Set<Integer> set = new HashSet<>();
        for (var number : numbers)
            set.add(number);

        // Once we're done, remove this number from our set so we don't double count it.
        var count = 0;
        for (var number : numbers) {
            if (set.contains(number + difference))
                count++;
            if (set.contains(number - difference))
                count++;
            set.remove(number);
        }
        return count;
    }

    //Given an array of integers, return indices of the two numbers such that they add up to a specific target. Assume that each input has exactly one solution, and you may not use the same element twice.
    //Input: [2, 7, 11, 15], target = 9    =>     Output: [0, 1] (because 2 + 7 = 9)
    public int[] twoSum(int arr[], int target){
        Map<Integer, Integer> map = new HashMap<>(); // <Value, Index>

        for(int i=0; i<arr.length; i++){
            int complement = target - arr[i];
            if(map.containsKey(complement))
                return new int[] {map.get(complement), i};
            map.put(arr[i], i);
        }
        return null;
    }
}
