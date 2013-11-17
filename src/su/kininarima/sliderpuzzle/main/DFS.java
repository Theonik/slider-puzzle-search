package su.kininarima.sliderpuzzle.main;

import java.util.ArrayDeque;
import java.util.ArrayList;
/** Depth first Search implementation **/
public class DFS {

	private BoardState sState, eState;
	private ArrayList<BoardState> visitedList;
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
		visitedList = new ArrayList<BoardState>();
		frontierList = new ArrayDeque<BoardState>();
		long startTime, endTime, totalTime; //for time keeping
		startTime = System.nanoTime();
		while (cState != null && !cState.equals(eState)){ //keep going if not done
			if (!visitedList.contains(cState)) { //expand all nodes if not already done so
				frontierList.push(new BoardState(cState, Direction.UP));
				frontierList.push(new BoardState(cState, Direction.DOWN));
				frontierList.push(new BoardState(cState, Direction.LEFT));
				frontierList.push(new BoardState(cState, Direction.RIGHT));
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
