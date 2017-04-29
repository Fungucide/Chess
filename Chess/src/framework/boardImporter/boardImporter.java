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
	public static Board importer(String path) throws Exception {
		Board b = new Board();
		BufferedReader br = new BufferedReader(new FileReader(new File(path)));
		String temp;
		String[] split;
		while ((temp = br.readLine()) != null) {
			split = temp.split(" ");
			int color = Integer.parseInt(split[1]);
			int x = Integer.parseInt(split[2]);
			int y = Integer.parseInt(split[3]);
			Piece a;
			switch (split[0]) {
			case "PAWN":
				a = new Pawn(PieceName.PAWN, color, b, x, y, false);
				break;
			case "KING":
				a = new Pawn(PieceName.KING, color, b, x, y, false);
				break;
			case "QUEEN":
				a = new Pawn(PieceName.QUEEN, color, b, x, y, false);
				break;
			case "BISHOP":
				a = new Pawn(PieceName.BISHOP, color, b, x, y, false);
				break;
			case "KNIGHT":
				a = new Pawn(PieceName.KNIGHT, color, b, x, y, false);
				break;
			case "ROOK":
				a = new Pawn(PieceName.ROOK, color, b, x, y, false);
				break;
			default:
				throw new Exception("Illegal piece");
			}
			b.setPiece(a, x, y);
		}
		return b;
	}
}
