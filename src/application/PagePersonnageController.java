package application;

import javafx.scene.input.MouseEvent;


import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Gender;
import model.Inventory;
import model.Player;

public class PagePersonnageController {
	
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
	                currentGender = Gender.FEMALE;
	                break;
	            case "Autre":
	            	default :
	                personnages = personnagesAutre;
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
	        System.out.println("Veuillez remplir tous les champs.");
	        return;
	    }
	    player = new Player(prenom, nom, currentGender, skin,  1, 1, new Inventory());
//	    System.out.println(PagePersonnageController.player.getSkin());
	    lancerXML("PageJouer.fxml");
	}
}
