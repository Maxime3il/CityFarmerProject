package application;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
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
	
	private static final Logger logger = Logger.getLogger(PagePersonnageController.class.getName());

	
	private int currentGenderSelection = 0;
	private boolean jouerSonActif = true;
	public Gender currentGender = Gender.HOMME;
	
	private String homme = "Homme";
	private String femme = "Femme";
	private String autre = "Autre";

	
	private int indice = 0;
	private String[] personnages;
	private String[] personnagesHommes = { "../Images/sprite5.png", "../Images/sprite6.png" };
	private String[] personnagesFemmes = { "../Images/sprite1.png", "../Images/sprite4.png" };
	private String[] personnagesAutre = { "../Images/sprite2.png", "../Images/sprite1.png", "../Images/sprite4.png", "../Images/sprite5.png", "../Images/sprite6.png" };
	@FXML
	private BorderPane scene;
	@FXML
	private ImageView myImageView;
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
	
	/**
	 * Joue un son qu'on lui donne en paramètres
	 * @param path
	 * @author Iulian GAINAR
	 */
	private void jouerSon(String path) {
		MediaPlayerSingleton.getInstance().jouerSon(path);
	}
	
	/**
	 * Permet de lancer la vue XML
	 * @param url
	 * @author Iulian GAINAR
	 */
	 private void lancerXML(String url) {
	        try {
	            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
	            Parent root1 = (Parent) fxmlLoader.load();
	            Stage stage = new Stage();
	            stage.setOnCloseRequest(Event::consume);
	            stage.initStyle(StageStyle.UNDECORATED);
	            Scene scene1 = new Scene(root1, 1920, 1080);
	            stage.setScene(scene1);
	            stage.setResizable(false);
	            stage.show();
	        } catch (Exception e) {
	            logger.log(Level.SEVERE, "Impossible de charger la fenêtre", e);
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
	
	/**
	 * Initialise la logique de la page
	 * @author Iulian GAINAR
	 */
	@FXML
	public void initialize() {
	    if (jouerSonActif) {
	        jouerSon("src/Audio/MessagePagePersonnage.mp3");
	    }
	    currentKeyBinding();
	    addFocusListeners();
	    
	    myComboBox.getItems().addAll(homme, femme, autre);
	    myComboBox.setValue(homme);
	    personnages = personnagesHommes;
	    afficherImage();
	    
	    Map<String, Runnable> genderHandlers = new HashMap<>();
	    genderHandlers.put(homme, () -> {
	        personnages = personnagesHommes;
	        currentGender = Gender.HOMME;
	        jouerSon("src/Audio/Homme.mp3");
	    });
	    genderHandlers.put(femme, () -> {
	        personnages = personnagesFemmes;
	        currentGender = Gender.FEMME;
	        jouerSon("src/Audio/Femme.mp3");
	    });
	    genderHandlers.put(autre, () -> {
	        personnages = personnagesAutre;
	        currentGender = Gender.AUTRE;
	        jouerSon("src/Audio/Autre.mp3");
	    });
	    
	    myComboBox.setOnAction((ActionEvent event) -> {
	        String selectedValue = myComboBox.getValue();
	        genderHandlers.getOrDefault(selectedValue, () -> {}).run();
	        afficherImage();
	    });
	}

	/**
	 * Ajoute des ecouteurs sur tous les boutons
	 */
	private void addFocusListeners() {
	    addFocusListener(inputPrenom, "src/Audio/Prenom.mp3");
	    addFocusListener(inputNom, "src/Audio/Nom.mp3");
	    addFocusListener(inputFerme, "src/Audio/NomFerme.mp3");
	    addFocusListener(closeButton, "src/Audio/Quitter.mp3");
	    addFocusListener(validatePerso, "src/Audio/Valider.mp3");
	}
	
	
	private void addFocusListener(Control control, String audioPath) {
	    control.focusedProperty().addListener((observable, oldValue, newValue) -> {
	        if (Boolean.TRUE.equals(newValue) && jouerSonActif) {
	            jouerSon(audioPath);
	        }
	    });
	}
	
	/**
	 * Ajoute des ecouteurs sur les touches avec lesquelles l'utilisateur peut interagir
	 */
	private void currentKeyBinding() {
	    scene.setOnKeyPressed(e -> {
	        if (e.isControlDown()) {
	            switch (e.getCode()) {
	                case P -> inputPrenom.requestFocus();
	                case N -> inputNom.requestFocus();
	                case F -> inputFerme.requestFocus();
	                case G -> handleGenderSelection();
	                case Q -> handleQuit();
	                case R -> handleAudio("src/Audio/MessagePagePersonnage.mp3");
	                case J -> valider(null);
	                default -> {}
	            }
	        }
	    });
	}

	/**
	 * Gère la sélection de genre du personnage
	 */
	private void handleGenderSelection() {
	    currentGenderSelection = (currentGenderSelection + 1) % myComboBox.getItems().size();
	    myComboBox.getSelectionModel().select(currentGenderSelection);
	    String selectedValue = myComboBox.getValue();
	    switch (selectedValue) {
	        case "Homme" -> {
	            personnages = personnagesHommes;
	            currentGender = Gender.HOMME;
	            handleAudio("src/Audio/Homme.mp3");
	        }
	        case "Femme" -> {
	            personnages = personnagesFemmes;
	            currentGender = Gender.FEMME;
	            handleAudio("src/Audio/Femme.mp3");
	        }
	        default -> {
	            personnages = personnagesAutre;
	            currentGender = Gender.AUTRE;
	            handleAudio("src/Audio/Autre.mp3");
	        }
	    }
	    afficherImage();
	}

	/**
	 * Gère la fermeture de la page
	 */
	private void handleQuit() {
	    handleAudio("src/Audio/Quitter.mp3");
	    this.close();
	}

	/**
	 * Gere l'audio de la page
	 * @param audioPath
	 */
	private void handleAudio(String audioPath) {
	    if (jouerSonActif) {
	        jouerSon(audioPath);
	    }
	}

	/**
	 * Methode pour l'affichage des modeles (lorsqu'il appuie sur fleche droite)
	 * @param event
	 */
	@FXML
	public void handleNextButton(MouseEvent event) {
	    indice++;
	    if (indice >= personnages.length) {
	        indice = 0;
	    }
	    afficherImage();
	}
	/**
	 * Methode pour l'affichage des modeles (lorsqu'il appuie sur sur fleche gauche)
	 * @param event
	 */
	@FXML
	public void handlePreviousButton(MouseEvent event) {
	    indice--;
	    if (indice < 0) {
	        indice = personnages.length - 1;
	    }
	    afficherImage();
	}

	/**
	 * Permet d'afficher l'image du modèle de personnage
	 */
	private void afficherImage() {
	    Image image = new Image(getClass().getResourceAsStream(personnages[indice]));
	    myImageView.setImage(image);
	}

	/**
	 * Definit le skin du personnage
	 * @return le skin du personnage
	 */
	private String skin() {
	    Image image = new Image(getClass().getResourceAsStream(personnages[indice]));
	    myImageView.setImage(image);
	    return personnages[indice];
	}
	
	
	/**
	 * Gère la logique du bouton Valider
	 * @param event
	 */
	@FXML
	public void valider(ActionEvent event) {
	    String nom = inputNom.getText();
	    String prenom = inputPrenom.getText();
	    String nomFerme = inputFerme.getText();
	    String skin = skin();
	    // si des champs sont vides on affiche un message d'erreur
	    if(nom.isEmpty() || prenom.isEmpty() || nomFerme.isEmpty()){
	    	if (jouerSonActif) {
    			jouerSon("src/Audio/ChampsVidesPopUp.mp3");
    		}
    		Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Un ou plusieurs champs sont vides");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait(); 
            
	    }else {
	    	lancerPartie(nom, prenom, nomFerme, skin);
	    }

	    
	}
	
	/**
	 * Methode qui lance une nouvelle partie (gère la création de personnage et tout ce qui concerne une nouvelle partie)
	 * @param nom
	 * @param prenom
	 * @param nomFerme
	 * @param skin
	 */
	public void lancerPartie(String nom, String prenom, String nomFerme, String skin) {
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
