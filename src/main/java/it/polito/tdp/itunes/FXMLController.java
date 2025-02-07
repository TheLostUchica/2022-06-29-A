/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.itunes;

import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import it.polito.tdp.itunes.model.Album;
import it.polito.tdp.itunes.model.Model;
import it.polito.tdp.itunes.model.Vertice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnAdiacenze"
    private Button btnAdiacenze; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnPercorso"
    private Button btnPercorso; // Value injected by FXMLLoader

    @FXML // fx:id="cmbA1"
    private ComboBox<Album> cmbA1; // Value injected by FXMLLoader

    @FXML // fx:id="cmbA2"
    private ComboBox<Album> cmbA2; // Value injected by FXMLLoader

    @FXML // fx:id="txtN"
    private TextField txtN; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="txtX"
    private TextField txtX; // Value injected by FXMLLoader

    @FXML
    void doCalcolaAdiacenze(ActionEvent event) {
    	Album a = this.cmbA1.getValue();
    	if(a!=null) {
    		for(Vertice v : model.getAdiacenze(a)) {
    			this.txtResult.appendText("\n"+v.toString());
    		}
    	}
    }

    @FXML
    void doCalcolaPercorso(ActionEvent event) {
    	this.txtResult.clear();
    	Album partenza = this.cmbA1.getValue();
    	Album arrivo = this.cmbA2.getValue();
    	String s = this.txtX.getText();
    	
    	if(partenza!=null && arrivo!=null && s!="") {
    		try {
    			int x = Integer.parseInt(s);
    			model.setX(x);
        		model.getPath(partenza, arrivo);
    		}catch(NumberFormatException e) {
    			this.txtResult.setText("Numero inserito nel formato sbagliato");
    		}
    		for (Album a : model.getBest()) {
    			this.txtResult.appendText(a.toString()+"\n");
    		}
    		if(model.getBest().size()==0) {
    			this.txtResult.appendText("Non ci sono cammini disponibili tra i due nodi.");
    		}
    	}
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	String s = this.txtN.getText();
    	if(s!=null) {
    		try {
    			int i = Integer.parseInt(s);
    			model.CreaGrafo(i);
    			this.setCombos();
    			this.txtResult.setText("Creato un grafo con "+model.getGrafo().vertexSet().size()+" vertici e "+ model.getGrafo().edgeSet().size()+" archi");
    		}catch(NumberFormatException e) {
    			this.txtResult.setText("Numero inserito nel formato sbagliato");
    		}
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnAdiacenze != null : "fx:id=\"btnAdiacenze\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnPercorso != null : "fx:id=\"btnPercorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbA1 != null : "fx:id=\"cmbA1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbA2 != null : "fx:id=\"cmbA2\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtN != null : "fx:id=\"txtN\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtX != null : "fx:id=\"txtX\" was not injected: check your FXML file 'Scene.fxml'.";

    }

    
    public void setModel(Model model) {
    	this.model = model;
    }
    
    public void setCombos() {
    	LinkedList<Album> albumi = new LinkedList<>(model.getGrafo().vertexSet());
    	Collections.sort(albumi);
		this.cmbA1.getItems().addAll(albumi);
		this.cmbA2.getItems().addAll(albumi);
    	
    }
}
