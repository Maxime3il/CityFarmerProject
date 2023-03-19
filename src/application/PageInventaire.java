package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PageInventaire {

	@FXML
    private ImageView carotte;

    @FXML
    private Button closeButton;

    @FXML
    private Label nbCarotte;

    @FXML
    private Label nbLait;

    @FXML
    private Label nbSteak;

    @FXML
    private ImageView steak;

    @FXML
    void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
