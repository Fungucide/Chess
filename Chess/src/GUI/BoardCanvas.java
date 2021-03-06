package GUI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import framework.Board;
import framework.Game;
import framework.GenericMove;
import framework.Move;
import framework.PieceName;
import framework.Pieces.Piece;

public class BoardCanvas extends Canvas {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private Board b;
	private Game g;

	public BoardCanvas(Game g) {
		// TODO Auto-generated constructor stub
		this.b = g.b;
		this.g = g;
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
					if (ref == null || ref.COLOR != g.turn) {
						if (px != -1 && py != -1) {
							try {
								if (b.getPiece(px, py).move(new GenericMove(px, py, xPos, yPos))) {
									g.turn *= -1;
									colored[px][py] = 0;
									for (Move m : show) {
										colored[m.endX][m.endY] = 0;
									}
									show.clear();
									px = -1;
									py = -1;
									repaint();
								}
								return;
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {
							return;
						}
					}
					if (px == xPos && py == yPos) {
						colored[px][py] = 0;
						for (Move m : show) {
							colored[m.endX][m.endY] = 0;
						}
						show.clear();
						px = -1;
						py = -1;
						repaint();
						return;
					} else if (px != -1 && py != -1) {
						colored[px][py] = 0;
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
					colored[xPos][yPos] = 3;
					for (Move m : show) {
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
		squareSize = Math.min(this.getHeight() / 16, this.getWidth() / 9);
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
				int ty = by - row * squareSize - squareSize;

				if ((col + row) % 2 != 0) {
					g.setColor(Color.WHITE);
				} else {
					g.setColor(Color.GRAY);
				}
				g.fillRect(tx, ty, squareSize, squareSize);
				if (colored[col][row] == 1) {
					if ((col + row) % 2 != 0) {
						g.setColor(new Color(0, 255, 0, 150));
					} else {
						g.setColor(new Color(0, 255, 0, 100));
					}
				} else if (colored[col][row] == 2) {
					if ((col + row) % 2 != 0) {
						g.setColor(new Color(255, 0, 0, 200));
					} else {
						g.setColor(new Color(255, 0, 0, 100));
					}
				} else if (colored[col][row] == 3) {
					if ((col + row) % 2 != 0) {
						g.setColor(new Color(255, 255, 0, 230));
					} else {
						g.setColor(new Color(255, 255, 0, 175));
					}
				}
				g.fillRect(tx, ty, squareSize, squareSize);
			}
		}

		int tx = cx - 4 * squareSize;
		int ty = (int) (cy - squareSize * 7);
		g.setFont(new Font("Times New Roman", Font.PLAIN, (int) (squareSize * .8)));
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(tx, ty, squareSize * 8, squareSize * 2);
		g.setColor(Color.BLACK);
		g.drawRect(tx, ty, squareSize * 8, squareSize * 2);
		if (this.g.turn == -1) {
			g.drawString("Player 2's Turn", tx, ty + (int) Math.round(squareSize * 2.8));
		}
		for (int i = 0; i < b.TakenRefrence.get(1).size(); i++) {
			g.drawImage(picRefrence.get(1).get(b.TakenRefrence.get(1).get(i).TYPE), tx + squareSize * i, ty, this);
		}

		tx = cx - 4 * squareSize;
		ty = (int) (cy + squareSize * 5);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(tx, ty, squareSize * 8, squareSize * 2);
		g.setColor(Color.BLACK);
		g.drawRect(tx, ty, squareSize * 8, squareSize * 2);
		if (this.g.turn == 1) {
			g.drawString("Player 1's Turn", tx, ty - (int) Math.round(squareSize * .2));
		}
		for (int i = 0; i < b.TakenRefrence.get(-1).size(); i++) {
			g.drawImage(picRefrence.get(-1).get(b.TakenRefrence.get(-1).get(i).TYPE), tx + squareSize * i, ty, this);
		}

		for (int i = 0; i < 8; i++) {
			for (int h = 0; h < 8; h++) {
				b.getPiece(i, h);
				if (b.getPiece(i, h) != null) {
					g.drawImage(picRefrence.get(b.getPiece(i, h).COLOR).get(b.getPiece(i, h).TYPE), bx + squareSize * i,
							by - squareSize * (h + 1), this);
				}
			}
		}
		System.out.println(this.g.noMoves + " " + this.g.check);
		if (this.g.noMoves != 0 && this.g.check != 0) {
			System.out.println("Checkmate:" + this.g.noMoves);
		} else if (this.g.noMoves != 0) {
			System.out.println("Draw");
		}
	}
}
