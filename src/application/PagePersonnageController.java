package application;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PagePersonnageController {
	@FXML
	private Button closeButton;
	@FXML
	void close() {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	private ComboBox<String> myComboBox;

	@FXML
	private ComboBox<String> myComboBoxAge;

	@FXML
	public void initialize() {
	    myComboBox.getItems().addAll("Homme", "Femme", "Autre");
	    myComboBox.setValue("Homme");

	    // Afficher par défaut les images du tableau personnagesHommes
	    personnages = personnagesHommes;
	    afficherImage();

	    for (int i = 0; i <= 100; i++) {
	        myComboBoxAge.getItems().add(Integer.toString(i));
	    }

	    myComboBox.setOnAction((event) -> {
	        String selectedValue = myComboBox.getValue();
	        switch (selectedValue) {
	            case "Homme":
	                personnages = personnagesHommes;
	                break;
	            case "Femme":
	                personnages = personnagesFemmes;
	                break;
	            case "Autre":
	            default:
	                personnages = personnagesAutre;
	                break;
	        }
	        afficherImage();
	    });
	}

	private String[] personnages;
	private String[] personnagesHommes = { "../Images/sprite5.png", "../Images/sprite6.png" };
	private String[] personnagesFemmes = { "../Images/sprite1.png", "../Images/sprite3.png", "../Images/sprite4.png" };
	private String[] personnagesAutre = { "../Images/sprite2.png" };

	@FXML
	private ImageView MyImageView;

	@FXML
	private Button nextButton;

	@FXML
	private Button previousButton;

	private int indice = 0;

	@FXML
	public void handleNextButton(ActionEvent event) {
	    indice++;
	    if (indice >= personnages.length) {
	        indice = 0;
	    }
	    afficherImage();
	}

	@FXML
	public void handlePreviousButton(ActionEvent event) {
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




}
