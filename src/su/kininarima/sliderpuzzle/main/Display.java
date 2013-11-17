package su.kininarima.sliderpuzzle.main;

import java.util.ArrayDeque;

public class Display {
	private static final String newLine = System.getProperty("line.separator"); //OS agnostic linebreak

	public Display() {
	}

	public String boardToString(BoardState b) {
		StringBuffer sb = new StringBuffer();
		for (int y = 0; y<b.getBoard().length; y++){
			for (int x =0; x<b.getBoard().length; x++) {
				sb.append(b.getBoard()[x][y]);
			}
			sb.append(newLine);
		}
		return sb.toString();
	}

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
