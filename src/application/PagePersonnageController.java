package application;

import javafx.scene.input.MouseEvent;


import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Gender;
import model.Inventory;
import model.Player;

public class PagePersonnageController {
	@FXML
	private Button closeButton;
	@FXML
	void close() {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}

	public Gender currentGender = Gender.MALE;
	
	@FXML
	private ComboBox<String> myComboBox;
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
	                currentGender = Gender.MALE;
	                break;
	            case "Femme":
	                personnages = personnagesFemmes;
	                System.out.println(currentGender);
	                currentGender = Gender.FEMALE;
	                break;
	            case "Autre":
	            	default :
	                personnages = personnagesAutre;
	                System.out.println(currentGender);
	                currentGender = Gender.OTHER;
	                break;
	        }
	        afficherImage();
	    });
	}

	private String[] personnages;
	private String[] personnagesHommes = { "../Images/sprite5.png", "../Images/sprite6.png" };
	private String[] personnagesFemmes = { "../Images/sprite1.png", "../Images/sprite4.png" };
	private String[] personnagesAutre = { "../Images/sprite2.png", "../Images/sprite1.png", "../Images/sprite4.png", "../Images/sprite5.png", "../Images/sprite6.png" };

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
	
	public static Player player;
	
	@FXML
	public void valider(ActionEvent event) {
	    String nom = inputNom.getText();
	    String prenom = inputPrenom.getText();
	    String nomFerme = inputFerme.getText();
	    if(nom.isEmpty() || prenom.isEmpty() || nomFerme.isEmpty()){
	        System.out.println("Veuillez remplir tous les champs.");
	        return;
	    }
	    
	    player = new Player(prenom, nom, Gender.MALE, 1, 1, new Inventory());
	    System.out.println(PagePersonnageController.player.getName());
	    player.setGender(currentGender);
	    Stage stage = (Stage) closeButton.getScene().getWindow();
	    stage.close();
	}
}
