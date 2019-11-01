/**
 * Implementaton of Heap Sort.
 * @author richard.stansbury
 */

public class HeapSort {

    /**
     * Implements Bubble Sort on an array
     * @param data - array to sort
     */
    public static void HeapSort(int [] data)
    {
       //Heapify using Floyd's
        int lastNonRoot = (data.length/2) - 1;
        for (int i=lastNonRoot; i >= 0; i--) {
            moveDown(data,i,data.length - 1);
        }

        //Dequeue each eleemtn and add it to the end of the array
        for (int i=data.length-1; i>=1; --i) {
            swap(data,0,i);
            moveDown(data,0, i-1);
        }
    }

      /**
  	 * Restores a heap by moving a value down through the array.
  	 * @param first - first index of move down algorithm
  	 * @param last - last index of the move down algorithm
  	 */
  	public static void moveDown(int [] data, int first, int last)
  	{
  	    int cur = first;
  	    int largest = 2*cur+1; //Left child of cur

  	    while (largest <= last) {

  	    	//If two children, find largest of two.
  	    	if (largest < last)
  	    		if (heap[largest] < heap[largest+1])
              largest++;

  	    	//Compare cur with largest sub-child.
  	    	if (heap[cur] < heap[largest]) {
  	    		swap(cur, largest);
  	    		cur = largest;
  	    		largest = 2*cur + 1; 
  	    	}
  	    	else
  	    		return; //Done restoring
  	    }

  	}

    /**
     * Swaps the values within an array between two indices (indexX and indexY
     * @param data - array (pass by reference)
     * @param indexX - first index
     * @param indexY - second index
     */
    public static void swap(int [] data, int indexX, int indexY) {
        int tmp = data[indexX];
        data[indexX] = data[indexY];
        data[indexY] = tmp;
    }

    public static void main(String [] args) {

        int [] arr;
        int size = 20;

        //Ascending Test
        System.out.println("Ascending Order Test");
        arr = SortHelper.getAscendingArray(size);
        System.out.println(SortHelper.toArrayToString(arr));
        HeapSort.HeapSort(arr);
        System.out.println(SortHelper.toArrayToString(arr));

        //Descending Test
        System.out.println("Descending Order Test");
        arr = SortHelper.getDescendingArray(size);
        System.out.println(SortHelper.toArrayToString(arr));
        HeapSort.HeapSort(arr);
        System.out.println(SortHelper.toArrayToString(arr));

        //Random Test
        System.out.println("Random Order Test");
        arr = SortHelper.getRandomArray(size);
        System.out.println(SortHelper.toArrayToString(arr));
        HeapSort.HeapSort(arr);
        System.out.println(SortHelper.toArrayToString(arr));


    }



}
