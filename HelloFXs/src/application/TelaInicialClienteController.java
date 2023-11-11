package application;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
//
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ResourceBundle;
import java.nio.file.Files;


//
public class TelaInicialClienteController {
	@FXML
	Label labelEmail;
	private String cpf;
	private Cliente cliente;
	
	public void receberObjeto(Cliente cliente) {
		this.cliente = cliente;
    }
	
	public void boasVindas(String nome) {
		labelEmail.setText("Bem vindo(a) " + nome);
	}
	
	public void mudarParaTela1(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void guardarDados(String cpf) {
		this.cpf = cpf;
	}
	public void editarDados(ActionEvent event) {

	    String nomeArquivo = "Pessoa.txt";
	    try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
	        String linha;

	        while ((linha = br.readLine()) != null) { 
	            String[] dadosPessoa = linha.split("\t");
	            String nomeSalvo = dadosPessoa[2];
	            String senhaSalva = dadosPessoa[1];
	            String emailSalvo = dadosPessoa[0];
	            String eAdminSalvo = dadosPessoa[5];
	            String milhasSalvo = dadosPessoa[4];
	            String cpfSalvo = dadosPessoa[3];
	            if (cliente.getCpf().equals(cpfSalvo)) {
	            	//
	            	 FXMLLoader loader = new FXMLLoader(getClass().getResource("/RegistroScene.fxml"));
	  	            Parent root = loader.load();
	  	            RegistrarController scene2Controller = loader.getController();
	  	            scene2Controller.receberDados(this.cliente);
	  	            String linhaExcluida = emailSalvo+"\t"+senhaSalva+"\t"+nomeSalvo+"\t"+cpfSalvo+"\t"+milhasSalvo+"\t"+eAdminSalvo;
	  	            excluirLinha("Pessoa.txt",linhaExcluida);
	  	            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	  	            Scene scene = new Scene(root);
	  	            stage.setScene(scene);
	  	            stage.show();
	                break;
	            }
	        }

	        
	    } catch (IOException e) {
	        System.out.println("Ocorreu um erro ao ler o arquivo de dados.");
	        e.printStackTrace();
	    }
	}
	
	
	public void excluirLinha(String arquivo, String linhaExcluir) {
	    try {
	        Path path = Path.of(arquivo);
	        String conteudo = Files.readString(path);
	        conteudo = conteudo.replace(linhaExcluir + System.lineSeparator(), "");
	        Files.writeString(path, conteudo, StandardOpenOption.TRUNCATE_EXISTING);
	        System.out.println("A linha foi excluída com sucesso.");
	    } catch (IOException e) {
	        System.out.println("Ocorreu um erro ao excluir a linha do arquivo.");
	        e.printStackTrace();
	    }
	}
	
	public void mudarParaCenaVoo(ActionEvent event) throws IOException{
		
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Voo.fxml"));
		Parent root = loader.load();
		VooController vooController = loader.getController();
		vooController.receberObjeto(this.cliente);
	
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void mostrarRelatorio(ActionEvent event)throws IOException{
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Relatorios.fxml"));
		Parent root = loader.load();
		RelatorioClienteController relatorioClienteController = loader.getController();
		relatorioClienteController.receberObjeto(this.cliente);
		relatorioClienteController.configurarTabelaRelatorio();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	

}
