public class Stack {
    public static void main(String[] args) {
        int n = 10;
        Array<Integer> stack = new Array<Integer>(n);


        // Test addFirst()
        for (int i = 0; i < n; i++) {
            stack.addFirst(i);

        }

        for (int i = 0; i < n; i++) {
            System.out.println(stack.getItem(i));
        }
    }
}
