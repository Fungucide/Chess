package framework.Pieces;

import framework.Board;
import framework.Move;
import framework.PieceName;

public class Queen extends Piece {

	protected Queen(int color, Board board, PieceName name) {
		super(name, color, board);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isValidMove(Move m) {
		// TODO Auto-generated method stub
		return _isValidQueenMove(new QueenMove(m));
	}

	protected boolean _isValidQueenMove(QueenMove m) {
		int slope;
		if (m.startX == m.endX) {
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
		} else if (m.startX == m.endX) {
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
		} else if ((slope = (m.startX - m.endX) / (m.startY - m.endY)) == 1) {
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
		if (currentBoard.getPiece(m.endX, m.endY) != null && currentBoard.getPiece(m.endX, m.endY).COLOR == COLOR) {
			return false;
		}
		return true;
	}

	private static class QueenMove extends Move {

		public QueenMove(int sx, int sy, int ex, int ey) throws Exception {
			super(sx, sy, ex, ey);
			// TODO Auto-generated constructor stub
		}

		public QueenMove(Move m) {
			super(m);
			// TODO Auto-generated constructor stub
		}

	}

}
