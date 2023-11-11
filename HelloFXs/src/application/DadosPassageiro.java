package application;

public class DadosPassageiro{
	
	//private Cliente cliente;
	private String nome;
	private String cpf;
	private String numeroPassagem;
	private double valorTotal;
	private Boolean checkin = false;
	private Voo voo;
	private boolean BagagemExtra;
	private boolean PrimeiraClasse;
	private boolean AluguelDeCarro;
	private boolean Hotel;

	public DadosPassageiro(boolean bagagemExtra, boolean primeiraClasse, boolean aluguelDeCarro, boolean hotel,
			String nome, String cpf, double valorTotal, String numeroPassagem, Voo voo, Boolean checkin) {
		
		//this.cliente = cliente;
		BagagemExtra = bagagemExtra;
		PrimeiraClasse = primeiraClasse;
		AluguelDeCarro = aluguelDeCarro;
		Hotel = hotel;
		this.nome = nome;
		this.cpf = cpf;
		this.valorTotal = valorTotal;
		this.setNumeroPassagem(numeroPassagem);
		this.checkin = checkin;
		this.setVoo(voo);
	}
	
	public DadosPassageiro(boolean bagagemExtra, boolean primeiraClasse, boolean aluguelDeCarro, boolean hotel,
			String nome, String cpf, double valorTotal, String numeroPassagem, Voo voo) {
		
		//this.cliente = cliente;
		BagagemExtra = bagagemExtra;
		PrimeiraClasse = primeiraClasse;
		AluguelDeCarro = aluguelDeCarro;
		Hotel = hotel;
		this.nome = nome;
		this.cpf = cpf;
		this.valorTotal = valorTotal;
		this.setNumeroPassagem(numeroPassagem);
		this.setVoo(voo);
	}

	
	public String getAssento() {
		String assentoString = Integer.toString(voo.getAssentos());
		return assentoString;
	}
	
	public String getCidadePartida() {
        return voo.getCidadePartida();
    }
    
    public String getDataPartida() {
        return voo.getDataIda().toString();
    }
    
    public String getHoraPartida() {
        return voo.getHoraIda().toString();
    }
    
    public String getCidadeDestino() {
        return voo.getCidadeDestino();
    }
    
    public String getDataDestino() {
        return voo.getDataVolta().toString();
    }
    
    public String getHoraDestino() {
        return voo.getHoraVolta().toString();
    }

	public Boolean getCheckin() {
		return checkin;
	}


	public void setCheckin(Boolean checkin) {
		this.checkin = checkin;
	}


	public boolean isBagagemExtra() {
		return BagagemExtra;
	}
	public void setBagagemExtra(boolean bagagemExtra) {
		BagagemExtra = bagagemExtra;
	}
	public boolean isPrimeiraClasse() {
		return PrimeiraClasse;
	}
	public void setPrimeiraClasse(boolean primeiraClasse) {
		PrimeiraClasse = primeiraClasse;
	}
	public boolean isAluguelDeCarro() {
		return AluguelDeCarro;
	}
	public void setAluguelDeCarro(boolean aluguelDeCarro) {
		AluguelDeCarro = aluguelDeCarro;
	}
	public boolean isHotel() {
		return Hotel;
	}
	public void setHotel(boolean hotel) {
		Hotel = hotel;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}


	public String getNumeroPassagem() {
		return numeroPassagem;
	}


	public void setNumeroPassagem(String numeroPassagem) {
		this.numeroPassagem = numeroPassagem;
	}


	public Voo getVoo() {
		return voo;
	}


	public void setVoo(Voo voo) {
		this.voo = voo;
	}
	
	
	

}
