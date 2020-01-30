import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Matthew Browning (mrb0094@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  TODAY
*
*/
public final class Selector {

   /**
    * Can't instantiate this class.
    *
    * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
    *
    */
   private Selector() { }


   /**
    * Selects the minimum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int min(int[] a) {
      if(a == null || a.length == 0) {
         throw new IllegalArgumentException();
      } 
      
      int min1 = a[0];
      int i;
      for(i = 0; i < a.length; i++) {
         if(a[i] < min1) {
            min1 = a[i];
         }
      }
      return min1;
   }


   /**
    * Selects the maximum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int max(int[] a) {
      if(a == null || a.length == 0) {
         throw new IllegalArgumentException();
      } 
      
      int max1 = a[0];
      int i;
      for(i = 0; i < a.length; i++) {
         if(a[i] > max1) {
            max1 = a[i];
         }
      }
      return max1;
   }


   /**
    * Selects the kth minimum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth minimum value. Note that there is no kth
    * minimum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmin(int[] a, int k) {
      if(a == null || a.length == 0 || k < 1 || k > a.length) {
         throw new IllegalArgumentException();
      }
      
      int min1 = 0;
      int distinct = 1;
      
      int[] b = Arrays.copyOf(a, a.length);
      Arrays.sort(b);
      int holder = b[0];
      
      if(k == 1) {
         return holder;
      }
      
      int i;
      for(i = 1; i < a.length; i++) {
         if(b[i] != holder){
            distinct = distinct + 1;
            if (distinct == k) {
               min1 = b[i];
            }
         }
         holder = b[i];
      }
      
      if(distinct < k) {
         throw new IllegalArgumentException();
      }
      return min1;
   }


   /**
    * Selects the kth maximum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth maximum value. Note that there is no kth
    * maximum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmax(int[] a, int k) {
      if(a == null || a.length == 0 || k < 1 || k > a.length) {
         throw new IllegalArgumentException();
      }
      
      int max1 = 0;
      int distinct = 1;
      
      int[] b = Arrays.copyOf(a, a.length);
      Arrays.sort(b);
      int holder = b[b.length - 1];
      
      if(k == 1) {
         return holder;
      }
      
      int i;
      for(i = b.length - 1; i >= 0; i--) {
         if(b[i] != holder){
            distinct = distinct + 1;
            if (distinct == k) {
               max1 = b[i];
            }
         }
         holder = b[i];
      }
      
      if(distinct < k) {
         throw new IllegalArgumentException();
      }
      return max1;
   }


   /**
    * Returns an array containing all the values in a in the
    * range [low..high]; that is, all the values that are greater
    * than or equal to low and less than or equal to high,
    * including duplicate values. The length of the returned array
    * is the same as the number of values in the range [low..high].
    * If there are no qualifying values, this method returns a
    * zero-length array. Note that low and high do not have
    * to be actual values in a. This method throws an
    * IllegalArgumentException if a is null or has zero length.
    * The array a is not changed by this method.
    */
   public static int[] range(int[] a, int low, int high) {
      if(a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      
      int count = 0;
      int index = 0;
      int i;

      for(i = 0; i < a.length; i++) {
         if(low <= a[i] && a[i] <= high) {
            count++;
         }
      }
      
      int[] valuesInRange = new int[count];
      if(count == 0) {
         return valuesInRange;
      } else {
         for(i = 0; i < a.length; i++) {
            if(low <= a[i] && a[i] <= high) {
               valuesInRange[index] = a[i];
               index++;
            }
         }
      }

      return valuesInRange;
   }


   /**
    * Returns the smallest value in a that is greater than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int ceiling(int[] a, int key) {
      if(a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      
      int smallestValue = 0;
      int i;
      
      for(i = 0; i < a.length; i++) {
         if (a[i] >= key && a[i] <= smallestValue) {
            smallestValue = a[i];
         }
      }
      
      return smallestValue;
   }


   /**
    * Returns the largest value in a that is less than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int floor(int[] a, int key) {
      return -99;
   }

}
