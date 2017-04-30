package test;

import java.util.ArrayList;

import framework.Board;
import framework.Move;
import framework.boardImporter.boardImporter;

public class Test {

	public static void main(String[] args) throws Exception {
		Board b = boardImporter.importer("Resources\\BoardLayouts\\KingTest2.dat");
		ArrayList<Move> m = b.getPiece(4, 0).allMoves();
		for (Move a : m) {
			a.print();
		}
	}

}
