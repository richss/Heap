/**
 * Implementation of a min heap (i.e. lower priority values are dequeued sooner.
 * @param <P> - comparable type for priority
 * @param <T> - Information associated with the priority.
 */
public class MinHeap<P extends Comparable<P>, T> {

		
	HeapNode<P,T> [] heap;
	int count = 0;

	/**
	 * Default constructor for an empty heap.
	 * @param maxSize - maximum storage size of heap.
	 */
	public MinHeap(int maxSize)
	{
		heap = new HeapNode[maxSize];
	}

	/**
	 * Constructor for heap with the data passed in by two arrays (one for priorities and one for their corresponding
	 * information)
	 * @param priority - array of priorities
	 * @param info - array of info
	 */
	public MinHeap(P [] priority, T [] info) {
		

		// Error checking
		if (priority.length != info.length) {
			System.err.println("Invalid input");
			return;
		}
		
		//Copy data into an array
		this.heap = new HeapNode[priority.length];
		this.count = priority.length;
		
		for (int i=0; i < priority.length; i++) {
			heap[i] = new HeapNode<P, T>(priority[i], info[i]);
		}
		
		//Heapify using Floyd's Algorithm
		floydsAlgorithm();
	}


	/**
	 * Enqueues a new item into the heap-based priority queue
	 * @param priority - priority of new value
	 * @param item - info of new value.
	 */
	public void enqueue(P priority, T item)
	{
		//Confirm heap is not full
		if (count == heap.length) {
			System.err.println("Heap full, unable to add");
			return;
		}

		//Insert new priority, info pair into heap at the right most open location
		// Then restore the heap structure.
		int parent;
		int cur = count; //Add item to the last location
		heap[cur] = new HeapNode<P,T>(priority, item);
		count++;  //Increment Count;

		//Restores heap structure from new node insert location to root.
		while (cur > 0) {
			
			parent = getParentIndex(cur);
			
			//if cur is smaller than parent, then swap
			if (heap[cur].priority.compareTo(heap[parent].priority) < 0) {
				swap(cur, parent);
				cur = parent;
			}
			else
				return;	
		}
	}

	/**
	 * Dequeues and returns minimum priority value from the heap.
	 * @return info with minimum priority in the heap
	 */
	public T dequeue()
	{
		//Check for empty
		if (count == 0) return null;
		
		//Remove item and store in tmp
		T tmp = (T) heap[0].info;
		count--;
		
		//Copy last leaf into the root location
		heap[0] = heap[count];
		
		//Move new root down to its proper locaiton in the heap
		//i.e. repair heap
		moveDown(0,count-1);
			
		return (T) tmp;
	}

	/**
	 * Restores a heap by moving a value down through the array.
	 * @param first - first index of move down algorithm
	 * @param last - last index of the move down algorithm
	 */
	public void moveDown(int first, int last)
	{
	    int cur = first;
	    int smallest = 2*cur+1; //Left child of cur
	    
	    while (smallest <= last) {

	    	//If two children, find smallest of two.
	    	if (smallest < last) 
	    		if (heap[smallest].priority.compareTo(heap[smallest+1].priority) > 0)
	    			smallest++; 

	    	//Compare cur with smallest sub-child. 
	    	if (heap[cur].priority.compareTo(heap[smallest].priority) > 0) {
	    		swap(cur, smallest);
	    		cur = smallest; 
	    		smallest = 2*cur + 1; 
	    	}
	    	else
	    		return; //Done restoring
	    }

	}

	/**
	 * Returns index of the parent of the node specified by the parameter index.
	 * @param index - index whose parent we are seeking.
	 * @return
	 */
	private int getParentIndex(int index)
	{
		if ((index % 2) == 0)
			return (int) (index - 2)/2;
		else
			return (int) (index - 1)/2;
	}

	/**
	 * Swaps values between locations in the heap.
	 * @param cur
	 * @param parent
	 */
	private void swap(int cur, int parent)
	{
		HeapNode<P,T> tmp = heap[cur];
		heap[cur] = heap[parent];
		heap[parent] = tmp;
	}

	/**
	 * @return true if empty, else otherwise.
	 */
	public boolean isEmpty()
	{
		return (count == 0);
	}

	/**
	 *
	 * @return  true if full, false othewise.
	 */
	public boolean isFull()
	{
		return (count == heap.length);
	}


	/**
	 * Simple wrapper class for the priority and information of a heap item.
	 * @param <P>
	 * @param <T>
	 */
	public class HeapNode<P, T> {
		public P priority;
		public T info;
		
		public HeapNode(P priority, T info) {
			this.priority = priority;
			this.info = info;
		}
	}

	/**
	 * Algorithm that heapifies an array
	 */
	private void floydsAlgorithm()
	{
		int lastNonLeaf = (int) (heap.length/2 - 1);
		for (int i=lastNonLeaf; i >= 0; i--) {
			moveDown(i, count-1);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		Integer [] data = new Integer[]{1, 2, 10, 5, 30, 7, 9, 100};
	
		System.out.println("Top-Down Heap Demo");
		
		MinHeap<Integer,Integer> h = new MinHeap<Integer,Integer>(10);
	
		for (int i=0; i < data.length; i++) {
			h.enqueue(data[i],data[i]);
		}
		
		
		while (h.isEmpty() == false) {
			System.out.println(h.dequeue());
		}
		
		System.out.println("Bottom-Up Heap Demo");
		
		MinHeap<Integer, Integer> h2 = new MinHeap<Integer,Integer>(data,data);
		
		
		while (h2.isEmpty() == false) {
			System.out.println(h2.dequeue());
		}
		
	}
	
}
