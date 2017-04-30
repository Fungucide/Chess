package framework.Pieces;

import java.util.ArrayList;
import java.util.Collections;

import framework.Board;
import framework.GenericMove;
import framework.Move;
import framework.PieceName;

public abstract class Piece {
	public final int COLOR;
	protected static Board currentBoard;
	public final PieceName TYPE;
	public boolean valid = true;
	public boolean moved = false;
	public int x;
	public int y;

	protected Piece(PieceName name, int color, Board board) {
		this.COLOR = color;
		currentBoard = board;
		this.TYPE = name;
	}

	protected Piece(PieceName name, int color, Board board, int x, int y, boolean moved) throws Exception {
		this.COLOR = color;
		currentBoard = board;
		this.TYPE = name;
		this.moved = moved;
		if (!board.setPiece(this, x, y)) {
			throw new Exception("Illegal Placement of piece");
		}
	}

	public boolean move(Move m) throws Exception {
		return move(m, true);
	}

	protected boolean move(Move m, boolean real) throws Exception {
		if (currentBoard.getPiece(m.startX, m.startY) == this && valid && isValidMove(m)) {
			Piece tr = null;
			boolean taken = false;
			if (currentBoard.getPiece(m.endX, m.endY) != null) {
				taken = true;
				tr = currentBoard.getPiece(m.endX, m.endY);
				currentBoard.removePiece(m.endX, m.endY);
				tr.valid = false;
			}
			currentBoard.removePiece(m.startX, m.startY);
			currentBoard.setPiece(this, m.endX, m.endY);
			x = m.endX;
			y = m.endY;

			ArrayList<Move> pMoves = new ArrayList<>();
			for (Piece p : currentBoard.TeamRefrence.get(this.COLOR * -1)) {
				if (p.valid) {
					pMoves.addAll(p.kingCheck());
				}
			}
			Collections.sort(pMoves);

			boolean flag = false;
			for (Piece p : currentBoard.TeamRefrence.get(this.COLOR)) {
				if (p.TYPE == PieceName.KING) {
					if (Collections.binarySearch(pMoves, new GenericMove(-1, -1, p.x, p.y)) >= 0) {
						flag = true;
						break;
					}
				}
			}

			if (flag || !real) {
				currentBoard.setPiece(this, m.startX, m.startY);
				currentBoard.removePiece(m.endX, m.endY);
				x = m.startX;
				y = m.startY;
				if (taken) {
					currentBoard.setPiece(tr, m.endX, m.endY);
					tr.valid = true;
				}
			}

			if (flag) {
				return false;
			}

			if (real) {
				currentBoard.lastMove = m;
				moved = true;
				if (taken) {
					currentBoard.TakenRefrence.get(this.COLOR * -1).add(tr);
					currentBoard.TeamRefrence.get(this.COLOR * -1).remove(tr);
					tr.valid = false;
					tr.x = -1;
					tr.y = -1;
				}
			}

			return true;
		}
		return false;
	}

	public abstract boolean isValidMove(Move m);

	public ArrayList<Move> allMoves() throws Exception {
		GenericMove m;
		ArrayList<Move> valid = new ArrayList<Move>();
		for (int i = 0; i < 8; i++) {
			for (int h = 0; h < 8; h++) {
				if (x == i && y == h) {
					continue;
				}
				m = new GenericMove(x, y, i, h);
				if (this.valid && move(m, false)) {
					valid.add(m);
				}
			}
		}
		return valid;
	}

	public ArrayList<Move> kingCheck() throws Exception {
		GenericMove m;
		ArrayList<Move> valid = new ArrayList<Move>();
		for (int i = 0; i < 8; i++) {
			for (int h = 0; h < 8; h++) {
				if (x == i && y == h) {
					continue;
				}
				m = new GenericMove(x, y, i, h);
				if (this.valid && isValidMove(m)) {
					valid.add(m);
				}
			}
		}
		return valid;
	}
}
