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

public class PageMaisonController {

	private static final Logger logger = Logger.getLogger(PageMaisonController.class.getName());
	
	@FXML
	private Button closeButton;
	
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

	@FXML
	void dormir(ActionEvent event) {
		// RECUPERER TOUTE L'ENERGIE
		PageInformationPersonnageController.timeline.stop();
		PagePersonnageController.player.setEnergy(1);
	}
	
	@FXML
    void openSpriteInformation() {
		lancerXML("PageInformationPersonnage.fxml", 930, 580);
    }
	
	@FXML
	void openSpriteInventory() {
		lancerXML("PageInventairePersonnage.fxml", 930, 580);
	}
	
	
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
	private void movementSetup(){
	    scene.setOnKeyPressed(e -> handleKeyPressed(e.getCode()));
	    scene.setOnKeyReleased(e -> handleKeyReleased(e.getCode()));
	}

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

	private static BooleanProperty wPressed = new SimpleBooleanProperty();
	private static BooleanProperty aPressed = new SimpleBooleanProperty();
	private static BooleanProperty sPressed = new SimpleBooleanProperty();
	private static BooleanProperty dPressed = new SimpleBooleanProperty();

	private BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed);

	private int movementVariable = 2;

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

	AnimationTimer timer = new AnimationTimer() {
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
