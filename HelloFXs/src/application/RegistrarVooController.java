package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistrarVooController {
	
	@FXML
	Label nomeLabel;
	@FXML
	TextField partidaTextField;
	@FXML
	TextField destinoTextField;
	@FXML
	TextField dataIdaTextField;
	@FXML
	TextField dataVoltaTextField;
	@FXML
	TextField valorMilhasTextField;
	@FXML
	TextField valorReaisTextField;
	@FXML
	TextField assentosTextField;
	@FXML
	TextField horaIdaTextField;
	@FXML
	TextField horaVoltaTextField;
	

	
	public void boasVindas(Label nome) {
		String labelAnterior = nome.getText();
		nomeLabel.setText(labelAnterior);
	}
	
	public void cadastrarVoo() {
		String partida = partidaTextField.getText();
		String destino = destinoTextField.getText();
		
		String dataIda = dataIdaTextField.getText();
		DateTimeFormatter formatterdataIda = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataIdaLocalDate = LocalDate.parse(dataIda, formatterdataIda);

		String dataVolta = dataVoltaTextField.getText();
		DateTimeFormatter formatterdataVolta = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataVoltaLocalDate = LocalDate.parse(dataVolta, formatterdataVolta);
		
		String horaIda = horaIdaTextField.getText();
		DateTimeFormatter formatterhoraIda = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime horaIdaLocalTime = LocalTime.parse(horaIda, formatterhoraIda);
		
		String horaVolta = horaVoltaTextField.getText();
		DateTimeFormatter formatterhoraVolta = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime horaVoltaLocalTime = LocalTime.parse(horaVolta, formatterhoraVolta);
		
		String valorMilhas = valorMilhasTextField.getText();
		int valorMilhasInt = Integer.parseInt(valorMilhas);
		
		String valorReais = valorReaisTextField.getText();
		Double valorReaisDouble = Double.parseDouble(valorReais);
		
		String assentos = assentosTextField.getText();
		Integer assentosInt = Integer.parseInt(assentos);
		
		
		
		System.out.println(partida + destino + dataIdaLocalDate + dataVoltaLocalDate + horaIdaLocalTime
				+horaVoltaLocalTime + valorMilhasInt + valorReaisDouble + assentosInt);
		
		Voo voo = new Voo (partida,destino,dataIdaLocalDate,dataVoltaLocalDate,horaIdaLocalTime
		,horaVoltaLocalTime,valorReaisDouble,valorMilhasInt,assentosInt);
		
		try {
			String nomeArquivo = "Voos.txt";
			PrintWriter printWriter = new PrintWriter(new FileWriter(nomeArquivo,true));			

			printWriter.println(voo.getCidadePartida()+"\t"+voo.getCidadeDestino()+"\t"+dataIdaTextField.getText()+"\t"+
			dataVoltaTextField.getText()+"\t"+voo.getHoraIda()+"\t"+voo.getHoraVolta()+"\t"+voo.getValorReais()+"\t"+voo.getValorMilhas()+"\t"+voo.getAssentos());
			
			System.out.println("Voo salvo com sucesso no arquivo " + nomeArquivo);
			printWriter.close();
			
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao salvar o voo no arquivo.");
			e.printStackTrace();
		}
	}
	
	public void voltarParaMenu(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void editarVoos() {

	    /*String nomeArquivo = "Voos.txt";
	    try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
	        String linha;

	        while ((linha = br.readLine()) != null) {
	            String[] dadosVoo = linha.split("\t");
	            String cidadePartida = dadosVoo[0];
	            String cidadeDestino = dadosVoo[1];
	            String dataIda = dadosVoo[2];
	            String dataVolta = dadosVoo[3];
	            String horaIda = dadosVoo[4];
	            String horaVolta = dadosVoo[5];
	            String valorReais = dadosVoo[6];
	            String valorMilhas = dadosVoo[7];
	            String assentos = dadosVoo[8];
	            
	            if (voo.getCidadePartida().equals(cidadePartida) && voo.getCidadeDestino().equals(cidadeDestino) ) {
	            	//
	            	FXMLLoader loader = new FXMLLoader(getClass().getResource("/RegistrarVoo.fxml"));
	  	            Parent root = loader.load();
	  	            RegistrarController scene2Controller = loader.getController();
	  	            scene2Controller.receberDados(this.voo);
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
	    }*/
	}

	public void receberDados(Voo novoVoo) {
		partidaTextField.setText(novoVoo.getCidadePartida());
		destinoTextField.setText(novoVoo.getCidadeDestino());
		
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	     // Convertendo o LocalDate para String usando format()
	     String dataIdaString = novoVoo.getDataIda().format(formatter);
	     String dataVoltaString = novoVoo.getDataVolta().format(formatter);
	     
	     DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
	     
	     String valorReaisString = Double.toString(novoVoo.getValorReais());
	     String valorMilhasString = "" + novoVoo.getValorMilhas();
	     String assentosString = Integer.toString(novoVoo.getAssentos());

	     
	     String horaIdaString = novoVoo.getHoraIda().format(formatterTime);
	     String horaVoltaString = novoVoo.getHoraVolta().format(formatterTime);

	     
	     dataIdaTextField.setText(dataIdaString);
	     dataVoltaTextField.setText(dataVoltaString);
	     horaIdaTextField.setText(horaIdaString);
	     horaVoltaTextField.setText(horaVoltaString);
	     valorReaisTextField.setText(valorReaisString);
	     valorMilhasTextField.setText(valorMilhasString);
	     assentosTextField.setText(assentosString);
	     
	     
	     

	}
	


}
