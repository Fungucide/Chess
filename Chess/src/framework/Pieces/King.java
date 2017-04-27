package framework.Pieces;

import java.util.ArrayList;
import java.util.Collections;

import framework.Board;
import framework.GenericMove;
import framework.Move;
import framework.PieceName;

public class King extends Piece {

	private boolean moveIsShortCastle = false;
	private boolean moveIsLongCastle = false;

	public King(PieceName name, int color, Board board) {
		super(name, color, board);
		// TODO Auto-generated constructor stub
	}

	public King(PieceName name, int color, Board board, int x, int y, boolean moved) throws Exception {
		super(name, color, board, x, y, moved);
	}

	@Override
	public boolean move(Move m) throws Exception {
		ArrayList<Move> pMoves = new ArrayList<>();
		for (Piece p : currentBoard.TeamRefrence.get(this.COLOR * -1)) {
			pMoves.addAll(p.allMoves());
		}
		Collections.sort(pMoves);
		if (currentBoard.getPiece(m.startX, m.startY) == this && valid && isValidMove(m)) {
			currentBoard.lastMove = m;
			if (moveIsShortCastle) {
				if (Collections.binarySearch(pMoves, new GenericMove(-1, -1, 4, m.startY)) == -1
						&& Collections.binarySearch(pMoves, new GenericMove(-1, -1, 5, m.startY)) == -1
						&& Collections.binarySearch(pMoves, new GenericMove(-1, -1, 6, m.startY)) == -1) {
					currentBoard.removePiece(4, m.startY);
					currentBoard.setPiece(this, 6, m.endY);
					currentBoard.setPiece(currentBoard.getPiece(7, m.endY), 5, m.endY);
					currentBoard.removePiece(7, m.endY);
				} else {
					return false;
				}
			} else if (moveIsLongCastle) {
				if (Collections.binarySearch(pMoves, new GenericMove(-1, -1, 4, m.startY)) == -1
						&& Collections.binarySearch(pMoves, new GenericMove(-1, -1, 5, m.startY)) == -1
						&& Collections.binarySearch(pMoves, new GenericMove(-1, -1, 6, m.startY)) == -1) {
					currentBoard.removePiece(4, m.startY);
					currentBoard.setPiece(this, 2, m.endY);
					currentBoard.setPiece(currentBoard.getPiece(0, m.endY), 3, m.endY);
					currentBoard.removePiece(0, m.endY);
				} else {
					return false;
				}
			} else if (Collections.binarySearch(pMoves, new GenericMove(-1, -1, m.endX, m.endY)) == -1) {
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
		}
		return false;
	}

	@Override
	public boolean isValidMove(Move m) {
		// TODO Auto-generated method stub
		return _isValidKingMove(new KingMove(m));
	}

	protected boolean _isValidKingMove(KingMove m) {
		if (Math.abs(m.startX - m.endX) == 1 || Math.abs(m.startY - m.endY) == 1) {
			if (currentBoard.getPiece(m.endX, m.endY) != null) {
				return false;
			} else {
			}
		} else if (!moved && m.endX == 6 && m.startY == m.endY) {
			if (currentBoard.getPiece(5, m.startY) == null && currentBoard.getPiece(6, m.startY) == null
					&& !currentBoard.getPiece(7, m.startY).moved) {
				moveIsShortCastle = true;
				return true;
			}
		} else if (!moved && m.endX == 2 && m.startY == m.endY) {
			if (currentBoard.getPiece(1, m.startY) == null && currentBoard.getPiece(2, m.startY) == null
					&& currentBoard.getPiece(3, m.startY) == null && !currentBoard.getPiece(7, m.startY).moved) {
				moveIsLongCastle = true;
				return true;
			}
		}
		return false;
	}

	private static class KingMove extends Move {

		public KingMove(int sx, int sy, int ex, int ey) throws Exception {
			super(sx, sy, ex, ey);
			// TODO Auto-generated constructor stub
		}

		public KingMove(Move m) {
			super(m);
			// TODO Auto-generated constructor stub
		}
	}
}
