/**
 * Provides a sum function on arrays.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2018-03-26
 */
public class ArraySum {

	/** Returns the sum of values in the given array. */
   public static int sum(int[] a, int left, int right) {
      int sum = 0;
		for (int i = 0; i <= right; i++) {
			sum = sum + i;
		}
		return sum;
   }
   
   /** Drives execution. */
   public static void main(String[] args) {
      int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
      
      for (int i = 1; i < a.length; i++) {
			int s1 = sum(a, i, i + 1);
			System.out.println(i + ": " + s1);
		}    
   }
}