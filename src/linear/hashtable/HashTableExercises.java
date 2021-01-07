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


}
