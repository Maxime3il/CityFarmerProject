package application;

import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.event.ActionEvent;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class PageAccueilController implements Initializable {
    @FXML
    private MediaView mediaView;
    private MediaPlayer mediaPlayer;
    private Media media;
    
    private void lancerXML(String url) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root1, 1080, 600);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.out.println("Impossible de charger la fenêtre");
        }
    }

	
	@FXML
	private Button BoutonPersonnage;

    @FXML
    private void redirectPersonnage(ActionEvent event) {
        lancerXML("PagePersonnage.fxml");
    }
    
    

    @FXML
    private Slider volumeSlider;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String path = new File("src/Video/TitleScreen.mp4").getAbsolutePath();
        media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
        mediaView.setFitWidth(1080);
        mediaView.setFitHeight(800);
        mediaPlayer.setVolume(0.5);

        volumeSlider.setMin(0);
        volumeSlider.setMax(1);
        volumeSlider.setValue(0.5);

        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            mediaPlayer.setVolume(newValue.doubleValue());
        });
    }
    
    
    @FXML
	private Button closeButton;
    
    @FXML
    void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
