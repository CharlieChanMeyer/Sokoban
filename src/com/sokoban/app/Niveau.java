package com.sokoban.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

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
	private boolean bullet = false;
	private int pseudoTemps = 0;

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
		updateGrille(false);
		//Creation du label de reset
		Label reset = new Label("Vous ï¿½tes bloque ? Appuyez sur 'R' pour reset le niveau.");
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
				config.getJoueur().setRegard(Direction.HAUT);
				updateGrille(false);
				//S'il sagit de la touche S, deplacement vers le bas et actualisation de l'affichage
			} else if (key.getCode() == KeyCode.S) {
				config.bougerJoueurVers(Direction.BAS);
				config.getJoueur().setRegard(Direction.BAS);
				updateGrille(false);
				//S'il sagit de la touche Q, deplacement vers la gauche et actualisation de l'affichage
			} else if (key.getCode() == KeyCode.Q) {
				config.bougerJoueurVers(Direction.GAUCHE);
				config.getJoueur().setRegard(Direction.GAUCHE);
				updateGrille(false);
				//S'il sagit de la touche D, deplacement vers la droite et actualisation de l'affichage
			} else if (key.getCode() == KeyCode.D) {
				config.bougerJoueurVers(Direction.DROITE);
				config.getJoueur().setRegard(Direction.DROITE);
				updateGrille(false);
			//S'il sagit de la touche fleche du haut, tire vers le haut et actualisation de l'affichage
			} else if (key.getCode() == KeyCode.UP) {
				config.getJoueur().tirer(Direction.HAUT);
				config.getJoueur().setRegard(Direction.HAUT);
				updateGrille(false);
			//S'il sagit de la touche fleche du bas, tire vers le bas et actualisation de l'affichage
			} else if (key.getCode() == KeyCode.DOWN) {
				config.getJoueur().tirer(Direction.BAS);
				config.getJoueur().setRegard(Direction.BAS);
				updateGrille(false);
			//S'il sagit de la touche fleche de gauche, tire vers la gauche et actualisation de l'affichage
			} else if (key.getCode() == KeyCode.LEFT) {
				config.getJoueur().tirer(Direction.GAUCHE);
				config.getJoueur().setRegard(Direction.GAUCHE);
				updateGrille(false);
			//S'il sagit de la touche fleche de droite, tire vers la droite et actualisation de l'affichage
			} else if (key.getCode() == KeyCode.RIGHT) {
				config.getJoueur().tirer(Direction.DROITE);
				config.getJoueur().setRegard(Direction.DROITE);
				updateGrille(false);
				//S'il sagit de la touche R, reset la configuration du niveau et actualise l'affichage
			} else if (key.getCode() == KeyCode.R) {
				try {
					this.config = new Configuration(this.nivSelec);
				} catch (IOException e) {
					e.printStackTrace();
				}
				updateGrille(true);
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
			//Connection ï¿½ la BDD des scores
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://e90653-mysql.services.easyname.eu/u143161db7","u143161db7","ProjetGL2");
			//Creation de la variable de requete
			Statement stmt=con.createStatement();
			//Crï¿½ation de la variable de resultat et recuperation du resultat de la requete
			ResultSet rs=stmt.executeQuery("SELECT pseudo,nbDeplacement FROM `HighScore` WHERE niveau = "+this.nivSelec+" ORDER BY nbDeplacement ASC LIMIT 5"); 
			//Creation de la variable de deplacement
			int i = 1;
			//Tant qu'il y a un rï¿½sultat dans rs
			while (rs.next()) {
				//on modifie les labels de la ligne
				scoreLabels[i][0].setText(rs.getString(1));
				scoreLabels[i][1].setText(rs.getString(2));
				//On incremente le numero de la ligne
				i++;
			}
			//On ferme la connection ï¿½ la BDD
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
	
	private void updateGrille(boolean reset) {
		//Variable de "probabilite" du pop d'une balle
		int randomNum;
		//Variables de deplacement
		int i;
		int j;
		//Variable de limitation de boucle
		int x = this.config.getNiveau().getX();
		int y = this.config.getNiveau().getY();
		//Variable mort du joueur
		boolean mort = false;
		
		Position tmpPos;
		
		//Variable position du policier
		Position posP;
		//Variable direction du policier
		Direction dirP;
		//Reset du compteur pour le pop de balle si reset == true
		if (reset) {
			this.bullet = false;
			this.pseudoTemps = 0;
		}
		//Pour chaque label de tmpGrille
		for (i=0;i<x;i++) {
			for (j=0;j<y;j++) {
				tmpPos = new Position(i,j);
				//S'il sagit d'un mur, change sa class en mur
				if (this.config.get(tmpPos).getType().equals(Type.MUR)) {
					tmpGrille[i][j].getStyleClass().clear();
					tmpGrille[i][j].getStyleClass().add(getMur(tmpPos));
				//S'il sagit d'un joueur, change sa class en joueur
				} else if (this.config.get(tmpPos).getType().equals(Type.JOUEUR)) {
					//S'il passe sur une balle, rajoute la balle a son pistolet et reset pseudoTemps et bullet
					if (tmpGrille[i][j].getStyleClass().contains("bullet")) {
						this.config.getJoueur().addBalle();
						this.bullet = false;
						this.pseudoTemps = 0;
					}
					tmpGrille[i][j].getStyleClass().clear();
					tmpGrille[i][j].getStyleClass().add(this.getRegard());
				//S'il sagit d'un diamant, change sa class en diamant
				} else if (this.config.get(tmpPos).getType().equals(Type.DIAMANT)) {
					//S'il passe sur une balle, rajoute la balle a son pistolet et reset pseudoTemps et bullet
					if (tmpGrille[i][j].getStyleClass().contains("bullet")) {
						this.config.getJoueur().addBalle();
						this.bullet = false;
						this.pseudoTemps = 0;
					}
					tmpGrille[i][j].getStyleClass().clear();
					tmpGrille[i][j].getStyleClass().add("diamant");
				//S'il sagit d'une case, change sa class en case
				} else if (this.config.get(tmpPos).getType().equals(Type.CASE)) {
					//Prends le prochain nombre random entre 1 et 20 compris.
					randomNum = ThreadLocalRandom.current().nextInt(1,21);
					//Si une balle n'est pas déjà sur la grille, que le nombre random est egal a 1, qut le pseudoTemps est egal à 5 et que la pos n'est pas une cible
					//Ou si la case contenait deja une balle et que ce n'est pas un reset
					if ((!this.bullet && this.pseudoTemps == 5 && randomNum==1 && !this.config.getNiveau().getCibles().contains(tmpPos)) || (tmpGrille[i][j].getStyleClass().contains("bullet") && !reset)) {
						//Met this.bullet sur true
						this.bullet = true;
						//Cha,gement la class pour bullet
						tmpGrille[i][j].getStyleClass().clear();
						tmpGrille[i][j].getStyleClass().add("bullet");
					} else {
						//Sinon, change la class pour case
						tmpGrille[i][j].getStyleClass().clear();
						tmpGrille[i][j].getStyleClass().add("case");
					}
				//S'il sagit d'un policier, change sa class en policier
				} else if (this.config.get(tmpPos).getType().equals(Type.POLICIER)) {
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
		//Augmente le pseudo temps de 1 s'il est inferieur à 5 et qu'il n'y a pas deja une balle sur la grille
		if (!this.bullet && this.pseudoTemps<5) {
			this.pseudoTemps++;
		}
		//Reset la grille d'affichage
		this.affGrille.getChildren().clear();
		//Pour chaque case, ajoute le label correspondant
		for (i=0;i<x;i++) {
			for (j=0;j<y;j++) {
				this.affGrille.add(tmpGrille[i][j], j, i);
			}
		}
		//Variable verif mur
		boolean mur = false;
		//Pour chaque policier
		for(i=0;i<this.config.getPoliciers().size();i++) {
			//On recupere la position et la vision du policier
			posP = this.config.getPoliciers().get(i).getPosition();
			dirP = this.config.getPoliciers().get(i).getRegard();
			//Pour les deux cases devant le policier
			for (j=0;j<2;j++) {
				//on actualise la position verifie
				posP = posP.add(dirP);
				//Si on est pas dï¿½jï¿½ mort
				if (!mort && !mur) {
					mur = this.config.get(posP).getType().equals(Type.MUR);
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
				updateGrille(true);
			});
			
		}
	}
	
	private String getRegard() {
		String res = "";
		if (this.config.getJoueur().getRegard().equals(Direction.DROITE)) {
			res = "joueur_droite";
		}else if(this.config.getJoueur().getRegard().equals(Direction.GAUCHE)) {
			res = "joueur_gauche";
		}else if(this.config.getJoueur().getRegard().equals(Direction.HAUT)) {
			res = "joueur_haut";
		}else if(this.config.getJoueur().getRegard().equals(Direction.BAS)) {
			res = "joueur_bas";
		}
		return res;
	}
	
	private boolean inGrille(Position pos) {
		int sizeX = this.config.getNiveau().getX();
		int sizeY = this.config.getNiveau().getY();
		if (((pos.getX() < sizeX)&&(pos.getX() >= 0)) && ((pos.getY() < sizeY)&&(pos.getY() >= 0))){
			return true;
		}else {
			return false;
		}
	}
	
	private String getMur(Position pos) {
		String res = "";
		
		boolean haut = false;
		boolean bas = false;
		boolean gauche = false;
		boolean droite = false;
		boolean hautDroite = false;
		boolean hautGauche = false;
		boolean basGauche = false;
		boolean basDroite = false;
		
		Position tmpPos = new Position(pos);
		
		//haut
		tmpPos = tmpPos.add(Direction.HAUT);
		if ((inGrille(tmpPos) && (this.config.get(tmpPos).getType().equals(Type.MUR)))){
			haut = true;
		}
		tmpPos = tmpPos.sub(Direction.HAUT);
		
		//bas
		tmpPos = tmpPos.add(Direction.BAS);
		if ((inGrille(tmpPos) && (this.config.get(tmpPos).getType().equals(Type.MUR)))){
			bas = true;
		}
		tmpPos = tmpPos.sub(Direction.BAS);
		
		//droite
		tmpPos = tmpPos.add(Direction.DROITE);
		if ((inGrille(tmpPos) && (this.config.get(tmpPos).getType().equals(Type.MUR)))){
			droite = true;
		}
		tmpPos = tmpPos.sub(Direction.DROITE);
		
		//gauche
		tmpPos = tmpPos.add(Direction.GAUCHE);
		if ((inGrille(tmpPos) && (this.config.get(tmpPos).getType().equals(Type.MUR)))){
			gauche = true;
		}
		tmpPos = tmpPos.sub(Direction.GAUCHE);
		
		//diagonale hautgauche
		tmpPos = tmpPos.add(Direction.HAUT).add(Direction.GAUCHE);
		if ((inGrille(tmpPos) && (this.config.get(tmpPos).getType().equals(Type.MUR)))){
			hautGauche = true;
		}
		tmpPos = tmpPos.sub(Direction.GAUCHE).sub(Direction.HAUT);
		
		//diagonale hautdroite
		tmpPos = tmpPos.add(Direction.HAUT).add(Direction.DROITE);
		if ((inGrille(tmpPos) && (this.config.get(tmpPos).getType().equals(Type.MUR)))){
			hautDroite = true;
		}
		tmpPos = tmpPos.sub(Direction.DROITE).sub(Direction.HAUT);
		
		//diagonale basgauche
		tmpPos = tmpPos.add(Direction.BAS).add(Direction.GAUCHE);
		if ((inGrille(tmpPos) && (this.config.get(tmpPos).getType().equals(Type.MUR)))){
			basGauche = true;
		}
		tmpPos = tmpPos.sub(Direction.GAUCHE).sub(Direction.BAS);
		
		//diagonale basdroite
		tmpPos = tmpPos.add(Direction.BAS).add(Direction.DROITE);
		if ((inGrille(tmpPos) && (this.config.get(tmpPos).getType().equals(Type.MUR)))){
			basDroite = true;
		}
		tmpPos = tmpPos.sub(Direction.DROITE).sub(Direction.BAS);
		
		if (haut && bas && droite && gauche) {
			res = "inter_4";
		} else if (haut && bas && droite) {
			res = "inter_3_droite";
		} else if (bas && droite && gauche) {
			res = "inter_3_bas";
		} else if (haut && bas && gauche) {
			res = "inter_3_gauche";
		} else if (haut && droite && gauche) {
			res = "inter_3_haut";
		} else if (haut && bas) {
			res = "mur_vertical";
		} else if (gauche && droite) {
			res = "mur_horizontal";
		} else if (haut && droite) {
			res = "coin_bas_gauche";
		} else if (haut && gauche) {
			res = "coin_bas_droite";
		} else if (bas && gauche) {
			res = "coin_haut_droite";
		} else if (bas && droite) {
			res = "coin_haut_gauche";
		} else if (bas) {
			res = "bout_haut";
		} else if (haut) {
			res = "bout_bas";
		} else if (gauche) {
			res = "bout_droite";
		} else if (droite) {
			res = "bout_gauche";
		}
		
		return res;
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
			updateGrille(true);
			//Actualise le score
			updatescore();
		});
		saveBt.setOnAction(e -> {
			try {
				//Connection ï¿½ la BDD des scores
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://e90653-mysql.services.easyname.eu/u143161db7","u143161db7","ProjetGL2");
				//Creation de la variable de requete
				Statement stmt=con.createStatement();
				//Crï¿½ation de la variable de resultat et recuperation du resultat de la requete
				int status =stmt.executeUpdate("INSERT INTO HighScore VALUES('"+pseudoTF.getText()+"',"+this.nivSelec+","+this.config.getJoueur().getHisto().size()+")"); 
				//Creation du label de fin de sauvegarde
				Label saveLabel = new Label();
				if (status == 1) {
					saveLabel.setText("Votre score a bien ï¿½tï¿½ sauvegarde!");
				} else {
					saveLabel.setText("Desole, il semblerait que l'application n'arrive pas a sauvegarder votre score. Merci de verifier votre connexion internet.");
				}
				notSaveBt.setText("Ok");
				//Actualisation des elements 
				saveVBox.getChildren().clear();
				saveVBox.getChildren().addAll(saveLabel,notSaveBt);
				//On ferme la connection ï¿½ la BDD
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
