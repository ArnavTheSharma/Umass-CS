import java.util.StringArrayList;

public class Lecture5 {
    public static void main(String[] args) {
        StringList l = new StringArrayList();
        l.add("Hello");
        l.add("World");
        System.out.println(l); // will print junk (which is just a useless memory address. it'll output like StringArrayList@4c98385c because we haven't written our own toString() method)
    }

    // exampletoString fn 
    public String toString(StringArrayList l) {
        String result = "[";
        for (int i = 0; i < l.size; i++) {
        result += l.get(i);
        if (i!=l.size-1){
        result += ", "; //After all but last string
        }
        }
        result += "]";
        return result;
        }
}
