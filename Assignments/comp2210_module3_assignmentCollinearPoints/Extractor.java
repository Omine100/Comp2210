import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Extractor.java. Implements feature extraction for collinear points in
 * two dimensional data.
 *
 * @author  YOUR NAME (you@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version TODAY
 *
 */
public class Extractor {
   
   /** raw data: all (x,y) points from source data. */
   private Point[] points;
   
   /** lines identified from raw data. */
   private SortedSet<Line> lines;
  
   /**
    * Builds an extractor based on the points in the file named by filename. 
    */
   public Extractor(String filename) {
      try {
         Scanner sc = new Scanner(new File(filename));
         int fileSize = sc.nextInt();
         points = new Point[fileSize];
         int i, x, y;
         
         for (i = 0; i < fileSize; i++) {
            x = sc.nextInt();
            y = sc.nextInt();
            
            points[i] = new Point(x, y);
         }
      } catch (Exception e) {
         System.out.println("Error.");
      }
   }
  
   /**
    * Builds an extractor based on the points in the Collection named by pcoll. 
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   public Extractor(Collection<Point> pcoll) {
      points = pcoll.toArray(new Point[]{});
   }
  
   /**
    * Returns a sorted set of all line segments of exactly four collinear
    * points. Uses a brute-force combinatorial strategy. Returns an empty set
    * if there are no qualifying line segments.
    */
   public SortedSet<Line> getLinesBrute() {
      lines = new TreeSet<Line>();
      Point[] testPoints = Arrays.copyOf(points, points.length);
      int i, j, k, l;
      double slopeToJ, slopeToK, slopeToL;
      
      for (i = 3; i < testPoints.length; i++) {
         for (j = 2; j < i; j++) {
            for (k = 1; k < j; k++) {
               for (l = 0; l < k; l++) {
                  slopeToJ = testPoints[i].slopeTo(testPoints[j]);
                  slopeToK = testPoints[i].slopeTo(testPoints[k]);
                  slopeToL = testPoints[i].slopeTo(testPoints[l]);
                  
                  if ((slopeToJ == slopeToK) && (slopeToK == slopeToL)) {
                     Line newLine = new Line();
                     newLine.add(testPoints[i]);
                     newLine.add(testPoints[j]);
                     newLine.add(testPoints[k]);
                     newLine.add(testPoints[l]);
                     
                     lines.add(newLine);
                  }
               }
            }
         }
      }
      return lines;
   }
  
   /**
    * Returns a sorted set of all line segments of at least four collinear
    * points. The line segments are maximal; that is, no sub-segments are
    * identified separately. A sort-and-scan strategy is used. Returns an empty
    * set if there are no qualifying line segments.
    */
   public SortedSet<Line> getLinesFast() {
      lines = new TreeSet<Line>(); 
      Point[] sortedPoints = Arrays.copyOf(points, points.length);
      int i, j, k = 0, l;
      Line currentLine = new Line();
      boolean canAdd = true;
      
      for (i = 0; i < points.length; i++) {
         Arrays.sort(sortedPoints, points[i].slopeOrder);
         for (j = 0; j < points.length; j++) {
            currentLine.add(sortedPoints[0]);
            canAdd = currentLine.add(sortedPoints[j]);
            if (canAdd == false) {
               if (currentLine.length() >= 4) {
                  lines.add(currentLine);
               }
               currentLine = new Line();
               currentLine.add(sortedPoints[j]);
            }
         }
      }
      return lines;
   }
}
