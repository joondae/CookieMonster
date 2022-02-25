public class CopyOfHeapPQ<E extends Comparable<E>> implements CopyOfMyPriorityQueue<E> {

	private E[] heap;
	private int objectCount;
	
	public CopyOfHeapPQ()
    {
        this.heap = (E[])new Comparable[3];
        this.objectCount = 0;
    }

	//Adds obj to the Priority Queue
	//should only need to use swap, parent, bubbleUp, and increaseCapacity
	public void add(E obj)
	{
		
		if(objectCount >= heap.length)
			increaseCapacity();
		
		//alternatively use placeholder value and change index in for loop
		//alternatively use recursion
		
		heap[objectCount] = obj;
		bubbleUp(objectCount);
		objectCount++;

	}
	
	//Removes and returns the MINIMUM element from the Priority Queue
	public E removeMin()
	{
		//alternatively use placeholder value and change index in for loop
		//alternatively use recursion
		
		//switch top and bottom
		//remove bottom
		//bubble down
		//(dependent on add method???)
		
		E min = heap[0];
		
		swap(0, objectCount - 1);
		objectCount--;
		bubbleDown(0);
		
		return min;
	}
	
	//Returns the MINIMUM element from the Priority Queue without removing it
	public E peek()
	{
		//IOOBE?
		
		return heap[0];
	}
	
	// Returns true if the priority queue is empty
	public boolean isEmpty()
	{
		return objectCount == 0;
	}
	
	//Returns the number of elements in the priority queue
	public int size()
	{
		return objectCount;
	}
	
	public String toString()
	{
		StringBuffer stringbuf = new StringBuffer("[");
		for (int i = 0; i < objectCount; i++)
		{
			stringbuf.append(heap[i]);
			if (i < objectCount - 1)
				stringbuf.append(", ");
		}
		stringbuf.append("]\nor alternatively,\n");

		for(int rowLength = 1, j = 0; j < objectCount; rowLength *= 2)
		{
			for (int i = 0; i < rowLength && j < objectCount; i++, j++)
			{
				stringbuf.append(heap[j] + " ");
			}
			stringbuf.append("\n");
			if (j < objectCount)
			{
				for (int i = 0; i < Math.min(objectCount - j, rowLength*2); i++)
				{
					if (i%2 == 0)
						stringbuf.append("/");
					else
						stringbuf.append("\\ ");
				}
				stringbuf.append("\n");
			}
		}
		return stringbuf.toString();
	}
	
	//Doubles the size of the heap array
	private void increaseCapacity()
	{
		E[] newArr = (E[]) new Comparable [heap.length * 2];
		
		for(int i = 0; i < heap.length; i++) {
			newArr[i] = heap[i];
		}
		
		heap = newArr;
	}

	//Returns the index of the "parent" of index i
	private int parent(int i)
	{
		//IOOBE
		/*
		if(i >= objectCount || i < 1) {
			throw new IndexOutOfBoundsException();
		}
		*/
		
		return (i-1)/2;
	}
	//Returns the index of the "smaller child" of index i
	private int smallerChild(int i)
	{
		//IOOBE
		if(i >= objectCount - 1 || i < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		//if left is smaller than right, return left (otherwise return right)
		if(heap[i*2+1].compareTo(heap[i*2+2]) < 0)
			return i*2+1;
		
		return i*2+2;
	}
	//Swaps the contents of indices i and j
	private void swap(int i, int j)
	{
		E temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}

	// Bubbles the element at index i upwards until the heap properties hold again.
	private void bubbleUp(int i)
	{
		//while child is smaller than parent, swap 
		while((i-1)/2 > 0 && heap[i].compareTo(heap[parent(i)]) < 0) {
			swap(i, parent(i));
			i = parent(i);
		}
	}
	
	// Bubbles the element at index i downwards until the heap properties hold again.
	private void bubbleDown(int i)
	{
		//while parent is larger than its smallest child, swap with it
		while(i*2+2 < objectCount && heap[i].compareTo(heap[smallerChild(i)]) > 0) {
			//System.out.println("smallerChild: " + smallerChild(i));
			int indexOfSmallerChild = smallerChild(i);
			//System.out.println("Swapped " + heap[i] + " with " + heap[smallerChild(i)]);
			swap(i, smallerChild(i));
			i = indexOfSmallerChild;
			//System.out.println("New i: " + i);
		}
	}

}
