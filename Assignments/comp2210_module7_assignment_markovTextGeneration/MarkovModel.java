import java.io.File;
import java.util.HashMap;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * MarkovModel.java Creates an order K Markov model of the supplied source
 * text. The value of K determines the size of the "kgrams" used to generate
 * the model. A kgram is a sequence of k consecutive characters in the source
 * text.
 *
 * @author     Your Name (you@auburn.edu)
 * @author     Dean Hendrix (dh@auburn.edu)
 * @version    2018-04-17
 *
 */
public class MarkovModel {

   // Map of <kgram, chars following> pairs that stores the Markov model.
   private HashMap<String, String> model;

   // add other fields as you need them ...
   String firstKgram;

   /**
    * Reads the contents of the file sourceText into a string, then calls
    * buildModel to construct the order K model.
    *
    * DO NOT CHANGE THIS CONSTRUCTOR.
    *
    */
   public MarkovModel(int K, File sourceText) {
      model = new HashMap<>();
      try {
         String text = new Scanner(sourceText).useDelimiter("\\Z").next();
         buildModel(K, text);
      }
      catch (IOException e) {
         System.out.println("Error loading source text: " + e);
      }
   }


   /**
    * Calls buildModel to construct the order K model of the string sourceText.
    *
    * DO NOT CHANGE THIS CONSTRUCTOR.
    *
    */
   public MarkovModel(int K, String sourceText) {
      model = new HashMap<>();
      buildModel(K, sourceText);
   }


   /**
    * Builds an order K Markov model of the string sourceText.
    */
   private void buildModel(int K, String sourceText) {
      firstKgram = sourceText.substring(0, K);
      int x = 0;
      int y = 0; 
      
      while (x + K <= sourceText.length()) {
         String kgram = sourceText.substring(x, x + K);
         String emptyString = "";   
         
         if (!model.containsKey(kgram)) {
            int j = K;
            while (y + j < sourceText.length()) {
               String a = sourceText.substring(y, y + j);
               
               if (y + K >= sourceText.length()) {
                  emptyString += '\u0000';
               } else if (kgram.equals(a)) {
                  emptyString += sourceText.substring(y + j, y + j + 1);
               }
               y++;
            }
            model.put(kgram, emptyString);
         }         
         y = 0;
         x++;
      }
   }


   /** Returns the first kgram found in the source text. */
   public String getFirstKgram() {
      return firstKgram;
   }


   /** Returns a kgram chosen at random from the source text. */
   public String getRandomKgram() {
      int initial = 0;
      int size = model.size();
      
      Random randomValue = new Random();
      int index = randomValue.nextInt(size);
      
      for (String initialString : model.keySet()) {
         if (index == initial) {
            return initialString;
         }
         initial++;
      }
      return null;
   }


   /**
    * Returns the set of kgrams in the source text.
    *
    * DO NOT CHANGE THIS METHOD.
    *
    */
    public Set<String> getAllKgrams() {
      return model.keySet();
   }


   /**
    * Returns a single character that follows the given kgram in the source
    * text. This method selects the character according to the probability
    * distribution of all characters that follow the given kgram in the source
    * text.
    */
   public char getNextChar(String kgram) {
      Random randomValue = new Random();
      String emptyString = "";
      int initial = 0;
      
      for(String modelString: model.keySet()) {
         if (modelString.equals(kgram)) {
            emptyString = model.get(kgram);
            int i = emptyString.length();
            
            if (i > 0) {
               initial = randomValue.nextInt(i) + 1;
            }
         }
      }
      int checkValue = initial - 1;
      
      if (!emptyString.equals("")) {
         return emptyString.charAt(checkValue);
      }
      return '\u0000';
   }


   /**
    * Returns a string representation of the model.
    * This is not part of the provided shell for the assignment.
    *
    * DO NOT CHANGE THIS METHOD.
    *
    */
    @Override
    public String toString() {
      return model.toString();
   }

}
