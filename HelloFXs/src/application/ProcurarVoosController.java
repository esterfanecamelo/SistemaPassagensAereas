package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ProcurarVoosController {
	
	@FXML
	TextField cidadePartidaTextField;
	@FXML
	TextField cidadeDestinoTextField;
	@FXML
	TextField dataIdaTextField;
	@FXML
	TextField horaIdaTextField;
	
	
	public void voltarParaMenu(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void procurarVoo(ActionEvent event) throws IOException{

		String nomeArquivo = "Voos.txt";
	    try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
	        String linha;

	        while ((linha = br.readLine()) != null) {
	            String[] dadosVoo = linha.split("\t");
	            String partida = dadosVoo[0];
	            String destino = dadosVoo[1];
	            String dataIda = dadosVoo[2];
	            String dataVolta = dadosVoo[3];
	            String horaIda = dadosVoo[4];
	            String horaVolta = dadosVoo[5];
	            String valorReais = dadosVoo[6];
	            String valorMilhas = dadosVoo[7];
	            String assentos = dadosVoo[8];
	
	            if (cidadePartidaTextField.getText().equals(partida) && cidadeDestinoTextField.getText().equals(destino) && dataIdaTextField.getText().equals(dataIda)  && horaIdaTextField.getText().equals(horaIda)) {
	            	//
	            	 FXMLLoader loader = new FXMLLoader(getClass().getResource("/RegistrarVoo.fxml"));
	  	            Parent root = loader.load();
	  	            RegistrarVooController registrarVooController = loader.getController();
	  	            
	  	            DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	  	            DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");

	  	            LocalDate dataIdaConvertido = LocalDate.parse(dataIda, formatterDate);
	  	            
	  	            LocalDate dataVoltaConvertido = LocalDate.parse(dataIda, formatterDate);
	  	            
	  	            LocalTime horaIdaConvertido = LocalTime.parse(horaIda, formatterTime);

	  	            LocalTime horaVoltaConvertido = LocalTime.parse(horaVolta, formatterTime);
	  	            
	  	            double valorReaisConvertido = Double.parseDouble(valorReais);
	  	            
	  	            int valorMilhasConvertido = Integer.parseInt(valorMilhas);
	  	            
	  	            int assentosConvertido = Integer.parseInt(assentos);



	  	            Voo novoVoo = new Voo(partida,destino,dataIdaConvertido,dataVoltaConvertido,horaIdaConvertido,horaVoltaConvertido,valorReaisConvertido,valorMilhasConvertido,assentosConvertido);
	  	            registrarVooController.receberDados(novoVoo);
	  	            
	  	            String linhaExcluida = partida+"\t"+destino+"\t"+dataIda+"\t"+dataVolta+"\t"+horaIda+"\t"+horaVolta+"\t"+valorReais+"\t"+valorMilhas+"\t"+assentos;
	  	            excluirLinha("Voos.txt",linhaExcluida);
	  	            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	  	            Scene scene = new Scene(root);
	  	            stage.setScene(scene);
	  	            stage.show();
	                break;
	            }
	        }
		
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
}
