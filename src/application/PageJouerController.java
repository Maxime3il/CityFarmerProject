package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
import model.Player;

public class PageJouerController {
	
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
    private Button BoutonInteractionCochon;


	@FXML
    private Button BoutonInteraction;
	
	 @FXML
	 private Button BoutonInteractionPotager;
	 
	 @FXML
	 private Button BoutonInteractionCoffre;
	 
	 @FXML
	 private Button BoutonInteractionVache;
	
	@FXML
	public void initialize() {
		if (jouerSonActif) {
			jouerSon("src/Audio/boutonMusique.mp3");
		}

		BoutonInteraction.setVisible(false);
		BoutonInteractionPotager.setVisible(false);
		BoutonInteractionCochon.setVisible(false);
		BoutonInteractionCoffre.setVisible(false);
		BoutonInteractionVache.setVisible(false);
		makeMovable(sprite, scene);
		Image image = new Image(getClass().getResourceAsStream(PagePersonnageController.player.getSkin()));
		sprite.setImage(image);
		// On appelle l'affichage Sprite Information
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("PageInformationPersonnage.fxml"));
			Parent root = loader.load();
			
			FXMLLoader loader2 = new FXMLLoader(getClass().getResource("PageInventairePersonnage.fxml"));
			Parent root2 = loader2.load();
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
			if(e.getCode() == KeyCode.A) {
				OpenSpriteInformation();		
			}
			if(e.getCode() == KeyCode.I) {
				OpenSpriteInventory();
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
	
	static boolean gg = false;

	AnimationTimer timer = new AnimationTimer() {
		@Override
		public void handle(long timestamp) {
			//Interaction avec le marchand
			if (sprite.getLayoutX() <= 1328 && sprite.getLayoutX() >= 1222 && sprite.getLayoutY() <= 580 && sprite.getLayoutY() >= 370) {
				BoutonInteraction.setVisible(true);
			}else {
				BoutonInteraction.setVisible(false);
			}
			//Interaction avec le potager
			if ((sprite.getLayoutX() <= 960 && sprite.getLayoutX() >= 784 && sprite.getLayoutY() <= 576 && sprite.getLayoutY() >= 400) 
					|| (sprite.getLayoutX() <= 978 && sprite.getLayoutX() >= 772 && sprite.getLayoutY() <= 862 && sprite.getLayoutY() >= 632)
					|| (sprite.getLayoutX() <= 1234 && sprite.getLayoutX() >= 1040 && sprite.getLayoutY() <= 862 && sprite.getLayoutY() >= 674)
					|| (sprite.getLayoutX() <= 1198 && sprite.getLayoutX() >= 1040 && sprite.getLayoutY() <= 576 && sprite.getLayoutY() >= 400)
					) {
				BoutonInteractionPotager.setVisible(true);
			}else {
				BoutonInteractionPotager.setVisible(false);
			}
			//Interaction avec les cochons
			if (sprite.getLayoutX() <= 364 && sprite.getLayoutX() >= 196 && sprite.getLayoutY() <= 576 && sprite.getLayoutY() >= 380) {
				BoutonInteractionCochon.setVisible(true);
			}else {
				BoutonInteractionCochon.setVisible(false);
			}
			//Interaction avec le coffre
			if (sprite.getLayoutX() <= 1446 && sprite.getLayoutX() >= 1208 && sprite.getLayoutY() <= 242 && sprite.getLayoutY() >= 48) {
				BoutonInteractionCoffre.setVisible(true);
			}else {
				BoutonInteractionCoffre.setVisible(false);
			}
			//Interaction avec les vaches
			if (sprite.getLayoutX() <= 1668 && sprite.getLayoutX() >= 1512 && sprite.getLayoutY() <= 288 && sprite.getLayoutY() >= 0) {
				BoutonInteractionVache.setVisible(true);
			}else {
				BoutonInteractionVache.setVisible(false);
			}
			if(wPressed.get()) {
                if ((((sprite.getLayoutX() < 470 && sprite.getLayoutY() <= 144 ) || (sprite.getLayoutX() > 1130 && sprite.getLayoutY() <= 144 ) || sprite.getLayoutY() > 144)) && ( -2 < sprite.getLayoutY())) {
                    System.out.println(" X : " + sprite.getLayoutX() + " Y : " + sprite.getLayoutY());
                    sprite.setLayoutY(sprite.getLayoutY() - movementVariable);
                    if (sprite.getLayoutX() <= 904 && sprite.getLayoutX() >= 868 && sprite.getLayoutY() == 146) {
                        close(); 
                        lancerXML("PageMaison.fxml");
                    }
                }
            }

            if(sPressed.get()) {
                if ( 926 > sprite.getLayoutY()) {
                    System.out.println(" X : " + sprite.getLayoutX() + " Y : " + sprite.getLayoutY());
                    sprite.setLayoutY(sprite.getLayoutY() + movementVariable);
                }
            }

            if(aPressed.get()){
                if (((sprite.getLayoutX() < 470 || 1138 < sprite.getLayoutX()) && sprite.getLayoutY() < 144 && -12 < sprite.getLayoutX() ) || sprite.getLayoutY() > 144 && -12 < sprite.getLayoutX()) {
                    System.out.println(" X : " + sprite.getLayoutX() + " Y : " + sprite.getLayoutY());
                    sprite.setLayoutX(sprite.getLayoutX() - movementVariable);
                }
            }

            if(dPressed.get()){
                if (((sprite.getLayoutX() < 464 || 900 < sprite.getLayoutX()) && sprite.getLayoutY() < 144 && 1832 > sprite.getLayoutX() ) || sprite.getLayoutY() > 144 && 1832 > sprite.getLayoutX()) {
                    System.out.println(" X : " + sprite.getLayoutX() + " Y : " + sprite.getLayoutY());
                    sprite.setLayoutX(sprite.getLayoutX() + movementVariable); 
                }
            }
        }
    };

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
    
    @FXML
    void OpenSpriteInventory() {
        try {
            // Charger le fichier FXML qui définit la fenêtre
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PageInventairePersonnage.fxml"));
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
    	try {
    		if(PagePersonnageController.player.getInventory().getArgentJoueur() >= 200) {
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("PageCoffre.fxml"));
                Parent root = loader.load();
                
                Scene scene = new Scene(root, 930, 580);
                
                Stage stage = new Stage();
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.setResizable(false);
                stage.show();
    		}
    		else {
    			Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Coins insuffisants");
                alert.setHeaderText(null);
                alert.setContentText("Vous devez avoir 200 coins pour ouvir le coffre !");
                alert.showAndWait();
    		}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void interactionMarchandButton(ActionEvent evt) {
		lancerXML("PageEchangeMarchand.fxml");
    }
}
