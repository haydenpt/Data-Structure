import java.util.Arrays;

public class StackTest<E> {
    public static void main(String[] arg) {
        Stack<Integer> stack= new StackArray<>(1);
        System.out.println(stack.isEmpty()); // true
        stack.push(5);
        stack.push(69);
        System.out.println(stack.size());           // 2
        System.out.println(stack.pop());            // 69
        System.out.println(stack.isEmpty());        // false
        System.out.println(stack.top());            // 5
        System.out.println(stack.pop());            // 5
        System.out.println(stack.isEmpty());        // true

        Integer[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        String[] s = {"Jack", "Kate", "Hurley", "Jin", "Michael"};
        System.out.println("a: " + Arrays.toString(a));
        System.out.println("s: " + Arrays.toString(s));
        System.out.println("Reversing...");

        StackArray.reverse(a);
        StackArray.reverse(s);
        System.out.println("a: " + Arrays.toString(a));
        System.out.println("s: " + Arrays.toString(s));

    }


}
