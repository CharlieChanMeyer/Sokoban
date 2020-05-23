package com.sokoban.app;

import java.io.IOException;
import java.util.ArrayList;

import com.sokoban.Configuration;
import com.sokoban.Position;
import com.sokoban.Type;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Niveau extends Application {
	private int nivSelec;
	private Configuration config;
	private Label nbDeplacement;
	private GridPane affGrille;
	private Label[][] tmpGrille;

	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("Sokoban - Niveau " + nivSelec);// Change le titre de la fenetre pour Sokoban - Erreur
		//Creation du conteneur
		VBox root = new VBox();
		
		//Creation du label Titre
		Label titleLabel = new Label("Sokoban");
		titleLabel.setId("gameTitle");
		//Creation du label du niveau
		Label nivLabel = new Label("Niveau : "+this.nivSelec);
		//Actualisation du label de deplacement
		updateNbDeplacement();
		//Creation du separateur vertical
		Separator separator = new Separator();
		//Actualisation de la grille
		updateGrille();
		//Ajout des elements
		root.getChildren().addAll(titleLabel,nivLabel,nbDeplacement,separator,affGrille);
		
		// Charge la scene a partir du parent
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/ressources/css/Niveau.css").toString());

		// Gestion des events.
		//Si l'utilisateur appuie sur une touche
		scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			//S'il sagit de la touche fleche du haut
			if (key.getCode() == KeyCode.UP) {
				config.bougerJoueurVers(Direction.GAUCHE);
				updateGrille();
			//S'il sagit de la touche fleche du bas
			} else if (key.getCode() == KeyCode.DOWN) {
				config.bougerJoueurVers(Direction.DROITE);
				updateGrille();
			//S'il sagit de la touche fleche de gauche
			} else if (key.getCode() == KeyCode.LEFT) {
				config.bougerJoueurVers(Direction.HAUT);
				updateGrille();
			//S'il sagit de la touche fleche de droite
			} else if (key.getCode() == KeyCode.RIGHT) {
				config.bougerJoueurVers(Direction.BAS);
				updateGrille();
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
	public Niveau(int nivSelec) {
		super();
		this.nivSelec = nivSelec;
		this.nbDeplacement = new Label();
		try {
			this.config = new Configuration(nivSelec);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int i;
		int j;
		int x = this.config.getNiveau().getX();
		int y = this.config.getNiveau().getY();
		this.tmpGrille = new Label[x][y];
		for (i=0;i<x;i++) {
			for (j=0;j<y;j++) {
				tmpGrille[i][j] = new Label();
			}
		}
		this.affGrille = new GridPane();
	}
	
	private void updateNbDeplacement() {
		this.nbDeplacement.setText("Vous avez effectue "+0+" deplacements."); //this.config.getJoueur().getHisto().size()
	}
	
	private void updateGrille() {
		int i;
		int j;
		int x = this.config.getNiveau().getX();
		int y = this.config.getNiveau().getY();
		for (i=0;i<x;i++) {
			for (j=0;j<y;j++) {
				if (this.config.get(new Position(i,j)).getType().equals(Type.MUR)) {
					tmpGrille[i][j].getStyleClass().clear();
					tmpGrille[i][j].getStyleClass().add("mur");
				} else if (this.config.get(new Position(i,j)).getType().equals(Type.DIAMANT)) {
					tmpGrille[i][j].getStyleClass().clear();
					tmpGrille[i][j].getStyleClass().add("diamant");
				} else if (this.config.get(new Position(i,j)).getType().equals(Type.CASE)) {
					tmpGrille[i][j].getStyleClass().clear();
					tmpGrille[i][j].getStyleClass().add("case");	
				} else if (this.config.get(new Position(i,j)).getType().equals(Type.JOUEUR)) {
					tmpGrille[i][j].getStyleClass().clear();
					tmpGrille[i][j].getStyleClass().add("joueur");
				} else if (this.config.get(new Position(i,j)).getType().equals(Type.POLICIER)) {
					tmpGrille[i][j].getStyleClass().clear();
					tmpGrille[i][j].getStyleClass().add("policier");
				}
			}
		}
		ArrayList<Position> cibles = this.config.getNiveau().getCibles();
		for (Position cible : cibles) {
			if (!this.config.get(cible).getType().equals(Type.DIAMANT)) {
				tmpGrille[cible.getX()][cible.getY()].getStyleClass().clear();
				tmpGrille[cible.getX()][cible.getY()].getStyleClass().add("entrepot");
			}
		}
		this.affGrille.getChildren().clear();
		for (i=0;i<x;i++) {
			for (j=0;j<y;j++) {
				this.affGrille.add(tmpGrille[i][j], j, i);
			}
		}
	}
	
}
