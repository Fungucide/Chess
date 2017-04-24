package framework.Pieces;

import framework.Board;
import framework.Move;
import framework.PieceName;

public class Bishop extends Piece {

	public Bishop(int color, Board board, PieceName name) {
		super(name, color, board);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isValidMove(Move m) {
		// TODO Auto-generated method stub
		return _isValidBishopMove(new BishopMove(m));
	}

	protected boolean _isValidBishopMove(BishopMove m) {
		if (Math.abs(m.startX - m.endX) == Math.abs(m.startY - m.endY)) {
			int slope = (m.startX - m.endX) / (m.startY - m.endY);
			if (slope == 1) {
				if (m.startX < m.endX) {
					for (int i = 1; i < m.endX - m.startX - 1; i++) {
						if (currentBoard.getPiece(m.startX + i, m.startY + i) != null) {
							return false;
						}
					}
				} else {
					for (int i = -1; i > m.endX - m.startX + 1; i--) {
						if (currentBoard.getPiece(m.startX + i, m.startY + i) != null) {
							return false;
						}
					}
				}
			} else if (slope == -1) {
				if (m.startX > m.endX) {
					for (int i = 1; i < m.startX - m.endX - 1; i++) {
						if (currentBoard.getPiece(m.startX - i, m.startY + i) != null) {
							return false;
						}
					}
				} else {
					for (int i = 1; i < m.endX - m.startX - 1; i++) {
						if (currentBoard.getPiece(m.startX + i, m.startY - i) != null) {
							return false;
						}
					}
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
		if (currentBoard.getPiece(m.endX, m.endY) != null && currentBoard.getPiece(m.endX, m.endY).COLOR == COLOR) {
			return false;
		}
		return true;
	}

	private static class BishopMove extends Move {

		public BishopMove(int sx, int sy, int ex, int ey) throws Exception {
			super(sx, sy, ex, ey);
			// TODO Auto-generated constructor stub
		}

		public BishopMove(Move m) {
			super(m);
			// TODO Auto-generated constructor stub
		}

	}
}
