package com.sokoban.app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController implements Initializable {
	
	@FXML
	protected void openRegle(ActionEvent e) throws IOException {
		Stage regleStage = new Stage();
		
		Parent root = FXMLLoader.load(getClass().getResource("/ressources/fxml/Regles.fxml"));
		
		Scene scene = new Scene(root);
		regleStage.setScene(scene);
		regleStage.show();
		regleStage.centerOnScreen();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
