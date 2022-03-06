public class LinkListTester {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        int n = 10;

        // Test addFirst()
        for (int i = 0; i < n; i++) {
            list.addFirst(n);
            System.out.println("Added " + n + " to first position. Current size is " + list.getCurrentSize());
        }

        for(int i : list) {
            System.out.println(i);
        }

        // Test removeFirst()
        for (int i = n - 1; i >= 0; i--) { // for loop runs for 10 times
            int x = list.removeFirst();
            System.out.println(x + " was removed. Current size is " + list.getCurrentSize());
        }

        for (int i = 0; i < n; i++) {
            list.addLast(n);
            System.out.println("Added " + n + " to position " + (list.getCurrentSize() - 1));
        }

        // Test removeLast()
        for (int i = 0; i < n; i++) {
            int x = list.removeLast();
            System.out.println(x + " was removed from position " + (n - 1 - i));
        }

    }
}
