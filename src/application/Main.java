package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
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