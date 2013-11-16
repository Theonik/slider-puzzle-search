package su.kininarima.sliderpuzzle.main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AStar {

	private BoardState sState, eState;
	private ArrayList<BoardState> visitedList;
	private PriorityQueue<BoardState> frontierList;
	private long nodesExpanded =0;
	Comparator<BoardState> heuristic;


	public AStar(BoardState startState, BoardState endState, Comparator<BoardState> h) {
		sState=startState;
		eState=endState;
		heuristic = h;
	}
	public void solve() {
		BoardState cState = sState;
		visitedList = new ArrayList<BoardState>();
		frontierList = new PriorityQueue<BoardState>(20, heuristic);
		long startTime, endTime, totalTime;
		startTime = System.nanoTime();
		while (cState != null && !cState.equals(eState)){
			if (!visitedList.contains(cState)) {
				frontierList.add(new BoardState(cState, Direction.UP));
				frontierList.add(new BoardState(cState, Direction.DOWN));
				frontierList.add(new BoardState(cState, Direction.LEFT));
				frontierList.add(new BoardState(cState, Direction.RIGHT));
				visitedList.add(cState);
				nodesExpanded++;
			}
			cState = frontierList.poll();
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
