package application;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConfirmarCompraController implements Initializable {
	
	@FXML
    private CheckBox aluguelDeCarroCheckBox;

    @FXML
    private CheckBox bagagemExtraCheckBox;

    @FXML
    private Label comentariosLabel;

    @FXML
    private Label formaDePagamentoLabel;

    @FXML
    private CheckBox hotelCheckBox;
    
    @FXML
    private CheckBox milhasCheckBox;
    
    @FXML
    private CheckBox cartaoCheckBox;

    @FXML
    private Label nomeLabel;

    @FXML
    private CheckBox primeiraClasseCheckBox;

    @FXML
    private ChoiceBox<String> opcaoDoAssentoSelecionadoChoiseBox;

    @FXML
    private Label saldoLabel;

    @FXML
    private Label totalLabel;

    @FXML
    private Label vooEscolhidoLabel;
    @FXML
    private Label passageiroLabel;
    @FXML
    private TextField nomeTextField;
    @FXML
    private TextField cpfTextField;
    @FXML
    private Label valorVoo;

    private int valorBagagemExtra = 50;
	private int valorPrimeiraClasse = 100;
	private int valorHotel = 350;
	private int valorAluguelDeCarro = 200;
	private Cliente cliente;
	private Voo voo;
	private double valorTotalDosPassageiros = 0;
	private String assentoSelecionadoChoiceBox;
	private ArrayList<String> listaChoiceBox = new ArrayList<String>();
	
	private DadosPassageiro passageiroUm, passageiroDois, passageiroTres;
	
	
	public void voltarParaMenu(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	


	public void receberDados(String todosOsAssentosClicadosString,String assentosChoiceBoxValor, String cidadePartida,
			String cidadeDestino, LocalDate dataIda, LocalDate dataVolta, LocalTime horaIda, LocalTime horaVolta,
			double valorReais, int valorMilhas, int assentos) {
		
			voo = new Voo(cidadePartida,cidadeDestino,dataIda,dataVolta,horaIda,horaVolta,valorReais,valorMilhas,assentos);
			
			vooEscolhidoLabel.setText("De "+voo.getCidadePartida()+" para " + voo.getCidadeDestino());
			String valorVooString = Double.toString(valorReais);
			String valorVooMilhasString = Integer.toString(valorMilhas);
			valorVoo.setText("Valor do voo em Reais: " + valorVooString+"\nValor do voo em milhas: " + valorVooMilhasString);
			String saldo = "O seu saldo em milhas é de: " + this.cliente.getMilhas() + "\nSeu saldo no cartão é de: " + this.cliente.getCreditoCartao();
			saldoLabel.setText(saldo);
        	
	        comentariosLabel.setText(todosOsAssentosClicadosString);
	        
			String[] partes = todosOsAssentosClicadosString.split("\t");
			
			for (String parte : partes) {
				if(parte.equals("Assentos Selecionados:") || parte.equals("")) {
				}else {					
					opcaoDoAssentoSelecionadoChoiseBox.getItems().addAll(parte);
					listaChoiceBox.add(parte);
				}
			}
			

	        
	}
	public void initialize(URL url, ResourceBundle rb) {
		
		opcaoDoAssentoSelecionadoChoiseBox.setOnAction(event -> {
        	passageiroLabel.setText("Passageiro: " + opcaoDoAssentoSelecionadoChoiseBox.getValue());
        	assentoSelecionadoChoiceBox = opcaoDoAssentoSelecionadoChoiseBox.getValue();
        });
		
	}
	
	public void receberObjeto(Cliente cliente) {
		this.cliente = cliente;
		nomeLabel.setText("Usuário: " + this.cliente.getNome());

	}
	
	@FXML
    void confirmarCompra(ActionEvent event) throws IOException {
		if(opcaoDoAssentoSelecionadoChoiseBox.getItems().isEmpty()) {			
			boolean isCartaoCheckBox = cartaoCheckBox.isSelected();
			boolean isMilhasCheckBox = milhasCheckBox.isSelected();
			double valorCompra = 0;
			int valorCompraEmMilhas = 0;
			if(passageiroUm != null) {
				valorCompra += (passageiroUm.getValorTotal());
			}
			if(passageiroDois != null) {
				valorCompra += (passageiroDois.getValorTotal());
				
			}
			if(passageiroTres != null) {
				valorCompra += (passageiroTres.getValorTotal());
				
			}
			if(isCartaoCheckBox == false && isMilhasCheckBox == false) {
				comentariosLabel.setText("Por favor, selecione uma forma de pagamento.");
			}else if(passageiroUm == null && passageiroDois == null && passageiroTres == null) {
				comentariosLabel.setText("Por favor, cadastre o passageiro.");
			}else if(isMilhasCheckBox == true && isCartaoCheckBox == true) {
				comentariosLabel.setText("Por favor, selecione APENAS uma forma de pagamento.");

				
			}else if(isMilhasCheckBox == true && isCartaoCheckBox == false) {
				
				valorCompraEmMilhas = this.reaisParaMilha(valorCompra);
				comentariosLabel.setText("Valor em milhas de: " + valorCompraEmMilhas);
				if(cliente.getMilhas() < valorCompraEmMilhas) {
					comentariosLabel.setText("Milhas insuficientes. \nValor da atual compra em milhas: " + valorCompraEmMilhas);
					
				}else {
					cliente.setMilhas(cliente.getMilhas()- valorCompraEmMilhas);
					comentariosLabel.setText("Voo comprado com sucesso no valor Total de: " + valorCompraEmMilhas + " milhas.");
					
				}
				
			}else if(isCartaoCheckBox == true) {
				cliente.setCreditoCartao(cliente.getCreditoCartao() - valorCompra);
				comentariosLabel.setText("Voo comprado com sucesso no valor Total de: " + valorCompra + " R$.");
				
			}
		}else {
			comentariosLabel.setText("Ainda falta preencher dados do assento");
		}
		
		System.out.println(voo.toString());
		
		
		String nomeArquivo = "Relatorio.txt";
		PrintWriter printWriter = new PrintWriter(new FileWriter(nomeArquivo,true));
		
		if (passageiroUm != null) {
			printWriter.println(cliente.getEmail()+"\t"+cliente.getCpf()+"\t"+passageiroUm.isBagagemExtra()+"\t"+passageiroUm.isPrimeiraClasse()+"\t"+passageiroUm.isAluguelDeCarro()+"\t"+
			passageiroUm.isHotel()+"\t"+passageiroUm.getNome()+"\t"+passageiroUm.getCpf()+"\t"+passageiroUm.getValorTotal()+"\t"+passageiroUm.getNumeroPassagem()+
			"\t"+voo.toString()+"\t"+passageiroUm.getCheckin());
			
		}
		if (passageiroDois != null) {
			printWriter.println(cliente.getEmail()+"\t"+cliente.getCpf()+"\t"+passageiroDois.isBagagemExtra()+"\t"+passageiroDois.isPrimeiraClasse()+"\t"+passageiroDois.isAluguelDeCarro()+"\t"+
					passageiroDois.isHotel()+"\t"+passageiroDois.getNome()+"\t"+passageiroDois.getCpf()+"\t"+passageiroDois.getValorTotal()+"\t"+passageiroDois.getNumeroPassagem()+
			"\t"+voo.toString()+"\t"+passageiroDois.getCheckin());
		}	
		if (passageiroTres != null) {
			printWriter.println(cliente.getEmail()+"\t"+cliente.getCpf()+"\t"+passageiroTres.isBagagemExtra()+"\t"+passageiroTres.isPrimeiraClasse()+"\t"+passageiroTres.isAluguelDeCarro()+"\t"+
					passageiroTres.isHotel()+"\t"+passageiroTres.getNome()+"\t"+passageiroTres.getCpf()+"\t"+passageiroTres.getValorTotal()+"\t"+passageiroTres.getNumeroPassagem()+
			"\t"+voo.toString()+"\t"+passageiroTres.getCheckin());
		}
		printWriter.close();
		
    }
	
	@FXML
	void apagarDados() {
		
		passageiroUm = null;
		passageiroDois = null;
		passageiroTres = null;
		valorTotalDosPassageiros = 0;
        comentariosLabel.setText("Selecione novamente os passageiros");
		totalLabel.setText("Total: " + "0");
		hotelCheckBox.setSelected(false);
		primeiraClasseCheckBox.setSelected(false);
		aluguelDeCarroCheckBox.setSelected(false);
		bagagemExtraCheckBox.setSelected(false);
		cartaoCheckBox.setSelected(false);
		milhasCheckBox.setSelected(false);
		nomeTextField.setText("");
		cpfTextField.setText("");
		opcaoDoAssentoSelecionadoChoiseBox.getItems().clear();
		opcaoDoAssentoSelecionadoChoiseBox.setValue(null);
		passageiroLabel.setText("Passageiro: ");
		if(listaChoiceBox != null) {
			for(String string: listaChoiceBox) {					
					opcaoDoAssentoSelecionadoChoiseBox.getItems().addAll(string);								
			}
			
		}
	}
	@FXML
	public void verificarValor() {
		
		if(passageiroUm != null) {
			valorTotalDosPassageiros += passageiroUm.getValorTotal();
			System.out.println(passageiroUm.getValorTotal());
		}
		if(passageiroDois != null) {
			valorTotalDosPassageiros += passageiroDois.getValorTotal();
			System.out.println(passageiroDois.getValorTotal());
		}
		if(passageiroTres != null) {
			valorTotalDosPassageiros += passageiroTres.getValorTotal();
			System.out.println(passageiroTres.getValorTotal());
		}
		String total = Double.toString(valorTotalDosPassageiros);
		
		totalLabel.setText("Total: " + total + "R$");
		valorTotalDosPassageiros = 0;
		
	}
	
	public void salvarPassageiro() {
		if(assentoSelecionadoChoiceBox == null) {
	        comentariosLabel.setText("Selecione o assento");

		}else{
			boolean isBagagemExtra = bagagemExtraCheckBox.isSelected();
			boolean isPrimeiraClasse = primeiraClasseCheckBox.isSelected();
			boolean isAluguelDeCarro = aluguelDeCarroCheckBox.isSelected();
			boolean isHotel = hotelCheckBox.isSelected();
			String numeroPassagem = assentoSelecionadoChoiceBox;
			String nome = nomeTextField.getText();
			String cpf = cpfTextField.getText();
			if(cpf.equals("") && nome.equals("")) {
		        comentariosLabel.setText("Digite o CPF e nome");

			}else if(nome.equals("")) {
		        comentariosLabel.setText("Digite o nome");

			}else if(cpf.equals("")) {
		        comentariosLabel.setText("Digite o CPF");

			}else {
				double valorTotal = voo.getValorReais();
				
				if(isBagagemExtra == true) {
					valorTotal += valorBagagemExtra;
				}
				if(isPrimeiraClasse == true) {
					valorTotal += valorPrimeiraClasse;
				}
				if(isAluguelDeCarro == true) {
					valorTotal += valorAluguelDeCarro;
				}
				if(isHotel == true){
					valorTotal += valorHotel;
				}
				
				/////////////
				DadosPassageiro passageiroAuxiliar = new DadosPassageiro(isBagagemExtra, isPrimeiraClasse, isAluguelDeCarro, isHotel, nome, cpf, valorTotal,numeroPassagem, voo);

				if(passageiroUm == null) {
					passageiroUm = passageiroAuxiliar;
					String valorTotalString = Double.toString(valorTotal);
					comentariosLabel.setText("Passageiro: " + nome + " Subtotal: " + valorTotalString + "\n");
		            opcaoDoAssentoSelecionadoChoiseBox.setValue(null);
				    opcaoDoAssentoSelecionadoChoiseBox.getItems().remove(numeroPassagem);


				}else if (passageiroDois == null) {
					passageiroDois = passageiroAuxiliar;
					String valorTotalString = Double.toString(valorTotal);
					comentariosLabel.setText("Passageiro: " + nome + " Subtotal: " + valorTotalString + "\n");
		            opcaoDoAssentoSelecionadoChoiseBox.setValue(null);
				    opcaoDoAssentoSelecionadoChoiseBox.getItems().remove(numeroPassagem);

				}else if (passageiroTres == null) {
					passageiroTres = passageiroAuxiliar;
					String valorTotalString = Double.toString(valorTotal);
					comentariosLabel.setText("Passageiro: " + nome + " Subtotal: " + valorTotalString + "\n");
		            opcaoDoAssentoSelecionadoChoiseBox.setValue(null);
				    opcaoDoAssentoSelecionadoChoiseBox.getItems().remove(numeroPassagem);

				}
			}
			hotelCheckBox.setSelected(false);
			primeiraClasseCheckBox.setSelected(false);
			aluguelDeCarroCheckBox.setSelected(false);
			bagagemExtraCheckBox.setSelected(false);
			cartaoCheckBox.setSelected(false);
			milhasCheckBox.setSelected(false);
			nomeTextField.setText("");
			cpfTextField.setText("");
			passageiroLabel.setText("Passageiro: ");

		}
			/////////////
			
		
		
	}
	
	public int reaisParaMilha(double valorReais) {
	    int milhas = (int) (valorReais / 70 * 1000);
	    return milhas;
	}
	
	public double milhasParaReais(int milhas) {
		double contador = 0;
		while(milhas%1000 == 0) {
			contador++;
			milhas = milhas/1000;
		}
		contador *= 70;
		return contador;
	}
	public int restoMilhas(int milhas) {
		while(milhas%1000 == 0) {
			milhas = milhas/1000;
		}
		return milhas;
	}
	

}