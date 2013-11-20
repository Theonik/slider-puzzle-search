package su.kininarima.sliderpuzzle.main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
/** This class implements A* search and is used for that purpose 
 * @author Theonik. This software is available under the GPLv2 license.**/
public class AStar {

	private BoardState sState, eState; //Store start and end states for the puzzle
	private ArrayList<BoardState> visitedList; //Stores the list of visited nodes
	private PriorityQueue<BoardState> frontierList; //Stores the nodes to be evaluated
	private long nodesExpanded =0; //tracks the number of nodes expanded
	Comparator<BoardState> heuristic; //heuristic used to order the queue

	/** Default constructor
	 * @param startState the start state
	 * @param endState the goal state
	 * @param h some heuristic **/
	public AStar(BoardState startState, BoardState endState, Comparator<BoardState> h) {
		sState=startState;
		eState=endState;
		heuristic = h;
	}

	/**Method to solve**/
	public void solve() {
		BoardState cState = sState;
		visitedList = new ArrayList<BoardState>();
		frontierList = new PriorityQueue<BoardState>(100, heuristic);
		long startTime, endTime, totalTime; //to measure solution time
		startTime = System.nanoTime();
		while (cState != null && !cState.equals(eState)){ //keep going unless all nodes are evaluated or solution is found
			if (!visitedList.contains(cState)) { //expand if not visited
				for (Direction d : Direction.values()) {
					frontierList.add(new BoardState(cState, d));
				}
				visitedList.add(cState);
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
