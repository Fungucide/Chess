package framework.Pieces;

import framework.Board;
import framework.Move;
import framework.PieceName;

public class Bishop extends Piece {

	public Bishop(PieceName name, int color, Board board) {
		super(name, color, board);
		// TODO Auto-generated constructor stub
	}

	public Bishop(PieceName name, int color, Board board, int x, int y, boolean moved) throws Exception {
		super(name, color, board, x, y, moved);
	}

	@Override
	public boolean isValidMove(Move m) {
		// TODO Auto-generated method stub
		try {
			return _isValidBishopMove(new BishopMove(m));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	protected boolean _isValidBishopMove(BishopMove m) throws Exception {
		if (currentBoard.getPiece(m.endX, m.endY) != null && currentBoard.getPiece(m.endX, m.endY).COLOR == COLOR) {
			return false;
		} else {
			if (Math.abs(m.startX - m.endX) != Math.abs(m.startY - m.endY)) {
				return false;
			}
			int dX = (m.endX - m.startX) / Math.abs(m.endX - m.startX);
			int dY = (m.endY - m.startY) / Math.abs(m.endY - m.startY);
			for (int i = 1; i < Math.abs(m.endX - m.startX); i++) {
				if (currentBoard.getPiece(m.startX + dX * i, m.startY + dY * i) != null) {
					return false;
				}
			}
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
