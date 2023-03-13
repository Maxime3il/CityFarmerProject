package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PageJouerController {
	@FXML
	private Button closeButton;
	@FXML
    void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
	
	public static void moveUp() {
		System.out.println("haut");
	}
	
	public static void moveDown() {
		System.out.println("bas");
	}
	
	public static void moveLeft() {
		
		System.out.println("gauche");
	}
	
	public static void moveRight() {
		System.out.println("droite");
	}
}
