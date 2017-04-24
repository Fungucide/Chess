package framework.boardImporter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import framework.Board;
import framework.PieceName;
import framework.Pieces.Pawn;
import framework.Pieces.Piece;

public class boardImporter {
	public static Board boardImporter(String path) throws IOException {
		Board b = new Board();
		BufferedReader br = new BufferedReader(new FileReader(new File(path)));
		String temp;
		String[] split;
		while ((temp = br.readLine()) != null) {
			split = temp.split(" ");
			Piece a;
			switch (split[0]) {
			case "PAWN":
				a=new Pawn(color, board, name)
				break;
			case "KING":
				break;
			case "QUEEN":
				break;
			case "BISHOP":
				break;
			case "KNIGHT":
				break;
			case "ROOK":
				break;
			default:
				break;
			}
		}
		return b;
	}
}
