package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Inventory;

public class PageEchangeController {

	// Région paramètre
	@FXML
	private BorderPane scene;
	
    @FXML
    private Button echangerButton;
    
    @FXML
    private ImageView argent;

    @FXML
    private Label argentInventaire;

    @FXML
    private ImageView carotte;

    @FXML
    private ImageView carotteMarchand;

    @FXML
    private Button closeButton;

    @FXML
    private ComboBox<String> comboBoxItem;

    @FXML
    private Label itemLabel;

    @FXML
    private Label nbCarotte;

    @FXML
    private Label nbCarotteMarchand;

    @FXML
    private Label nbLait;

    @FXML
    private Label nbLaitMarchand;

    @FXML
    private Label nbSteak;

    @FXML
    private Label nbSteakMarchand;

    @FXML
    private TextField nombreItem;

    @FXML
    private Label nombreLabel;

    @FXML
    private Label prixLabel;

    @FXML
    private TextField prixTotal;

    @FXML
    private Label separator;

    @FXML
    private ImageView steak;

    @FXML
    private ImageView steakMarchand;

    @FXML
    private Label titreInventaire;

    @FXML
    private Label titreInventaireMarchand;

    private Inventory inventory = PagePersonnageController.inventaireMarchand;
    
    //Définition de variable string pour éviter la répétition
    private String carotteStr = "carotte";
    private String porcStr = "porc";
    private String laitStr = "lait";

