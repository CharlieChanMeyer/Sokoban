package com.sokoban.app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Niveau extends Application{
	private String nivSelec;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Sokoban - "+nivSelec);//Change le titre de la fenetre pour Sokoban - Erreur
		//Charge la ressource parent à partir du fichier Niveau.fxml
		Parent root = FXMLLoader.load(getClass().getResource("/ressources/fxml/Niveau.fxml"));
		//Charge la scene à partir du parent
		Scene scene = new Scene(root);
		//Affiche la scene dans la nouvelle fenetre
		primaryStage.setScene(scene);
		//On empeche de redimmenssionner la fenetre
		primaryStage.setResizable(false);
		//Affiche la fenetre au centre de l'ecran
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

	public static void main(String[] args) {
		//Lance l'application
		launch(args);
	}

}
