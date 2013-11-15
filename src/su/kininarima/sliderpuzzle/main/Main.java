package su.kininarima.sliderpuzzle.main;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileIO loader = new FileIO();
		try {
		BoardState startState = loader.loadPuzzle(new File(args[0]));
		BoardState endState = loader.loadPuzzle(new File(args[1]));
		}
		catch (FileNotFoundException e) {
			System.out.println("Error File Not Found");
			System.exit(1);
		}
		if (args[2].equals("bfs")) {
			
		}
		else if (args[2].equals("dfs")) {
			
		}
		else if (args[2].equals("astar1")) {
			
		}
		else if (args[2].equals("astar2")) {
			
		}
		else {
			System.out.println("Error: Invalid mode arguement, acceptable arguements are 'bfs', 'dfs', 'astar1', 'astar2'");
			System.exit(1);
		}
	}

}
