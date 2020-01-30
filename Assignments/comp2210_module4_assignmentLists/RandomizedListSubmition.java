import java.util.Iterator;
import java.util.Random;
import java.util.NoSuchElementException;

public class RandomizedListSubmition<T> implements RandomizedList<T> {
   private T[] elements;
   private int size;
   private static final int INITIAL_LENGTH = 1;
   
   @SuppressWarnings("unchecked")
   public RandomizedListSubmition() {
      elements = (T[]) new Object[INITIAL_LENGTH];
      size = 0;
   }
   
   public boolean isEmpty() {
      return size == 0;
   }
   
   public int size() {
      return size;
   }
   
   @SuppressWarnings("unchecked")
   public void resize(int length) {
      T[] array = (T[]) new Object[length];
      int i;
      
      for (i = 0; i < size(); i++) {
         array[i] = elements[i];
      }
      
      elements = array;
   }
   
   public void add(T element) {
      if (element == null) {
         throw new IllegalArgumentException();
      } else if (elements.length == size) {
         resize(elements.length * 2);
      }
      elements[size] = element;
      size++;
   }
   
   public T remove() {
      if (size == 0) {
         return null;
      }
      
      Random random = new Random();
      int randomSizeValue = random.nextInt(size);
      T removedValue = elements[randomSizeValue];
      elements[randomSizeValue] = null;
      
      if (randomSizeValue == (size - 1)) {
         size--;   
      } else if (randomSizeValue != (size - 1)) {
         elements[randomSizeValue] = elements[size - 1];
         elements[size - 1] = null;
         size--;
      }
      
      if (size > 0 && size < (elements.length / 4)) {
         resize(elements.length / 2);
      }
      
      return removedValue;
   }
   
   public T sample() {
      if (size == 0) {
         return null;
      }
      
      Random random = new Random();
      int randomSizeValue = random.nextInt(size);
      T randomValue = elements[randomSizeValue];
      return randomValue;
   }
   
   @SuppressWarnings("unchecked")
   public Iterator<T> iterator() {
      return new iterator(elements, size);
   }
   
   public class iterator<T> implements Iterator<T> {
      private T[] iteratedElements;
      private int length;
      
      public iterator(T[] iteratedElementsIn, int lengthIn) {
         iteratedElements = iteratedElementsIn;
         length = lengthIn;
      }  
      
      public boolean hasNext() {
         return (length > 0);
      }
      
      public void remove() {
         throw new UnsupportedOperationException();
      }
      
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         
         Random random = new Random();
         int randomSizeValue = random.nextInt(size);
         T randomValue = iteratedElements[randomSizeValue];
         
         if (randomSizeValue != (length - 1)) {
            iteratedElements[randomSizeValue] = iteratedElements[length - 1];
            iteratedElements[length - 1] = randomValue;
         }
         length--;
         return randomValue;
      }
   }
}