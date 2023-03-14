package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
	    myComboBox.getItems().addAll("Homme", "Femme","Autre");
	    for (int i = 0; i <= 100; i++) {
	        myComboBoxAge.getItems().add(Integer.toString(i));
	    }
	}
	
}
