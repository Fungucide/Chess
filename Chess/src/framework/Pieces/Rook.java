package framework.Pieces;

import framework.Board;
import framework.Move;
import framework.PieceName;

public class Rook extends Piece {

	protected boolean moved = false;

	public Rook(PieceName name, int color, Board board) {
		super(name, color, board);
		// TODO Auto-generated constructor stub
	}

	public Rook(PieceName name, int color, Board board, int x, int y, boolean moved) throws Exception {
		super(name, color, board, x, y, moved);
	}

	@Override
	public boolean isValidMove(Move m) {
		// TODO Auto-generated method stub
		return _isValidRookMove(new RookMove(m));
	}

	protected boolean _isValidRookMove(RookMove m) {
		if (m.startX != m.endX && m.startY != m.endY) {
			return false;
		} else if (m.startY == m.endY) {
			int dX = (m.endX - m.startX) / Math.abs(m.endX - m.startX);
			for (int i = 1; i < m.endX - m.startX; i++) {
				if (currentBoard.getPiece(m.startX + dX * i, m.startY) != null) {
					return false;
				}
			}
		} else if (m.startX == m.endX) {
			int dY = (m.endY - m.startY) / Math.abs(m.endY - m.startY);
			for (int i = 1; i < m.endY - m.startY; i++) {
				if (currentBoard.getPiece(m.startX, m.startY + dY * i) != null) {
					return false;
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
