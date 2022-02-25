import java.util.ArrayDeque;
import java.util.PriorityQueue;

/* YOU ARE ALLOWED (AND EXPECTED) TO USE THE JAVA ARRAYDEQUE CLASS */

/*62 for cookies.txt, 359 for cookies2.txt, 251 for cookies3.txt, 330 for cookies4.txt*/
/* COOKIES4.TXT IS SUPPOSED TO TAKE A LONG TIME - HEAP SPACE ERROR FOR THIS COMPUTER IS OKAY*/
public class CookieMonster {

    private int [][] cookieGrid;
    private int numRows;
    private int numCols;
    private int mostCookies = -1;	//Why is this -1 rather than 0?
    private int maxCallStackDepth = -1; //Used only for recursive technique

    public CookieMonster(int [][] cookieGrid) {
        this.cookieGrid = cookieGrid;
        this.numRows    = cookieGrid.length;
        this.numCols    = cookieGrid[0].length;
    }

    /* Return the calculated most cookies edible on the optimal path. */
    public int getMostCookies() {
        return mostCookies;
    }

    private boolean goodPoint(int row, int col) {
        return (row >= 0 && row < numRows && col >= 0 && col < numCols && cookieGrid[row][col] >= 0);
    }

	/* RECURSIVELY calculates the route which grants the most cookies, and returns the maximum depth the call stack reached during the operation. */
	public int maxCallStackDepth() {
		this.mostCookies = recursiveOptimalPath(0, 0, 1);
		return maxCallStackDepth;
	}	

	/* Helper function for the above, which returns the maximum number of cookies edible starting at coordinate (row, col). */
	/* From any given position, always check right before checking down */
	/* Also, updates the maxCallStackDepth variable, to find out how deep the recursion call stack got during the operation. 
	 * I've implemented this part already (you should add 1 to the depth when it calls itself). */ 

	private int recursiveOptimalPath(int row, int col, int depth) {
		if (depth > this.maxCallStackDepth) {
			this.maxCallStackDepth = depth;
		}
			

		/* -- YOU IMPLEMENT THIS -- */
		//to test logic
		//System.out.println("R: " + row + "  C: " + col + "  T: " + mostCookies);
	
		if(goodPoint(row, col) == false) {
			
			return 0;
		}
		
		int right = recursiveOptimalPath(row, col+1, depth++);
		int down = recursiveOptimalPath(row+1, col, depth++);
		
		if(right > down) {
			return right + cookieGrid[row][col];
		}
		
		return down + cookieGrid[row][col];
	}

	/* Calculate the route which grants the most cookies, and return the maximum queue size during the operation. */
    /* From any given position, always check right before checking down */
    /* Set this.mostCookies before returning */
	/* maxQueueSize should be ≈5 million*/
    public int maxQueueSize() {
        ArrayDeque<PathMarker> queue = new ArrayDeque<PathMarker>();
        int maxQueueSize = 0;
        int mostCookiesSoFar = -1;
        queue.addFirst(new PathMarker(0, 0, cookieGrid[0][0]));

        /* -- YOU IMPLEMENT THIS -- */
      //while all paths from spawn have NOT run into a mine/reached bottom right cell of grid
        while(!queue.isEmpty()) {
        	//
        	if(mostCookiesSoFar < queue.peek().total) {
        		//System.out.println("cookies before replacing: " + mostCookiesSoFar);
        		mostCookiesSoFar = queue.peek().total;
        		//System.out.println("cookies after replacing: " + mostCookiesSoFar);
        	}
        	
        	//to track queue size at any time
        	//maxQueueSize--;
        	
        	//System.out.println("P " + queue.peek().row + " " + queue.peek().col + "\n");
        	PathMarker p = queue.remove();
        	PathMarker pRight = right(p);
        	PathMarker pDown = down(p);
        	
        	if(pRight != null) {
        		queue.addLast(pRight);
        		//maxQueueSize++;
        		//System.out.println("R: " + pRight);
        	}
        	if(pDown != null) {
        		queue.addLast(pDown);
        		//maxQueueSize++;
        		//System.out.println("D: " + pDown);
        	}
        	
        	if(maxQueueSize < queue.size()) {
        		maxQueueSize = queue.size();
        	}
        }
        
        mostCookies = mostCookiesSoFar;
        return maxQueueSize;
    }

