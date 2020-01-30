import java.util.Iterator;
import java.util.Random;
import java.util.NoSuchElementException;

public class DoubleEndedListSubmition<T> implements DoubleEndedList<T> {
   private Node front, back;
   private int size;
   
   public DoubleEndedListSubmition() {
      front = null;
      size = 0;
   }
   
   private class Node {
      private T element;
      private Node next;
      
      public Node(T t) {
         element = t;
      }
      
      public Node(T t, Node n) {
         element = t;
         next = n;
      }
   }
   
   public boolean isEmpty() {
      return size == 0;
   }
   
   public int size() {
      return size;
   }
   
   public void addFront(T element) {
      if (element == null) {
         throw new IllegalArgumentException();
      }
      
      Node newElement = new Node(element);
      
      if (front == null) {
         front = newElement;
         back = newElement;
      } else {
         newElement.next = front;
         front = newElement;
      }
      
      size++;
   }
   
   public void addLast(T element) {
      if (element == null) {
         throw new IllegalArgumentException();
      }
      
      Node newElement = new Node(element);
      newElement.element = element;
      
      if (front == null) {
         front = newElement;
         rear = newElement;
      } else {
         rear.next = front;
         rear = newElement;
      }
      
      size++;
   }
   
   public T removeFirst() {
      if (size == 0) {
         return null;
      }
      
      T removedValue = front.element;
      
      if (size == 1) {
         front == null;
         rear == null;
      } else {
         front = front.next;
      }
      
      size--;
      
      returned removedValue;
   }
   
   public T removeLast() {
      if (size == 0) {
         return null;
      }
      
      T removedValue = rear.element;
      
      if (size == 1) {   
         front = null;
         back = null;
      } else {
         rear = null;
      }
      
      size--;
      
      return removedValue;
   }
   
   public Iterator<T> iterator() {
      return new iterator();
   }
   
   private clas iterator implements Iterator<T> {
      private Node current = front;
      
      public boolean hasNext() {
         return (current != null);
      }
      
      public void remove() {
         throw new UnsupportedOperationException();
      }
      
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         T result = current.element;
         current = current.next;
         return result;
      }
   }
}