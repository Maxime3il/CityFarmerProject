/**
*	Contrôleur de la page Paramètres pour l'application.
*/
package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PageParametreController {
	/**
	 * Bouton pour fermer la fenêtre des paramètres.
	 */
	@FXML
	private Button closeButton;

	/**
	 * Ferme la fenêtre des paramètres lorsque le bouton closeButton est cliqué.
	 */
	@FXML
	void close() {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}

}
