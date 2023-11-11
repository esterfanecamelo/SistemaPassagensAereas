package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class VerVooController implements Initializable {
	
	@FXML
	private TableView <Voo> tabelaVoos;
	@FXML
	private TableColumn <Voo, String> dataPartidaColuna;
	@FXML
	private TableColumn <Voo, String> horarioPartidaColuna;
	@FXML
	private TableColumn <Voo, String> partidaColuna;
	@FXML
	private TableColumn <Voo, String> dataDestinoColuna;
	@FXML
	private TableColumn <Voo, String> horarioDestinoColuna;
	@FXML
	private TableColumn <Voo, String> destinoColuna;
	@FXML
	private TableColumn <Voo, String> valorReaisColuna;
	@FXML
	private TableColumn <Voo, String> valorMilhasColuna;
	@FXML
	private TableColumn <Voo, String> assentosColuna;
	@FXML
	private CheckBox cartaoCheckBox;
	@FXML
	private CheckBox milhasCheckBox;
	@FXML
	private Label mensagemLabel;
	@FXML
	private Label nomeClienteLabel;
	/*@FXML
	private CheckBox bagagemExtraCheckBox;
	@FXML
	private CheckBox primeiraClasseCheckBox;
	@FXML
	private CheckBox hotelCheckBox;
	@FXML
	private CheckBox aluguelDeCarroCheckBox;*/
	@FXML
	private Label assentosEscolhidosLabel;
	
	private Admin admin;
	private Cliente cliente;
	private String assentosEscolhidos;
	private String todosOsAssentosClicados = "Assentos Selecionados:\t";
	private int cont = 0;
	private Voo voo;

	
	
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Voo> listaVoos = FXCollections.observableArrayList();

        //////////////////////////////////
        //////////////////////////////////
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
	            
	  
	    		DateTimeFormatter formatterdataIda = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    		LocalDate dataIdaLocalDate = LocalDate.parse(dataIda, formatterdataIda);

	  
	    		DateTimeFormatter formatterdataVolta = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    		LocalDate dataVoltaLocalDate = LocalDate.parse(dataVolta, formatterdataVolta);
	    		
	
	    		DateTimeFormatter formatterhoraIda = DateTimeFormatter.ofPattern("HH:mm");
	    		LocalTime horaIdaLocalTime = LocalTime.parse(horaIda, formatterhoraIda);
	    		

	    		DateTimeFormatter formatterhoraVolta = DateTimeFormatter.ofPattern("HH:mm");
	    		LocalTime horaVoltaLocalTime = LocalTime.parse(horaVolta, formatterhoraVolta);
	    		
	    		int valorMilhasInt = Integer.parseInt(valorMilhas);
	    		
	    		Double valorReaisDouble = Double.parseDouble(valorReais);
	    		
	    		Integer assentosInt = Integer.parseInt(assentos);
	    		
	            listaVoos.add(new Voo(partida, destino, dataIdaLocalDate, dataVoltaLocalDate, 
	            		horaIdaLocalTime, horaVoltaLocalTime , valorReaisDouble , valorMilhasInt, assentosInt));
	        }
	        br.close();
	    } catch (IOException e) {
	        System.out.println("Ocorreu um erro ao ler o arquivo de dados.");
	        e.printStackTrace();
	    }
	    
        


        dataPartidaColuna.setCellValueFactory(new PropertyValueFactory<Voo, String>("dataIda")); 
        horarioPartidaColuna.setCellValueFactory(new PropertyValueFactory<Voo, String>("horaIda"));
        partidaColuna.setCellValueFactory(new PropertyValueFactory<Voo, String>("cidadePartida"));
        
        dataDestinoColuna.setCellValueFactory(new PropertyValueFactory<Voo, String>("dataVolta"));
        horarioDestinoColuna.setCellValueFactory(new PropertyValueFactory<Voo, String>("horaVolta"));
        destinoColuna.setCellValueFactory(new PropertyValueFactory<Voo, String>("cidadeDestino"));
        
        valorReaisColuna.setCellValueFactory(new PropertyValueFactory<Voo, String>("valorReais"));
        valorMilhasColuna.setCellValueFactory(new PropertyValueFactory<Voo, String>("valorMilhas"));
        assentosColuna.setCellValueFactory(new PropertyValueFactory<Voo, String>("assentos"));
        
        tabelaVoos.setItems(listaVoos);
     

	}
	
	public void mostrarRelatoriosAdmin(ActionEvent event) throws IOException {
		Voo vooSelecionado = tabelaVoos.getSelectionModel().getSelectedItem();
		if(vooSelecionado != null) {
			System.out.println(vooSelecionado);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/RelatoriosAdmin.fxml"));
			Parent root = loader.load();
			RelatoriosAdminController relatorioAdminController = loader.getController();
			relatorioAdminController.receberObjeto(vooSelecionado);

			relatorioAdminController.mostrarTabela();
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	 else {	    		
	    	mensagemLabel.setText("Nenhum voo selecionado.");	    	
	    }
	}

	public void voltarAoInicio(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	public void procurarVoosButton() {
		
	}
	public void receberObjeto(Admin admin) {
		this.admin = admin;
		//nomeClienteLabel.setText("Usuário: " + this.cliente.getNome());
	}

}
