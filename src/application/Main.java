package application;

import java.io.File;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
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
            primaryStage.setOnCloseRequest(this::handleCloseRequest);

            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void handleCloseRequest(WindowEvent event) {
        event.consume();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}