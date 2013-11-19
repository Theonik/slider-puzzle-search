package su.kininarima.sliderpuzzle.main;

import java.util.Arrays;
/** This class is used to represent and generate each state of the 8-puzzle board.**/
public class BoardState {
	private byte spacePosX, spacePosY; //cursors storing the location of the space.
	private byte[][] board; //stores the board
	private long depth; //keeps track of the depth of a solution
	private BoardState parent; //keeps track of the parent that was used to generate this state

	/** Constructor used to load a new state 
	 * @param inBoard board to load
	 * @param x x-position of space
	 * @param y y-position of space**/
	public BoardState(byte[][] inBoard, byte x, byte y) {
		this.board = inBoard;
		this.spacePosX = x;
		this.spacePosY = y;
		this.depth = 0;
		this.parent = null;
	}

	/** Constructor used to create a new state based on an old one.
	 * @param b the state this state is based on
	 * @param d the direction the space needs to move**/
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
		result = prime * result + Arrays.deepHashCode(board);
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

	/** @return  the board in a byte[][]**/
	public byte[][] getBoard(){
		return board;
	}

	/** @return the depth the state is located at as a long **/
	public long getDepth(){
		return depth;
	}

	/** @return the pointer to the parent **/
	public BoardState getParent(){
		return parent;
	}

	/** moves the space by 1 in a specified direction and replaces it with what was previously in that location 
	 * @param d the direction of the motion
	 * @return true if the move was possible.**/
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
