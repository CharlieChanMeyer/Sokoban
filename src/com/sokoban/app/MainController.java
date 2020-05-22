package com.sokoban.app;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class MainController implements Initializable {
	@SuppressWarnings("rawtypes")
	ObservableList list=FXCollections.observableArrayList();
	@FXML
	private ChoiceBox<String> selecNiv;	//Id de la boc de choix
	
	@FXML
	protected void openRegle(ActionEvent e) throws IOException {
		Stage regleStage = new Stage();	//Cree une nouvelle fenetre
		regleStage.setTitle("Sokoban - Les r�gles");	//Change le titre de la fenetre pour Sokoban - Les r�gles
		//Charge la ressource parent � partir du fichier Regles.fxml
		Parent root = FXMLLoader.load(getClass().getResource("/ressources/fxml/Regles.fxml"));
		//Charge la scene � partir du parent
		Scene scene = new Scene(root);
		//Affiche la scene dans la nouvelle fenetre
		regleStage.setScene(scene);
		//Affiche la fenetre
		regleStage.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadData();//Charge le menu d�roulant afin de choisir le niveau
	}
	
	@SuppressWarnings("unchecked")
	private void loadData() {
		list.removeAll(list);	//Vide la liste
		//Charge le r�pertoire
		File monRepertoire=new File("./src/ressources/niveaux");
		//Charge le contenu du r�pertoire
		File[] f = monRepertoire.listFiles();
		//Pour chaque item contenu dans le r�pertoire
		for (int i = 0 ; i < f.length ; i++) {
			//s'il sagit d'un fichier
		  if (f[i].isFile()) {
			  //L'ajoute � la liste
		    list.add(new String("Niveau "+(i+1)));
		  }
		}
		//Ajoute la liste au menu deroulant
		selecNiv.getItems().addAll(list);
	}

	@FXML
	protected void openNiv(ActionEvent e) throws Exception {
		//Recupere le niveau selectionner
		String nivSelec = selecNiv.getValue();
		//Si un niveau est selectionner
		if (nivSelec!=null) {
			Stage nivStage = new Stage();
			Niveau niv = new Niveau(nivSelec);
			niv.start(nivStage);
		} else { //Sinon
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("ATTENTION");
			alert.setHeaderText("Probl�me de selection");
			alert.setContentText("Vous n'avez s�lectionn� aucun niveau !");
			alert.show();

			new Thread(() -> {
				try {
					Thread.sleep(2000);
					Platform.runLater(() -> alert.hide());
				} catch (InterruptedException error) {
					error.printStackTrace();
				}
			}).start();
			
		}
		
		
	}
}
