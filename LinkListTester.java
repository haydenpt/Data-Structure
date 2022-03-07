public class LinkListTester {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        int n = 10;

        // Test addFirst()
        for (int i = 0; i < n; i++) {
            list.addFirst(n);
            System.out.println("Added " + n + " to first position. Current size is " + list.getCurrentSize());
        }

        // Test removeFirst()
        for (int i = n - 1; i >= 0; i--) { // for loop runs for 10 times
            int x = list.removeFirst();
            System.out.println(x + " was removed. Current size is " + list.getCurrentSize());
        }

        // Test addLast()
        for (int i = 0; i < n; i++) {
            list.addLast(n);
            System.out.println("Added " + n + " to position " + (list.getCurrentSize() - 1));
        }

        // Test iteratorHelper class
        for(int i : list) {
            System.out.print("["+ i + "]");
        }
        System.out.println();

        /*Test addMiddle()
            If current size is event, new number will be added to (n/2 +1)th position
            If current size is odd, new number will be added to ((n+1)/2 +1)th position
         */

        System.out.println("Current size: " + list.getCurrentSize());
        int middle = 69;
        list.addMiddle(middle);
        System.out.println("Added " + middle + " to the middle of the list" );

        // Show list
        for(int i : list) {
            System.out.print("["+ i + "]");
        }
        System.out.println();

        // Test addMiddle() again
        System.out.println("Current size: " + list.getCurrentSize());
        middle = 420;
        list.addMiddle(middle);
        System.out.println("Added " + middle + " to the middle of the list" );

        // Show list
        for(int i : list) {
            System.out.print("["+ i + "]");
        }
        System.out.println();

        // Test removeLast()
        for (int i = 0; i < n; i++) {
            int x = list.removeLast();
            System.out.println(x + " was removed from position " + (n - 1 - i));
        }


    }
}
