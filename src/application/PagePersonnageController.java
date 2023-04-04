package application;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Gender;
import model.Inventory;
import model.Item;
import model.Player;

public class PagePersonnageController {

	// parametres
	
	private String currentUrl;
	private boolean jouerSonActif = true;
	public Gender currentGender = Gender.Homme;
	private int indice = 0;
	private String[] personnages;
	private String[] personnagesHommes = { "../Images/sprite5.png", "../Images/sprite6.png" };
	private String[] personnagesFemmes = { "../Images/sprite1.png", "../Images/sprite4.png" };
	private String[] personnagesAutre = { "../Images/sprite2.png", "../Images/sprite1.png", "../Images/sprite4.png", "../Images/sprite5.png", "../Images/sprite6.png" };
	@FXML
	private BorderPane scene;
	@FXML
	private ImageView MyImageView;
	@FXML
    private ImageView nextButton;
	@FXML
    private ImageView previousButton;
	@FXML
	private Button closeButton;
	@FXML
	private ComboBox<String> myComboBox;
	@FXML
    private Button validatePerso;
	
	@FXML
    private TextField inputNom;
	
	@FXML
    private TextField inputPrenom;
	
	@FXML
    private TextField inputFerme;
	
	//Création du joueur dans le modèle
	public static Player player;
	
	//Création de l'inventaire du marchand
	public static Inventory inventaireMarchand;
	
	
	// methodes
	
	private void jouerSon(String path) {
		MediaPlayerSingleton.getInstance().jouerSon(path);
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

	/**
	 * Ferme la page courante
	 * @author Iulian GAINAR
	 */
	@FXML
	void close() {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}

	
	@FXML
	public void initialize() {
		
		// Si le son est actif, on reproduit le son de la page
		if (jouerSonActif) {
			jouerSon("src/Audio/MessagePagePersonnage.mp3");
		}
		
		// on ecoute les touches du clavier et on réagit
		currenKeyBinding();
		// gere le focus sur les différents champs afin de lancer le son 
		inputPrenom.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue) {
				if (jouerSonActif) {
					jouerSon("src/Audio/Prenom.mp3");
				}
			}
		});
		inputNom.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue) {
				if (jouerSonActif) {
					jouerSon("src/Audio/Nom.mp3");
				}
			}
		});
		inputFerme.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue) {
				if (jouerSonActif) {
					jouerSon("src/Audio/NomFerme.mp3");
				}
			}
		});
		closeButton.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue) {
				if (jouerSonActif) {
					jouerSon("src/Audio/Quitter.mp3");
				}
			}
		});
		validatePerso.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue) {
				if (jouerSonActif) {
					jouerSon("src/Audio/Valider.mp3");
				}
			}
		});

	    myComboBox.getItems().addAll("Homme", "Femme", "Autre");
	    myComboBox.setValue("Homme");

	    personnages = personnagesHommes;
	    afficherImage();

	    myComboBox.setOnAction((ActionEvent event) -> {
	        String selectedValue = myComboBox.getValue();
	        switch (selectedValue) {
	            case "Homme":
	                personnages = personnagesHommes;
	                currentGender = Gender.Homme;
	        		if (jouerSonActif) {
	        			jouerSon("src/Audio/Homme.mp3");
	        		}
	                break;
	            case "Femme":
	                personnages = personnagesFemmes;
	                currentGender = Gender.Femme;
	        		if (jouerSonActif) {
	        			jouerSon("src/Audio/Femme.mp3");
	        		}
	                break;
	            case "Autre":
	            	default :
	                personnages = personnagesAutre;
	                currentGender = Gender.Autre;
	        		if (jouerSonActif) {
	        			jouerSon("src/Audio/Autre.mp3");
	        		}
	                break;
	        }
	        afficherImage();
	    });
	}
	
    /**
     * Détermine les touches clavier entrées par l'utilisateur
     * Si la touche correspond à une action alors elle sera exécutée.
     */
	private void currenKeyBinding() {
		scene.setOnKeyPressed(e -> {
			if (e.isControlDown() && e.getCode() == KeyCode.P) {
				inputPrenom.requestFocus();
			}
			if (e.getCode() == KeyCode.N) {
				inputNom.requestFocus();
			}
			if (e.getCode() == KeyCode.F) {
				inputFerme.requestFocus();
			}
			if (e.getCode() == KeyCode.X) {
				Platform.exit();
			}
		});
	}

	@FXML
	public void handleNextButton(MouseEvent event) {
	    indice++;
	    if (indice >= personnages.length) {
	        indice = 0;
	    }
	    afficherImage();
	}

	@FXML
	public void handlePreviousButton(MouseEvent event) {
	    indice--;
	    if (indice < 0) {
	        indice = personnages.length - 1;
	    }
	    afficherImage();
	}


	private void afficherImage() {
	    Image image = new Image(getClass().getResourceAsStream(personnages[indice]));
	    currentUrl = personnages[indice];
	    MyImageView.setImage(image);
	}

	
	

	private String Skin() {
	    Image image = new Image(getClass().getResourceAsStream(personnages[indice]));
	    MyImageView.setImage(image);
	    return personnages[indice];
	}
	
	@FXML
	public void valider(ActionEvent event) {
	    String nom = inputNom.getText();
	    String prenom = inputPrenom.getText();
	    String nomFerme = inputFerme.getText();
	    String skin = Skin();
	    if(nom.isEmpty() || prenom.isEmpty() || nomFerme.isEmpty()){
	    	if (jouerSonActif) {
    			jouerSon("src/Audio/ChampsVides.mp3");
    		}
	    	Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Un ou plusieurs champs sont vides");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
                
    		
            return;
	    }

	    Item carotteItemPlayer = new Item("carotte", 3, 10);
	    Item laitItemPlayer = new Item("lait", 7, 10);
	    Item porcItemPlayer = new Item("porc", 2, 10);
	    
	    Inventory inventairePlayer = new Inventory();
	    Inventory marchandInventaire = new Inventory();

	    Item carotteItemMarchand = new Item("carotte", 3, 50);
	    Item laitItemPlayerMarchand = new Item("lait", 7, 50);
	    Item porcItemPlayerMarchand = new Item("porc", 2, 50);
	    
	    inventairePlayer.addItem(porcItemPlayer);
	    inventairePlayer.addItem(laitItemPlayer);
	    inventairePlayer.addItem(carotteItemPlayer);
	    
	    marchandInventaire.addItem(carotteItemMarchand);
	    marchandInventaire.addItem(laitItemPlayerMarchand);
	    marchandInventaire.addItem(porcItemPlayerMarchand);
	    
		//Initialisation du joueur
	    player = new Player(prenom, nom, currentGender, skin,nomFerme ,  1, 1, inventairePlayer);
	    
	    //
	    inventaireMarchand = marchandInventaire;
	    
	    //Lancement de la page du jeu
	    lancerXML("PageJouer.fxml");
	    
	    //Close automatiquement cette page
	    Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}
}
