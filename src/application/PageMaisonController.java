package application;

import javafx.event.ActionEvent;
import javafx.event.Event;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.AnimationTimer;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * Cette classe est le contrôleur de la page d'accueil du jeu.
 */
public class PageMaisonController {

	private static final Logger logger = Logger.getLogger(PageMaisonController.class.getName());
	
	@FXML
	private Button closeButton;
	
	/**
     * Joue le son à partir du fichier spécifié.
     *
     * @param path Le chemin vers le fichier audio à jouer.
     */
	private void jouerSon(String path) {
		MediaPlayerSingleton.getInstance().jouerSon(path);
	}

	@FXML
	private Button btnActiverDesactiverSon;

	private boolean jouerSonActif = true;

	/*
	 * Fonction activerDesactiverSon param: return: Active ou Desactive la musique
	 * en changeant l'image selon le statut du bouton
	 */
	@FXML
	private void activerDesactiverSon(ActionEvent event) {
		jouerSonActif = !jouerSonActif;
		if (!jouerSonActif) {
			// Si le son est desactiver, on ajouter la classe CSS "muted" au bouton
			// btnActiverDesactiverSon
			btnActiverDesactiverSon.getStyleClass().add("muted");
		} else {
			// Si le son est activer, on retire la classe CSS "muted" du bouton
			// btnActiverDesactiverSon
			btnActiverDesactiverSon.getStyleClass().remove("muted");
		}
		MediaPlayerSingleton.getInstance().setMute(!jouerSonActif);
	}

	@FXML
	void close() {
			Stage stage = (Stage) closeButton.getScene().getWindow();
			stage.close();
	}
	
	@FXML
	private ImageView sprite;

	@FXML
	private BorderPane scene;
	
	@FXML
    private Button boutonInteractionLit;
	
	
	 /**
     * Initialise le contrôleur après que son élément racine ait été complètement traité.
     */
	@FXML
	public void initialize() {
		if (jouerSonActif) {
			jouerSon("src/Audio/Maison.mp3");
		}
		makeMovable(sprite, scene);
		boutonInteractionLit.setVisible(false);
		Image image = new Image(getClass().getResourceAsStream(PagePersonnageController.player.getSkin()));
		sprite.setImage(image);
	}

	
	 /**
     * Permet au joueur de dormir et de récupérer toute son énergie.
     *
     * @param event L'événement déclencheur.
     */
	@FXML
	void dormir(ActionEvent event) {
		// RECUPERER TOUTE L'ENERGIE
		PageInformationPersonnageController.timeline.stop();
		PagePersonnageController.player.setEnergy(1);
	}
	
	/**
     * Ouvre la fenêtre d'informations sur le personnage.
     */
	@FXML
    void openSpriteInformation() {
		lancerXML("PageInformationPersonnage.fxml", 930, 580);
    }
	
	/**
	 * Ouvre l'inventaire du personnage.
	 */
	@FXML
	void openSpriteInventory() {
		lancerXML("PageInventairePersonnage.fxml", 930, 580);
	}
	
