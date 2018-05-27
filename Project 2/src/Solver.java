/*Project 2
 *Artificial Intelligence
 *Professor: Scott Thede
 *Author: Duc Nguyen / Linh Lam
 *Solver class: This is the algorithm to solve the maze 
 */

import java.util.Arrays;

public class Solver {
	public char[][]maze;
	public int currX;
	public int currY;
	public String currPath;
	public boolean unsolvable;
	
	public Solver(char[][] maze1, int startX, int startY, String currentPath, boolean noSolution)
	{
		maze = maze1;
	    currX = startX;
	    currY = startY;
	    currPath = currentPath;
	    unsolvable = noSolution;
	}
	
	//The correct path then indicated by "*"
	void placePlus() {
	    maze[currX][currY] = '*';
	}
	
	//the "-" indicates that was the location the solver went through without solving the maze(ended up at dead ends), 
	//it is still needed for the checkForOpen() method to carry out.
	void placeMinus() {
	    maze[currX][currY] = '-';
	}
	
	void solveMaze() {
	    // check for win
	    if (checkForWin()) {
	        return;
	    }
	    
	    // No win, so let's check for an opening
	    // check east
	    if (currY + 1 < maze[currX].length && checkForOpen(currX, currY + 1)) {
	        currY++;
	        placePlus();
	        currPath += "E";
	        // recursive call continue searching
	        solveMaze();
	    } 
	    
	    // check west
	    else if (currY - 1 >= 0 && checkForOpen(currX, currY - 1)) {
	        currY--;
	        placePlus();
	        currPath += "W";
	        solveMaze();
	    }  
	    
	    // check south
	    else if (currX + 1 < maze.length && checkForOpen(currX + 1, currY)) {
	        currX++;
	        placePlus();
	        currPath += "S";
	        solveMaze();
	    }  
	    // check north
	    else if (currX - 1 >= 0 && checkForOpen(currX - 1, currY)) {
	        currX--;
	        placePlus();
	        currPath += "N";
	        solveMaze();
	    } else { // Dead end, backtrack
	        if (currPath.length() == 0) {
	            // starting point -> unsolvable
	            unsolvable = true;
	            return;
	        } else {
	            // we've reached a dead end, lets backtrack
	            placeMinus();
	            backTrack();
	        }
	    }
	}

	// Check Open at a location
	boolean checkForOpen(int x, int y) {
	    return maze[x][y] == ' ';
	}

	// Check for win
	boolean checkForWin() {
	    return ((currY+1 < maze[currX].length) && (maze[currX][currY+1] == 'G'));
	}

	//BackTrack method
	void backTrack() {
	    if (currPath.length() > 0) {
	        placeMinus();
	        switch (currPath.charAt(currPath.length() - 1)) {
	        case 'E':
	            currY--;
	            break;
	        case 'W':
	            currY++;
	            break;
	        case 'S':
	            currX--;
	            break;
	        case 'N':
	            currX++;
	            break;
	        }
	        currPath = currPath.substring(0, currPath.length()-1);
	        solveMaze();    
	    }
	}
	
	//Print method
	void printMaze() {
	    for (int i = 0; i < maze.length; i++) {
	        System.out.println(Arrays.toString(maze[i]));
	    }
	}

}
