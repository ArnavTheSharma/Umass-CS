import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class PrimeTest {
    @Test
    public void test1(){
        assertEquals(true, Prime.isPrime(17));
    }

    @Test
    public void test2(){
        assertEquals(false, Prime.isPrime(8));
    }
    
}
