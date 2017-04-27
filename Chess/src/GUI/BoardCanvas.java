package GUI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import framework.*;

public class BoardCanvas extends Canvas {
	Board b;
	HashMap<PieceName, Image> whitePicRefrence = new HashMap<>();
	HashMap<PieceName, Image> blackPicRefrence = new HashMap<>();
	HashMap<PieceName, Image> picRefrence = new HashMap<>();

	public void setBoard(Board b) {
		this.b = b;
	}

	public void getImage() throws IOException {
		System.out.println(new File("").getAbsolutePath().toString());
		Image source = ImageIO.read(new File("ChessPiece.png"));
		Image img;
		System.out.println(source.getHeight(null));
		for (int i = 0; i < 8; i++) {
			for (int h = 0; h < 2; h++) {
				//img = createImage(new FilteredImageSource(source.getSource(), new CropImageFilter(, arg1, arg2, arg3)))
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		// 20 Squares X 8 Squares
		int squareSize = Math.min(this.getHeight() / 15, this.getWidth() / 9);
		// Bottom Side Square
		g.drawRect((this.getWidth() - squareSize * 8) / 2, this.getHeight() - squareSize * 2 - squareSize / 2,
				squareSize * 8, squareSize * 2);
		// Board Outline
		g.drawRect((this.getWidth() - squareSize * 8) / 2 - 1, this.getHeight() - squareSize * 11 - squareSize / 2 - 1,
				squareSize * 8 + 1, squareSize * 8 + 1);
		// Actual Board
		for (int i = 0; i < 8; i++) {
			for (int h = 0; h < 8; h++) {
				if (((i + h) & 1) == 0) {
					g.setColor(Color.WHITE);
				} else {
					g.setColor(Color.BLACK);
				}
				g.fillRect((this.getWidth() - squareSize * 8) / 2 + squareSize * h,
						this.getHeight() - squareSize * 11 - squareSize / 2 + squareSize * i, squareSize, squareSize);
			}
		}

		// Top Side Square
		g.setColor(Color.BLACK);
		g.drawRect((this.getWidth() - squareSize * 8) / 2, this.getHeight() - squareSize * 14 - squareSize / 2,
				squareSize * 8, squareSize * 2);
	}
}
