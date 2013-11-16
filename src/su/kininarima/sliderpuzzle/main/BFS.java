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
		while (cState != null && !cState.equals(eState)){
			if (!visitedList.contains(cState)) {
				frontierList.add(new BoardState(cState, Direction.UP));
				frontierList.add(new BoardState(cState, Direction.DOWN));
				frontierList.add(new BoardState(cState, Direction.LEFT));
				frontierList.add(new BoardState(cState, Direction.RIGHT));
				nodesExpanded++;
			}
			visitedList.add(cState);
			cState = frontierList.poll();
		}
		if (cState == null) {
			System.out.println("Could not find solution after expanding " + nodesExpanded + " nodes.");
		}
		else {
			Display out = new Display();
			System.out.println("Solution found after expanding "+ nodesExpanded + " nodes.");
			System.out.println(out.solutionToString(cState));
		}
	}

}
