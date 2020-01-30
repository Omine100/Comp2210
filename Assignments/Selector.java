import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  Matthew Browning (mrb0094@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version TODAY
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
    * Returns the minimum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the minimum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T min(Collection<T> coll, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      } else if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      Iterator<T> iter = coll.iterator();
      T min = iter.next();
      
      while (iter.hasNext()) {
         T current = iter.next();
         if (comp.compare(min, current) > 0) {
            min = current;
         }
      }
      return min;
   }


   /**
    * Selects the maximum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the maximum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T max(Collection<T> coll, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      } else if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      Iterator<T> iter = coll.iterator();
      T max = iter.next();
      
      while (iter.hasNext()) {
         T current = iter.next();
         if (comp.compare(max, current) < 0) {
            max = current;
         }
      }
      return max;
   }


   /**
    * Selects the kth minimum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth minimum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth minimum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      } else if (k > coll.size() || k <= 0){
         throw new NoSuchElementException();
      } else if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      Iterator<T> iter = coll.iterator();
      ArrayList<T> collCopy = new ArrayList<T>();
      while (iter.hasNext()) {
         collCopy.add(iter.next());
      }
      java.util.Collections.sort(collCopy, comp);
      
      int distinct = collCopy.size();
      int total = collCopy.size();
      
      int i;
      for (i = 0; i < collCopy.size() - 1; i++) {
         while ((collCopy.size() > 1) && (collCopy.size() - 1 > i) && (collCopy.get(i) == collCopy.get(i + 1))) {
            distinct--;
            collCopy.remove(i);
            if (distinct < k) {
               throw new NoSuchElementException();
            }
         }
      }
      
      T min = collCopy.get(k - 1);
      
      return min;
   }
   

   /**
    * Selects the kth maximum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth maximum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth maximum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      } else if (k > coll.size() || k <= 0) {
         throw new NoSuchElementException();
      } else if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      Iterator<T> iter = coll.iterator();
      ArrayList<T> collCopy = new ArrayList<T>();
      while (iter.hasNext()) {
         collCopy.add(iter.next());
      }
      java.util.Collections.sort(collCopy, comp);
      
      int distinct = collCopy.size();
      int total = collCopy.size();
      
      int i;
      for (i = 0; i < collCopy.size() - 1; i++) {
         while ((collCopy.size() > 1) && (collCopy.size() - 1 > i) && (collCopy.get(i) == collCopy.get(i + 1))) {
            distinct--;
            collCopy.remove(i);
            if (distinct < k) {
               throw new NoSuchElementException();
            }
         }
      }
      
      T max = collCopy.get(collCopy.size() - k);
      
      return max;
   }
   

   /**
    * Returns a new Collection containing all the values in the Collection coll
    * that are greater than or equal to low and less than or equal to high, as
    * defined by the Comparator comp. The returned collection must contain only
    * these values and no others. The values low and high themselves do not have
    * to be in coll. Any duplicate values that are in coll must also be in the
    * returned Collection. If no values in coll fall into the specified range or
    * if coll is empty, this method throws a NoSuchElementException. If either
    * coll or comp is null, this method throws an IllegalArgumentException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the range values are selected
    * @param low     the lower bound of the range
    * @param high    the upper bound of the range
    * @param comp    the Comparator that defines the total order on T
    * @return        a Collection of values between low and high
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                         Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      } else if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      Iterator<T> iter = coll.iterator();
      int itemsInRange = 0;
      ArrayList<T> items = new ArrayList<T>();
      
      while (iter.hasNext()) {
         T current = iter.next();
         if ((comp.compare(current, low) >= 0) && (comp.compare(current, high) <= 0)) {
            items.add(current);
            itemsInRange++;
         }
      }
      
      if (itemsInRange == 0) {
         throw new NoSuchElementException();
      }
      
      return items;
   }


   /**
    * Returns the smallest value in the Collection coll that is greater than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the ceiling value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the ceiling value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      } else if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      Iterator<T> iter = coll.iterator();
      T smallestValue = key;
      boolean present = false;
      
      while (iter.hasNext()) {
         T current = iter.next();
         if (!present && (comp.compare(current, key) >= 0)) {
            smallestValue = current;
            present = true;
         } else if (comp.compare(current, key) >= 0 && comp.compare(current, smallestValue) <= 0){
            smallestValue = current;
         }
      }
      
      if (!present) {
         throw new NoSuchElementException();
      }
      
      return smallestValue;
   }


   /**
    * Returns the largest value in the Collection coll that is less than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the floor value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the floor value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      } else if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      Iterator<T> iter = coll.iterator();
      T largestValue = key;
      boolean present = false;
      
      while (iter.hasNext()) {
         T current = iter.next();
         if (!present && (comp.compare(current, key) <= 0)) {
            largestValue = current;
            present = true;
         } else if (comp.compare(current, key) <= 0 && comp.compare(current, largestValue) >= 0){
            largestValue = current;
         }
      }
      
      if (!present) {
         throw new NoSuchElementException();
      }
      
      return largestValue;
   }
}
