package su.kininarima.sliderpuzzle.main;

import java.util.Comparator;

public class Heuristics implements Comparator<BoardState> {
	private BoardState endState;
	private byte heuristic;

	/** Constructor
	 * @param h the chosen heuristic 
	 * @param eState the end state used to calculate heuristics.**/
	public Heuristics(byte h, BoardState eState) {
		endState = eState;
		heuristic = h;
	}

	@Override
	public int compare(BoardState s1, BoardState s2) {
		long dEstimate1 = s1.getDepth(), dEstimate2 = s2.getDepth(); //get path cost so far
		if (heuristic == 0){ //for mode 0
			for(int y = 0; y<endState.getBoard().length;y++) { //calculate unsolved tiles
				for(int x = 0; x<endState.getBoard()[0].length; x++){
					if (endState.getBoard()[x][y] != s1.getBoard()[x][y]&&s1.getBoard()[x][y]!=0) {
						dEstimate1++;
					}
					if (endState.getBoard()[x][y] != s2.getBoard()[x][y]&&s2.getBoard()[x][y]!=0) {
						dEstimate2++;
					}
				}
			}
		}
		else if (heuristic == 1){ //for mode 1
			for (int stY = 0;stY<endState.getBoard().length;stY++){ //calculate Manhattan distance
				for (int stX = 0;stX<endState.getBoard()[0].length;stX++){
					byte value1=s1.getBoard()[stX][stY], value2=s2.getBoard()[stX][stY];
					for (int enY = 0;enY<endState.getBoard().length;enY++){
						for (int enX = 0;enX<endState.getBoard()[0].length;enX++){
							if (value1!=0&&value1==endState.getBoard()[enX][enY]){
								dEstimate1 = dEstimate1 + Math.abs(stX-enX) + Math.abs(stY-enY);
							}
							if (value2!=0&&value2==endState.getBoard()[enX][enY]){
								dEstimate2 = dEstimate2 + Math.abs(stX-enX) + Math.abs(stY-enY);
							}
						}
					}
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
			return 0; //do nothing if the mode is invalid
		}
	}

}
