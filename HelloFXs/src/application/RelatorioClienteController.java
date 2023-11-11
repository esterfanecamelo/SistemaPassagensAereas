package application;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.beans.property.SimpleStringProperty;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class RelatorioClienteController{
	
	@FXML
	private Label comentariosRelatorioLabel;
	@FXML
	private Label relatorioLabel;
	@FXML
	private TableView <DadosPassageiro> tabelaViewRelatorio;
	@FXML
	private TableColumn <DadosPassageiro, String> passageiroColuna;
	@FXML
	private TableColumn <DadosPassageiro, String> cpfColuna;
	@FXML
	private TableColumn <DadosPassageiro, String> assentoColuna;
	@FXML
	private TableColumn <DadosPassageiro, String> partidaColuna;
	@FXML
	private TableColumn <DadosPassageiro, String> dataPartidaColuna;
	@FXML
	private TableColumn <DadosPassageiro, String> horaPartidaColuna;
	@FXML
	private TableColumn <DadosPassageiro, String> destinoColuna;
	@FXML
	private TableColumn <DadosPassageiro, String> dataDestinoColuna;
	@FXML
	private TableColumn <DadosPassageiro, String> horaDestinoColuna;
	@FXML
	private TableColumn <DadosPassageiro, String> checkinColuna;
	String emailCliente;
    String cpfCliente;
    String bagagem;
    String primeiraClasse;
    String aluguelCarro;
    String hotel;
    String nomePassageiro;
    String cpfPassageiro;
    String valorTotal;
    String numeroPassagem;
    String cidadePartida;
    String cidadeDestino;
    String dataIda;
    String dataChegada;
    String horaIda;
    String horaChegada;
    String valorReais;
    String valorMilhas;
    String assentos;
    String checkin;

    private Cliente cliente;

    ObservableList<DadosPassageiro> listaPassageiros = FXCollections.observableArrayList();
	
    String linhaDoCheckin;
	
	
	public void boasVindas() {
		relatorioLabel.setText("Relatório de " + cliente.getNome());
	}
	
	public void voltarAoMenu(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	public void configurarTabelaRelatorio() {
		
		
		try (BufferedReader br = new BufferedReader(new FileReader("Relatorio.txt"))) {
	        String linha;

	        while ((linha = br.readLine()) != null) {
	            String[] dadosPassageiro = linha.split("\t");
	             emailCliente = dadosPassageiro[0];
	             cpfCliente = dadosPassageiro[1];
	             bagagem = dadosPassageiro[2];
	             primeiraClasse = dadosPassageiro[3];
	             aluguelCarro = dadosPassageiro[4];
	             hotel = dadosPassageiro[5];
	             nomePassageiro = dadosPassageiro[6];
	             cpfPassageiro = dadosPassageiro[7];
	             valorTotal = dadosPassageiro[8];
	             numeroPassagem = dadosPassageiro[9];
	             cidadePartida = dadosPassageiro[10];
	             cidadeDestino = dadosPassageiro[11];
	             dataIda = dadosPassageiro[12];
	             dataChegada = dadosPassageiro[13];
	             horaIda = dadosPassageiro[14];
	             horaChegada = dadosPassageiro[15];
	             valorReais = dadosPassageiro[16];
	             valorMilhas = dadosPassageiro[17];
	             assentos = dadosPassageiro[18];
	             checkin = dadosPassageiro[19];

 
	            boolean bagagemBoolean = Boolean.parseBoolean(bagagem);
	            boolean primeiraClasseBoolean = Boolean.parseBoolean(primeiraClasse);
	            boolean aluguelCarroBoolean = Boolean.parseBoolean(aluguelCarro);
	            boolean hotelBoolean = Boolean.parseBoolean(hotel);

	  
	    		DateTimeFormatter formatterdataIda = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	    		LocalDate dataIdaLocalDate = LocalDate.parse(dataIda, formatterdataIda);
 
	  
	    		DateTimeFormatter formatterdataVolta = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	    		LocalDate dataChegadaLocalDate = LocalDate.parse(dataChegada, formatterdataVolta);
	    		 
	
	    		DateTimeFormatter formatterhoraIda = DateTimeFormatter.ofPattern("HH:mm");
	    		LocalTime horaIdaLocalTime = LocalTime.parse(horaIda, formatterhoraIda);
	    		

	    		DateTimeFormatter formatterhoraVolta = DateTimeFormatter.ofPattern("HH:mm");
	    		LocalTime horaChegadaLocalTime = LocalTime.parse(horaChegada, formatterhoraVolta);
	    		
	    		int valorMilhasInt = Integer.parseInt(valorMilhas);
	    		Double valorReaisDouble = Double.parseDouble(valorReais);
	    		Integer assentosInt = Integer.parseInt(assentos);
	    		Double valorTotalDouble = Double.parseDouble(valorTotal);
	    		Boolean checkinBoolean = Boolean.parseBoolean(checkin);
	    		
	    		Voo vooDoPassageiro = new Voo (cidadePartida, cidadeDestino, dataIdaLocalDate, dataChegadaLocalDate, horaIdaLocalTime, horaChegadaLocalTime, valorReaisDouble, valorMilhasInt, assentosInt);	
	    		System.out.println(vooDoPassageiro.getValorMilhas());
	    		if(this.cliente.getCpf().equals(cpfCliente)) {	    			
	    			listaPassageiros.add(new DadosPassageiro (bagagemBoolean, primeiraClasseBoolean, aluguelCarroBoolean, hotelBoolean, 
	    					nomePassageiro, cpfPassageiro, valorTotalDouble, numeroPassagem, vooDoPassageiro, checkinBoolean));
	    		}
	    		
	    		
 
	        }
	        br.close();
	    } catch (IOException e) {
	        System.out.println("Ocorreu um erro ao ler o arquivo de dados.");
	        e.printStackTrace();
	    }

        passageiroColuna.setCellValueFactory(new PropertyValueFactory<DadosPassageiro, String>("nome")); 
        cpfColuna.setCellValueFactory(new PropertyValueFactory<DadosPassageiro, String>("cpf"));
        assentoColuna.setCellValueFactory(new PropertyValueFactory<DadosPassageiro, String>("numeroPassagem"));

        partidaColuna.setCellValueFactory(new PropertyValueFactory<DadosPassageiro, String>("cidadePartida"));
        dataPartidaColuna.setCellValueFactory(new PropertyValueFactory<DadosPassageiro, String>("DataPartida"));
        horaPartidaColuna.setCellValueFactory(new PropertyValueFactory<DadosPassageiro, String>("HoraPartida"));
        
        destinoColuna.setCellValueFactory(new PropertyValueFactory<DadosPassageiro, String>("CidadeDestino"));
        dataDestinoColuna.setCellValueFactory(new PropertyValueFactory<DadosPassageiro, String>("DataDestino"));
        horaDestinoColuna.setCellValueFactory(new PropertyValueFactory<DadosPassageiro, String>("HoraDestino"));
        checkinColuna.setCellValueFactory(new PropertyValueFactory<DadosPassageiro, String>("checkin"));
        
        tabelaViewRelatorio.setItems(listaPassageiros);
	}
	public void receberObjeto(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void realizarCheckin() {
		
		DadosPassageiro dadosPassageiroSelecionado = tabelaViewRelatorio.getSelectionModel().getSelectedItem();
		
		if (dadosPassageiroSelecionado == null) {
			
			comentariosRelatorioLabel.setText("SELECIONE UM VOO PARA FAZER CHECKIN!");
			
		}else {
			
			dadosPassageiroSelecionado.setCheckin(true);
			
			comentariosRelatorioLabel.setText("Nome: " + dadosPassageiroSelecionado.getNome() + "\nCPF: " + dadosPassageiroSelecionado.getCpf() + "\nCHECKIN REALIZADO!");
	        //checkinColuna.setCellValueFactory(new PropertyValueFactory<DadosPassageiro, String>("checkin"));
	        tabelaViewRelatorio.refresh();
	        // tabelaViewRelatorio.setItems(listaPassageiros);
	        
	        try (BufferedReader br = new BufferedReader(new FileReader("Relatorio.txt"))) {
		        String linha;

		        while ((linha = br.readLine()) != null) { 
		        	String[] dadosPassageiro = linha.split("\t");
		             emailCliente = dadosPassageiro[0];
		             cpfCliente = dadosPassageiro[1];
		             bagagem = dadosPassageiro[2];
		             primeiraClasse = dadosPassageiro[3];
		             aluguelCarro = dadosPassageiro[4];
		             hotel = dadosPassageiro[5];
		             nomePassageiro = dadosPassageiro[6];
		             cpfPassageiro = dadosPassageiro[7];
		             valorTotal = dadosPassageiro[8];
		             numeroPassagem = dadosPassageiro[9];
		             cidadePartida = dadosPassageiro[10];
		             cidadeDestino = dadosPassageiro[11];
		             dataIda = dadosPassageiro[12];
		             dataChegada = dadosPassageiro[13];
		             horaIda = dadosPassageiro[14];
		             horaChegada = dadosPassageiro[15];
		             valorReais = dadosPassageiro[16];
		             valorMilhas = dadosPassageiro[17];
		             assentos = dadosPassageiro[18];
		             checkin = dadosPassageiro[19];
          
		            
		            if (cliente.getCpf().equals(cpfCliente) && dadosPassageiroSelecionado.getCpf().equals(cpfPassageiro)) {
		            	//         
		  	          excluirLinha("Relatorio.txt",linha);
		  	            
		  	          Boolean checkinBoolean = true;
		  	            
		  	          linhaDoCheckin = emailCliente+"\t"+cpfCliente+"\t"+bagagem+"\t"+primeiraClasse+"\t"+aluguelCarro+"\t"+
		  	            	hotel+"\t"+nomePassageiro+"\t"+cpfPassageiro+"\t"+valorTotal+"\t"+numeroPassagem+
		  					"\t"+cidadePartida+"\t"+cidadeDestino+"\t"+dataIda+"\t"+dataChegada+"\t"+horaIda+"\t"+horaChegada+"\t"+valorReais+"\t"+valorMilhas+"\t"+assentos+"\t"+checkinBoolean;
			
			  	          
		            }
		        }
		        br.close();
 
		    } catch (IOException e) {
		        System.out.println("Ocorreu um erro ao ler o arquivo de dados.");
		        e.printStackTrace();
		    }
	        
	        try {
	        	PrintWriter printWriter = new PrintWriter(new FileWriter("Relatorio.txt",true));
	        	
	        	System.out.println(linhaDoCheckin);
	            printWriter.println(linhaDoCheckin);

	            printWriter.close();
	            
	            System.out.println("Linha escrita com sucesso no arquivo.");
	            
	        } catch (IOException e) {
	        	
	            System.out.println("Ocorreu um erro ao escrever a linha no arquivo.");
	            e.printStackTrace();
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
