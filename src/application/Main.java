package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

    /**
     * Cette méthode initialise et affiche la page d'accueil de l'application.
     * Elle charge le fichier fxml correspondant à la page d'accueil,
     * définit le style de la fenêtre, configure la scène et affiche la fenêtre.
     *
     * @param primaryStage La fenêtre principale de l'application.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("PageAccueil.fxml"));
            Scene scene = new Scene(root, 1920, 1080);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            // Configurer la sc�ne
            primaryStage.setTitle("CityFarmer");
            primaryStage.setOnCloseRequest(this::handleCloseRequest);

            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Cette méthode est appelée lorsque l'utilisateur tente de fermer la fenêtre.
     * Elle consomme l'événement pour empêcher la fermeture de la fenêtre.
     *
     * @param event L'événement de fermeture de fenêtre.
     */
    private void handleCloseRequest(WindowEvent event) {
        event.consume();
    }

    /**
     * La méthode main() est la méthode principale de l'application.
     * Elle appelle la méthode launch() de la classe Application pour lancer l'application.
     *
     * @param args Les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        launch(args);
    }

}