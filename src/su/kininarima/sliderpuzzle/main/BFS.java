package su.kininarima.sliderpuzzle.main;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
/** Breadth First Search implementation **/
public class BFS {

	private BoardState sState, eState; //start and goal states
	private ArrayList<BoardState> visitedList; //list of explored nodes
	private LinkedBlockingQueue<BoardState> frontierList; //list of nodes to explore
	private long nodesExpanded =0; //number of nodes explored

	/** @param startState start state @param endState goal state**/
	public BFS(BoardState startState, BoardState endState) {
		sState=startState;
		eState=endState;
	}
	/** Method to solve **/
	public void solve(){
		BoardState cState = sState;
		visitedList = new ArrayList<BoardState>();
		frontierList = new LinkedBlockingQueue<BoardState>();
		long startTime, endTime, totalTime; //for time keeping
		startTime = System.nanoTime();
		while (cState != null && !cState.equals(eState)){ //stop if all nodes explored or found solution
			if (!visitedList.contains(cState)) { //expand node if not already expanded
				frontierList.add(new BoardState(cState, Direction.UP));
				frontierList.add(new BoardState(cState, Direction.DOWN));
				frontierList.add(new BoardState(cState, Direction.LEFT));
				frontierList.add(new BoardState(cState, Direction.RIGHT));
				visitedList.add(cState); //add to explored list
				nodesExpanded++;
			}
			cState = frontierList.poll(); //get new node from frontier
		}
		endTime = System.nanoTime();
		totalTime = endTime - startTime;
		if (cState == null) {
			System.out.println("Could not find solution after expanding " + nodesExpanded + " nodes. Searched for " + totalTime*1E-6 + "ms.");
		}
		else {
			Display out = new Display();
			System.out.println("Solution found after expanding "+ nodesExpanded + " nodes. Searched for " + totalTime*1E-6 + "ms. Depth: " + cState.getDepth());
			System.out.println(out.solutionToString(cState));
		}
	}

}
