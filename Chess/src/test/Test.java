package test;

import java.util.ArrayList;

import framework.Board;
import framework.GenericMove;
import framework.Move;
import framework.PieceName;
import framework.Pieces.*;
import framework.boardImporter.boardImporter;

public class Test {

	public static void main(String[] args) throws Exception {
		Board b = boardImporter.importer("Resources\\BoardLayouts\\Classic.dat");
		System.out.println(b.getPiece(1, 0).TYPE);
		ArrayList<Move> valid = b.getPiece(1, 0).allMoves();
		for (Move m : valid) {
			m.print();
		}
	}

}
