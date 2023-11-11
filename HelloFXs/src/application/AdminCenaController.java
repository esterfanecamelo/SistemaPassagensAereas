package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AdminCenaController {
	@FXML
	Label labelNome;
	
	
	public void boasVindas(String nome) {
		labelNome.setText("Bem vindo(a) " + nome);
	}
	
	public void mudarParaTela1(ActionEvent event) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene); 
		stage.show();
	}
	public void inserirVoos(ActionEvent event)throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/RegistrarVoo.fxml"));
        Parent root = loader.load();
        RegistrarVooController registrarVooController = loader.getController();
        registrarVooController.boasVindas(labelNome);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
	}
	public void procurarVoos(ActionEvent event)throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProcurarVoos.fxml"));
        Parent root = loader.load();
        //ProcurarVoosController procurarVoosController = loader.getController();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
		
	}
	public void verificarVoos(ActionEvent event)throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/VerificarVoos.fxml"));
        Parent root = loader.load();
        //ProcurarVoosController procurarVoosController = loader.getController();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
		
	}
}
