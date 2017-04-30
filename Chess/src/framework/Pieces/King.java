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
	protected boolean move(Move m, boolean real) throws Exception {
		ArrayList<Move> pMoves = new ArrayList<>();
		for (Piece p : currentBoard.TeamRefrence.get(this.COLOR * -1)) {
			pMoves.addAll(p.kingCheck());
		}
		Collections.sort(pMoves);
		if (super.move(m, real) && !moveIsShortCastle && !moveIsLongCastle) {
			System.out.print("Normal");
			m.print();
			return true;
		} else if (Collections.binarySearch(pMoves, new GenericMove(-1, -1, m.startX, m.startY)) < 0
				&& Collections.binarySearch(pMoves, new GenericMove(-1, -1, m.endX, m.endY)) < 0) {
			if (moveIsShortCastle) {
				moveIsShortCastle = false;
				if (Collections.binarySearch(pMoves, new GenericMove(-1, -1, 5, m.endY)) < 0) {
					if (real) {
						currentBoard.setPiece(this, m.endX, m.endY);
						currentBoard.removePiece(m.startX, m.startY);
						currentBoard.setPiece(currentBoard.getPiece(7, y), 5, y);
						currentBoard.removePiece(7, y);
						x = m.endX;
						y = m.endY;
						moved = true;
						currentBoard.lastMove = m;
						currentBoard.getPiece(5, y).x = 5;
						currentBoard.getPiece(5, y).moved = true;
					}
					System.out.print("Short");
					m.print();
					return true;
				}
			} else if (moveIsLongCastle) {
				moveIsLongCastle = false;
				if (Collections.binarySearch(pMoves, new GenericMove(-1, -1, 3, m.endY)) < 0) {
					if (real) {
						currentBoard.setPiece(this, m.endX, m.endY);
						currentBoard.removePiece(m.startX, m.startY);
						currentBoard.setPiece(currentBoard.getPiece(0, y), 3, y);
						currentBoard.removePiece(0, y);
						x = m.endX;
						y = m.endY;
						moved = true;
						currentBoard.lastMove = m;
						currentBoard.getPiece(3, y).x = 3;
						currentBoard.getPiece(3, y).moved = true;
					}
					System.out.print("Long");
					m.print();
					return true;
				}
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
		if (Math.abs(m.startX - m.endX) <= 1 && Math.abs(m.startY - m.endY) <= 1) {
			if (currentBoard.getPiece(m.endX, m.endY) != null && currentBoard.getPiece(m.endX, m.endY).COLOR == COLOR) {
				return false;
			} else {
				return true;
			}
		} else if (!moved && m.endX == 6 && ((m.startY == 0 && COLOR == 1) || (m.startY == 7 && COLOR == -1))
				&& m.startY == m.endY) {
			if (currentBoard.getPiece(5, m.startY) == null && currentBoard.getPiece(6, m.startY) == null
					&& currentBoard.getPiece(7, m.startY) != null
					&& currentBoard.getPiece(7, m.startY).TYPE == PieceName.ROOK
					&& !currentBoard.getPiece(7, m.startY).moved) {
				moveIsShortCastle = true;
				return true;
			}
		} else if (!moved && m.endX == 2 && ((m.startY == 0 && COLOR == 1) || (m.startY == 7 && COLOR == -1))
				&& m.startY == m.endY) {
			if (currentBoard.getPiece(1, m.startY) == null && currentBoard.getPiece(2, m.startY) == null
					&& currentBoard.getPiece(3, m.startY) == null && currentBoard.getPiece(0, m.startY) != null
					&& currentBoard.getPiece(0, m.startY).TYPE == PieceName.ROOK
					&& !currentBoard.getPiece(0, m.startY).moved) {
				moveIsLongCastle = true;
				System.out.print("Long C");
				m.print();
				return true;
			}
		}
		return false;
	}

	@Override
	public ArrayList<Move> kingCheck() throws Exception {
		ArrayList<Move> r = super.kingCheck();
		moveIsShortCastle = false;
		moveIsLongCastle = false;
		return r;
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
