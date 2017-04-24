package test;

import java.util.ArrayList;

import framework.Board;
import framework.GenericMove;
import framework.Move;
import framework.PieceName;
import framework.Pieces.*;

public class Test {

	public static void main(String[] args) throws Exception {
		Board b = new Board();
		Knight k = new Knight(1, b, PieceName.KNIGHT);
		b.setPiece(k, 0, 0);
		if (k.move(new GenericMove(0, 0, 1, 2))) {
			System.out.println("1");
		}
		ArrayList<Move> valid = k.allMoves();
		for (Move m : valid) {
			m.print();
		}
	}

}
