package su.kininarima.sliderpuzzle.main;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class BFS {

	private BoardState sState, eState;
	private ArrayList<BoardState> visitedList;
	private LinkedBlockingQueue<BoardState> frontierList;
	private long nodesExpanded =0;
	
	
	public BFS(BoardState startState, BoardState endState) {
		sState=startState;
		eState=endState;
	}
	public void solve(){
		BoardState cState = sState;
		visitedList = new ArrayList<BoardState>();
		frontierList = new LinkedBlockingQueue<BoardState>();
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
			System.out.println("Solution found after expanding "+ nodesExpanded + " nodes. Searched for " + totalTime*1E-6 + "ms. Depth: " + cState.getDepth());
			System.out.println(out.solutionToString(cState));
		}
	}

}
