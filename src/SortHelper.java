import java.util.Arrays;
import java.util.Collections;

/**
 * Defines static methods to help with sorting demonstrations.
 */
public class SortHelper {


    /**
     * Generates an ascending order array from 0 to size - 1
     * @param size - size of array
     * @return ascending array
     */
    public static int [] getAscendingArray(int size) {
        int [] arr = new int[size];

        for (int i=0; i < size; i++) {
            arr[i] = i;
        }

        return arr;
    }


    /**
     * Generates an array in descending order from size - 1 to 0
     * @param size - size of array
     * @return descending array
     */
    public static int [] getDescendingArray(int size) {
        int [] arr = new int[size];

        for (int i=0; i < size; i++) {
            arr[i] = (size - 1) - i;
        }

        return arr;
    }


    /**
     * Generates an array of values from 0 to size - 1 that is
     * randomly shuffled.
     * @param size - size of the array
     * @return random array
     */
    public static int [] getRandomArray(int size) {

        Integer [] arr = new Integer[size];

        for (int i=0; i < size; i++) {
            arr[i] = (size - 1) - i;
        }

       Collections.shuffle(Arrays.asList(arr));

        int outArr [] = new int[size];

        for (int i=0; i < size; i++) {
            outArr[i] = arr[i];
        }

       return outArr;
    }


    /**
     * Creates a string representation of an array
     * @param arr - array to cnvert to string
     * @return comma delimited string of array elements
     */
    public static String toArrayToString(int [] arr) {

        String out = "";

        for (int i=0; i< arr.length; i++ ) {
            out += Integer.toString(arr[i]);
            if (i != arr.length - 1) out += ", ";
        }

        return out;
    }

    public static void main(String [] args) {

        System.out.println(SortHelper.toArrayToString(SortHelper.getAscendingArray(20)));
        System.out.println(SortHelper.toArrayToString(SortHelper.getDescendingArray(20)));
        System.out.println(SortHelper.toArrayToString(SortHelper.getRandomArray(20)));


    }


}
