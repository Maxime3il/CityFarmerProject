package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Inventory;
import model.Item;

public class PageEchangeController {

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

    public Inventory inventory = new Inventory();
    
    @FXML
    public void initialize() {
    	
        int nbPorcI = PagePersonnageController.player.getInventory().contains("porc").getCount();
        nbSteak.setText(String.valueOf(nbPorcI));
        
        int nbCarottesI = PagePersonnageController.player.getInventory().contains("carotte").getCount();
        nbCarotte.setText(String.valueOf(nbCarottesI));
        
        int nbLaitsI = PagePersonnageController.player.getInventory().contains("lait").getCount();
        nbLait.setText(String.valueOf(nbLaitsI));
    	
    	//Initialisation de l'affichage de l'inventaire du marchand
        System.out.println(PagePersonnageController.inventaireMarchand.contains("porc").getCount());
        int nbPorc = PagePersonnageController.inventaireMarchand.contains("porc").getCount();
        nbSteakMarchand.setText(String.valueOf(nbPorc));
        
        int nbCarottes = PagePersonnageController.inventaireMarchand.contains("carotte").getCount();
        nbCarotteMarchand.setText(String.valueOf(nbCarottes));
        
        int nbLaits = PagePersonnageController.inventaireMarchand.contains("lait").getCount();
        nbLaitMarchand.setText(String.valueOf(nbLaits));
    	
    	nombreItem.setText("1");
    	comboBoxItem.getItems().addAll("porc", "carotte", "lait");
    	comboBoxItem.setValue("porc");
    	nombreItem.textProperty().addListener(new ChangeListener<String>() {
    	    @Override
    	    public void changed(ObservableValue<? extends String> observable, String oldValue, 
    	        String newValue) {
    	        if (!newValue.matches("\\d*")) {
    	        	nombreItem.setText(newValue.replaceAll("[^\\d]", ""));
    	        }
    	    }
    	});
        
        //int nbArgent = PagePersonnageController.player.getInventory().contains("lait").getCount();
        argentInventaire.setText("100");
        
     // Ajout d'un listener sur la sélection de la ComboBox
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
    
 // Méthode pour mettre à jour le prix total en fonction du nombre entré dans le TextField
    private void updatePrixTotal() {
        double prix = Double.parseDouble(prixLabel.getText());
        int quantite = Integer.parseInt(nombreItem.getText());
        double prixTotalValue = prix * quantite;
        prixTotal.setText(String.valueOf(prixTotalValue));
    }


    @FXML
    void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    void echangerAvecMarchand(ActionEvent event) {
    	if(PagePersonnageController.player.getInventory().getArgentJoueur() < Integer.parseInt(prixTotal.getText())) {
    		PagePersonnageController.player.getInventory().setArgentJoueur(
    				PagePersonnageController.player.getInventory().getArgentJoueur() - Integer.parseInt(prixTotal.getText()));
            int prixFinalJoueur = PagePersonnageController.player.getInventory().getArgentJoueur();
            argentInventaire.setText(String.valueOf(prixFinalJoueur));
    	} else {
    		//TODO FAIRE UNE POPUP OU MESSAGE DE PREVENTION
    	}
    }

}
