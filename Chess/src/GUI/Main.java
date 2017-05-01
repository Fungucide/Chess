package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;

import framework.Board;
import framework.Game;
import framework.boardImporter.gameImporter;

public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws Exception
	 */
	public Main() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws Exception
	 */
	private void initialize() throws Exception {
		Board b = gameImporter.importer("Resources\\BoardLayouts\\Classic.dat");
		Game g = new Game(b);
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BoardPanel bp = new BoardPanel(g);
		frame.add(bp);
	}

}
