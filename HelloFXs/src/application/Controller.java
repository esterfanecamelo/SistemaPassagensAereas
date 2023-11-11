package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;


public class Controller {
	
	@FXML
	private Stage stage;
	private Scene scene;
	private Parent root;
	private String nomePessoa;
	private String cpfPessoa;
	private String eAdmin;
	private String emailSalvo;
	private String senhaSalva;
	private String milhasSalva;

	
	@FXML
	TextField textFieldEmail;
	@FXML
	TextField textFieldSenha;
	
	public void mudarParaTela2(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/Scene2.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void login(ActionEvent event) throws IOException {
	    String email = textFieldEmail.getText();
	    String senha = textFieldSenha.getText();

	    String nomeArquivo = "Pessoa.txt";
	    try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
	        String linha;
	        boolean loginValido = false;

	        while ((linha = br.readLine()) != null) {
	            String[] dadosPessoa = linha.split("\t");
	            emailSalvo = dadosPessoa[0];
	            senhaSalva = dadosPessoa[1];
	            nomePessoa = dadosPessoa[2];
	            cpfPessoa = dadosPessoa[3];
	            eAdmin = dadosPessoa[5];
	            milhasSalva = dadosPessoa[4];
	            
	            if (email.equals(emailSalvo) && senha.equals(senhaSalva)) {
	                loginValido = true;
	                break;
	            }
	        }

	        if (loginValido && eAdmin.equals("false")) {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scene2.fxml"));
	            Parent root = loader.load();
	            TelaInicialClienteController scene2Controller = loader.getController();
	            scene2Controller.boasVindas(nomePessoa);
	            double milhaStringParaDouble = Double.parseDouble(milhasSalva);

	            Cliente novoCliente = new Cliente(nomePessoa,emailSalvo,senhaSalva,cpfPessoa,false,milhaStringParaDouble);
	            
	            scene2Controller.guardarDados(cpfPessoa);
	            scene2Controller.receberObjeto(novoCliente);
	            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	            Scene scene = new Scene(root);
	            stage.setScene(scene);
	            stage.show();
	        }else if(loginValido && eAdmin.equals("true")) {
	        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/CenaPrincipalAdmin.fxml"));
	            Parent root = loader.load();
	            
	            AdminCenaController adminCenaController = loader.getController();
	            adminCenaController.boasVindas(nomePessoa);
	            //aqui
	            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	            Scene scene = new Scene(root);
	            stage.setScene(scene);
	            stage.show();
	        }
	        else {
	            System.out.println("Dados de login inválidos. Verifique seu email e senha.");
	        }
	    } catch (IOException e) {
	        System.out.println("Ocorreu um erro ao ler o arquivo de dados.");
	        e.printStackTrace();
	    }
	}

	public void Registrar(ActionEvent event) throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/RegistroScene.fxml"));
		root = loader.load();
		RegistrarController scene2Controller = loader.getController();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	
}
