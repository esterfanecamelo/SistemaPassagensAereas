package application;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileWriter;



public class RegistrarController {
	@FXML
	TextField nomeTextField;
	@FXML
	TextField emailTextField;
	@FXML
	TextField senhaTextField;
	@FXML
	TextField cpfTextField;
	@FXML
	CheckBox adminCheckBox;
	

	public void receberDados(Cliente cliente) {
		nomeTextField.setText(cliente.getNome());
		emailTextField.setText(cliente.getEmail());
		senhaTextField.setText(cliente.getSenha());
		cpfTextField.setText(cliente.getCpf());
		
	}
	
	public void voltarParaMenu(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void enviarDados(ActionEvent event) {
		String email = emailTextField.getText();
		String nome = nomeTextField.getText();
		String senha = senhaTextField.getText();
		String cpf = cpfTextField.getText();
		boolean isAdmin = adminCheckBox.isSelected();
		if(isAdmin == true) {
	        String arquivoPath = "Admin.txt";
	        Boolean validarAdmin = false;
	        
	        try {
	            Scanner scanner = new Scanner(new File(arquivoPath));
            
	            while (scanner.hasNextLine()) {
	                String linhaCPF = scanner.nextLine();
	                if(linhaCPF.equals(cpf)) {
	                	validarAdmin = true;
	                	System.out.println("Pessoa é admin");
	                	Admin novoAdmin = new Admin(nome,email,senha,cpf,true);
	                	
	                	try {
	            			String nomeArquivo = "Pessoa.txt";
	                        PrintWriter printWriter = new PrintWriter(new FileWriter(nomeArquivo,true));
	                        printWriter.println(novoAdmin.getEmail()+"\t"+novoAdmin.getSenha()+"\t"+novoAdmin.getNome()+"\t"+novoAdmin.getCpf()+"\t"+"nãoPossui"+"\t"+novoAdmin.getEADMIN());
	                        printWriter.close();

	                        System.out.println("Conteúdo salvo com sucesso no arquivo " + nomeArquivo);
	                    break;
	                    } catch (IOException e) {
	                        System.out.println("Ocorreu um erro ao salvar o conteúdo no arquivo.");
	                        e.printStackTrace();
	                    }
	                }
	            }
	            if(validarAdmin == false) {
	            	System.out.println("Pessoa não autorizada a ser admin");            	
	            }
	            
	            scanner.close();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
		
		} else {
			 
			//abrir arquivo
			try {
				String nomeArquivo = "Pessoa.txt";
				PrintWriter printWriter = new PrintWriter(new FileWriter(nomeArquivo,true));
				Cliente novoCliente = new Cliente(nome,email,senha,cpf,false,13550);
				
				
				//passar o objeto cliente para o TelaInicialClienteController
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scene2.fxml"));
				Parent root = loader.load();
				TelaInicialClienteController scene2Controller = loader.getController();


				// Chamar o método para receber o objeto no TelaInicialClienteController
				scene2Controller.receberObjeto(novoCliente);
				scene2Controller.boasVindas(novoCliente.getNome());
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	            Scene scene = new Scene(root);
	            stage.setScene(scene);
	            stage.show(); 
				//
				printWriter.println(novoCliente.getEmail()+"\t"+novoCliente.getSenha()+"\t"+novoCliente.getNome()+"\t"+novoCliente.getCpf()+"\t"+novoCliente.getMilhas()+"\t"+novoCliente.getEADMIN());
				printWriter.close();
				
				System.out.println("Conteúdo salvo com sucesso no arquivo " + nomeArquivo);
			} catch (IOException e) {
				System.out.println("Ocorreu um erro ao salvar o conteúdo no arquivo.");
				e.printStackTrace();
			}
		}
	
	
	}
	
}
