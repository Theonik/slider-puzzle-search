package su.kininarima.sliderpuzzle.main;

import java.util.ArrayList;

public abstract class Search {

	BoardState sState, eState;
	ArrayList<BoardState> visitedList;
	
	public Search(BoardState startState, BoardState endState) {
		sState=startState;
		eState=endState;
	}

}
