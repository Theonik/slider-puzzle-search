package su.kininarima.sliderpuzzle.main;

import java.io.File;
import java.io.FileNotFoundException;
/** Class that contains the main method for this project, use with correct parameters to start the program 
 * @author Theonik. This software is available under the GPLv2 license.**/
public class Main {
	private static final String newLine = System.getProperty("line.separator"); //OS agnostic linebreak
	/**
	 * @param args provide start state, goal state and mode.
	 */
	public static void main(String[] args) {
		FileIO loader = new FileIO(); 
		BoardState startState=null, endState = null;
		if (args.length !=3) {
			System.out.println("Error: Invalid command line arguments." + newLine +"Arguments should be in the form of " +
					"< initial puzzle state file > < goal puzzle state file > < mode >");
			System.exit(1);
		}
		try {
			startState = loader.loadPuzzle(new File(args[0]));
			endState = loader.loadPuzzle(new File(args[1]));
		}
		catch (FileNotFoundException e) {
			System.out.println("Error: File Not Found");
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
			AStar searcher = new AStar(startState, endState, new Heuristics((byte)0,endState));
			searcher.solve();
		}
		else if (args[2].equals("astar2")) {
			AStar searcher = new AStar(startState, endState, new Heuristics((byte)1,endState));
			searcher.solve();
		}
		else {
			System.out.println("Error: Invalid mode argument, acceptable arguments are 'bfs', 'dfs', 'astar1', or 'astar2'");
			System.exit(1);
		}
	}

}
