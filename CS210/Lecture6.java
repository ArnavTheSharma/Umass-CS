import java.util.ArrayList;
import java.util.LinkedList;

// doing something like int n = "banana"; wouldn't be executed because the compiler notices this type mismatch error and thus doesn't compile it 

public class Lecture6 {

    // Creates a new list of Integers that takes in a list and outputs the first n elements of the list
    static List<Integer> firstN(List<Integer> l,int n){
        List<Integer> r = new ArrayList<>();
        for(int i=0; i < n; i++){
            r.add(l.get(i));
        }
    return r;
    }


    // Now for generics, this is INCORRECT 
    static List<E> firstN(List<E> l,int n){ // correct Java syntax requires adding an extra <E> BEFORE the return type
        List<E> r = new ArrayList<>(); 
        return r;
    }

    // this is CORRECT

    static <E> List<E> firstN(List<E> l,int n){
        List<E> r = new ArrayList<>();
        return r;
    }
}

private class Pair <K, V> {
    public final K k;
    public final V v;

    public static <K, V> boolean areEqual(Pair<K, V> p1, Pair<K, V> p2) { // returns a Bool about if they're equal. Need <K, V> before return type and after the private/public/static
        return (p1.k.equals(p2.k) && p1.v.equals(p2.v));
    }

    public static void main(String[] args) {

        // In link lists, can we use a for each loop to access each element?
    
        List<String> l = new LinkedList<String>();
        List<String> l2 = new LinkedList<>();
        // Note: l2 is the more preferred convention, both do the same though
        l.add("now");
        l.add("the");
        l.add("time");
        l.add(1,"is");

        for (String s : l){
            System.out.println(s);
        }

        // The answer is we'd get a compilation error: Can only iteratre through array or java.lang.iterable
        //      We need to implement the iterable interface!

        // This Iterator<E> Object points to the beginning of the list. Now the compiler lets us write a for each loop because it is iterable now
        // What we do is we make a new iterator object, it, and run a while loop saying:
        public interface List<E> extends Iterable<E> {
            public boolean hasNext() {
                // Bool tha tells if it has a next element or not.
                throw new UnsupportedOperationException("Unimplemented method 'hasNext'");
            }
            
                public E next() {
                // returns value of current item AND sets iterator to the next item
                throw new UnsupportedOperationException("Unimplemented method ‘next'");
            }
        }
            
        LinkedList<String> ll = new LinkedList<>();
        Iterator<String> it = ll.iterator(); // notice how we do the LinkedList name . iterator() ---> This is why we do the LinkedList interface extends Iterator 
        

        while (it.hasNext()) {
            String s = it.next();
            System.out.println(s);
        
        }

        Iterator<String> list = ll.iterator();
        for (String s = list.next(); list.hasNext(); s = list.next()) { // for loop has (initialization; condition; update)
            System.out.println(s);
        }
        // the s = list.next runs ONCE (e.g. int i = 0; i < 4; i++)
        // This would return all elements but the last one because list.hasNext() in last element is False, and so it brekas then.
        //       Also if list is empty, causes null pointer exception




    }
}

