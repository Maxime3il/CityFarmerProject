package application;

import javafx.animation.AnimationTimer;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PageJouerController {
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
				//if ( -2 < sprite.getLayoutY()) {
				if ((((sprite.getLayoutX() < 464 && sprite.getLayoutY() <= 144 ) || (sprite.getLayoutX() > 1138 && sprite.getLayoutY() <= 144 ) || sprite.getLayoutY() > 144)) && ( -2 < sprite.getLayoutY())) {
					System.out.println(" X : " + sprite.getLayoutX() + " Y : " + sprite.getLayoutY());
					sprite.setLayoutY(sprite.getLayoutY() - movementVariable);
				}
			}

			if(sPressed.get()){
				if ( 926 > sprite.getLayoutY()) {
					System.out.println(" X : " + sprite.getLayoutX() + " Y : " + sprite.getLayoutY());
					sprite.setLayoutY(sprite.getLayoutY() + movementVariable);
				}
			}

			if(aPressed.get()){
				if (((sprite.getLayoutX() < 464 || 1138 < sprite.getLayoutX()) && sprite.getLayoutY() < 144 && -12 < sprite.getLayoutX() ) || sprite.getLayoutY() > 144 && -12 < sprite.getLayoutX()) {
					System.out.println(" X : " + sprite.getLayoutX() + " Y : " + sprite.getLayoutY());
					sprite.setLayoutX(sprite.getLayoutX() - movementVariable);
				}
			}

			if(dPressed.get()){
				if (((sprite.getLayoutX() < 464 || 1138 < sprite.getLayoutX()) && sprite.getLayoutY() < 144 && 1832 > sprite.getLayoutX() ) || sprite.getLayoutY() > 144 && 1832 > sprite.getLayoutX()) {
					System.out.println(" X : " + sprite.getLayoutX() + " Y : " + sprite.getLayoutY());
					sprite.setLayoutX(sprite.getLayoutX() + movementVariable); 
				}
			}
		}
	};


}
