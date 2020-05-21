package com.sokoban.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Sokoban");
		
		Parent root = FXMLLoader.load(getClass().getResource("/ressources/fxml/Main.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.centerOnScreen();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}