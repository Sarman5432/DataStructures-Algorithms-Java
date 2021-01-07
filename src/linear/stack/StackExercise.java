package linear.stack;

import java.util.*;

public class StackExercise {
    public void reverseString(String str){
        if(str == null) throw new IllegalArgumentException();

        Stack<Character> stack = new Stack<>();
        for(char ch : str.toCharArray())
            stack.push(ch);

//      String newString = "";
//      while (!stack.empty())
//          newString += stack.pop();   //Strings are Immutable in Java. A new string is created, allocating more memory per +=. Thus expensive

        //Use String buffer/append rather than += char to save alot of memory
        StringBuffer newString = new StringBuffer();
        while (!stack.empty())
            newString.append(stack.pop());

        System.out.println(newString);
    }

    public void reverseStringWithoutStack(String str){
        StringBuffer newString = new StringBuffer();
        for (int i = 0; i < str.length(); i++)
            newString.append(str.charAt(str.length() - 1 - i));
        System.out.println(newString);
    }

    public boolean balancedBracketsCheck_V2(String str){
        // TODO: check why Using ArrayDeque is faster than using Stack class
        Deque<Character> stack = new ArrayDeque<Character>();

        List<Character> opening = Arrays.asList('(', '{', '[', '<');
        List<Character> closing = Arrays.asList(')', '}', ']', '>');

        for(char ch : str.toCharArray()){
            if(opening.contains(ch))
                stack.push(ch);
            if(closing.contains(ch)) {
                if(stack.isEmpty()) return false;
                var top = stack.pop();
                if (opening.indexOf(top) != closing.indexOf(ch))
                    return false;
            }
        }
        return stack.isEmpty();
    }

    public boolean balancedBracketsCheck(String str){
        if(str=="") return true;

        Stack<Character> stack = new Stack<>();
        char[] opening = {'(', '{', '[', '<'};
        char[] closing = {')', '}', ']', '>'};
        char top = ' ';

        for(char ch : str.toCharArray()){
            boolean openAdded = false;
            for(int i=0; i<opening.length; i++){
                if (ch == opening[i]) {
                    stack.push(ch);
                    if (!stack.isEmpty())
                        top = stack.peek();
                    openAdded = true;
                    break;
                }
            }
            if(openAdded != true){
                if(stack.isEmpty()){
                    return false;
                }
                for(char close : closing) {
                    if (ch == close) {
                        if(top == getOpeningBracket(ch)){
                            stack.pop();
                            if (!stack.isEmpty())
                                top = stack.peek();
                            break;
                        }
                    }
                }
            }
        }
        return stack.isEmpty();
    }

    public char getOpeningBracket(char closing){
        switch (closing) {
            case ')':
                return '(';
            case '}':
                return '{';
            case ']':
                return '[';
            case '>':
                return '<';
        }
        throw new IllegalArgumentException();
    }
}


