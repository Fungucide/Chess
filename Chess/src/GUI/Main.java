package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;

import framework.*;
import framework.boardImporter.boardImporter;

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
	 * @throws Exception 
	 */
	public Main() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		Board board = boardImporter.importer("Resources\\BoardLayouts\\BishopTest.dat");
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BoardPanel b = new BoardPanel(board);
		frame.add(b);
	}

}
