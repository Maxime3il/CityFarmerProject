package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PageCoffre {

	/**
	 * Bouton pour fermer la fenêtre.
	 */
	@FXML
	private Button closeButton;

	/**
	 * Label pour afficher le texte de fin de la page.
	 */
	@FXML
	private Label TextFin;

	/**
	 * Cette méthode est appelée lorsque l'utilisateur clique sur le bouton Fermer.
	 * Elle ferme la fenêtre en cours.
	 */
	@FXML
	void close() {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}
}