	/**
	 * Lance la fenêtre correspondant au fichier XML donné en paramètre avec une taille donnée.
	 * 
	 * @param url la localisation du fichier XML de la fenêtre
	 * @param largeur la largeur de la fenêtre
	 * @param longueur la longueur de la fenêtre
	 */
	private void lancerXML(String url, int largeur, int longueur) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setOnCloseRequest(Event::consume);
			stage.initStyle(StageStyle.UNDECORATED);
			Scene scene1 = new Scene(root1, largeur, longueur);
			stage.setScene(scene1);
			stage.setResizable(false);
			stage.centerOnScreen();
			stage.show();
		} catch (Exception e) {
		    logger.log(Level.SEVERE, "Impossible de charger la fenêtre", e);
		}
	}
	
	
	/**
	 * Configure la gestion des mouvements du sprite du joueur.
	 */
	private void movementSetup(){
	    scene.setOnKeyPressed(e -> handleKeyPressed(e.getCode()));
	    scene.setOnKeyReleased(e -> handleKeyReleased(e.getCode()));
	}

	
	
	/**
	 * Gère les actions à effectuer lorsque la touche correspondante est pressée.
	 * 
	 * @param code le code de la touche pressée
	 */
	private void handleKeyPressed(KeyCode code) {
	    if (code == KeyCode.Z) {
	        wPressed.set(true);
	    } else if (code == KeyCode.Q) {
	        aPressed.set(true);
	    } else if (code == KeyCode.S) {
	        sPressed.set(true);
	    } else if (code == KeyCode.D) {
	        dPressed.set(true);
	    } else if (code == KeyCode.A) {
	        openSpriteInformation();
	    } else if (code == KeyCode.I) {
	        openSpriteInventory();
	    } else if (code == KeyCode.R) {
	        dormir(null);
	    } else if (code == KeyCode.F) {
	        lancerXML("PageJouer.fxml", 1920, 1080);
	        close();
	    }
	}

	
	
	/**
	 * Gère les actions à effectuer lorsque la touche correspondante est relâchée.
	 * 
	 * @param code le code de la touche relâchée
	 */
	private void handleKeyReleased(KeyCode code) {
	    if (code == KeyCode.Z) {
	        wPressed.set(false);
	    } else if (code == KeyCode.Q) {
	        aPressed.set(false);
	    } else if (code == KeyCode.S) {
	        sPressed.set(false);
	    } else if (code == KeyCode.D) {
	        dPressed.set(false);
	    }
	}

	/**
	 * Propriété booléenne indiquant si la touche "W" est enfoncée.
	 */
	private static BooleanProperty wPressed = new SimpleBooleanProperty();
	/**
	 * Propriété booléenne indiquant si la touche "A" est enfoncée.
	 */
	private static BooleanProperty aPressed = new SimpleBooleanProperty();
	/**
	 * Propriété booléenne indiquant si la touche "S" est enfoncée.
	 */
	private static BooleanProperty sPressed = new SimpleBooleanProperty();
	/**
	 * Propriété booléenne indiquant si la touche "D" est enfoncée.
	 */
	private static BooleanProperty dPressed = new SimpleBooleanProperty();

	
	/**
	 * Propriété booléenne indiquant si une touche de mouvement est enfoncée.
	 */
	private BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed);

	/**
	 * Variable de mouvement pour le sprite du joueur.
	 */
	private int movementVariable = 2;

	
	/**
	 * Configure les mouvements du sprite du joueur et les interactions avec les éléments de la maison.
	 * 
	 * @param sprite l'ImageView représentant le sprite du joueur
	 * @param scene le BorderPane de la page de la maison
	 */
	public void makeMovable(ImageView sprite, BorderPane scene){
		this.sprite = sprite;
		this.scene = scene;
		movementSetup();
		keyPressed.addListener(((observableValue, aBoolean, t1) -> {
			if(Boolean.FALSE.equals(aBoolean)){
				timer.start();
			} else {
				timer.stop();
			}
		}));
	}

	
	/**
	 * Classe définissant un objet AnimationTimer pour gérer les mouvements du personnage.
	 * Les collisions avec les bords de l'écran et les interactions avec les objets sont également gérées ici.
	 */
	AnimationTimer timer = new AnimationTimer() {
		
		/**
		 * Méthode appelée à chaque frame de l'animation pour mettre à jour les mouvements du personnage,
		 * gérer les collisions avec les bords de l'écran, et détecter les interactions avec les objets.
         * @param timestamp Le timestamp de l'animation en cours
		 */
		@Override
		public void handle(long timestamp) {			
			//collision avec le bas
			if(sprite.getLayoutY() >= 780) {
				sprite.setLayoutY(780);
			}
			
			//collision avec le haut
			if(sprite.getLayoutY() <= 170) {
				sprite.setLayoutY(170);
			}
			
			//collision avec le gauche
			if(sprite.getLayoutX() <= 78) {
				sprite.setLayoutX(78);
			}
			
			//collision avec le gauche
			if(sprite.getLayoutX() >= 1724) {
				sprite.setLayoutX(1724);
			}
			
			// Collision carre en haut a droite
			if (sprite.getLayoutX() <= 1724 && sprite.getLayoutX() >= 1176 && sprite.getLayoutY() <= 506 && sprite.getLayoutY() >= 170) {
				if(dPressed.get()){
					sprite.setLayoutX(sprite.getLayoutX() - movementVariable);
				}
				if(wPressed.get()){
					sprite.setLayoutY(sprite.getLayoutY() + movementVariable);
				}
			}
			
			//Interaction avec le lit
			boutonInteractionLit.setVisible(sprite.getLayoutX() <= 1312 && sprite.getLayoutX() >= 1124 && sprite.getLayoutY() <= 674 && sprite.getLayoutY() >= 484);

			if(wPressed.get() &&  (!(sprite.getLayoutX() <= 1176 && sprite.getLayoutX() >= 548 && sprite.getLayoutY() <= 506 && sprite.getLayoutY() >= 312))) {
					sprite.setLayoutY(sprite.getLayoutY() - movementVariable);
				
			}
			if(sPressed.get() &&  ( 926 > sprite.getLayoutY())) {
					sprite.setLayoutY(sprite.getLayoutY() + movementVariable);
					if (sprite.getLayoutX() <= 576 && sprite.getLayoutX() >= 526 && sprite.getLayoutY() == 772) {
						close(); 
						lancerXML("PageJouer.fxml", 1920, 1080);
					}
				
			}

			if(aPressed.get() &&  (-12 < sprite.getLayoutX())) {
					sprite.setLayoutX(sprite.getLayoutX() - movementVariable);
				
			}
			
			if(dPressed.get() &&  (1832 > sprite.getLayoutX())) {
					sprite.setLayoutX(sprite.getLayoutX() + movementVariable); 
				
			}
		}
	};
}