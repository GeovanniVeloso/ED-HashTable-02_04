package model;

public class Produto {

	public int andar;
	public String productName;
	public String descricao;
	
	public Produto(int andar, String productName, String descricao) {
		this.andar = andar;
		this.productName = productName;
		this.descricao = descricao;
	}

	public int getAndar() {
		return andar;
	}

	public String getProductName() {
		return productName;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	
}
