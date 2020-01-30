/**
 * Applies the linear scan strategy to counting the number of negative
 * values in an array.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2017-08-22
 */
public class CountNegatives {

   public static void main(String args[]) {
      int[] array = {1, 2, -3}; 
      System.out.println(countNegatives(array));
   }
   
   /**
    * Returns the number of negative values in the given array.
    */
   public static int countNegatives(int[]a) {
      int negativeCount = 0;
      for(int i = 0; i < a.length; i++) {
         if(a[i] < 0) {
            negativeCount++;
         }
      }
      return negativeCount;
   }
}
