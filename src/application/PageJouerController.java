package application;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.AnimationTimer;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PageJouerController {

	private static final Logger logger = Logger.getLogger(PageJouerController.class.getName());
	
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
	private void activerDesactiverSon() {
		jouerSonActif = !jouerSonActif;
		if (!jouerSonActif) {
			// Si le son est desactivé, on ajouter la classe CSS "muted" au bouton
			// btnActiverDesactiverSon
			btnActiverDesactiverSon.getStyleClass().add("muted");
		} else {
			// Si le son est activé, on retire la classe CSS "muted" du bouton
			// btnActiverDesactiverSon
			btnActiverDesactiverSon.getStyleClass().remove("muted");
		}
		MediaPlayerSingleton.getInstance().setMute(!jouerSonActif);
	}


	@FXML
	private Button closeButton;

	@FXML
	private Button buttonSprite;

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
	private ImageView cochon;

	@FXML
	private ImageView cochon2;

	@FXML
	private ImageView vache;

	@FXML
	private ImageView vache2;

	@FXML
	private Button boutonInteractionCochon;

	@FXML
	private Button boutonInteraction;

	@FXML
	private Button boutonInteractionPotager;

	@FXML
	private Button boutonInteractionCoffre;

	@FXML
	private Button boutonInteractionVache;

	@FXML
	public void initialize() {
		if (jouerSonActif) {
			jouerSon("src/Audio/Jeu.mp3");
		}

		boutonInteraction.setVisible(false);
		boutonInteractionPotager.setVisible(false);
		boutonInteractionCochon.setVisible(false);
		boutonInteractionCoffre.setVisible(false);
		boutonInteractionVache.setVisible(false);
		makeMovable(sprite, scene);
		Image image = new Image(getClass().getResourceAsStream(PagePersonnageController.player.getSkin()));
		sprite.setImage(image);
		// On appelle l'affichage Sprite Information
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("PageInformationPersonnage.fxml"));
			loader.load();

			FXMLLoader loader2 = new FXMLLoader(getClass().getResource("PageInventairePersonnage.fxml"));
			loader2.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		    logger.log(Level.SEVERE, "Impossible de charger la page", e);
		}
	}

	private void movementSetup() {
	    scene.setOnKeyPressed(e -> {
	        KeyCode code = e.getCode();

	        switch (code) {
	            case Z -> wPressed.set(true);
	            case Q -> aPressed.set(true);
	            case S -> sPressed.set(true);
	            case D -> dPressed.set(true);
	            case A -> openSpriteInformation();
	            case I -> openSpriteInventory();
	            case K -> interactionCochonButton(null);
	            case R -> interactionPotagerButton(null);
	            case L -> interactionLaitButton(null);
	            case M -> {
	                close();
	                lancerXML("PageMaison.fxml", 1920, 1080);
	            }
	            case C -> interactionCoffreButton(null);
	            case T -> interactionMarchandButton(null);
	            case H -> jouerSon("src/Audio/AideJeu.mp3");
	            default -> {}
	        }
	    });

	    scene.setOnKeyReleased(e -> {
	        KeyCode code = e.getCode();

	        switch (code) {
	            case Z -> wPressed.set(false);
	            case Q -> aPressed.set(false);
	            case S -> sPressed.set(false);
	            case D -> dPressed.set(false);
	            default -> {}
	        }
	    });
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
			
			boolean interactionMarchand = sprite.getLayoutX() <= 1328 && sprite.getLayoutX() >= 1222 && sprite.getLayoutY() <= 580 && sprite.getLayoutY() >= 370;
	        boolean interactionPotager = (sprite.getLayoutX() <= 960 && sprite.getLayoutX() >= 784 && sprite.getLayoutY() <= 576 && sprite.getLayoutY() >= 400) 
	                || (sprite.getLayoutX() <= 978 && sprite.getLayoutX() >= 772 && sprite.getLayoutY() <= 862 && sprite.getLayoutY() >= 632)
	                || (sprite.getLayoutX() <= 1234 && sprite.getLayoutX() >= 1040 && sprite.getLayoutY() <= 862 && sprite.getLayoutY() >= 674)
	                || (sprite.getLayoutX() <= 1198 && sprite.getLayoutX() >= 1040 && sprite.getLayoutY() <= 576 && sprite.getLayoutY() >= 400);
	        boolean interactionCochon = sprite.getLayoutX() <= 364 && sprite.getLayoutX() >= 196 && sprite.getLayoutY() <= 576 && sprite.getLayoutY() >= 380;
	        boolean interactionCoffre = sprite.getLayoutX() <= 1446 && sprite.getLayoutX() >= 1208 && sprite.getLayoutY() <= 242 && sprite.getLayoutY() >= 48;
	        boolean interactionVache = sprite.getLayoutX() <= 1668 && sprite.getLayoutX() >= 1512 && sprite.getLayoutY() <= 288 && sprite.getLayoutY() >= 0;
	        
	        boutonInteraction.setVisible(interactionMarchand);
	        boutonInteractionPotager.setVisible(interactionPotager);
	        boutonInteractionCochon.setVisible(interactionCochon);
	        boutonInteractionCoffre.setVisible(interactionCoffre);
	        boutonInteractionVache.setVisible(interactionVache);
	        
			if(wPressed.get() &&  (((sprite.getLayoutX() < 470 && sprite.getLayoutY() <= 144 ) || (sprite.getLayoutX() > 1130 && sprite.getLayoutY() <= 144 ) || sprite.getLayoutY() > 144) && ( -2 < sprite.getLayoutY()))) {
					sprite.setLayoutY(sprite.getLayoutY() - movementVariable);
					if (sprite.getLayoutX() <= 904 && sprite.getLayoutX() >= 868 && sprite.getLayoutY() == 146) {
						close(); 
						lancerXML("PageMaison.fxml", 1920, 1080);
					}
				
			}

			if(sPressed.get() &&  ( 926 > sprite.getLayoutY())) {
					sprite.setLayoutY(sprite.getLayoutY() + movementVariable);
				
			}

			if(aPressed.get() &&  (((sprite.getLayoutX() < 470 || 1138 < sprite.getLayoutX()) && sprite.getLayoutY() < 144 && -12 < sprite.getLayoutX() ) || sprite.getLayoutY() > 144 && -12 < sprite.getLayoutX())) {
					sprite.setLayoutX(sprite.getLayoutX() - movementVariable);
				
			}

			if(dPressed.get() &&  (((sprite.getLayoutX() < 464 || 900 < sprite.getLayoutX()) && sprite.getLayoutY() < 144 && 1832 > sprite.getLayoutX() ) || sprite.getLayoutY() > 144 && 1832 > sprite.getLayoutX())) {
					sprite.setLayoutX(sprite.getLayoutX() + movementVariable); 
				
			}
		}
	};

	@FXML
	void openSpriteInformation() {
		lancerXML("PageInformationPersonnage.fxml", 930, 580);
	}

	@FXML
	void openSpriteInventory() {
		lancerXML("PageInventairePersonnage.fxml", 930, 580);
	}

	@FXML
	public void interactionCochonButton(ActionEvent evt) {
		PagePersonnageController.player.getInventory().addItem("porc");
	}

	@FXML
	public void interactionPotagerButton(ActionEvent evt) {
		PagePersonnageController.player.getInventory().addItem("carotte");
	}

	@FXML
	public void interactionLaitButton(ActionEvent evt) {
		PagePersonnageController.player.getInventory().addItem("lait");
	}

	@FXML
	public void interactionCoffreButton(ActionEvent evt) {
		if(PagePersonnageController.player.getInventory().getArgentJoueur() >= 200) {
			lancerXML("PageCoffre.fxml", 930, 580);
		}
		else {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Coins insuffisants");
			alert.setHeaderText(null);
			alert.setContentText("Vous devez avoir 200 coins pour ouvir le coffre !");
			alert.showAndWait();
		}
	}

	@FXML
	public void interactionMarchandButton(ActionEvent evt) {
		lancerXML("PageEchangeMarchand.fxml", 1920, 1080);
	}
}
