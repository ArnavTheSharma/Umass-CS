package umass.cics160.Assignments;
import java.util.LinkedList;

public class quiz4 {
    public static void main(String[] args) {
        return;
    }
    public static int maximum(LinkedList<Integer> ll, int upToHere) {
        int max = ll.get(0);
        int index = 0;
        for (int i=0; i <= upToHere; i++) {
            if (ll.get(i) > max) {
                max = ll.get(i);
                index = i;
            }
        }
        return index;
    }
}
