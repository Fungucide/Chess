package GUIJavaFX;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class GUIMain extends Application {

	@Override
	public void start(Stage mainStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("BoardWindow.fxml"));
		GridPane gp = new GridPane();
		int numRows = 8;
		int numColumns = 8;
		for (int row = 0; row < numRows; row++) {
			RowConstraints rc = new RowConstraints();
			rc.setFillHeight(true);
			rc.setVgrow(Priority.ALWAYS);
			gp.getRowConstraints().add(rc);
		}
		for (int col = 0; col < numColumns; col++) {
			ColumnConstraints cc = new ColumnConstraints();
			cc.setFillWidth(true);
			cc.setHgrow(Priority.ALWAYS);
			gp.getColumnConstraints().add(cc);
		}

		for (int i = 0; i < numRows; i++) {
			for (int h = 0; h < numColumns; h++) {
				Button b = new Button("");
				b.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				if (((i + h) & 1) == 1) {
					b.setStyle("-fx-background-color: black; -fx-border-radius: 0px;-fx-border-color: grey;}");
				} else {
					b.setStyle("-fx-border-radius: 0px;-fx-border-color: grey;}");
				}
				gp.add(b, i, h);
			}
		}
		Scene stuart = new Scene(gp);
		mainStage.setTitle("Royi is always faster");
		mainStage.setScene(stuart);
		mainStage.show();
		System.out.println(mainStage.getHeight());
		System.out.println(mainStage.getWidth());
		double min = Math.min(mainStage.getHeight(), mainStage.getWidth());
		System.out.println(min);
		mainStage.setHeight(min);
		mainStage.setWidth(min);
		gp.autosize();
	}

	public static void main(String[] args) {
		launch();
	}
}
