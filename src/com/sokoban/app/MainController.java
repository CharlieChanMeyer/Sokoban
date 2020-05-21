package com.sokoban.app;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.sokoban.app.Niveau;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class MainController implements Initializable {
	@SuppressWarnings("rawtypes")
	ObservableList list=FXCollections.observableArrayList();
	@FXML
	private ChoiceBox<String> selecNiv;	//Id de la boc de choix
	
	@FXML
	protected void openRegle(ActionEvent e) throws IOException {
		Stage regleStage = new Stage();	//Cree une nouvelle fenetre
		regleStage.setTitle("Sokoban - Les règles");	//Change le titre de la fenetre pour Sokoban - Les règles
		//Charge la ressource parent à partir du fichier Regles.fxml
		Parent root = FXMLLoader.load(getClass().getResource("/ressources/fxml/Regles.fxml"));
		//Charge la scene à partir du parent
		Scene scene = new Scene(root);
		//Affiche la scene dans la nouvelle fenetre
		regleStage.setScene(scene);
		//Affiche la fenetre
		regleStage.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadData();//Charge le menu déroulant afin de choisir le niveau
	}
	
	@SuppressWarnings("unchecked")
	private void loadData() {
		list.removeAll(list);	//Vide la liste
		//Charge le répertoire
		File monRepertoire=new File("./src/ressources/niveaux");
		//Charge le contenu du répertoire
		File[] f = monRepertoire.listFiles();
		//Pour chaque item contenu dans le répertoire
		for (int i = 0 ; i < f.length ; i++) {
			//s'il sagit d'un fichier
		  if (f[i].isFile()) {
			  //L'ajoute à la liste
		    list.add(new String("Niveau "+(i+1)));
		  }
		}
		//Ajoute la liste au menu deroulant
		selecNiv.getItems().addAll(list);
	}

	@FXML
	protected void openNiv(ActionEvent e) throws IOException {
		//Recupere le niveau selectionner
		String nivSelec = selecNiv.getValue();
		//Si un niveau est selectionner
		if (nivSelec!=null) {
			Stage niveauStage = new Stage();
			niveauStage.setTitle("Sokoban - "+nivSelec);//Change le titre de la fenetre pour Sokoban - Erreur
			//Charge la ressource parent à partir du fichier Niveau.fxml
			Parent root = FXMLLoader.load(getClass().getResource("/ressources/fxml/Niveau.fxml"));
			//Charge la scene à partir du parent
			Scene scene = new Scene(root);
			//Affiche la scene dans la nouvelle fenetre
			niveauStage.setScene(scene);
			//On empeche de redimmenssionner la fenetre
			niveauStage.setResizable(false);
			//Affiche la fenetre au centre de l'ecran
			niveauStage.show();
			niveauStage.centerOnScreen();
			
			
		} else { //Sinon
			Stage erreurSelectionStage = new Stage(); //Cree une nouvelle fenetre
			erreurSelectionStage.setTitle("Sokoban - Erreur");//Change le titre de la fenetre pour Sokoban - Erreur
			//Charge la ressource parent à partir du fichier ErreurNiv.fxml
			Parent root = FXMLLoader.load(getClass().getResource("/ressources/fxml/ErreurNiv.fxml"));
			//Charge la scene à partir du parent
			Scene scene = new Scene(root);
			//Affiche la scene dans la nouvelle fenetre
			erreurSelectionStage.setScene(scene);
			//On empeche de redimmenssionner la fenetre
			erreurSelectionStage.setResizable(false);
			//Affiche la fenetre au centre de l'ecran
			erreurSelectionStage.show();
			erreurSelectionStage.centerOnScreen();
			
			new Thread(() -> {
				try {
					Thread.sleep(2000);
					Platform.runLater(() -> erreurSelectionStage.hide());
				} catch (InterruptedException error) {
					error.printStackTrace();
				}
			}).start();
			
		}
		
		
	}
}