    @FXML
    public void initialize() {
    	
    	currentKeyBinding();
    	
        int nbPorcI = PagePersonnageController.player.getInventory().contains(porcStr).getCount();
        nbSteak.setText(String.valueOf(nbPorcI));
        
        int nbCarottesI = PagePersonnageController.player.getInventory().contains(carotteStr).getCount();
        nbCarotte.setText(String.valueOf(nbCarottesI));
        
        int nbLaitsI = PagePersonnageController.player.getInventory().contains(laitStr).getCount();
        nbLait.setText(String.valueOf(nbLaitsI));
    	
        // Gérer le porc
        int nbPorc = PagePersonnageController.inventaireMarchand.contains(porcStr).getCount();
        nbSteakMarchand.setText(String.valueOf(nbPorc));
        // Gérer les carottes
        int nbCarottes = PagePersonnageController.inventaireMarchand.contains(carotteStr).getCount();
        nbCarotteMarchand.setText(String.valueOf(nbCarottes));
        // Gerer le lait
        int nbLaits = PagePersonnageController.inventaireMarchand.contains(laitStr).getCount();
        nbLaitMarchand.setText(String.valueOf(nbLaits));
    	
    	nombreItem.setText("1");
    	comboBoxItem.getItems().addAll(porcStr, carotteStr, laitStr);
    	comboBoxItem.setValue(porcStr);
    	prixLabel.setText(PagePersonnageController.player.getInventory().contains(comboBoxItem.getValue()).getPrice() + "");
    	nombreItem.textProperty().addListener((observable, oldValue, newValue) -> {
    	    if (!newValue.matches("\\d*")) {
    	        nombreItem.setText(newValue.replaceAll("[^\\d]", ""));
    	    }
    	});
        
    	// Mise à jour de l'argent du joueur
        double nbArgent = PagePersonnageController.player.getInventory().getArgentJoueur();
        argentInventaire.setText(String.valueOf(nbArgent));
        
        // Ajout d'un listener sur la sélection de la ComboBox pour savoir si il y a eu modification
        comboBoxItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Récupération du prix du produit sélectionné
            double prix = inventory.contains(newValue).getPrice();
            prixLabel.setText(String.valueOf(prix));
            updatePrixTotal();
        });

        // Ajout d'un listener sur la saisie dans le TextField nombreItem
        nombreItem.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                nombreItem.setText(newValue.replaceAll("[^\\d]", ""));
            }
            updatePrixTotal();
        });

        // Mise à jour du prix total initial
        updatePrixTotal();
    }

    /**
     * Détermine les touches claviers entrées par l'utilisateur
     * Si la touche correspond à une action alors elle sera exécutée.
     */
	private void currentKeyBinding() {
		scene.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.T) {
				//Ferme la fenetre
				close();
			}
		});	
	}
    
    /**
     * Méthode pour mettre à jour le prix total en fonction du nombre entré dans le TextField
     */
    private void updatePrixTotal() {
        double prix = Double.parseDouble(prixLabel.getText());
        if(!nombreItem.getText().equals("")) {
            int quantite = Integer.parseInt(nombreItem.getText());
            double prixTotalValue = prix * quantite;
            prixTotal.setText(String.valueOf(prixTotalValue));
        }
    }

    /**
     * Permet de close la window courante
     */
    @FXML
    void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    
    /**
     * Permet d'intéragir avec le marchand, cette méthode sert à acheter les items
     * que le marchand possède dans son inventaire intéractif.
     * La méthode fait des calculs et met à jour l'argent que le joueur possède en fonction de l'échange effectué
     * Si l'utilisateur n'a pas la quantité nécessaire à la transaction alors une alerte est affichée
     * @param event
     */
    @FXML
    void acheterAvecMarchand(ActionEvent event) {
    	if(PagePersonnageController.player.getInventory().getArgentJoueur() >= Double.parseDouble(prixTotal.getText())) {
    		if(PagePersonnageController.inventaireMarchand.contains(comboBoxItem.getValue()).getCount() >= Integer.parseInt(nombreItem.getText())) {
	    		//Changement de l'argent du joueur après l'achat des items
	    		PagePersonnageController.player.getInventory().setArgentJoueur(PagePersonnageController.player.getInventory().getArgentJoueur() - Double.parseDouble(prixTotal.getText()));
	    		double prixFinalJoueur = PagePersonnageController.player.getInventory().getArgentJoueur();
	    		argentInventaire.setText(String.valueOf(prixFinalJoueur));
	    		//Changement inventaire du personnage de l'item selectionné
	    		int nbItem = PagePersonnageController.player.getInventory().contains(comboBoxItem.getValue()).getCount();
	    		PagePersonnageController.player.getInventory().contains(comboBoxItem.getValue()).setCount(nbItem + Integer.parseInt(nombreItem.getText()));
	    		//Changement inentaire marchand
	    		int nbItemMarchand = PagePersonnageController.inventaireMarchand.contains(comboBoxItem.getValue()).getCount();
	    		PagePersonnageController.inventaireMarchand.contains(comboBoxItem.getValue()).setCount(nbItemMarchand - Integer.parseInt(nombreItem.getText()));
	    		//Modification du bon label en fonction de l'item sélectionné par l'User
	    		if(comboBoxItem.getValue().equals("porc")) {
	    			nbSteak.setText(nbItem + Integer.parseInt(nombreItem.getText()) + "");
	    			nbSteakMarchand.setText(nbItemMarchand - Integer.parseInt(nombreItem.getText())  + "");
	    		} else if(comboBoxItem.getValue().equals("lait")) {
	    			nbLait.setText(nbItem + Integer.parseInt(nombreItem.getText())+"");
	    			nbLaitMarchand.setText(nbItemMarchand - Integer.parseInt(nombreItem.getText()) + "");
	    		} else {
	    			nbCarotte.setText(nbItem + Integer.parseInt(nombreItem.getText())+"");
	    			nbCarotteMarchand.setText( nbItemMarchand - Integer.parseInt(nombreItem.getText()) + "");
	    		}
    		} else {
        		Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Quantité insuffisante");
                alert.setHeaderText(null);
                alert.setContentText("Le marchand ne possède plus l'item concerné");
                alert.showAndWait();
    		}
    	} else {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Vous n'avez pas assez de ressources");
            alert.setHeaderText(null);
            alert.setContentText("Vous n'avez pas l'argent nécessaire");
            alert.showAndWait();
    	}	
    }
    
    /**
     * Permet d'intéragir avec le marchand, cette méthode sert à vendre les items
     * que l'utilisateur possède dans son inventaire
     * La méthode fait des calculs et met à jour l'argent qu'il possède en fonction de l'échange effectué.
     * Si l'utilisateur n'a pas la quantité nécessaire à la transaction alors une alerte est affichée
     * @param evt
     */
    @FXML
    void vendreAvecMarchand(ActionEvent evt) {
    	if(PagePersonnageController.player.getInventory().contains(comboBoxItem.getValue()).getCount() >= Integer.parseInt(nombreItem.getText())) {
    		//Changement de l'argent du joueur après la vente des items
    		PagePersonnageController.player.getInventory().setArgentJoueur(PagePersonnageController.player.getInventory().getArgentJoueur() + Double.parseDouble(prixTotal.getText()));
    		double prixFinalJoueur = PagePersonnageController.player.getInventory().getArgentJoueur();
    		argentInventaire.setText(String.valueOf(prixFinalJoueur));
    		//Changement inventaire du personnage de l'item selectionné
    		int nbItem = PagePersonnageController.player.getInventory().contains(comboBoxItem.getValue()).getCount();
    		PagePersonnageController.player.getInventory().contains(comboBoxItem.getValue()).setCount(nbItem - Integer.parseInt(nombreItem.getText()));
    		//Changement inventaire marchand
    		int nbItemMarchand = PagePersonnageController.inventaireMarchand.contains(comboBoxItem.getValue()).getCount();
    		PagePersonnageController.inventaireMarchand.contains(comboBoxItem.getValue()).setCount(nbItemMarchand + Integer.parseInt(nombreItem.getText()));
    		if(comboBoxItem.getValue().equals("porc")) {
    			nbSteak.setText(nbItem - Integer.parseInt(nombreItem.getText()) + "");
    			nbSteakMarchand.setText(nbItemMarchand + Integer.parseInt(nombreItem.getText())  + "");
    		} else if(comboBoxItem.getValue().equals("lait")) {
    			nbLait.setText(nbItem - Integer.parseInt(nombreItem.getText())+"");
    			nbLaitMarchand.setText(nbItemMarchand + Integer.parseInt(nombreItem.getText()) + "");
    		} else {
    			nbCarotte.setText(nbItem - Integer.parseInt(nombreItem.getText())+"");
    			nbCarotteMarchand.setText( nbItemMarchand + Integer.parseInt(nombreItem.getText()) + "");
    		}
    	} else {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Quantité insuffisante");
            alert.setHeaderText(null);
            alert.setContentText("Vous n'avez pas la quantité nécessaire");
            alert.showAndWait();
    	}	
    }
}
