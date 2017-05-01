package GUI;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import framework.Game;

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
	public BoardPanel(Game g) throws Exception {

		setLayout(new BorderLayout(0, 0));
		BoardCanvas bc = new BoardCanvas(g);
		add(bc, BorderLayout.CENTER);

	}

}
