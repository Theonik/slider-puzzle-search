package su.kininarima.sliderpuzzle.main;

public class BoardState {
	private byte spacePosY;
	private byte spacePosX;
	private byte[][] board;
	public BoardState(byte[][] inBoard, byte x, byte y) {
		board = inBoard;
		spacePosX = x;
		spacePosY = y;
	}
	
	public BoardState(BoardState b, Direction d){
		this.spacePosY = b.spacePosY;
		this.spacePosX = b.spacePosX;
		this.board = b.board;
		moveSpace(d);
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
	
	private boolean moveSpace(Direction d) {
		switch (d){
		case UP:
			if (spacePosY>0){
				board[spacePosX][spacePosY] = board[spacePosX][spacePosY-1];
				spacePosY--;
				board[spacePosX][spacePosY] = 0;
				return true;
			}
		case DOWN:
			if (spacePosY<(board.length-1)) {
				board[spacePosX][spacePosY] = board[spacePosX][spacePosY+1];
				spacePosY++;
				board[spacePosX][spacePosY] = 0;
				return true;
			}
		case LEFT:
			if (spacePosX>0){
				board[spacePosX][spacePosY] = board[spacePosX-1][spacePosY];
				spacePosX--;
				board[spacePosX][spacePosY] = 0;
				return true;
			}
		case RIGHT:
			if (spacePosX<(board[0].length-1)) {
				board[spacePosX][spacePosY] = board[spacePosX+1][spacePosY];
				spacePosX++;
				board[spacePosX][spacePosY] = 0;
				return true;
			}
		}
		return false;		
	}

}
