import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Iterator;

import java.util.stream.Collectors;

/**
 * Provides an implementation of the WordLadderGame interface. 
 *
 * @author Matthew Browning (mrb0094@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2019-03-29
 */
public class Doublets implements WordLadderGame {
   private TreeSet<String> lexicon;
	private boolean lexiconComplete;
   
    public Doublets(InputStream in) {
        try {
            lexicon = new TreeSet<String>();
            
            Scanner s = new Scanner(new BufferedReader(new InputStreamReader(in)));
            Scanner lineScanner;
            while (s.hasNext()) {
                String str = s.next();
                lineScanner = new Scanner(str);
                lineScanner.useDelimiter(" ");
                if (lineScanner.hasNext()) {
                  lexicon.add(lineScanner.next().toLowerCase());
                }
                s.nextLine();
                lineScanner.close();
            }
            s.close();
            in.close();
        }
        catch (java.io.IOException e) {
            System.err.println("Error reading from InputStream.");
            System.exit(1);
        }

		lexiconComplete = true;
    }
    
    public int getHammingDistance(String str1, String str2) {
		if (str1.length() != str2.length()) {
			return -1;
		}
      
      int hammingDistance = 0;
      str1 = str1.toLowerCase();
      str2 = str2.toLowerCase();
      
      for (int i = 0; i < str1.length(); i++) {
         if (str1.charAt(i) != str2.charAt(i)) {
            hammingDistance++;
         }
      }
      
      return hammingDistance;
	}
    
	//Need to do all of this
    public List<String> getMinLadder(String start, String end) {
	   start = start.toLowerCase();
      end = end.toLowerCase();
      ArrayList<String> backwards = new ArrayList<String>();
      List<String> minLadder = new ArrayList<String>();

      if (start.equals(end)) {
         minLadder.add(start);
         return minLadder;
      }

      if (getHammingDistance(start, end) == -1) {
         return minLadder;
      }

      if(isWord(start) && isWord(end)) {
         backwards = bfs(start, end);
      }

      if (backwards.isEmpty()) {
         return minLadder;
      }

      for (int i = backwards.size() -1; i >= 0; i--) {
         minLadder.add(backwards.get(i));
      }
      
      return minLadder;
	}
   
    private ArrayList<String> bfs(String start, String end) {
       Deque<Node> queue = new ArrayDeque<Node>();
      HashSet<String> visited = new HashSet<String>();
      ArrayList<String> backwards = new ArrayList<String>();
      visited.add(start);
      queue.addLast(new Node(start, null));
      Node endNode = new Node(end, null);

      outerloop:
      while (!queue.isEmpty()) {
         Node n = queue.removeFirst();
         String word = n.word;
         List<String> neighbors = getNeighbors(word);
         for (String neighbor : neighbors) {
            if(!visited.contains(neighbor)) {
               visited.add(neighbor);
               queue.addLast(new Node(neighbor, n));
               if (neighbor.equals(end)) {
                  endNode.predecessor = n;
                  break outerloop;
               }
            }
         }
      }
      
      if(endNode.predecessor == null) {
         return backwards;
      }
      Node m = endNode;
      while (m != null) {
         backwards.add(m.word);
         m = m.predecessor;
      }
      return backwards;
    }
   
   private class Node {
      String word;
      Node predecessor;
   
      public Node(String s, Node pred) {
         word = s;
         predecessor = pred;
      }
   }
    
    public List<String> getNeighbors(String word) {
		List<String> neighbors = new ArrayList<String>();
      Iterator<String> iterator = lexicon.iterator();
      
      while (iterator.hasNext()) {
         String secondWord = iterator.next();
         if (getHammingDistance(word, secondWord) == 1) {
            neighbors.add(secondWord);
         }
      }      
      return neighbors;
	}
    
    public int getWordCount() {
		return lexicon.size();
	}
    
    public boolean isWord(String word) {
		word = word.toLowerCase();

		return lexicon.contains(word);
	}
    
    public boolean isWordLadder(List<String> sequence) {
      if (lexiconComplete == false) {
         throw new IllegalStateException();
      }
      
      if (sequence.isEmpty()) {
         return false;
      } else if (sequence.size() == 1) {
         return true;
      }
    
      String firstString = "";
      String secondString = "";
    
      for (int i = 0; i < sequence.size() - 1; i++) {
         firstString = sequence.get(i);
         secondString = sequence.get(i + 1);
         
         if (!isWord(firstString) || !isWord(secondString)) {
            return false;
         }
         if (getHammingDistance(firstString, secondString) != 1) {
            return false;
         }
      }

		return true;
	}
}