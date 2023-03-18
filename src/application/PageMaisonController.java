package application;

import javafx.animation.AnimationTimer;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PageMaisonController {

	@FXML
	private Button closeButton;
	@FXML
	void close() {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	private ImageView sprite;

	@FXML
	private BorderPane scene;

	@FXML
	public void initialize() {
		makeMovable(sprite, scene);
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
			System.out.println("Impossible de charger la fenêtre");
		}
	}

	private void movementSetup(){
		scene.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.Z) {
				wPressed.set(true);
			}

			if(e.getCode() == KeyCode.Q) {
				aPressed.set(true);
			}

			if(e.getCode() == KeyCode.S) {
				sPressed.set(true);
			}

			if(e.getCode() == KeyCode.D) {
				dPressed.set(true);
			}
		});

		scene.setOnKeyReleased(e ->{
			if(e.getCode() == KeyCode.Z) {
				wPressed.set(false);
			}

			if(e.getCode() == KeyCode.Q) {
				aPressed.set(false);
			}

			if(e.getCode() == KeyCode.S) {
				sPressed.set(false);
			}

			if(e.getCode() == KeyCode.D) {
				dPressed.set(false);
			}
		});
	}

	private static BooleanProperty wPressed = new SimpleBooleanProperty();
	private static BooleanProperty aPressed = new SimpleBooleanProperty();
	private static BooleanProperty sPressed = new SimpleBooleanProperty();
	private static BooleanProperty dPressed = new SimpleBooleanProperty();

	private BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed);

	private int movementVariable = 2;

	public void makeMovable(ImageView sprite, BorderPane scene){
		this.sprite = sprite;
		this.scene = scene;

		movementSetup();

		keyPressed.addListener(((observableValue, aBoolean, t1) -> {
			if(!aBoolean){
				timer.start();
			} else {
				timer.stop();
			}
		}));
	}

	AnimationTimer timer = new AnimationTimer() {
		@Override
		public void handle(long timestamp) {

			if(wPressed.get()) {
				if (-2 < sprite.getLayoutY()) {
					System.out.println(" X : " + sprite.getLayoutX() + " Y : " + sprite.getLayoutY());
					sprite.setLayoutY(sprite.getLayoutY() - movementVariable);
				}
			}

			if(sPressed.get()){
				if ( 926 > sprite.getLayoutY()) {
					System.out.println(" X : " + sprite.getLayoutX() + " Y : " + sprite.getLayoutY());
					sprite.setLayoutY(sprite.getLayoutY() + movementVariable);
					if (sprite.getLayoutX() <= 576 && sprite.getLayoutX() >= 526 && sprite.getLayoutY() == 772) {
						close(); 
						lancerXML("PageJouer.fxml");
					}
				}
			}

			if(aPressed.get()){
				if (-12 < sprite.getLayoutX()) {
					System.out.println(" X : " + sprite.getLayoutX() + " Y : " + sprite.getLayoutY());
					sprite.setLayoutX(sprite.getLayoutX() - movementVariable);
				}
			}

			if(dPressed.get()){
				if (1832 > sprite.getLayoutX()) {
					System.out.println(" X : " + sprite.getLayoutX() + " Y : " + sprite.getLayoutY());
					sprite.setLayoutX(sprite.getLayoutX() + movementVariable); 
				}
			}
		}
	};
}
