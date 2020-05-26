package com.sokoban.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sokoban.Configuration;
import com.sokoban.Direction;
import com.sokoban.Position;
import com.sokoban.Type;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Niveau extends Application {
	private int nivSelec;
	private Configuration config;
	private Label nbDeplacement;
	private Label nbBalle;
	private GridPane affGrille;
	private Label[][] tmpGrille;
	private Label[][] scoreLabels;
	private GridPane score;

	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("Sokoban - Niveau " + nivSelec);// Change le titre de la fenetre pour Sokoban - Niveau + numero du niveau
		//Creation des conteneurs
		HBox root = new HBox();
		VBox root2 = new VBox();
		VBox scoreBox = new VBox();
		//Creation du label HighScore et mise en place de son id css
		Label hsLabel = new Label("HighScore");
		hsLabel.setId("hsLabel");
		//Ajouts des entetes et de leur class css
		scoreLabels[0][0] = new Label("Pseudo");
		scoreLabels[0][0].getStyleClass().add("entete");
		scoreLabels[0][1] = new Label("Score");
		scoreLabels[0][1].getStyleClass().add("entete");
		//Creations des labels
		for (int i = 1; i < 6; i++) {
			for (int j = 0; j < 2; j++) {
				scoreLabels[i][j] = new Label();
			}
		}
		updatescore();
		
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
		scoreBox.getChildren().addAll(hsLabel,score);
		scoreBox.setId("scoreBox");
		root2.getChildren().addAll(titleLabel,nivLabel,nbDeplacement,nbBalle,separator,affGrille,separator2,reset);
		root2.setId("niveau");
		root.getChildren().addAll(root2,scoreBox);
		
		// Charge la scene a partir du parent
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/ressources/css/Niveau.css").toString());

		// Gestion des events.
		//Si l'utilisateur appuie sur une touche
		scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			//S'il sagit de la touche Z, deplacement vers le haut et actualisation de l'affichage
			if (key.getCode() == KeyCode.Z) {
				config.bougerJoueurVers(Direction.HAUT);
				updateGrille();
				//S'il sagit de la touche S, deplacement vers le bas et actualisation de l'affichage
			} else if (key.getCode() == KeyCode.S) {
				config.bougerJoueurVers(Direction.BAS);
				updateGrille();
				//S'il sagit de la touche Q, deplacement vers la gauche et actualisation de l'affichage
			} else if (key.getCode() == KeyCode.Q) {
				config.bougerJoueurVers(Direction.GAUCHE);
				updateGrille();
				//S'il sagit de la touche D, deplacement vers la droite et actualisation de l'affichage
			} else if (key.getCode() == KeyCode.D) {
				config.bougerJoueurVers(Direction.DROITE);
				updateGrille();
			//S'il sagit de la touche fleche du haut, tire vers le haut et actualisation de l'affichage
			} else if (key.getCode() == KeyCode.UP) {
				config.getJoueur().tirer(Direction.HAUT);
				updateGrille();
			//S'il sagit de la touche fleche du bas, tire vers le bas et actualisation de l'affichage
			} else if (key.getCode() == KeyCode.DOWN) {
				config.getJoueur().tirer(Direction.BAS);
				updateGrille();
			//S'il sagit de la touche fleche de gauche, tire vers la gauche et actualisation de l'affichage
			} else if (key.getCode() == KeyCode.LEFT) {
				config.getJoueur().tirer(Direction.GAUCHE);
				updateGrille();
			//S'il sagit de la touche fleche de droite, tire vers la droite et actualisation de l'affichage
			} else if (key.getCode() == KeyCode.RIGHT) {
				config.getJoueur().tirer(Direction.DROITE);
				updateGrille();
				//S'il sagit de la touche R, reset la configuration du niveau et actualise l'affichage
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

	private void updatescore() {
		try {
			//Connection à la BDD des scores
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://e90653-mysql.services.easyname.eu/u143161db7","u143161db7","ProjetGL2");
			//Creation de la variable de requete
			Statement stmt=con.createStatement();
			//Création de la variable de resultat et recuperation du resultat de la requete
			ResultSet rs=stmt.executeQuery("SELECT pseudo,nbDeplacement FROM `HighScore` WHERE niveau = "+this.nivSelec+" ORDER BY nbDeplacement ASC LIMIT 5"); 
			//Creation de la variable de deplacement
			int i = 1;
			//Tant qu'il y a un résultat dans rs
			while (rs.next()) {
				//on modifie les labels de la ligne
				scoreLabels[i][0].setText(rs.getString(1));
				scoreLabels[i][1].setText(rs.getString(2));
				//On incremente le numero de la ligne
				i++;
			}
			//On ferme la connection à la BDD
			con.close();
			for (i = 0; i<6;i++) {
				for(int j = 0; j<2;j++) {
					score.add(scoreLabels[i][j],j,i);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	

	/**
	 * @param nivSelec
	 */
	public Niveau(int nivSelec) {
		super();
		//Set le numero du niveau
		this.nivSelec = nivSelec;
		//Initialise les labels
		this.nbDeplacement = new Label();
		this.nbBalle = new Label();
		//Initialise la configuration du niveau
		try {
			this.config = new Configuration(nivSelec);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Variables de deplacement
		int i;
		int j;
		//Variable de limitation de boucle
		int x = this.config.getNiveau().getX();
		int y = this.config.getNiveau().getY();
		//Initialise le tableau de label
		this.tmpGrille = new Label[x][y];
		//Initialise tous les labels du tableau
		for (i=0;i<x;i++) {
			for (j=0;j<y;j++) {
				tmpGrille[i][j] = new Label();
			}
		}
		//Initialise la grille d'affichage
		this.affGrille = new GridPane();
		//Creations de la grille d'affichage pour les scores
		this.score = new GridPane();
		this.score.setId("score");
		this.scoreLabels = new Label[6][2];
	}
	
	private void updateLabels() {
		//Met a jour le nombre de deplacement
		this.nbDeplacement.setText("Vous avez effectue "+this.config.getJoueur().getHisto().size()+" deplacements.");
		//Met la jour le nombre de balles restantes
		if (this.config.getJoueur().getBalles() > 0) {
			this.nbBalle.setText("Vous avez "+this.config.getJoueur().getBalles()+" balles dans votre revolver.");
		} else {
			this.nbBalle.setText("Vous n'avez plus de balles dans votre revolver.");
		}
	}
	
	private void updateGrille() {
		//Variables de deplacement
		int i;
		int j;
		//Variable de limitation de boucle
		int x = this.config.getNiveau().getX();
		int y = this.config.getNiveau().getY();
		//Variable mort du joueur
		boolean mort = false;
		//Variable position du policier
		Position posP;
		//Variable direction du policier
		Direction dirP;
		//Pour chaque label de tmpGrille
		for (i=0;i<x;i++) {
			for (j=0;j<y;j++) {
				//S'il sagit d'un mur, change sa class en mur
				if (this.config.get(new Position(i,j)).getType().equals(Type.MUR)) {
					tmpGrille[i][j].getStyleClass().clear();
					tmpGrille[i][j].getStyleClass().add("mur");
				//S'il sagit d'un joueur, change sa class en joueur
				} else if (this.config.get(new Position(i,j)).getType().equals(Type.JOUEUR)) {
					tmpGrille[i][j].getStyleClass().clear();
					tmpGrille[i][j].getStyleClass().add("joueur");
				//S'il sagit d'un diamant, change sa class en diamant
				} else if (this.config.get(new Position(i,j)).getType().equals(Type.DIAMANT)) {
					tmpGrille[i][j].getStyleClass().clear();
					tmpGrille[i][j].getStyleClass().add("diamant");
				//S'il sagit d'une case, change sa class en case
				} else if (this.config.get(new Position(i,j)).getType().equals(Type.CASE)) {
					tmpGrille[i][j].getStyleClass().clear();
					tmpGrille[i][j].getStyleClass().add("case");
				//S'il sagit d'un policier, change sa class en policier
				} else if (this.config.get(new Position(i,j)).getType().equals(Type.POLICIER)) {
					tmpGrille[i][j].getStyleClass().clear();
					tmpGrille[i][j].getStyleClass().add("policier");
				}
			}
		}
		//Variables des cibles
		ArrayList<Position> cibles = this.config.getNiveau().getCibles();
		//Compteur de cible remplies
		int cp = 0;
		//Pour chaque cible
		for (Position cible : cibles) {
			//Si il n'y a pas de diamant, ni de joueur dessus, change sa class en entrepot
			if (!this.config.get(cible).getType().equals(Type.DIAMANT) && !this.config.get(cible).getType().equals(Type.JOUEUR)) {
				tmpGrille[cible.getX()][cible.getY()].getStyleClass().clear();
				tmpGrille[cible.getX()][cible.getY()].getStyleClass().add("entrepot");
			//S'il y a un diamant dessus, change sa class en diamantEntrepot et increment le compteur
			} else if (this.config.get(cible).getType().equals(Type.DIAMANT)) {
				tmpGrille[cible.getX()][cible.getY()].getStyleClass().clear();
				tmpGrille[cible.getX()][cible.getY()].getStyleClass().add("diamantEntrepot");
				cp++;
			}
		}
		//Reset la grille d'affichage
		this.affGrille.getChildren().clear();
		//Pour chaque case, ajoute le label correspondant
		for (i=0;i<x;i++) {
			for (j=0;j<y;j++) {
				this.affGrille.add(tmpGrille[i][j], j, i);
			}
		}
		//Pour chaque policier
		for(i=0;i<this.config.getPoliciers().size();i++) {
			//On recupere la position et la vision du policier
			posP = this.config.getPoliciers().get(i).getPosition();
			dirP = this.config.getPoliciers().get(i).getRegard();
			//Pour les deux cases devant le policier
			for (j=0;j<2;j++) {
				//on actualise la position verifie
				posP = posP.add(dirP);
				//Si on est pas déjà mort
				if (!mort) {
					//Le booleen mort prend la valeur de l'egalite (positionJoueur == positionVeirifier)
					mort = this.config.getJoueur().getPosition().equals(posP);
				}
			}
		}
		//Update les labels d'informations
		updateLabels();
		//Si le compteur est egal au nombre de diamant du niveau
		if (cp == this.config.getDiamants().size()) {
			//Cree et affiche une alerteBox indiquant la victoire
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Sokoban - Niveau "+this.nivSelec);
			alert.setHeaderText("Victoire");
			alert.setContentText("Bravo, vous avez gagne le niveau "+this.nivSelec+" en "+this.config.getJoueur().getHisto().size()+" coups !");
			alert.show();
			//Sauvegarde le score
			saveScore();
		//Sinon, si on est mort
		} else if (mort) {
			//Cree et affiche une alerteBox indiquant la defaite
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Sokoban - Niveau "+this.nivSelec);
			alert.setHeaderText("Defaite");
			alert.setContentText("Dommage, vous avez perdu le niveau "+this.nivSelec+". Vous avez etes reperer par un policier.");
			alert.show();
			//Lors de la fermeture de l'alerteBox
			alert.setOnCloseRequest(e -> {
				//Reset le niveau
				try {
					this.config = new Configuration(this.nivSelec);
				} catch (IOException error) {
					error.printStackTrace();
				}
				updateGrille();
			});
			
		}
	}

	private void saveScore() {
		//Creation du stage de sauvegarde de score 
		Stage save = new Stage();
		//Mise a jour du titre du stage
		save.setTitle("Sokoban - Niveau "+this.nivSelec);
		//Creation du conteneur
		VBox saveVBox = new VBox();
		//Creation du label et du champ de texte pour le pseudo
		Label pseudoLabel = new Label("Pseudo : ");
		TextField pseudoTF = new TextField();
		//Creation de la HBox
		HBox pseudoHBox = new HBox();
		pseudoHBox.getChildren().addAll(pseudoLabel, pseudoTF);
		//Creation des boutons
		Button saveBt = new Button("Sauvegarder le score");
		Button notSaveBt = new Button("Ne pas sauvegarder");
		//Ajouts des fonctions des boutons
		notSaveBt.setOnAction(e -> {
			save.hide();
			//Reset le niveau
			try {
				this.config = new Configuration(this.nivSelec);
			} catch (IOException error) {
				error.printStackTrace();
			}
			//Actualise la grille
			updateGrille();
			//Actualise le score
			updatescore();
		});
		saveBt.setOnAction(e -> {
			try {
				//Connection à la BDD des scores
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://e90653-mysql.services.easyname.eu/u143161db7","u143161db7","ProjetGL2");
				//Creation de la variable de requete
				Statement stmt=con.createStatement();
				//Création de la variable de resultat et recuperation du resultat de la requete
				int status =stmt.executeUpdate("INSERT INTO HighScore VALUES('"+pseudoTF.getText()+"',"+this.nivSelec+","+this.config.getJoueur().getHisto().size()+")"); 
				//Creation du label de fin de sauvegarde
				Label saveLabel = new Label();
				if (status == 1) {
					saveLabel.setText("Votre score a bien été sauvegarde!");
				} else {
					saveLabel.setText("Desole, il semblerait que l'application n'arrive pas a sauvegarder votre score. Merci de verifier votre connexion internet.");
				}
				notSaveBt.setText("Ok");
				//Actualisation des elements 
				saveVBox.getChildren().clear();
				saveVBox.getChildren().addAll(saveLabel,notSaveBt);
				//On ferme la connection à la BDD
				con.close();
		} catch (Exception error) {
			System.out.println(error);
		}
		});
		//Ajouts des elements dans le conteneur
		saveVBox.getChildren().addAll(pseudoHBox,saveBt,notSaveBt);
		//Creation de la scene
		Scene saveScene = new Scene(saveVBox);
		//Set la scene
		save.setScene(saveScene);
		// On empeche de redimmenssionner la fenetre
		save.setResizable(false);
		// Affiche la fenetre au centre de l'ecran
		save.show();
		save.centerOnScreen();
	}

}
