public class ArrayTester {
    public static void main(String[] args) {
        int n = 10;
        Array<Integer> stack = new Array<Integer>(n);


        // Test addFirst()
        for (int i = 0; i < n; i++) {
            stack.addFirst(i);
        }

        // Get element at index
        for (int i = 0; i < stack.getCounter(); i++) {
            System.out.print("[" + stack.getItem(i) + "]");
        }
        System.out.println();

        // Test removeFirst()
        System.out.println(stack.removeFirst());

    }
}
