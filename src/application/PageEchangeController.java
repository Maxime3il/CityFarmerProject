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
import model.Player;

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
    
    private int nbPorc;
    
    private int nbPorcI;

    private int nbCarottes;
    
    private int nbCarottesI;

    private int nbLaits;
    
    private int nbLaitsI;

    
    @FXML
    public void initialize() {
    	
        nbPorcI = PagePersonnageController.player.getInventory().contains("porc").getCount();
        nbSteak.setText(String.valueOf(nbPorcI));
        
        nbCarottesI = PagePersonnageController.player.getInventory().contains("carotte").getCount();
        nbCarotte.setText(String.valueOf(nbCarottesI));
        
        nbLaitsI = PagePersonnageController.player.getInventory().contains("lait").getCount();
        nbLait.setText(String.valueOf(nbLaitsI));
    	
    	//Initialisation de l'affichage de l'inventaire du marchand
        System.out.println(PagePersonnageController.inventaireMarchand.contains("porc").getCount());
        // Gérer le porc
        nbPorc = PagePersonnageController.inventaireMarchand.contains("porc").getCount();
        nbSteakMarchand.setText(String.valueOf(nbPorc));
        // Gérer les carottes
        nbCarottes = PagePersonnageController.inventaireMarchand.contains("carotte").getCount();
        nbCarotteMarchand.setText(String.valueOf(nbCarottes));
        // Gerer le lait
        nbLaits = PagePersonnageController.inventaireMarchand.contains("lait").getCount();
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
        
        double nbArgent = PagePersonnageController.player.getInventory().getArgentJoueur();
        argentInventaire.setText(String.valueOf(nbArgent));
        
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
        if(!nombreItem.getText().equals("")) {
            int quantite = Integer.parseInt(nombreItem.getText());
            double prixTotalValue = prix * quantite;
            prixTotal.setText(String.valueOf(prixTotalValue));
        }
    }

    @FXML
    void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    void echangerAvecMarchand(ActionEvent event) {
    	if(PagePersonnageController.player.getInventory().getArgentJoueur() > Double.parseDouble(prixTotal.getText())) {
    		PagePersonnageController.player.getInventory().setArgentJoueur(
    				PagePersonnageController.player.getInventory().getArgentJoueur() - Double.parseDouble(prixTotal.getText()));
    		double prixFinalJoueur = PagePersonnageController.player.getInventory().getArgentJoueur();
    		argentInventaire.setText(String.valueOf(prixFinalJoueur));
    		//Changement inventaire du personnage de l'item selectionné
    		int nbItem = PagePersonnageController.player.getInventory().contains(comboBoxItem.getValue()).getCount();
    		PagePersonnageController.player.getInventory().contains(comboBoxItem.getValue()).setCount(nbItem + Integer.parseInt(nombreItem.getText()));
    		//Changement inentaire marchand
    		int nbItemMarchand = PagePersonnageController.inventaireMarchand.contains(comboBoxItem.getValue()).getCount();
    		PagePersonnageController.inventaireMarchand.contains(comboBoxItem.getValue()).setCount(nbItemMarchand - Integer.parseInt(nombreItem.getText()));
    		if(comboBoxItem.getValue().equals("porc")) {
    			nbSteak.setText(nbItem + Integer.parseInt(nombreItem.getText()) + "");
    			System.out.println(Integer.parseInt(nombreItem.getText()));
    			nbSteakMarchand.setText(nbItemMarchand - Integer.parseInt(nombreItem.getText())  + "");
    		} else if(comboBoxItem.getValue().equals("lait")) {
    			nbLait.setText(nbItem + Integer.parseInt(nombreItem.getText())+"");
    			nbLaitMarchand.setText(nbItemMarchand - Integer.parseInt(nombreItem.getText()) + "");
    		} else {
    			System.out.println();
    			nbCarotte.setText(nbItem + Integer.parseInt(nombreItem.getText())+"");
    			nbCarotteMarchand.setText( nbItemMarchand - Integer.parseInt(nombreItem.getText()) + "");
    		}
    	} else {
    		//TODO FAIRE UNE POPUP OU MESSAGE DE PREVENTION
    	}	
    }
    
    @FXML
    void vendreAvecMarchand(ActionEvent evt) {
    	if(PagePersonnageController.player.getInventory().getArgentJoueur() > Double.parseDouble(prixTotal.getText())) {
    		PagePersonnageController.player.getInventory().setArgentJoueur(
    				PagePersonnageController.player.getInventory().getArgentJoueur() - Double.parseDouble(prixTotal.getText()));
    		double prixFinalJoueur = PagePersonnageController.player.getInventory().getArgentJoueur();
    		argentInventaire.setText(String.valueOf(prixFinalJoueur));
    		//Changement inventaire du personnage de l'item selectionné
    		int nbItem = PagePersonnageController.player.getInventory().contains(comboBoxItem.getValue()).getCount();
    		PagePersonnageController.player.getInventory().contains(comboBoxItem.getValue()).setCount(nbItem - Integer.parseInt(nombreItem.getText()));
    		//Changement inentaire marchand
    		int nbItemMarchand = PagePersonnageController.inventaireMarchand.contains(comboBoxItem.getValue()).getCount();
    		PagePersonnageController.inventaireMarchand.contains(comboBoxItem.getValue()).setCount(nbItemMarchand + Integer.parseInt(nombreItem.getText()));
    		if(comboBoxItem.getValue().equals("porc")) {
    			nbSteak.setText(nbItem - Integer.parseInt(nombreItem.getText()) + "");
    			System.out.println(Integer.parseInt(nombreItem.getText()));
    			nbSteakMarchand.setText(nbItemMarchand + Integer.parseInt(nombreItem.getText())  + "");
    		} else if(comboBoxItem.getValue().equals("lait")) {
    			nbLait.setText(nbItem - Integer.parseInt(nombreItem.getText())+"");
    			nbLaitMarchand.setText(nbItemMarchand + Integer.parseInt(nombreItem.getText()) + "");
    		} else {
    			System.out.println();
    			nbCarotte.setText(nbItem - Integer.parseInt(nombreItem.getText())+"");
    			nbCarotteMarchand.setText( nbItemMarchand + Integer.parseInt(nombreItem.getText()) + "");
    		}
    	} else {
    		//TODO FAIRE UNE POPUP OU MESSAGE DE PREVENTION
    	}	
    }

}
