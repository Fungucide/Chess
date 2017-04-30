package framework;

import java.util.ArrayList;
import java.util.HashMap;

import framework.Pieces.Piece;

public class Board {
	private Piece[][] board;
	public Move lastMove;
	public HashMap<Integer, ArrayList<Piece>> TeamRefrence = new HashMap<>();
	private ArrayList<Piece> team1 = new ArrayList<>();
	private ArrayList<Piece> team2 = new ArrayList<>();
	public HashMap<Integer, ArrayList<Piece>> TakenRefrence = new HashMap<>();
	private ArrayList<Piece> taken1 = new ArrayList<>();
	private ArrayList<Piece> taken2 = new ArrayList<>();

	public Board() {
		board = new Piece[8][8];
		TeamRefrence.put(1, team1);
		TeamRefrence.put(-1, team2);
		TakenRefrence.put(1, taken1);
		TakenRefrence.put(-1, taken2);
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
