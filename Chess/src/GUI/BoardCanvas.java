package GUI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import framework.*;

public class BoardCanvas extends Canvas {
	Board b;
	HashMap<PieceName, Image> whitePicRefrence = new HashMap<>();
	HashMap<PieceName, Image> blackPicRefrence = new HashMap<>();
	HashMap<Integer, HashMap<PieceName, Image>> picRefrence = new HashMap<>();

	public BoardCanvas() {
		// TODO Auto-generated constructor stub
		try {
			getImage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setBoard(Board b) {
		this.b = b;
	}

	public void getImage() throws IOException {
		String map = "KQBNRP";
		for (int i = 0; i < map.length(); i++) {
			Image img = ImageIO.read(new File("Resources\\Pictures\\Pieces\\White " + map.charAt(i) + ".png"));
			whitePicRefrence.put(PieceName.values()[i], img);

			Image img2 = ImageIO.read(new File("Resources\\Pictures\\Pieces\\Black " + map.charAt(i) + ".png"));
			blackPicRefrence.put(PieceName.values()[i], img2);
		}
		picRefrence.put(1, whitePicRefrence);
		picRefrence.put(-1, blackPicRefrence);
	}

	@Override
	public void paint(Graphics g) {
		// 20 Squares X 8 Squares
		int squareSize = Math.min(this.getHeight() / 15, this.getWidth() / 9);

		int cx = this.getWidth() / 2;
		int cy = this.getHeight() / 2;

		for (int col = -4; col < 4; col++) {
			for (int row = -4; row < 4; row++) {
				int tx = cx + col * squareSize;
				int ty = cy + row * squareSize;

				if ((col + row) % 2 == 0)
					g.setColor(Color.WHITE);
				else
					g.setColor(Color.BLACK);

				g.fillRect(tx, ty, squareSize, squareSize);
			}
		}

		g.setColor(Color.BLACK);
		int tx = cx - 4 * squareSize;
		int ty = (int) (cy - squareSize * 6.5);
		g.drawRect(tx, ty, squareSize * 8, squareSize * 2);
		tx = cx - 4 * squareSize;
		ty = (int) (cy + squareSize * 4.5);
		g.drawRect(tx, ty, squareSize * 8, squareSize * 2);
		g.drawImage(picRefrence.get(1).get(PieceName.PAWN), cx, cy, this);
		g.drawImage(picRefrence.get(1).get(PieceName.BISHOP), cx-100, cy-100, this);
	}
}
