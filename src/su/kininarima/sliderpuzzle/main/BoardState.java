package su.kininarima.sliderpuzzle.main;

public class BoardState {
	private byte spacePos;
	private byte[][] board;
	public BoardState() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean isEqual(BoardState other){
		for(byte y = 0; y<9; y++){
			for (byte x = 0; x<9; x++){
				if (this.board[x][y] != other.board[x][y]){
					return false;
				}
			}
		}
		return true;
	}

}
