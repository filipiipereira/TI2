package model;


public class Carro {
	private int id;
	private String cor;
	private String modelo;
	private String marca;
	private float preco;
	private int ano;	
	
	public Carro() {
		id = -1;
		cor = "";
		modelo = "";
		marca = "";
		preco = 0.00F;
		ano = 0;
	}

	public Carro(int id, String cor, String modelo, String marca, float preco, int ano) {
		setId(id);
		setCor(cor);
		setModelo(modelo);
		setMarca(marca);
		setPreco(preco);
		setAno(ano);
	}		
	
	public int getID() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}
	
	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public float getPreco() {
		return preco;
	}
	
	public void setPreco(float preco) {
		this.preco = preco;
	}

	public int getAno() {
		return ano;
	}
	
	public void setAno(int ano) {
		this.ano = ano;
	}


	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "Carro:  Cor: " + cor + "   Modelo: " + modelo + "   Marca: " + marca + "   Preço: R$" + preco + 
				"   Ano: " + ano;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getID() == ((Carro) obj).getID());
	}	
}