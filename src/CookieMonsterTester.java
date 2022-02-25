import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CookieMonsterTester {

/**
 * In this program Cookie Monster finds the optimal path from
 * the upper left corner (0,0) to the lower right corner
 * (SIZE-1,SIZE-1) in a cookie array.  The elements of
 * the array contain cookies (a non-negative number) or barrels
 * (-1).  On each step Cookie Monster can only go down or
 * to the right.  He is not allowed to step on barrels.
 * The optimal path contains the largest number of cookies.
 */
	public static void main(String[] args)
	{
		String[] fileNames = {"cookies3.txt", "cookies2.txt", "cookies.txt"};
		int numFilesToTest = 3;

		if (args.length >= 1)
		{
			fileNames[0] = args[0];
		}
		else
		{
			Scanner kboard = new Scanner(System.in);
			System.out.print("Enter the cookies file name or press ENTER for 3 default test cases: ");
			String filename = kboard.nextLine();
			if (!filename.equals(""))
			{
				System.out.print("Filename entered: " + filename + ".  ");
				fileNames[0] = filename.trim();  
				numFilesToTest = 1;
			}
			kboard.close();
		}
		

		while (numFilesToTest > 0)
		{
			numFilesToTest--;
			File file = new File(fileNames[numFilesToTest]);
			Scanner input = null;
			try
			{
				System.out.print("reading the file: " + fileNames[numFilesToTest]);
				input = new Scanner(file);
			}
			catch (FileNotFoundException ex)
			{
				System.out.println("Cannot open " + fileNames[numFilesToTest] + ".  You probably foolishly forgot to add all three text files to the project folder and NOT into the src folder.");
				System.exit(1);
			}
			
			int row = 0;
			int col = 0;
			int[][] cookies = null;
			try
			{
				int numRows = input.nextInt();  
				int numCols = input.nextInt();
				System.out.println(".  NumRows: " + numRows + ", NumCols: " + numCols);
				cookies = new int[numRows][numCols];
				
				for (row = 0;   row < numRows;   row++) 
					for (col = 0; col < numCols; col++)
					 	cookies[row][col] = input.nextInt();
			}
			catch (Exception e)
			{
				System.out.print("Error creating maze: " + e.toString());
				System.out.println("Error occurred at row: " + row + ", col: " + col);
			}
			input.close();
			
			CookieMonster monster = new CookieMonster(cookies);
			
			long start = System.currentTimeMillis();
			int maxCallStackDepth = monster.maxCallStackDepth();
			int optrecursion = monster.getMostCookies();
		System.out.println("Solved using recursion");
			long end1 = System.currentTimeMillis();
			int maxQueueSize = monster.maxQueueSize();
			int optqueue = monster.getMostCookies();
		System.out.println("Solved using queue");
			long end2 = System.currentTimeMillis();
			int maxStackSize = monster.maxStackSize();
			int optstack = monster.getMostCookies();
	System.out.println("Solved using stack");
			long end3 = System.currentTimeMillis();
			
			
			int maxPQSize = monster.maxPriorityQueueSize();
			int optpq = monster.getMostCookies();
	System.out.println("Solved using priority queue");
			long end4 = System.currentTimeMillis();
			
			System.out.println("Optimal path has " + optrecursion + " cookies, using recursion to search.  Maximum call stack depth: " + maxCallStackDepth + ".  TIME: " + (end1-start) + " milliseconds");
			System.out.println("Optimal path has " + optqueue + " cookies, using a queue to search.  Maximum queue size: " + maxQueueSize + ".  TIME: " + (end2-end1) + " milliseconds");
			System.out.println("Optimal path has " + optstack + " cookies, using a stack to search. Maximum stack size: " + maxStackSize + ".  TIME: " + (end3-end2)  + " milliseconds");
			
			System.out.println("Optimal path has " + optpq + " cookies, using a priority queue to search. Maximum priority queue size: " + maxPQSize + ".  TIME: " + (end4-end3)  + " milliseconds");
			
		}
	}
}