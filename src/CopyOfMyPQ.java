//THIS IS THE QUICKLY SELF MADE SH**Y ONE (DON'T USE)

import java.util.ArrayList;
import java.util.Collections;

public class CopyOfMyPQ {
	private ArrayList<Comparable> pq;
	private int objectCount;
	
	public CopyOfMyPQ() {
		pq = new ArrayList<Comparable>();
		objectCount = 0;
	}
	
	public void add(Comparable obj) {
		if(obj == null)
			throw new NullPointerException();
		
		if(objectCount + 1 > pq.size()) {
			pq.ensureCapacity(pq.size() * 2);
		}
		
		pq.add(obj);
		Collections.sort(pq);
		objectCount++;
	}
	
	public Comparable removeMin() {
		if(objectCount < 0) {
			throw new NullPointerException();
		}
		objectCount--;
		return pq.remove(0);
	}
	
	public Comparable peek() {
		if(objectCount < 0) {
			throw new NullPointerException();
		}
		
		return pq.get(0);
	}
	
	public boolean isEmpty() {
		return objectCount == 0;
	}
	
	//added this method
	public int size() {
		return objectCount;
	}
	
	public String toString()
	{
		StringBuffer result = new StringBuffer();
		
		for(int i = 0; i < pq.size(); i++) {
			result.append(pq.get(i) + " ");
		}
		
		return result.toString();
	}
}
