package com.sokoban.app;

import java.io.IOException;
import java.util.ArrayList;

import com.sokoban.Configuration;
import com.sokoban.Direction;
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
	private Label nbBalle;
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
		updateLabels();
		//Creation des separateurs vertical
		Separator separator = new Separator();
		Separator separator2 = new Separator();
		//Actualisation de la grille
		updateGrille();
		//Creation du label de reset
		Label reset = new Label("Vous êtes bloque ? Appuyez sur 'R' pour reset le niveau.");
		//Ajout des elements
		root.getChildren().addAll(titleLabel,nivLabel,nbDeplacement,nbBalle,separator,affGrille,separator2,reset);
		
		// Charge la scene a partir du parent
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/ressources/css/Niveau.css").toString());

		// Gestion des events.
		//Si l'utilisateur appuie sur une touche
		scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			//S'il sagit de la touche fleche du haut
			if (key.getCode() == KeyCode.Z) {
				config.bougerJoueurVers(Direction.GAUCHE);
				updateGrille();
			//S'il sagit de la touche fleche du bas
			} else if (key.getCode() == KeyCode.S) {
				config.bougerJoueurVers(Direction.DROITE);
				updateGrille();
			//S'il sagit de la touche fleche de gauche
			} else if (key.getCode() == KeyCode.Q) {
				config.bougerJoueurVers(Direction.HAUT);
				updateGrille();
			//S'il sagit de la touche fleche de droite
			} else if (key.getCode() == KeyCode.D) {
				config.bougerJoueurVers(Direction.BAS);
				updateGrille();
			//S'il sagit de la touche espace
			} else if (key.getCode() == KeyCode.UP) {
				config.getJoueur().tirer(Direction.GAUCHE);
				updateGrille();
			//S'il sagit de la touche fleche du bas
			} else if (key.getCode() == KeyCode.DOWN) {
				config.getJoueur().tirer(Direction.DROITE);
				updateGrille();
			//S'il sagit de la touche fleche de gauche
			} else if (key.getCode() == KeyCode.LEFT) {
				config.getJoueur().tirer(Direction.HAUT);
				updateGrille();
			//S'il sagit de la touche fleche de droite
			} else if (key.getCode() == KeyCode.RIGHT) {
				config.getJoueur().tirer(Direction.BAS);
				updateGrille();
			} else if (key.getCode() == KeyCode.R) {
				try {
					this.config = new Configuration(this.nivSelec);
				} catch (IOException e) {
					e.printStackTrace();
				}
				updateGrille();
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
		this.nbBalle = new Label();
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
	
	private void updateLabels() {
		this.nbDeplacement.setText("Vous avez effectue "+this.config.getJoueur().getHisto().size()+" deplacements.");
		if (this.config.getJoueur().getBalles() > 0) {
			this.nbBalle.setText("Vous avez "+this.config.getJoueur().getBalles()+" balles dans votre revolver.");
		} else {
			this.nbBalle.setText("Vous n'avez plus de balles dans votre revolver.");
		}
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
				} else if (this.config.get(new Position(i,j)).getType().equals(Type.JOUEUR)) {
					tmpGrille[i][j].getStyleClass().clear();
					tmpGrille[i][j].getStyleClass().add("joueur");
				} else if (this.config.get(new Position(i,j)).getType().equals(Type.DIAMANT)) {
					tmpGrille[i][j].getStyleClass().clear();
					tmpGrille[i][j].getStyleClass().add("diamant");
				} else if (this.config.get(new Position(i,j)).getType().equals(Type.CASE)) {
					tmpGrille[i][j].getStyleClass().clear();
					tmpGrille[i][j].getStyleClass().add("case");	
				} else if (this.config.get(new Position(i,j)).getType().equals(Type.POLICIER)) {
					tmpGrille[i][j].getStyleClass().clear();
					tmpGrille[i][j].getStyleClass().add("policier");
				}
			}
		}
		ArrayList<Position> cibles = this.config.getNiveau().getCibles();
		int cp = 0;
		for (Position cible : cibles) {
			if (!this.config.get(cible).getType().equals(Type.DIAMANT) && !this.config.get(cible).getType().equals(Type.JOUEUR)) {
				tmpGrille[cible.getX()][cible.getY()].getStyleClass().clear();
				tmpGrille[cible.getX()][cible.getY()].getStyleClass().add("entrepot");
			} else if (this.config.get(cible).getType().equals(Type.DIAMANT)) {
				tmpGrille[cible.getX()][cible.getY()].getStyleClass().clear();
				tmpGrille[cible.getX()][cible.getY()].getStyleClass().add("diamantEntrepot");
				cp++;
			}
		}
		this.affGrille.getChildren().clear();
		for (i=0;i<x;i++) {
			for (j=0;j<y;j++) {
				this.affGrille.add(tmpGrille[i][j], j, i);
			}
		}
		updateLabels();
		if (cp == this.config.getDiamants().size()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Sokoban - Niveau "+this.nivSelec);
			alert.setHeaderText("Victoire");
			alert.setContentText("Bravo, vous avez gagne le niveau "+this.nivSelec+" en "+this.config.getJoueur().getHisto().size()+" coups !");
			alert.show();
		}
	}
	
//	private void verifVictoire() {
//		if (this.config.victoire()) {
//			Alert alert = new Alert(AlertType.INFORMATION);
//			alert.setTitle("Sokoban - Niveau "+this.nivSelec);
//			alert.setHeaderText("Victoire");
//			alert.setContentText("Bravo, vous avez gagne le niveau "+this.nivSelec+" en "+this.config.getJoueur().getHisto().size()+" coups !");
//			alert.show();
//
//			new Thread(() -> {
//				try {
//					Thread.sleep(6000);
//					Platform.runLater(() -> {
//						alert.hide();
//					});
//				} catch (InterruptedException error) {
//					error.printStackTrace();
//				}
//			}).start();
//		}
//	}
}
