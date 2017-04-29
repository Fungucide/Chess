package GUI;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JPanel;

import framework.Board;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public BoardPanel(Board b) {
		setLayout(new BorderLayout(0, 0));
		BoardCanvas bc = new BoardCanvas(b);
		add(bc,BorderLayout.CENTER);

		
	}

}
