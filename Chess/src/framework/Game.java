package framework;

import java.util.ArrayList;
import java.util.Collections;

import framework.Pieces.Piece;

public class Game {
	public final Board b;
	public int turn = 1;
	public int noMoves;
	public int check;

	public Game(Board b) {
		this.b = b;
	}

	public void moveMade() throws Exception {
		System.out.println("Here " + turn);
		noMoves = 0;
		check = 0;
		ArrayList<Move> t1 = b.teamKingCheck(1);
		ArrayList<Move> t2 = b.teamKingCheck(-1);
		System.out.println(t1.size());
		System.out.println(t2.size());
		if (turn == -1 && t1.size() == 0) {
			System.out.println("Team 1 turn:" + t1.size());
			noMoves = -1;
		} else if (turn == 1 && t2.size() == 0) {
			System.out.println("Team 2 turn:" + t2.size());
			noMoves = 1;
		}

		if (turn == -1) {
			for (Piece p : b.TeamRefrence.get(1)) {
				if (p.TYPE == PieceName.KING) {
					if (Collections.binarySearch(t2, new GenericMove(-1, -1, p.x, p.y)) >= 0) {
						check = 1;
						break;
					}
				}
			}
		} else if (turn == 1) {
			for (Piece p : b.TeamRefrence.get(-1)) {
				if (p.TYPE == PieceName.KING) {
					if (Collections.binarySearch(t1, new GenericMove(-1, -1, p.x, p.y)) >= 0) {
						check = -1;
						break;
					}
				}
			}
		}
		turn *= -1;
	}
}
