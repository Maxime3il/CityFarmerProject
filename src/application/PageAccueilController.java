package application;

import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.animation.PauseTransition;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

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
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;

public class PageAccueilController implements Initializable {
	
	@FXML
	private BorderPane scene;
	
    @FXML
    private MediaView mediaView;
    private MediaPlayer mediaPlayer;
    private Media media;
    
    private PageJouerController jouerController = new PageJouerController();
    
    public void lancerXML(String url) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            stage.setOnCloseRequest(event -> {
                event.consume();
            });

            stage.initStyle(StageStyle.UNDECORATED);

            Scene scene = new Scene(root1, 1920, 1080);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.out.println("Impossible de charger la fenêtre");
        }
    }
    
    @FXML
	private Button BtnParam;
    @FXML
    private void redirectParam(ActionEvent event) {
        lancerXML("PageParametre.fxml");
    }
    
    @FXML
   	private Button BtnJouer;
    @FXML
    private void redirectJouer(ActionEvent evt) {
        lancerXML("PagePersonnage.fxml");    
    }
    @FXML
    private Slider volumeSlider;
    
    @FXML
    private Button couperSonMusique;

	@FXML
	private Button btnActiverDesactiverSon;

	private boolean jouerSonActif = true;

	@FXML
	private void activerDesactiverSon(ActionEvent event) {
	    jouerSonActif = !jouerSonActif;	    
	    if (!jouerSonActif) {
	        // Si le son est d�sactiv�, on ajouter la classe CSS "muted" au bouton btnActiverDesactiverSon
	    	btnActiverDesactiverSon.getStyleClass().add("muted");
	    } else {
	        // Si le son est activ�, on retire la classe CSS "muted" du bouton btnActiverDesactiverSon
	    	btnActiverDesactiverSon.getStyleClass().remove("muted");
	    }
	}
	
	private void jouerSon(String path) {
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	makeMovable(scene);
    	String path = new File("src/Video/TitleScreen.mp4").getAbsolutePath();
		media = new Media(new File(path).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaView.setMediaPlayer(mediaPlayer);
		mediaView.setFitWidth(1920);
		mediaView.setFitHeight(1080);
		mediaPlayer.setVolume(0.1);
		volumeSlider.setMin(0);
		volumeSlider.setMax(1);
		volumeSlider.setValue(0.1);
		volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
			mediaPlayer.setVolume(newValue.doubleValue());
		});
		mediaPlayer.setOnEndOfMedia(() -> {
			mediaPlayer.seek(Duration.ZERO);
		});
		couperSonMusique.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (mediaPlayer.isMute()) {
					mediaPlayer.setMute(false);
					couperSonMusique.getStyleClass().remove("muted");
				} else {
					mediaPlayer.setMute(true);
					couperSonMusique.getStyleClass().add("muted");
				}
			}
		});
		mediaPlayer.setAutoPlay(true);
		
		 PauseTransition pause = new PauseTransition(Duration.seconds(5));

		    // Ajouter un EventHandler qui sera d�clench� apr�s la pause
		    pause.setOnFinished(event -> {
		        BtnParam.setVisible(true);
		        BtnJouer.setVisible(true);
		    });
		    // Lancer la pause
		    pause.play();
		    
		BtnJouer.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (jouerSonActif) {
					jouerSon("src/Audio/Personnage.mp3");
				}
			}
		});
		BtnParam.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (jouerSonActif) {
					jouerSon("src/Audio/Personnage.mp3");
				}
			}
		});
		couperSonMusique.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (jouerSonActif) {
					jouerSon("src/Audio/Personnage.mp3");
				}
			}
		});
		btnActiverDesactiverSon.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (jouerSonActif) {
					jouerSon("src/Audio/Personnage.mp3");
				}
			}
		});
		closeButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (jouerSonActif) {
					jouerSon("src/Audio/Personnage.mp3");
				}
			}
		});
        mediaPlayer.setAutoPlay(true);
}

    @FXML
	private Button closeButton;
    
    @FXML
    void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    private void movementSetup(){
		scene.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.J) {
		        lancerXML("PagePersonnage.fxml");    
			}
			if(e.getCode() == KeyCode.P) {
		        lancerXML("PageParametre.fxml");    
			}
		});
	}
    
    public void makeMovable(BorderPane scene){
		this.scene = scene;
		movementSetup();
	}
}
