package framework;

public abstract class Move implements Comparable<Move> {
	public final int startX, startY, endX, endY;

	public Move(int sx, int sy, int ex, int ey) throws Exception {
		if (sx > 8 || sx < 0) {
			throw new Exception("Move Class -> Starting X bound out of range");
		}
		if (sy > 8 || sy < 0) {
			throw new Exception("Move Class -> Starting Y bound out of range");
		}
		if (ex > 8 || ex < 0) {
			throw new Exception("Move Class -> Ending X bound out of range");
		}
		if (ey > 8 || ey < 0) {
			throw new Exception("Move Class -> Ending Y bound out of range");
		}
		if (sx == ex && sy == ey) {
			throw new Exception("Move Class -> Illegal move -> Cannot move to same spot");
		}
		startX = sx;
		startY = sy;
		endX = ex;
		endY = ey;
	}

	public Move(Move m) {
		startX = m.startX;
		startY = m.startY;
		endX = m.endX;
		endY = m.endY;
	}

	public void print() {
		System.out.println("[(" + startX + "," + startY + "),(" + endX + "," + endY + ")]");
	}

	@Override
	public int compareTo(Move m) {
		if (endX > m.endX) {
			return 1;
		} else if (endX < m.endX) {
			return -1;
		} else {
			if (endY > m.endY) {
				return 1;
			} else if (endY < m.endY) {
				return -1;
			} else {
				return 0;
			}
		}
	}
}
