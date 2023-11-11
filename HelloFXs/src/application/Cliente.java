package application;

public class Cliente extends Pessoa{
	
	private double milhas;
	private boolean eCartao;
	private double creditoCartao;
	
	public double getCreditoCartao() {
		return creditoCartao;
	}

	public void setCreditoCartao(double creditoCartao) {
		this.creditoCartao = creditoCartao;
	}

	public boolean getECartao() {
		return eCartao;
	}

	public void setECartao(boolean eCartao) {
		this.eCartao = eCartao;
	}

	public double getMilhas() {
		return milhas;
	}

	public void setMilhas(double milhas) {
		this.milhas = milhas;
	}

	public Cliente(String nome, String email, String senha, String cpf, boolean eADMIN, double milhas) {
		super(nome, email, senha, cpf, eADMIN);
		this.milhas = milhas;
	}
	
	
	
}
