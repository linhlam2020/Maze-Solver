/*Project 2
 *Artificial Intelligence
 *Professor: Scott Thede
 *Author: Duc Nguyen / Linh Lam
 *Main class: This is the driver of the program
 */

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Main {
	public static void main( String[] args )
	{
		String file;
		int width,height;
		char[][] maze = null;	
		Scanner key=new Scanner(System.in);
		System.out.println("Welcome to the maze solving program: ");
		System.out.println( "Enter a file name to read: " );
		file = key.nextLine();
		
		key.close();
		
		FileReader inFile = null;
		
		try
		{
			inFile = new FileReader( file );
		}
		catch( FileNotFoundException e )
		{
			System.out.println( "That file could not be found." );
			System.exit( 1 );
		}
		Scanner in = new Scanner( inFile );
		

		
		String siz = in.nextLine();
		
		String[] split = siz.split(" ");
        height =  Integer.valueOf(split[0]);
        width = Integer.valueOf(split[1]);
        
        maze = new char[height][width];
        
        String line;
        ArrayList<String> lines = new ArrayList<String>();
        while ( in.hasNextLine( ) )
		{
			line = in.nextLine();
			lines.add( line );
		}
        
        in.close();
		
        
        int j = 0;
        for ( int k = 0; k < lines.size(); k++ )
		{
			line = lines.get(k);
			char[] ch = line.toCharArray();
			
			for (int i = 0; i< ch.length;i++)
			{
				maze[j][i] = ch[i];
			}
			j++;
			
		}
        
        //print original maze
        System.out.println("The original maze:");
        for (int i = 0; i < maze.length; i++) {
	        System.out.println(Arrays.toString(maze[i]));
	    }
        

        int startX=0; 
        int startY=0;
        String startPath = "";
        for (int i = 0; i<height ;i++)
        {
        		if (maze[i][0] == ' ')
        		{
        			maze[i][0] = 'X';
        			startY = 0;
        			startX = i;
        		}
        		if (maze[i][width-1] == ' ')
        		{
        			maze[i][width-1]='G';
        		}
        }
        
        Solver solver = new Solver(maze, startX, startY, startPath, false);
        
        solver.placePlus();
        solver.solveMaze();
        if (solver.unsolvable) {
            System.out.println("The maze is unsolvable");
        } else {
            System.out.println("\nSolved, the correct path is: " );
            System.out.println();
        }
        solver.printMaze();
      
	}
	
}