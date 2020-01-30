import java.util.Scanner;
import java.io.FileReader;

import java.util.List;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class WordSearchGameEngine implements WordSearchGame {
   private TreeSet<String> wordTree;
   private String[][] board;
   private boolean wordTreeComplete;
   private int length; //If the board has to be square, height and width are the same length.
   private Boolean[][] visited;
   private SortedSet<String> validWords;
   private int minimumLength;
   private List<Integer> path;
   private List<Integer> actualPath;
   
   public WordSearchGameEngine() {
      wordTree = new TreeSet<String>();
      path = new ArrayList<Integer>();
      validWords = new TreeSet<String>();
      actualPath = new ArrayList<Integer>();
   }

   public void loadLexicon(String fileName) {
      if (fileName == null) {
         throw new IllegalArgumentException("No file name.");
      }
      
      try {
         Scanner scanner = new Scanner(new FileReader(fileName));
         Scanner wordScanner;
         while (scanner.hasNext()) {
            String nextWord = scanner.nextLine();
            wordScanner = new Scanner(nextWord);
            wordScanner.useDelimiter(" ");
            while (wordScanner.hasNext()) {
               wordTree.add(wordScanner.next().toLowerCase());
            }
         }
         scanner.close();
      } catch (Exception e) {
         throw new IllegalArgumentException("File name error.");
      }
      
      wordTreeComplete = true;
   }
   
   public void setBoard(String[] letterArray) {
      if (letterArray == null || squareCheck(letterArray.length) == false) {
         throw new IllegalArgumentException("Board setting error.");
      }
      
      length = (int) (Math.sqrt(letterArray.length));
      board = new String[length][length];
      visited = new Boolean[length][length];
      int count = 0;
      for (int i = 0; i < length; i++) {
         for (int j = 0; j < length; j++) {
            visited[i][j] = false;
            board[i][j] = letterArray[count].toLowerCase();
            count++;
         }
      }
   }
   
   private boolean squareCheck(int length) {
      double square = Math.sqrt(length);
      
      return ((square - Math.floor(square)) == 0);
   }
   
   public String getBoard() {
      String stringBoard = "";
      for (String[] string: board) {
         for (String stringAddition: string) {
            stringBoard += stringAddition;
         }
      }
      
      return stringBoard;
   }
   
   public SortedSet<String> getAllValidWords(int minimumWordLength) {
      minimumLength = minimumWordLength;
      validWords.clear();
      
      if (wordTreeComplete == false) {
         throw new IllegalStateException("The word tree is not loaded yet.");
      }
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException("Word length needs to be at least 1.");
      }
      
      for (int i = 0; i < length; i++) {
         for (int j = 0; j < length; j++) {
            locateWord(board[i][j], i, j);
         }
      }
      return validWords;
   }
   
   private void locateWord(String word, int x, int y) {
      if (isValidPrefix(word) == false) {
         return;
      }
      
      visited[x][y] = true;
      
      if (isValidWord(word) && word.length() >= minimumLength) {
         validWords.add(word.toUpperCase());
      }
      
      for (int i = -1; i <= 1; i++) {
         for (int j = -1; j <= 1; j++) {
            if ((x + i) <= ((int) length - 1)
               && (y + j) <= ((int) length - 1)
               && (x + i) >= 0 && (y + j) >= 0 
               && !visited[x + i][y + j]) {     
               visited[x + i][y + j] = true;
               locateWord(word + board[x + i][y + j], x + i, y + j);
               visited[x + i][y + j] = false;
            }
         }
      }
      visited[x][y] = false;
   }

   public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
      if (wordTreeComplete == false) {
         throw new IllegalStateException("The word tree is not loaded yet.");
      } else if (minimumWordLength < 1) {
         throw new IllegalArgumentException("Word length needs to be at least 1.");
      }
      
      int score = 0;
      
      for (String word: words) {
         int size = word.length();
         score += 1 + (size - minimumWordLength);
      }
      
      return score;
   }
   
   public boolean isValidWord(String wordToCheck) {
      if (wordTreeComplete == false) {
         throw new IllegalStateException("The word tree is not loaded yet.");
      }
      if (wordToCheck == null) {
         throw new IllegalArgumentException("There is no word to check.");
      }
      
      wordToCheck = wordToCheck.toLowerCase();
      
      return wordTree.contains(wordToCheck);
   }
   
   public boolean isValidPrefix(String prefixToCheck) {
      if (wordTreeComplete == false) {
         throw new IllegalStateException("The word tree is not loaded yet.");
      }
      if (prefixToCheck == null) {
         throw new IllegalArgumentException("There is no prefix to check.");
      }
      
      try {
         return wordTree.ceiling(prefixToCheck).startsWith(prefixToCheck);
      } catch (NullPointerException e) {
         System.out.println("Null pointer exception thrown.");
      }
      
      return false;
   }
   
   public List<Integer> isOnBoard(String wordToCheck) {
      if (wordTreeComplete == false) {
         throw new IllegalStateException("The word tree is not loaded yet.");
      } 
      if (wordToCheck == null) {
         throw new IllegalArgumentException("There is no word to check.");
      }
      
      wordToCheck.toUpperCase();
      path.clear();
      actualPath.clear();
   
      for (int i = 0; i < (int) length; i++) {
         for (int j = 0; j < length; j++) {
            if (Character.toUpperCase(board[i][j].charAt(0))
               == Character.toUpperCase(wordToCheck.charAt(0))) {
               int value = j + (i * length);
               path.add(value);
               recursion(wordToCheck, board[i][j], i, j);
               if (!actualPath.isEmpty()) {
                  return actualPath;
               }
               path.clear();
               actualPath.clear();
            }
         }
      }
      return path;
   }
   
   public void recursion(String wordToCheck, String current, int x, int y) {
      visited[x][y] = true;
      
      if (!(isValidPrefix(current))) {
         return;
      }
      if (current.toUpperCase().equals(wordToCheck.toUpperCase())) {
         actualPath = new ArrayList(path);
         return;
      }
      for (int i = -1; i <= 1; i++) {
         for (int j = -1; j <= 1; j++) {
            if (current.equals(wordToCheck)) {
               return;
            }
            if ((x + i) <= (length - 1)
               && (y + j) <= (length - 1)
               && (x + i) >= 0 && (y + j) >= 0 && !visited[x + i][y + j]) {
               visited[x + i][y + j] = true;
               int value = (x + i) * length + y + j;
               path.add(value);
               recursion(wordToCheck, current
                  + board[x + i][y + j], x + i, y + j);
               visited[x + i][y + j] = false;
               path.remove(path.size() - 1);
            }
         }
      }
      visited[x][y] = false;
      return;
   }
}