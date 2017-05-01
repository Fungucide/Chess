package framework.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import framework.Board;
import framework.Game;
import framework.PieceName;
import framework.Pieces.Bishop;
import framework.Pieces.King;
import framework.Pieces.Knight;
import framework.Pieces.Pawn;
import framework.Pieces.Piece;
import framework.Pieces.Queen;
import framework.Pieces.Rook;

public class gameLoader {
	public static Board load(String path) throws Exception {
		Board b = new Board();
		Game g = new Game(b);
		BufferedReader br = new BufferedReader(new FileReader(new File(path)));
		String temp;
		String[] split;
		while ((temp = br.readLine()) != null) {
			split = temp.split(" ");
			int color = Integer.parseInt(split[1]);
			int x = Integer.parseInt(split[2]);
			int y = Integer.parseInt(split[3]);
			boolean moved = Boolean.parseBoolean(split[4]);
			Piece a;
			switch (split[0]) {
			case "PAWN":
				a = new Pawn(PieceName.PAWN, color, g, b, x, y, moved);
				break;
			case "KING":
				a = new King(PieceName.KING, color, g, b, x, y, moved);
				break;
			case "QUEEN":
				a = new Queen(PieceName.QUEEN, color, g, b, x, y, moved);
				break;
			case "BISHOP":
				a = new Bishop(PieceName.BISHOP, color, g, b, x, y, moved);
				break;
			case "KNIGHT":
				a = new Knight(PieceName.KNIGHT, color, g, b, x, y, moved);
				break;
			case "ROOK":
				a = new Rook(PieceName.ROOK, color, g, b, x, y, moved);
				break;
			default:
				throw new Exception("Illegal piece");
			}
			if (x == -1 && y == -1) {
				b.TakenRefrence.get(color).add(a);
			} else {
				b.initializePiece(a, x, y);
			}
		}
		br.close();
		return b;
	}
}
