package it.polito.tdp.food;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.food.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtCalorie;

    @FXML
    private TextField txtPassi;

    @FXML
    private Button btnAnalisi;

    @FXML
    private Button btnCorrelate;

    @FXML
    private Button btnCammino;

    @FXML
    private ComboBox<String> boxPorzioni;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCammino(ActionEvent event) {

    	this.txtResult.clear();
    	String partenza = this.boxPorzioni.getValue();
    	int passi;
       	try {
    		passi = Integer.parseInt(this.txtPassi.getText());
    		
    		for (String s : this.model.getCammino(passi, partenza))
    		   this.txtResult.appendText(s+" \n");
    		
    		
    	}
    	catch(NumberFormatException e) {
    		this.txtResult.appendText("numero intero");
    	}
    }

    @FXML
    void doCorrelate(ActionEvent event) {

    	this.txtResult.clear();
    	String partenza = this.boxPorzioni.getValue();
    	if(this.model.getConnessi(partenza).isEmpty()) {
    		this.txtResult.appendText("non ha correlate");
    	}
    	else {
    		
    	for (String s : this.model.getConnessi(partenza))
    		this.txtResult.appendText(s+"\n");
    	}
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {

    	int calorie;
    	try {
    		calorie = Integer.parseInt(this.txtCalorie.getText());
    		this.model.creaGrafo(calorie);
    		this.txtResult.appendText("GRAFO CREATO \n");
    		this.txtResult.appendText("VERTICI "+this.model.getNumeroVertici()+"\n");
    		this.txtResult.appendText("ARCHI "+this.model.getNumeroArchi()+"\n");
    		this.boxPorzioni.getItems().addAll(this.model.getVertici());
    		
    	}
    	catch(NumberFormatException e) {
    		this.txtResult.appendText("numero intero");
    	}
    }

    @FXML
    void initialize() {
        assert txtCalorie != null : "fx:id=\"txtCalorie\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtPassi != null : "fx:id=\"txtPassi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCorrelate != null : "fx:id=\"btnCorrelate\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCammino != null : "fx:id=\"btnCammino\" was not injected: check your FXML file 'Food.fxml'.";
        assert boxPorzioni != null : "fx:id=\"boxPorzioni\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Food.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
	}
}
