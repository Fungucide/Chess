package GUI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import framework.*;
import framework.Pieces.Piece;

public class BoardCanvas extends Canvas {
	Board b;
	HashMap<PieceName, Image> whitePicRefrence = new HashMap<>();
	HashMap<PieceName, Image> blackPicRefrence = new HashMap<>();
	HashMap<Integer, HashMap<PieceName, Image>> picRefrence = new HashMap<>();
	int[][] colored = new int[8][8];
	ArrayList<Move> show;
	int squareSize;
	int cx;
	int cy;
	int px = -1;
	int py = -1;

	public BoardCanvas(Board b) {
		// TODO Auto-generated constructor stub
		this.b = b;
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent ce) {
				try {
					getImage();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				int x = me.getX();
				int y = me.getY();
				if (x < cx + squareSize * 4 && x > cx - squareSize * 4 && y < cy + squareSize * 4
						&& y > cy - squareSize * 4) {
					int xPos;
					int yPos;
					if (x > cx) {
						xPos = (int) Math.floor((double) (x - cx) / (double) squareSize) + 4;
					} else {
						xPos = (int) -Math.ceil((double) (cx - x) / (double) squareSize) + 4;
					}
					if (y > cy) {
						yPos = (int) -Math.ceil((double) (y - cy) / (double) squareSize) + 4;
					} else {
						yPos = (int) Math.floor((double) (cy - y) / (double) squareSize) + 4;
					}
					Piece ref = b.getPiece(xPos, yPos);
					if (ref == null) {
						return;
					}
					if (px == xPos && py == yPos) {
						for (Move m : show) {
							colored[m.endX][m.endY] = 0;
						}
						show.clear();
						repaint();
						return;
					} else if (px != -1 && py != -1) {
						for (Move m : show) {
							colored[m.endX][m.endY] = 0;
						}
						show.clear();
					}
					try {
						show = ref.allMoves();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return;
					}
					System.out.println(show.size());
					for (Move m : show) {
						m.print();
						if (b.getPiece(m.endX, m.endY) == null) {
							colored[m.endX][m.endY] = 1;
						} else {
							colored[m.endX][m.endY] = 2;
						}
					}
					px = xPos;
					py = yPos;
					repaint();
				}
			}
		});
	}

	public void getImage() throws IOException {
		String map = "KQBNRP";
		squareSize = Math.min(this.getHeight() / 15, this.getWidth() / 9);
		for (int i = 0; i < map.length(); i++) {
			Image img = ImageIO.read(new File("Resources\\Pictures\\Pieces\\White " + map.charAt(i) + ".png"))
					.getScaledInstance(squareSize, squareSize, Image.SCALE_SMOOTH);
			whitePicRefrence.put(PieceName.values()[i], img);

			Image img2 = ImageIO.read(new File("Resources\\Pictures\\Pieces\\Black " + map.charAt(i) + ".png"))
					.getScaledInstance(squareSize, squareSize, Image.SCALE_SMOOTH);
			blackPicRefrence.put(PieceName.values()[i], img2);
		}
		picRefrence.put(1, whitePicRefrence);
		picRefrence.put(-1, blackPicRefrence);
	}

	@Override
	public void paint(Graphics g) {
		// 20 Squares X 8 Squares
		cx = this.getWidth() / 2;
		cy = this.getHeight() / 2;
		int bx = cx - squareSize * 4;
		int by = cy + squareSize * 4;
		g.drawRect(cx - squareSize * 4 - 1, cy - squareSize * 4 - 1, squareSize * 8 + 1, squareSize * 8 + 1);
		for (int col = 0; col < 8; col++) {
			for (int row = 0; row < 8; row++) {
				int tx = bx + col * squareSize;
				int ty = by - row * squareSize-squareSize;

				if ((col + row) % 2 != 0) {
					g.setColor(Color.WHITE);
				} else {
					g.setColor(Color.GRAY);

				}
				g.fillRect(tx, ty, squareSize, squareSize);
				if (colored[col][row] == 1) {
					g.setColor(new Color(Color.YELLOW.getRed(), Color.YELLOW.getGreen(), Color.YELLOW.getBlue(), 127));
				} else if (colored[col][row] == 2) {
					g.setColor(new Color(Color.BLUE.getRed(), Color.BLUE.getGreen(), Color.BLUE.getBlue(), 100));
				}
				g.fillRect(tx + 1, ty + 1, squareSize - 1, squareSize - 1);
			}
		}

		g.setColor(Color.BLACK);
		int tx = cx - 4 * squareSize;
		int ty = (int) (cy - squareSize * 6.5);
		g.drawRect(tx, ty, squareSize * 8, squareSize * 2);
		tx = cx - 4 * squareSize;
		ty = (int) (cy + squareSize * 4.5);

		g.drawRect(tx, ty, squareSize * 8, squareSize * 2);

		for (int i = 0; i < 8; i++) {
			for (int h = 0; h < 8; h++) {
				b.getPiece(i, h);
				if (b.getPiece(i, h) != null) {
					g.drawImage(picRefrence.get(b.getPiece(i, h).COLOR).get(b.getPiece(i, h).TYPE),
							bx + squareSize * i, by - squareSize * (h+1), this);
				}
			}
		}
	}
}
