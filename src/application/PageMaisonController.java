/**
* Le controleur de la page maison qui permet la gestion de la maison et du personnage
*/

package application;

import javafx.event.ActionEvent;
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

	@FXML
	private Button closeButton;

	private void jouerSon(String path) {
		MediaPlayerSingleton.getInstance().jouerSon(path);
	}

	@FXML
	private Button btnActiverDesactiverSon;

	private boolean jouerSonActif = true;

	/**
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

	/**
	 * Fonction pour fermer la fenêtre
	 */
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
	private Button BoutonInteractionLit;

	@FXML
	public void initialize() {
		if (jouerSonActif) {
			jouerSon("src/Audio/Maison.mp3");
		}
		makeMovable(sprite, scene);
		BoutonInteractionLit.setVisible(false);
		Image image = new Image(getClass().getResourceAsStream(PagePersonnageController.player.getSkin()));
		sprite.setImage(image);
	}

	/**
	 * Fonction pour dormir, recharge l'énergie du personnage
	 * 
	 * @param event ActionEvent qui déclenche la fonction
	 */
	@FXML
	void dormir(ActionEvent event) {
		// RECUPERER TOUTE L'ENERGIE
		PageInformationPersonnageController.timeline.stop();
		PagePersonnageController.player.setEnergy(1);
		System.out.println(PagePersonnageController.player.getEnergy());
	}

	/**
	 * Fonction pour ouvrir la fenêtre d'information sur le personnage
	 */
	@FXML
	void OpenSpriteInformation() {
		lancerXML("PageInformationPersonnage.fxml", 930, 580);
	}

	/**
	 * Fonction pour ouvrir la fenêtre de l'inventaire du personnage
	 */
	@FXML
	void OpenSpriteInventory() {
		lancerXML("PageInventairePersonnage.fxml", 930, 580);
	}

	/**
	 * Charge une fenêtre XML avec les dimensions données.
	 *
	 * @param url      l'URL du fichier XML à charger
	 * @param largeur  la largeur de la fenêtre
	 * @param longueur la longueur de la fenêtre
	 */
	private void lancerXML(String url, int largeur, int longueur) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setOnCloseRequest(event -> {
				event.consume();
			});
			stage.initStyle(StageStyle.UNDECORATED);
			Scene scene = new Scene(root1, largeur, longueur);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.centerOnScreen();
			stage.show();
		} catch (Exception e) {
			e.printStackTrace(System.err);
			System.out.println("Impossible de charger la fenêtre");
		}
	}

	/**
	 * Initialise les événements de touche pour le déplacement du personnage
	 */
	private void movementSetup() {
		scene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.Z) {
				wPressed.set(true);
			}
			if (e.getCode() == KeyCode.Q) {
				aPressed.set(true);
			}
			if (e.getCode() == KeyCode.S) {
				sPressed.set(true);
			}
			if (e.getCode() == KeyCode.D) {
				dPressed.set(true);
			}
			if (e.getCode() == KeyCode.A) {
				OpenSpriteInformation();
			}
			if (e.getCode() == KeyCode.I) {
				OpenSpriteInventory();
			}
			if (e.getCode() == KeyCode.R) {
				dormir(null);
			}
			if (e.getCode() == KeyCode.F) {
				lancerXML("PageJouer.fxml", 1920, 1080);
				close();
			}
		});

		scene.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.Z) {
				wPressed.set(false);
			}

			if (e.getCode() == KeyCode.Q) {
				aPressed.set(false);
			}

			if (e.getCode() == KeyCode.S) {
				sPressed.set(false);
			}

			if (e.getCode() == KeyCode.D) {
				dPressed.set(false);
			}
		});
	}

	private static BooleanProperty wPressed = new SimpleBooleanProperty();
	private static BooleanProperty aPressed = new SimpleBooleanProperty();
	private static BooleanProperty sPressed = new SimpleBooleanProperty();
	private static BooleanProperty dPressed = new SimpleBooleanProperty();

	private BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed);

	/**
	 * La variable utilisée pour le déplacement du personnage
	 */
	private int movementVariable = 2;

	/**
	 * Rend un objet ImageView déplaçable à l'aide des touches directionnelles
	 *
	 * @param sprite l'objet ImageView à déplacer
	 * @param scene  le BorderPane de la scène
	 */
	public void makeMovable(ImageView sprite, BorderPane scene) {
		this.sprite = sprite;
		this.scene = scene;
		movementSetup();
		keyPressed.addListener(((observableValue, aBoolean, t1) -> {
			if (!aBoolean) {
				timer.start();
			} else {
				timer.stop();
			}
		}));
	}

	AnimationTimer timer = new AnimationTimer() {
		@Override
		public void handle(long timestamp) {
			// collision avec le bas
			if (sprite.getLayoutY() >= 780) {
				sprite.setLayoutY(780);
			}

			// collision avec le haut
			if (sprite.getLayoutY() <= 170) {
				sprite.setLayoutY(170);
			}

			// collision avec le gauche
			if (sprite.getLayoutX() <= 78) {
				sprite.setLayoutX(78);
			}

			// collision avec le gauche
			if (sprite.getLayoutX() >= 1724) {
				sprite.setLayoutX(1724);
			}

			// Collision carre en haut a droite
			if (sprite.getLayoutX() <= 1724 && sprite.getLayoutX() >= 1176 && sprite.getLayoutY() <= 506
					&& sprite.getLayoutY() >= 170) {
				if (dPressed.get()) {
					sprite.setLayoutX(sprite.getLayoutX() - movementVariable);
				}
				if (wPressed.get()) {
					sprite.setLayoutY(sprite.getLayoutY() + movementVariable);
				}
			}

			// Interaction avec le lit
			if (sprite.getLayoutX() <= 1312 && sprite.getLayoutX() >= 1124 && sprite.getLayoutY() <= 674
					&& sprite.getLayoutY() >= 484) {
				BoutonInteractionLit.setVisible(true);
			} else {
				BoutonInteractionLit.setVisible(false);
			}

			if (wPressed.get()) {
				if (!(sprite.getLayoutX() <= 1176 && sprite.getLayoutX() >= 548 && sprite.getLayoutY() <= 506
						&& sprite.getLayoutY() >= 312)) {
					System.out.println(" X : " + sprite.getLayoutX() + " Y : " + sprite.getLayoutY());

					sprite.setLayoutY(sprite.getLayoutY() - movementVariable);
				}
			}
			if (sPressed.get()) {
				if (926 > sprite.getLayoutY()) {
					// System.out.println(" X : " + sprite.getLayoutX() + " Y : " +
					// sprite.getLayoutY());
					sprite.setLayoutY(sprite.getLayoutY() + movementVariable);
					if (sprite.getLayoutX() <= 576 && sprite.getLayoutX() >= 526 && sprite.getLayoutY() == 772) {
						close();
						lancerXML("PageJouer.fxml", 1920, 1080);
					}
				}
			}

			if (aPressed.get()) {
				if (-12 < sprite.getLayoutX()) {
					// System.out.println(" X : " + sprite.getLayoutX() + " Y : " +
					// sprite.getLayoutY());
					sprite.setLayoutX(sprite.getLayoutX() - movementVariable);
				}
			}
			if (dPressed.get()) {
				if (1832 > sprite.getLayoutX()) {
					// System.out.println(" X : " + sprite.getLayoutX() + " Y : " +
					// sprite.getLayoutY());
					sprite.setLayoutX(sprite.getLayoutX() + movementVariable);
				}
			}
		}
	};
}
