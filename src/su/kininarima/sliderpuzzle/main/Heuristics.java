package su.kininarima.sliderpuzzle.main;

import java.util.Comparator;

public class Heuristics implements Comparator<BoardState> {
	private BoardState endState;
	private byte heuristic;
	
	/** @param h the chosen heuristic @param eState the end state used to calculate heuristics.**/
	public Heuristics(byte h, BoardState eState) {
		endState = eState;
		heuristic = h;
	}

	@Override
	public int compare(BoardState s1, BoardState s2) {
		long dEstimate1 = s1.getDepth(), dEstimate2 = s2.getDepth();
		if (heuristic == 0){
			for(int y = 0; y<endState.getBoard().length;y++) {
				for(int x = 0; x<endState.getBoard()[0].length; x++){
					if (endState.getBoard()[x][y] != s1.getBoard()[x][y]&&s1.getBoard()[x][y]!=0) {
						dEstimate1++;
					}
					if (endState.getBoard()[x][y] != s2.getBoard()[x][y]&&s2.getBoard()[x][y]!=0) {
						dEstimate2++;
					}
				}
			}
		if (dEstimate1 > dEstimate2){
			return 1;
		}
		else if (dEstimate1 < dEstimate2){
			return -1;
		}
		else{
			return 0;
		}
		}
		else if (heuristic == 1){
			
		}
		return 0;
	}

}
