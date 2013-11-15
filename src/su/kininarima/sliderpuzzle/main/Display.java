package su.kininarima.sliderpuzzle.main;

public class Display {
	BoardState bState;
	
	public Display(BoardState b) {
		bState = b;
	}
	
	public String boardToString() {
		StringBuffer sb = new StringBuffer();
		for (int y = 0; y<bState.getBoard().length; y++){
			for (int x =0; x<bState.getBoard().length; x++) {
				sb.append(bState.getBoard()[x][y]);
			}
			sb.append('\n');
		}
		return sb.toString();
	}

}
