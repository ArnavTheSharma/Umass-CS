public class App {
//Code for Lecture 11, CICS 210, UMASS Spring 2025
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        System.out.println("Test of DiscountHashTable");
        DiscountSet<Integer> s = new DiscountHashTable<>();
            s.add(2051);
            s.add(7);
            s.add(1030);
            s.add(1028);
            s.add(13);
            s.add(5);
            s.remove(1028);
            System.out.println("Set size is  " + Integer.toString(s.size()));
            s.ReportAll();

        System.out.println("\nTest of DiscountMap");
        DiscountMap<Integer,String> m =new DiscountHashMap<>();
         m.put(1,"A");
         m.put(2,"B");
         m.put(3,"C");
         System.out.println(m.get(1));
         m.put(1,"a");
         System.out.println(m.get(1));
         System.out.println("Map size is  " + Integer.toString(m.size()));




    
    }
}
