package GUI;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import framework.Game;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

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

		JMenuBar menuBar = new JMenuBar();
		add(menuBar, BorderLayout.NORTH);

		JMenu mnGame = new JMenu("Game");
		menuBar.add(mnGame);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		BoardCanvas bc = new BoardCanvas(g);
		add(bc, BorderLayout.CENTER);

	}

}
