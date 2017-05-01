package framework.game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import framework.Game;
import framework.Pieces.Piece;

public class gameExporter {
	public void export(Game g, String path) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path)));
		for (Piece p : g.b.TeamRefrence.get(1)) {
			bw.write(p.TYPE + " 1 " + p.x + " " + p.y + " " + p.moved);
		}
		for (Piece p : g.b.TeamRefrence.get(-1)) {
			bw.write(p.TYPE + " -1 " + p.x + " " + p.y + " " + p.moved);
		}
		for (Piece p : g.b.TakenRefrence.get(1)) {
			bw.write(p.TYPE + " 1 -1 -1 " + p.moved);
		}
		for (Piece p : g.b.TakenRefrence.get(-1)) {
			bw.write(p.TYPE + " -1 -1 -1 " + p.moved);
		}
		bw.close();
	}
}
