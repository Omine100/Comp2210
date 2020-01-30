/**
 * Provides a sample client for the AvlTree class.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2018-05-02
 */
public class AvlTreeClient {

	/** Drives execution. */
	public static void main(String[] args) {
        Integer[] values = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        AvlTree<Integer> avl = new AvlTree<>();
        for (Integer value : values) {
            avl.add(value);
        }
        System.out.println(avl);
        System.out.println(avl.size());
	}
}
