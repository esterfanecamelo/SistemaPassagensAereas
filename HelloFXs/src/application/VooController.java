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

public class VooController implements Initializable {
	
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
	private ChoiceBox<String> assentosChoiceBox;
	@FXML
	private Label assentosEscolhidosLabel;
	
	private Cliente cliente;
	private String assentosEscolhidos;
	private String todosOsAssentosClicados = "Assentos Selecionados:\t";
	private int cont = 0;

	
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Voo> listaVoos = FXCollections.observableArrayList();

        //////////////////////////////////
        tabelaVoos.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novoVoo) -> {
        	assentosChoiceBox.getItems().clear();
        	
        	cont = 0;
        	assentosEscolhidosLabel.setText("");
        	todosOsAssentosClicados = "Assentos Selecionados:\t";
            if (novoVoo != null) {
            	for(int i = 0; i < novoVoo.getAssentos();i++) {
            		String quantidadeDeAssentos = Integer.toString(i+1);
            		assentosChoiceBox.getItems().addAll(quantidadeDeAssentos);
            	}
            }
            
        });
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
        assentosChoiceBox.setOnAction(event -> {
        	if(cont < 3) {
        		cont++;
        		todosOsAssentosClicados += "\t"+assentosChoiceBox.getValue();
        		assentosEscolhidosLabel.setText(todosOsAssentosClicados) ;      		
        	}else {
        		mensagemLabel.setText("Número máximo de assentos permitidos para compra.");
        	}
        });
     

	}

	public void voltarAoInicio(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	
	
	public void comprarVoo(ActionEvent event) throws IOException {
		String verificarSeNaoENulo = "nulo";
		Voo vooSelecionado = tabelaVoos.getSelectionModel().getSelectedItem();
		String assentosChoiceBoxValor = assentosChoiceBox.getValue();
		String[] partes = todosOsAssentosClicados.split("\t");
		if(partes.length > 1) {			
			verificarSeNaoENulo = partes[1];
		}
		
	    if (vooSelecionado != null) {
	    	if(vooSelecionado.getAssentos() == 0) {
	    		mensagemLabel.setText("Voo sem assentos disponiveis");
	    	}else if(verificarSeNaoENulo.equals("nulo")) {
	    		mensagemLabel.setText("Nenhum assento escolhido.");
	    		
	    	}else {	    		
	    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ConfirmarCompra.fxml"));
	    		Parent root = loader.load(); 
	    		ConfirmarCompraController confirmarCompraController = loader.getController();
	    		confirmarCompraController.receberObjeto(this.cliente);
	    		confirmarCompraController.receberDados(todosOsAssentosClicados,assentosChoiceBoxValor,vooSelecionado.getCidadePartida(),vooSelecionado.getCidadeDestino(),vooSelecionado.getDataIda(),vooSelecionado.getDataVolta(),vooSelecionado.getHoraIda(),vooSelecionado.getHoraVolta(),vooSelecionado.getValorReais(),vooSelecionado.getValorMilhas(),vooSelecionado.getAssentos());
	    		
	    		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    		Scene scene = new Scene(root);
	    		stage.setScene(scene);
	    		stage.show();
	    	}
	    	
	    	
	    		
	    	
	    	
	    	
	    } else {	    		
	    	mensagemLabel.setText("Nenhum voo selecionado.");	    	
	    }
	}
	
	public void procurarVoosButton() {
		
	}

	public void receberObjeto(Cliente cliente) {
		this.cliente = cliente;
		nomeClienteLabel.setText("Usuário: " + this.cliente.getNome());
	}

}
