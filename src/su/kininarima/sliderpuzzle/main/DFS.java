package su.kininarima.sliderpuzzle.main;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class DFS {

	private BoardState sState, eState;
	private ArrayList<BoardState> visitedList;
	private ArrayDeque<BoardState> frontierList;
	private long nodesExpanded =0;


	public DFS(BoardState startState, BoardState endState) {
		sState=startState;
		eState=endState;
	}
	public void solve() {
		BoardState cState = sState;
		visitedList = new ArrayList<BoardState>();
		frontierList = new ArrayDeque<BoardState>();
		long startTime, endTime, totalTime;
		startTime = System.nanoTime();
		while (cState != null && !cState.equals(eState)){
			if (!visitedList.contains(cState)) {
				frontierList.push(new BoardState(cState, Direction.UP));
				frontierList.push(new BoardState(cState, Direction.DOWN));
				frontierList.push(new BoardState(cState, Direction.LEFT));
				frontierList.push(new BoardState(cState, Direction.RIGHT));
				visitedList.add(cState);
				nodesExpanded++;
			}
			cState = frontierList.pop();
		}
		endTime = System.nanoTime();
		totalTime = endTime - startTime;
		if (cState == null) {
			System.out.println("Could not find solution after expanding " + nodesExpanded + " nodes. Searched for " + totalTime*1E-6 + "ms.");
		}
		else {
			Display out = new Display();
			System.out.println("Solution found after expanding "+ nodesExpanded + " nodes. Searched for " + totalTime*1E-6 + "ms.");
			System.out.println(out.solutionToString(cState));
		}
	}
}
