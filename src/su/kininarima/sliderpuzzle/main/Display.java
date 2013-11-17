package su.kininarima.sliderpuzzle.main;

import java.util.ArrayDeque;
/** this class generates states and solutions as strings **/
public class Display {
	private static final String newLine = System.getProperty("line.separator"); //OS agnostic linebreak

	public Display() {
	}

	/** takes a single state and prints its board as a string 
	 * @param b the state to print**/
	public String boardToString(BoardState b) {
		StringBuffer sb = new StringBuffer();
		for (int y = 0; y<b.getBoard().length; y++){
			for (int x =0; x<b.getBoard().length; x++) {
				if (b.getBoard()[x][y] == 0){
					sb.append(' ');
				}
				else {
					sb.append(b.getBoard()[x][y]);
				}
			}
			sb.append(newLine);
		}
		return sb.toString();
	}

	/** takes a board state and tracks it down to its first parent. Then prints each node in the right order 
	 * @param b solution to print**/
	public String solutionToString(BoardState b) {
		StringBuffer sb = new StringBuffer();
		ArrayDeque<BoardState> stack = new ArrayDeque<BoardState>();
		stack.push(b);
		while (stack.peek().getParent() != null) {
			stack.push(stack.peek().getParent());
		}
		while (!stack.isEmpty()) {
			sb.append(boardToString(stack.pop()));
			sb.append(newLine);
		}
		return sb.toString();
	}
}
