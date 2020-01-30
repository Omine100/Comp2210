import java.util.Iterator;
import java.util.Random;
import java.util.NoSuchElementException;

public class DoubleEndedListSubmition<T> implements DoubleEndedList<T> {
   private Node front, rear;
   private int size;
   
   private class Node {
      private T element;
      private Node next;
      private Node prev;
   
      public Node(T element) {
         element = element;
         next = null;
         prev = null;
      }
     
      public Node(T element, Node next) {
         element = element;
         next = next;
      }
      
      public Node(T element, Node next, Node prev) {
         element = element;
         next = next;
         prev = prev;
      }
   }
   
   public DoubleEndedListSubmition() {
      front = null;
      rear = null;
      size = 0;
   }
   
   public boolean isEmpty() {
      return size == 0;
   }
   
   public int size() {
      return size;
   }
   
   public void addFirst(T element) {
      if (element == null) {
         throw new IllegalArgumentException();
      }
      
      Node newElement = new Node(element);
      
      if (size == 0) {
         front = newElement;
         rear = newElement;
      } else {
         front.prev = newElement;
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
         newElement.prev = rear;
         rear.next = newElement;
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
         front = null;
         rear = null;
      } else {
         front = front.next;
         front.prev = null;
      }
      
      size--;
      
      return removedValue;
   }
   
   public T removeLast() {
      if (size == 0) {
         return null;
      }
      
      T removedValue = rear.element;
      
      if (size == 1) {   
         front = null;
         rear = null;
      } else {
         rear = rear.prev;
         rear.next = null;
      }
      
      size--;
      
      return removedValue;
   }
   
   public Iterator<T> iterator() {
      return new iterator();
   }
   
   private class iterator implements Iterator<T> {
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