package framework.Pieces;

import java.util.ArrayList;

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

	protected Piece(PieceName name, int color, Board board, int x, int y, boolean moved) {
		this.COLOR = color;
		currentBoard = board;
		this.TYPE = name;
		this.moved = moved;
	}

	public boolean move(Move m) throws Exception {
		if (currentBoard.getPiece(m.startX, m.startY) == this && valid && isValidMove(m)) {
			currentBoard.lastMove = m;
			currentBoard.setPiece(null, m.startX, m.startY);
			if (currentBoard.getPiece(m.endX, m.endY) != null) {
				currentBoard.getPiece(m.endX, m.endY).valid = false;
				currentBoard.getPiece(m.endX, m.endY).x = -1;
				currentBoard.getPiece(m.endX, m.endY).y = -1;
			}
			currentBoard.removePiece(m.startX, m.startY);
			currentBoard.setPiece(this, m.endX, m.endY);
			x = m.endX;
			y = m.endY;
			moved = true;
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
				if (this.valid && isValidMove(m)) {
					valid.add(m);
				}
			}
		}
		return valid;
	}
}
