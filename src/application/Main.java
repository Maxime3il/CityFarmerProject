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
    @Override
    public void start(Stage primaryStage) {
        try {
            // Cr�er l'objet Media � partir du fichier MP3
            String musicFile = "src/Audio/Personnage.mp3";
            Media sound = new Media(new File(musicFile).toURI().toString());

            // Cr�er l'objet MediaPlayer pour lire le fichier MP3
            MediaPlayer mediaPlayer = new MediaPlayer(sound);

            // Jouer le fichier MP3
            mediaPlayer.play();

            // Charger votre interface utilisateur � partir du fichier FXML
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


    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void init() throws Exception {
        // TODO Auto-generated method stub
        super.init();
    }

    @Override
    public void stop() throws Exception {
        // TODO Auto-generated method stub
        super.stop();
    }
}