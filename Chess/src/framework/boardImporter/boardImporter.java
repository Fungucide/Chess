package framework.boardImporter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import framework.Board;
import framework.PieceName;
import framework.Pieces.Bishop;
import framework.Pieces.King;
import framework.Pieces.Knight;
import framework.Pieces.Pawn;
import framework.Pieces.Piece;
import framework.Pieces.Queen;
import framework.Pieces.Rook;

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
				a = new King(PieceName.KING, color, b, x, y, false);
				break;
			case "QUEEN":
				a = new Queen(PieceName.QUEEN, color, b, x, y, false);
				break;
			case "BISHOP":
				a = new Bishop(PieceName.BISHOP, color, b, x, y, false);
				break;
			case "KNIGHT":
				a = new Knight(PieceName.KNIGHT, color, b, x, y, false);
				break;
			case "ROOK":
				a = new Rook(PieceName.ROOK, color, b, x, y, false);
				break;
			default:
				throw new Exception("Illegal piece");
			}
			b.setPiece(a, x, y);
		}
		return b;
	}
}
