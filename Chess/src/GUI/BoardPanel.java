package GUI;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import framework.Board;

public class BoardPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public BoardPanel(Board b) {
		setLayout(new BorderLayout(0, 0));
		BoardCanvas bc = new BoardCanvas();
		add(bc,BorderLayout.CENTER);

		
	}

}
