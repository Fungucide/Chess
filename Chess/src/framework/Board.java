package framework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import framework.Pieces.Piece;

public class Board {
	private Piece[][] board;
	public Move lastMove;
	public HashMap<Integer, ArrayList<Piece>> TeamRefrence = new HashMap<>();
	private ArrayList<Piece> team1 = new ArrayList<Piece>();
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

	public boolean initializePiece(Piece p, int x, int y) {
		if (board[x][y] == null) {
			board[x][y] = p;
			p.x = x;
			p.y = y;
			TeamRefrence.get(p.COLOR).add(p);
			return true;
		}
		return false;
	}

	public boolean setPiece(Piece p, int x, int y) {
		if (board[x][y] == null) {
			board[x][y] = p;
			p.x = x;
			p.y = y;
			return true;
		}
		return false;
	}

	public void removePiece(int x, int y) {
		board[x][y] = null;
	}

	public ArrayList<Move> teamKingCheck(int t) throws Exception {
		ArrayList<Move> pMoves = new ArrayList<>();
		for (Piece p : TeamRefrence.get(t)) {
			if (p.valid) {
				pMoves.addAll(p.kingCheck());
			}
		}
		Collections.sort(pMoves);
		return pMoves;
	}

	public ArrayList<Move> teamMove(int t) throws Exception {
		ArrayList<Move> pMoves = new ArrayList<>();
		for (Piece p : TeamRefrence.get(t)) {
			if (p.valid) {
				pMoves.addAll(p.allMoves());
			}
		}
		Collections.sort(pMoves);
		return pMoves;
	}

}
