package su.kininarima.sliderpuzzle.main;

import java.util.ArrayDeque;
import java.util.HashSet;
/** Depth first Search implementation 
 * @author Theonik. This software is available under the GPLv2 license.**/
public class DFS {

	private BoardState sState, eState;
	private HashSet<BoardState> visitedList;
	private ArrayDeque<BoardState> frontierList;
	private long nodesExpanded =0;

	/** Default constructor
	 * @param startState the start state
	 * @param endState the goal state**/
	public DFS(BoardState startState, BoardState endState) {
		sState=startState;
		eState=endState;
	}
	/**Method used to solve **/
	public void solve() {
		BoardState cState = sState;
		visitedList = new HashSet<BoardState>();
		frontierList = new ArrayDeque<BoardState>();
		long startTime, endTime, totalTime; //for time keeping
		startTime = System.nanoTime();
		while (cState != null && !cState.equals(eState)){ //keep going if not done
			if (!visitedList.contains(cState)) { //expand all nodes if not already done so
				for (Direction d : Direction.values()) {
					frontierList.push(new BoardState(cState, d));
				}
				visitedList.add(cState); //add to explored
				nodesExpanded++;
			}
			cState = frontierList.pop(); //get new node from stack
		}
		endTime = System.nanoTime();
		totalTime = endTime - startTime;
		if (cState == null) {
			System.out.println("Could not find solution after expanding " + nodesExpanded + " nodes. Searched for " + totalTime*1E-6 + "ms.");
		}
		else {
			Display out = new Display();
			System.out.println(out.solutionToString(cState));
			System.out.println("Solution found after expanding "+ nodesExpanded + " nodes. Searched for " + totalTime*1E-6 + "ms. Depth: " + cState.getDepth());
		}
	}
}
