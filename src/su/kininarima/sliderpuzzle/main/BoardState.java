package su.kininarima.sliderpuzzle.main;

import java.util.Arrays;

public class BoardState {
	private byte spacePosY;
	private byte spacePosX;
	private byte[][] board;
	private long depth;
	private BoardState parent;
	
	public BoardState(byte[][] inBoard, byte x, byte y) {
		this.board = inBoard;
		this.spacePosX = x;
		this.spacePosY = y;
		this.depth = 0;
		this.parent = null;
	}
	
	public BoardState(BoardState b, Direction d){
		this.board = b.board.clone();
		for (int y = 0; y<this.board.length;y++){
			this.board[y] = b.board[y].clone();
		}	
		this.spacePosY = b.spacePosY;
		this.spacePosX = b.spacePosX;
		this.depth = b.depth;
		this.parent = b;
		this.moveSpace(d);
		this.depth++;
	}
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(board);
		return result;
	}

	@Override
	public boolean equals(Object obj){
		if (obj == null) {
			return false;
		}
		BoardState other = (BoardState) obj;
		for(byte y = 0; y<board.length; y++){ //because arrays in Java are the work of the devil and I don't trust Arrays.deepEquals() to work.
			for (byte x = 0; x<board[0].length; x++){
				if (this.board[x][y] != other.board[x][y]){
					return false;
				}
			}
		}
		return true;
	}

	public byte[][] getBoard(){
		return board;
	}
	
	public long getDepth(){
		return depth;
	}
	
	public BoardState getParent(){
		return parent;
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
