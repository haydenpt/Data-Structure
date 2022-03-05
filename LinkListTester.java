public class LinkListTester {
    public static void main(String[] args) {
        ListI<Integer> list = new LinkedList<Integer>();
        int n = 10;

        for(int i = 0; i<n; i++)
            list.addFirst(n);

        int x;
        for(int i = n-1; i>=0; i--)
            x = list.removeFirst();

    }
}
