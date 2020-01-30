import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class SelectorTest {

   @Test
   public void testSelectorKmin1() {
      int[] a = {5, 7};
      int k = 2;
      int expected = 7;
      int actual = Selector.kmin(a, k);
      assertEquals(expected, actual);
   }
   
   @Test
   public void testSelectorKmax1() {
      int[] a = {5, 7};
      int k = 1;
      int expected = 7;
      int actual = Selector.kmax(a, k);
      assertEquals(expected, actual);
   }
   
   @Test
   public void testSelectorKmax2() {
      int[] a = {1, 3, 5, 7, 9};
      int k = 1;
      int expected = 9;
      int actual = Selector.kmax(a, k);
      assertEquals(expected, actual);
   }
}