    /* Calculate the route which grants the most cookies, and return the maximum stack size during the operation. */
    /* From any given position, always check right before checking down */
    /* Set this.mostCookies before returning */
    public int maxStackSize() {
        ArrayDeque<PathMarker> stack = new ArrayDeque<PathMarker>();
        int maxStackSize = 0;
        int mostCookiesSoFar = -1;
        stack.addFirst(new PathMarker(0, 0, cookieGrid[0][0]));

        /* -- YOU IMPLEMENT THIS -- */
        //while all paths from spawn have NOT run into a mine/reached bottom right cell of grid
        while(!stack.isEmpty()) {
        	//
        	if(mostCookiesSoFar < stack.peek().total) {
        		//to fix mostCookies incrementing
        		//System.out.println("cookies before replacing: " + mostCookiesSoFar);
        		mostCookiesSoFar = stack.peek().total;
        		//System.out.println("cookies after replacing: " + mostCookiesSoFar);
        	}
        	
        	//to track stack size at any time
        	//maxStackSize--;
        	
        	//System.out.println("P: " + stack.peek().row + " " + stack.peek().col + "\n");
        	PathMarker p = stack.pop();
        	PathMarker pRight = right(p);
        	PathMarker pDown = down(p);
        	
        	//to test logic
        	//System.out.println("R: " + pRight);
        	//System.out.println("D: " + pDown + "\n");
        	
        	if(pDown != null) {
        		stack.push(pDown);
        		//maxStackSize++;
        		//System.out.println("D: " + pDown);
        	}
        	if(pRight != null) {
        		stack.push(pRight);
        		//maxStackSize++;
        		//System.out.println("R: " + pRight + "\n");
        	}
        	
        	//System.out.println("executed while loop\n");
        	
        	if(maxStackSize < stack.size()) {
        		maxStackSize = stack.size();
        	}
        }
        
        mostCookies = mostCookiesSoFar;
        return maxStackSize;
    }

    
    /**
     * Calculate the route which grants the most cookies, and return the maximum priority queue size during the operation. 
     * From any given position, always check right before checking down
     * Set this.mostCookies before returning
     * Should use the PriorityQueue you coded earlier (but still be the worst method)
     * Isaac: this method will never finish w/o coding heapPQ
     */
    public int maxPriorityQueueSize() {
        CopyOfHeapPQ priorityQueue = new CopyOfHeapPQ();
        int maxPriorityQueueSize = 0;
        int mostCookiesSoFar = -1;
        priorityQueue.add(new PathMarker(0, 0, cookieGrid[0][0]));

        /* -- YOU IMPLEMENT THIS -- */
        while(!priorityQueue.isEmpty()) {
        	if(mostCookiesSoFar < ((PathMarker) priorityQueue.peek()).total) {
        		//System.out.println("cookies before replacing: " + mostCookiesSoFar);
        		mostCookiesSoFar = ((PathMarker) priorityQueue.peek()).total;
        		//System.out.println("cookies after replacing: " + mostCookiesSoFar);
        	}
        	
        	//to track queue size at any time
        	//maxPriorityQueueSize--;
        	
        	PathMarker p = (PathMarker) priorityQueue.removeMin();
        	//System.out.println("P: " + p + "\n");
        	PathMarker pRight = right(p);
        	PathMarker pDown = down(p);
        	
        	//System.out.println("R: " + pRight);
        	//System.out.println("D: " + pDown + "\n");
        	
        	if(pRight != null) {
        		priorityQueue.add(pRight);
        		maxPriorityQueueSize++;
        	}
        	if(pDown != null) {
        		priorityQueue.add(pDown);
        		maxPriorityQueueSize++;
        	}
        	
        	if(maxPriorityQueueSize < priorityQueue.size()) {
        		maxPriorityQueueSize = priorityQueue.size();
        	}
        }
        
        mostCookies = mostCookiesSoFar;
        return maxPriorityQueueSize;
    }
    
    
    /**
     * returns null if the cell to the right contains a mine OR is out of the grid
     * 
     * otherwise returns a PathMarker containing the cell to the right's coordinates 
     * AND the number of cookies it contains
     */
    public PathMarker right(PathMarker p) {
    	if(goodPoint(p.row, p.col+1) == false) {
    		//return new PathMarker(p.row, p.col+1, cookieGrid[p.row][p.col+1]);
    		return null;
    	}
    	
    	return new PathMarker(p.row, p.col+1, p.total + cookieGrid[p.row][p.col+1]);
    }
    
    /**
     * returns null if the cell below contains a mine OR is out of the grid
     * 
     * otherwise returns a PathMarker containing the cell below's coordinates
     * AND the number of cookies it contains
     */
    public PathMarker down(PathMarker p) {
    	if(goodPoint(p.row+1, p.col) == false) {
    		//return new PathMarker(p.row+1, p.col, cookieGrid[p.row+1][p.col]);
    		return null;
    	}
    		
    	return new PathMarker(p.row+1, p.col, p.total + cookieGrid[p.row+1][p.col]);
    }
}
