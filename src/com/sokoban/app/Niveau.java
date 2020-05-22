package com.sokoban.app;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Niveau extends Application {
	private String nivSelec;

	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("Sokoban - " + nivSelec);// Change le titre de la fenetre pour Sokoban - Erreur
		// Charge la ressource parent à partir du fichier Niveau.fxml
		Parent root = FXMLLoader.load(getClass().getResource("/ressources/fxml/Niveau.fxml"));
		// Charge la scene à partir du parent
		Scene scene = new Scene(root);

		// Gestion des events.
		//Si l'utilisateur appuie sur une touche
		scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			//S'il sagit de la touche fleche du haut
			if (key.getCode() == KeyCode.UP) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Event");
				alert.setHeaderText("Resultat");
				alert.setContentText("Vous avez appuyez sur la flêche du haut");
				alert.showAndWait();
			//S'il sagit de la touche fleche du bas
			} else if (key.getCode() == KeyCode.DOWN) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Event");
				alert.setHeaderText("Resultat");
				alert.setContentText("Vous avez appuyez sur la flêche du bas");
				alert.showAndWait();
			//S'il sagit de la touche fleche de gauche
			} else if (key.getCode() == KeyCode.LEFT) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Event");
				alert.setHeaderText("Resultat");
				alert.setContentText("Vous avez appuyez sur la flêche de gauche");
				alert.showAndWait();
			//S'il sagit de la touche fleche de droite
			} else if (key.getCode() == KeyCode.RIGHT) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Event");
				alert.setHeaderText("Resultat");
				alert.setContentText("Vous avez appuyez sur la flêche de droite");
				alert.showAndWait();
			//S'il sagit de la touche espace
			} else if (key.getCode() == KeyCode.SPACE) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Event");
				alert.setHeaderText("Resultat");
				alert.setContentText("Vous avez appuyez sur la barre espace");
				alert.showAndWait();
			}
			;
		});

		// Affiche la scene dans la nouvelle fenetre
		primaryStage.setScene(scene);
		// On empeche de redimmenssionner la fenetre
		primaryStage.setResizable(false);
		// Affiche la fenetre au centre de l'ecran
		primaryStage.show();
		primaryStage.centerOnScreen();

	}

	/**
	 * @param nivSelec
	 */
	public Niveau(String nivSelec) {
		super();
		this.nivSelec = nivSelec;
	}

}
