package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * La classe PageCoffre contrôle la fenêtre du coffre de l'application.
 */

public class PageCoffre {

    /**
     * Le bouton pour fermer la fenêtre du coffre.
     */
    @FXML
    private Button closeButton;

    /**
     * Le texte affiché lorsque le coffre est ouvert.
     */
    @FXML
    private Label textFin;

    /**
     * Ferme la fenêtre du coffre lorsque le bouton est cliqué.
     */
    @FXML
    void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
