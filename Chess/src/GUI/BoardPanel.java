package GUI;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import framework.Board;

public class BoardPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public BoardPanel(Board b) {
		setLayout(new BorderLayout(0, 0));
		BoardCanvas bc = new BoardCanvas(b);
		add(bc,BorderLayout.CENTER);

		
	}

}
