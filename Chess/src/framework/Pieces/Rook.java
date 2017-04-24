package framework.Pieces;

import framework.Board;
import framework.Move;
import framework.PieceName;

public class Rook extends Piece {

	protected boolean moved = false;

	protected Rook(int color, Board board, PieceName name) {
		super(name, color, board);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isValidMove(Move m) {
		// TODO Auto-generated method stub
		return _isValidRookMove(new RookMove(m));
	}

	protected boolean _isValidRookMove(RookMove m) {
		if (m.startX != m.endX && m.startY != m.endY) {
			return false;
		} else if (m.startX == m.endX) {
			if (m.startY < m.endY) {
				for (int i = 1; i < m.endY - m.startY - 1; i++) {
					if (currentBoard.getPiece(m.startX, m.startY + i) != null) {
						return false;
					}
				}
			} else {
				for (int i = 1; i < m.startY - m.endY - 1; i++) {
					if (currentBoard.getPiece(m.startX, m.endY + i) != null) {
						return false;
					}
				}
			}
		} else if (m.startY == m.endY) {
			if (m.startX < m.endX) {
				for (int i = 0; i < m.endX - m.startX - 1; i++) {
					if (currentBoard.getPiece(m.startX + i, m.startY) != null) {
						return false;
					}
				}
			} else {
				for (int i = 1; i < m.startX - m.endX - 1; i++) {
					if (currentBoard.getPiece(m.endX + i, m.startY) != null) {
						return false;
					}
				}
			}
		}
		if (currentBoard.getPiece(m.endX, m.endY) != null && currentBoard.getPiece(m.endX, m.endY).COLOR == COLOR) {
			return false;
		}
		return true;
	}

	private static class RookMove extends Move {

		public RookMove(int sx, int sy, int ex, int ey) throws Exception {
			super(sx, sy, ex, ey);
			// TODO Auto-generated constructor stub
		}

		public RookMove(Move m) {
			super(m);
			// TODO Auto-generated constructor stub
		}
	}

}
