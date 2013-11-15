package su.kininarima.sliderpuzzle.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class FileIO {

	public FileIO() {

	}

	public BoardState loadPuzzle(File file) throws FileNotFoundException {
		byte[][] board = new byte[3][3];
		byte spaceX = -1;
		byte spaceY = -1;
		Scanner infile =new Scanner(new InputStreamReader
				(new FileInputStream(file)));
		for (int y = 0; y<3; y++){
			String s = infile.nextLine();
			int x = 0;
			for (int i = 0;i<s.length(); i++){
				if (s.charAt(i) == '0'){
					spaceX = (byte) x;
					spaceY = (byte) y;
					board[x][y] = 0;
					x++;
				}
				else if (s.charAt(i) == ',') {}
				else {
					board[x][y] = (byte)(s.charAt(i)-48);
					x++;
				}
			}
		}
		infile.close();
		BoardState outBoard = new BoardState(board,spaceX,spaceY);
		return outBoard;
	}

}
