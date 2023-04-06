/**
*	Contrôleur pour la page de création de personnage.
*	Permet à l'utilisateur de choisir le sexe, le nom, le prénom, le nom de sa ferme et son apparence.
*	Crée un objet joueur avec ces informations et un inventaire pour le joueur et le marchand.
*/

package application;

import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
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

	/**
	 * URL actuelle.
	 */
	private String currentUrl;

	/**
	 * Charge le fichier fxml spécifié dans une nouvelle fenêtre.
	 *
	 * @param url l'emplacement du fichier fxml à charger.
	 */
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
	 * Ferme la fenêtre actuelle.
	 */
	@FXML
	private Button closeButton;

	@FXML
	void close() {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}

	/**
	 * Sexe actuel du personnage.
	 */
	public Gender currentGender = Gender.Homme;

	@FXML
	private ComboBox<String> myComboBox;

	/**
	 * Initialise la ComboBox avec les choix de sexe et affiche l'image
	 * correspondante.
	 */
	@FXML
	public void initialize() {
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
				break;
			case "Femme":
				personnages = personnagesFemmes;
				currentGender = Gender.Femme;
				break;
			case "Autre":
			default:
				personnages = personnagesAutre;
				currentGender = Gender.Autre;
				break;
			}
			afficherImage();
		});
	}

	/**
	 * Tableau contenant les URL des images pour chaque personnage.
	 */
	private String[] personnages;
	private String[] personnagesHommes = { "../Images/sprite5.png", "../Images/sprite6.png" };
	private String[] personnagesFemmes = { "../Images/sprite1.png", "../Images/sprite4.png" };
	private String[] personnagesAutre = { "../Images/sprite2.png", "../Images/sprite1.png", "../Images/sprite4.png",
			"../Images/sprite5.png", "../Images/sprite6.png" };

	@FXML
	private ImageView MyImageView;

	@FXML
	private ImageView nextButton;

	@FXML
	private ImageView previousButton;

	private int indice = 0;

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

	@FXML
	private Button validatePerso;

	@FXML
	private TextField inputNom;

	@FXML
	private TextField inputPrenom;

	@FXML
	private TextField inputFerme;

	// Création du joueur dans le modèle
	public static Player player;

	// Création de l'inventaire du marchand
	public static Inventory inventaireMarchand;

	/**
	 * Retourne le nom du fichier d'image correspondant à l'indice actuel.
	 * 
	 * @return le nom du fichier d'image du personnage
	 */
	private String Skin() {
		Image image = new Image(getClass().getResourceAsStream(personnages[indice]));
		MyImageView.setImage(image);
		return personnages[indice];
	}

	/**
	 * Crée un joueur avec les informations saisies dans les champs du formulaire.
	 * Si un champ est vide, une alerte est affichée et la méthode se termine.
	 * Initialise l'inventaire du joueur avec trois objets. Initialise également
	 * l'inventaire du marchand avec trois objets. Lance la page de jeu et ferme
	 * automatiquement cette page.
	 * 
	 * @param event l'événement ActionEvent qui déclenche la méthode
	 */
	@FXML
	public void valider(ActionEvent event) {
		String nom = inputNom.getText();
		String prenom = inputPrenom.getText();
		String nomFerme = inputFerme.getText();
		String skin = Skin();
		if (nom.isEmpty() || prenom.isEmpty() || nomFerme.isEmpty()) {
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

		// Initialisation du joueur
		player = new Player(prenom, nom, currentGender, skin, nomFerme, 1, 1, inventairePlayer);

		//
		inventaireMarchand = marchandInventaire;

		// Lancement de la page du jeu
		lancerXML("PageJouer.fxml");

		// Close automatiquement cette page
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}
}
