package application;

import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.animation.AnimationTimer;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
		makeMovable(sprite, scene);
		BoutonInteractionLit.setVisible(false);
		Image image = new Image(getClass().getResourceAsStream(PagePersonnageController.player.getSkin()));        
		sprite.setImage(image);

	}
	
	@FXML
	void dormir(ActionEvent event) {
	    // RECUPERER TOUTE L'ENERGIE
	    PagePersonnageController.player.setEnergy(1.0);
	}

	
	@FXML
    void OpenSpriteInformation() {
        try {
            // Charger le fichier FXML qui définit la fenêtre
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PageInformationPersonnage.fxml"));
            Parent root = loader.load();
            
            // Créer une nouvelle scène avec la fenêtre chargée
            Scene scene = new Scene(root, 930, 580);
            
            // Créer une nouvelle fenêtre avec la scène
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setResizable(false);
            // Afficher la fenêtre
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	private void lancerXML(String url) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();

			stage.setOnCloseRequest(event -> {
				event.consume();
			});

			stage.initStyle(StageStyle.UNDECORATED);

			Scene scene = new Scene(root1, 1920, 1080);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace(System.err);
			System.out.println("Impossible de charger la fenêtre");
		}
	}

	private void movementSetup(){
		scene.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.Z) {
				wPressed.set(true);
			}

			if(e.getCode() == KeyCode.Q) {
				aPressed.set(true);
			}

			if(e.getCode() == KeyCode.S) {
				sPressed.set(true);
			}

			if(e.getCode() == KeyCode.D) {
				dPressed.set(true);
			}
		});

		scene.setOnKeyReleased(e ->{
			if(e.getCode() == KeyCode.Z) {
				wPressed.set(false);
			}

			if(e.getCode() == KeyCode.Q) {
				aPressed.set(false);
			}

			if(e.getCode() == KeyCode.S) {
				sPressed.set(false);
			}

			if(e.getCode() == KeyCode.D) {
				dPressed.set(false);
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
			if(!aBoolean){
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
//			
//			// Collision ligne haut - bas
//			if (sprite.getLayoutX() <= 1176 && sprite.getLayoutX() >= 548 && sprite.getLayoutY() <= 506 && sprite.getLayoutY() >= 312) {
//				if(dPressed.get()){
//					sprite.setLayoutX(sprite.getLayoutX() - movementVariable);
//				}
//				if(wPressed.get()){
//					sprite.setLayoutY(sprite.getLayoutY() + movementVariable);
//				}
//				if(aPressed.get()){
//					sprite.setLayoutX(sprite.getLayoutX() - movementVariable);
//				}
//				if(sPressed.get()){
//					sprite.setLayoutY(sprite.getLayoutY() + movementVariable);
//				}
//			}
			
			
			
			//Interaction avec le lit
			if (sprite.getLayoutX() <= 1312 && sprite.getLayoutX() >= 1124 && sprite.getLayoutY() <= 674 && sprite.getLayoutY() >= 484) {
				BoutonInteractionLit.setVisible(true);
			}else {
				BoutonInteractionLit.setVisible(false);
			}

			if(wPressed.get()) {
				if (!(sprite.getLayoutX() <= 1176 && sprite.getLayoutX() >= 548 && sprite.getLayoutY() <= 506 && sprite.getLayoutY() >= 312)) {
					System.out.println(" X : " + sprite.getLayoutX() + " Y : " + sprite.getLayoutY());
					sprite.setLayoutY(sprite.getLayoutY() - movementVariable);
				}
			}

			if(sPressed.get()){
				if (1==1) {
					System.out.println(" X : " + sprite.getLayoutX() + " Y : " + sprite.getLayoutY());
					sprite.setLayoutY(sprite.getLayoutY() + movementVariable);
					if (sprite.getLayoutX() <= 576 && sprite.getLayoutX() >= 526 && sprite.getLayoutY() == 772) {
						close(); 
						lancerXML("PageJouer.fxml");
					}
				}
			}

			if(aPressed.get()){
				if (1==1) {
					System.out.println(" X : " + sprite.getLayoutX() + " Y : " + sprite.getLayoutY());
					sprite.setLayoutX(sprite.getLayoutX() - movementVariable);
				}
			}

			if(dPressed.get()){
				if (1==1) {
					System.out.println(" X : " + sprite.getLayoutX() + " Y : " + sprite.getLayoutY());
					sprite.setLayoutX(sprite.getLayoutX() + movementVariable); 
				}
			}
		}
	};
}

