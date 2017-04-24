package framework;

import java.util.ArrayList;
import java.util.HashMap;

import framework.Pieces.Piece;

public class Board {
	private Piece[][] board;
	public Move lastMove;
	public HashMap<Integer, ArrayList<Piece>> TeamRefrence = new HashMap<>();
	private ArrayList<Piece> t1 = new ArrayList<>();
	private ArrayList<Piece> t2 = new ArrayList<>();

	public Board() {
		board = new Piece[8][8];
		TeamRefrence.put(1, t1);
		TeamRefrence.put(-1, t2);
	}

	public Piece getPiece(int x, int y) {
		return board[x][y];
	}

	public boolean setPiece(Piece p, int x, int y) {
		if (board[x][y] == null) {
			board[x][y] = p;
			p.x = x;
			p.y = y;
			TeamRefrence.get(p.COLOR).add(p);
			return true;
		}
		return false;
	}

	public void removePiece(int x, int y) {
		board[x][y] = null;
	}

}
