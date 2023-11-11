package application;


public abstract class Pessoa {


	private String nome;
	private String email;
	private String senha;
	private String cpf;
	private boolean eADMIN;
	
	public Pessoa(String nome, String email, String senha, String cpf, boolean eADMIN) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.cpf = cpf;
		this.eADMIN = eADMIN;
	}
	
	
	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getSenha() {
		return senha;
	}



	public void setSenha(String senha) {
		this.senha = senha;
	}



	public String getCpf() {
		return cpf;
	}



	public void setCpf(String cpf) {
		this.cpf = cpf;
	}



	public boolean getEADMIN() {
		return eADMIN;
	}



	public void setEADMIN(boolean eADMIN) {
		this.eADMIN = eADMIN;
	}



	
	
	
	public String toString() {
		return this.nome + this.email + this.senha + this.cpf + this.eADMIN;
	}
}
