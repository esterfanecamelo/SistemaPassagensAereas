package application;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Voo {
	
	private String cidadePartida;
	private String cidadeDestino;
	private LocalDate dataIda;
	private LocalDate dataVolta;
	private LocalTime horaIda;
	private LocalTime horaVolta;
	private double valorReais;
	private int valorMilhas;
	private int assentos;
	
	
	
	public Voo(String cidadePartida, String cidadeDestino, LocalDate dataIda, LocalDate dataVolta, LocalTime horaIda,
			LocalTime horaVolta, double valorReais, int valorMilhas, int assentos) {
		this.cidadePartida = cidadePartida;
		this.cidadeDestino = cidadeDestino;
		this.dataIda = dataIda;
		this.dataVolta = dataVolta;
		this.horaIda = horaIda;
		this.horaVolta = horaVolta;
		this.valorReais = valorReais;
		this.valorMilhas = valorMilhas;
		this.assentos = assentos;
	}
	
	public String getCidadePartida() {
		return cidadePartida;
	}
	public void setCidadePartida(String cidadePartida) {
		this.cidadePartida = cidadePartida;
	}
	public String getCidadeDestino() {
		return cidadeDestino;
	}
	public void setCidadeDestino(String cidadeDestino) {
		this.cidadeDestino = cidadeDestino;
	}
	public LocalDate getDataIda() {
		return dataIda;
	}
	public void setDataIda(LocalDate dataIda) {
		this.dataIda = dataIda;
	}
	public LocalDate getDataVolta() {
		return dataVolta;
	}
	public void setDataVolta(LocalDate dataVolta) {
		this.dataVolta = dataVolta;
	}
	public LocalTime getHoraIda() {
		return horaIda;
	}
	public void setHoraIda(LocalTime horaIda) {
		this.horaIda = horaIda;
	}
	public LocalTime getHoraVolta() {
		return horaVolta;
	}
	public void setHoraVolta(LocalTime horaVolta) {
		this.horaVolta = horaVolta;
	}
	public double getValorReais() {
		return valorReais;
	}
	public void setValorReais(double valorReais) {
		this.valorReais = valorReais;
	}
	public int getValorMilhas() {
		return valorMilhas;
	}
	public void setValorMilhas(int valorMilhas) {
		this.valorMilhas = valorMilhas;
	}
	public int getAssentos() {
		return assentos;
	}
	public void setAssentos(int assentos) {
		this.assentos = assentos;
	} 
	
	

	
	public String toString() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		String dataIdaString = dataIda.format(formatter);
		String[] partesDaDataIda = dataIdaString.split("-");
		String novaDataIda = partesDaDataIda[2] + "/" + partesDaDataIda[1] + "/" + partesDaDataIda[0];
		
		String dataVoltaString = dataVolta.format(formatter);
		String[] partesDaDataVolta = dataVoltaString.split("-");
		String novaDataVolta = partesDaDataVolta[2] + "/" + partesDaDataVolta[1] + "/" + partesDaDataVolta[0];

		return cidadePartida +"\t"+ cidadeDestino +"\t" + novaDataIda
				+"\t" + novaDataVolta +"\t"+ horaIda +"\t"+ horaVolta 
				+"\t"+ valorReais +"\t"+ valorMilhas +"\t"+ assentos;
	}
	

}
