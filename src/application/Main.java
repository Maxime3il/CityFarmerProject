/**
* La classe Main est la classe principale de l'application CityFarmer. 
* Elle étend la classe Application de JavaFX.
*/

package application;

import java.io.File;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	/**
	 * Cette méthode est appelée au lancement de l'application et permet de configurer la fenêtre principale.
	 * Elle charge également la page d'accueil de l'application.
	 * @param primaryStage La fenêtre principale de l'application
	 */
    @Override
    public void start(Stage primaryStage) {
        try {
            String musicFile = "src/Audio/lancementJeu.mp3";
            Media sound = new Media(new File(musicFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
            BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("PageAccueil.fxml"));
            Scene scene = new Scene(root,1920,1080);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            // Configurer la sc�ne
            primaryStage.setTitle("CityFarmer");
            primaryStage.setOnCloseRequest(event -> {
                event.consume();
            });
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * La méthode main permet de lancer l'application.
     * @param args Les arguments passés en ligne de commande
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * La méthode init est appelée avant que l'application ne démarre.
     * @throws Exception Exception levée en cas d'erreur lors de l'initialisation
     */
    @Override
    public void init() throws Exception {
        // TODO Auto-generated method stub
        super.init();
    }

    /**
     * La méthode stop est appelée lorsque l'application est fermée.
     * @throws Exception Exception levée en cas d'erreur lors de la fermeture
     */
    @Override
    public void stop() throws Exception {
        // TODO Auto-generated method stub
        super.stop();
    }
}