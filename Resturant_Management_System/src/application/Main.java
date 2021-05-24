package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

	static Stage stage = new Stage();

	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			FlowPane root = (FlowPane) FXMLLoader.load(getClass().getResource("Login_Panel.fxml"));
			Scene scene = new Scene(root);
			scene.getWindow();
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		launch(args);
	}

}
