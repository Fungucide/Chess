package framework.Pieces;

import framework.Board;
import framework.Move;
import framework.PieceName;

public class Pawn extends Piece {

	public Pawn(int color, Board board, PieceName name) {
		super(name, color, board);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isValidMove(Move m) {
		// TODO Auto-generated method stub
		return _isValidPawnMove(new PawnMove(m));
	}

	protected boolean _isValidPawnMove(PawnMove pm) {
		// TODO Auto-generated method stub
		if (currentBoard.getPiece(pm.endX, pm.endY) == null) {
			if (pm.startY == 1 || pm.startY == 6) { // Check for start pos
				if (pm.endY - pm.startY == 2 * COLOR && pm.startX == pm.endX
						&& currentBoard.getPiece(pm.startX, pm.startY - COLOR) == null) {
					// Check for two squares
					return true;
				}
			} else {
				if (pm.endY - pm.startY == COLOR && pm.startX == pm.endX) {
					// Check for normal move
					return true;
				} else if (pm.endY - pm.startY == COLOR && Math.abs(pm.startX - pm.endX) == 1
						&& ((COLOR == 1 && pm.startY == 4) || (COLOR == -1 && pm.startY == 3))
						&& currentBoard.getPiece(pm.endX, pm.startY).TYPE == PieceName.PAWN
						&& ((currentBoard.lastMove.startY == 1 && currentBoard.lastMove.startY == 3
								&& currentBoard.getPiece(pm.endX, pm.startY).COLOR == 1)
								|| (currentBoard.lastMove.startY == 6 && currentBoard.lastMove.endY == 4
										&& currentBoard.getPiece(pm.endX, pm.startY).COLOR == -1))) {
					// Check for edge case
					return true;
				}
			}
		} else {
			if (pm.endY - pm.startY == COLOR && Math.abs(pm.startX - pm.endX) == 1
					&& currentBoard.getPiece(pm.endX, pm.endY).COLOR == COLOR * -1) {
				return true;
			}
		}
		return false;
	}

	private static class PawnMove extends Move {

		public PawnMove(Move m) {
			super(m);
			// TODO Auto-generated constructor stub
		}

		public PawnMove(int sx, int sy, int ex, int ey) throws Exception {
			super(sx, sy, ex, ey);
		}
	}
}
