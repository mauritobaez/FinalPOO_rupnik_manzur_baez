package frontend;

import backend.CanvasState;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.awt.*;

public class AppLauncher extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	//Esto es un comentario!
	@Override
	public void start(Stage primaryStage) {
		CanvasState canvasState = new CanvasState(); // BackEnd
		MainFrame frame = new MainFrame(canvasState);
		Scene scene = new Scene(frame);
		// Título para que Microsoft no nos demande
		primaryStage.setTitle("Totally Not Paint");
		primaryStage.setResizable(false);
		// Imagen del perro de "This is fine."
		Image icon = new Image("/frontend/icon.png");
		primaryStage.getIcons().add(icon);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setOnCloseRequest(event -> System.exit(0));
	}

}
