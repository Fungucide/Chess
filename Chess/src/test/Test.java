package test;

import framework.Board;
import framework.GenericMove;
import framework.boardImporter.gameImporter;

public class Test {

	public static void main(String[] args) throws Exception {
		Board b = gameImporter.importer("Resources\\BoardLayouts\\RookTest.dat");
		System.out.println(b.getPiece(4, 4).move(new GenericMove(4, 4, 4, 0)));
	}

}
