package su.kininarima.sliderpuzzle.main;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileIO loader = new FileIO();
		BoardState startState=null, endState = null;
		try {
		startState = loader.loadPuzzle(new File(args[0]));
		endState = loader.loadPuzzle(new File(args[1]));
		}
		catch (FileNotFoundException e) {
			System.out.println("Error File Not Found");
			System.exit(1);
		}
		if (args[2].equals("bfs")) {
			BFS searcher = new BFS(startState, endState);
			searcher.solve();
		}
		else if (args[2].equals("dfs")) {
			DFS searcher = new DFS(startState, endState);
			searcher.solve();
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
