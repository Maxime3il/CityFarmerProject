package application;

import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.animation.PauseTransition;
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
	private MediaView mediaView;
	private MediaPlayer mediaPlayer;
	private Media media;

	@FXML
	private Button BoutonPersonnage;

	@FXML
	private void redirectPersonnage(ActionEvent event) {
		lancerXML("PagePersonnage.fxml");
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
		lancerXML("PageJouer.fxml");
	}

	@FXML
	private Slider volumeSlider;

	@FXML
	private Button couperSonMusique;
	
	@FXML
	private Button closeButton;

	@FXML
	private Button btnActiverDesactiverSon;

	private boolean jouerSonActif = true;

	@FXML
	private void activerDesactiverSon(ActionEvent event) {
	    jouerSonActif = !jouerSonActif;	    
	    if (!jouerSonActif) {
	        // Si le son est désactivé, on ajouter la classe CSS "muted" au bouton btnActiverDesactiverSon
	    	btnActiverDesactiverSon.getStyleClass().add("muted");
	    } else {
	        // Si le son est activé, on retire la classe CSS "muted" du bouton btnActiverDesactiverSon
	    	btnActiverDesactiverSon.getStyleClass().remove("muted");
	    }
	}
	
	private void lancerXML(String url) {
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
			System.out.println("Impossible de charger la fenÃªtre");
		}
	}

	private void jouerSon(String path) {
		Media media = new Media(new File(path).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String path = new File("src/Video/TitleScreen.mp4").getAbsolutePath();
		media = new Media(new File(path).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaView.setMediaPlayer(mediaPlayer);
		mediaView.setFitWidth(1920);
		mediaView.setFitHeight(1080);
		mediaPlayer.setVolume(0.2);
		volumeSlider.setMin(0);
		volumeSlider.setMax(1);
		volumeSlider.setValue(0.2);
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

		    // Ajouter un EventHandler qui sera déclenché après la pause
		    pause.setOnFinished(event -> {
		        // Rendre les boutons visibles
		        BoutonPersonnage.setVisible(true);
		        BtnParam.setVisible(true);
		        BtnJouer.setVisible(true);
		    });
		    // Lancer la pause
		    pause.play();
		    
		BtnJouer.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (jouerSonActif) {
					jouerSon("C:\\Users\\a.ruiz\\eclipse-workspace\\CityFarmer\\src\\Audio\\Personnage.mp3");
				}
			}
		});
		BtnParam.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (jouerSonActif) {
					jouerSon("C:\\Users\\a.ruiz\\eclipse-workspace\\CityFarmer\\src\\Audio\\Personnage.mp3");
				}
			}
		});
		BoutonPersonnage.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (jouerSonActif) {
					jouerSon("C:\\Users\\a.ruiz\\eclipse-workspace\\CityFarmer\\src\\Audio\\Personnage.mp3");
				}
			}
		});
		couperSonMusique.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (jouerSonActif) {
					jouerSon("C:\\Users\\a.ruiz\\eclipse-workspace\\CityFarmer\\src\\Audio\\Personnage.mp3");
				}
			}
		});
		closeButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (jouerSonActif) {
					jouerSon("C:\\Users\\a.ruiz\\eclipse-workspace\\CityFarmer\\src\\Audio\\Personnage.mp3");
				}
			}
		});
	}

	@FXML
	void close() {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}

}
