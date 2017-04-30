package GUI;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import framework.Board;
import framework.Pieces.Piece;

public class BoardPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 * 
	 * @throws Exception
	 */
	public BoardPanel(Board b) throws Exception {
		for (Piece p : b.TeamRefrence.get(1)) {
			p.kingCheck();
		}
		for (Piece p : b.TeamRefrence.get(-1)) {
			p.kingCheck();
		}
		setLayout(new BorderLayout(0, 0));
		BoardCanvas bc = new BoardCanvas(b);
		add(bc, BorderLayout.CENTER);

	}

}
