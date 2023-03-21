package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Inventory;
import model.Item;

public class PageInventaireController {

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
    public void initialize() {
        int nbPorc = PagePersonnageController.player.getInventory().contains("porc").getCount();
        nbSteak.setText(String.valueOf(nbPorc));
        
        int nbCarottes = PagePersonnageController.player.getInventory().contains("carotte").getCount();
        nbCarotte.setText(String.valueOf(nbCarottes));
        
        int nbLaits = PagePersonnageController.player.getInventory().contains("lait").getCount();
        nbLait.setText(String.valueOf(nbLaits));
    }


    @FXML
    void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
