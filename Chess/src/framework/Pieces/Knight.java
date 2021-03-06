package framework.Pieces;

import framework.Board;
import framework.Game;
import framework.Move;
import framework.PieceName;

public class Knight extends Piece {

	public Knight(PieceName name, int color, Game g, Board board) {
		super(name, color, g, board);
		// TODO Auto-generated constructor stub
	}

	public Knight(PieceName name, int color, Game g, Board board, int x, int y, boolean moved) throws Exception {
		super(name, color, g, board, x, y, moved);
	}

	@Override
	public boolean isValidMove(Move m) {
		// TODO Auto-generated method stub
		return _isValidKnightMove(new KnightMove(m));
	}

	protected boolean _isValidKnightMove(KnightMove m) {
		if (Math.abs(m.startX - m.endX) == 1 || Math.abs(m.startX - m.endX) == 2) {
			if (Math.abs(m.startY - m.endY) == 3 - Math.abs(m.startX - m.endX)) {
				if (currentBoard.getPiece(m.endX, m.endY) == null
						|| currentBoard.getPiece(m.endX, m.endY).COLOR != COLOR) {
					return true;
				}
			}
		}
		return false;
	}

	private static class KnightMove extends Move {

		public KnightMove(int sx, int sy, int ex, int ey) throws Exception {
			super(sx, sy, ex, ey);
			// TODO Auto-generated constructor stub
		}

		public KnightMove(Move m) {
			super(m);
			// TODO Auto-generated constructor stub
		}
	}

}
