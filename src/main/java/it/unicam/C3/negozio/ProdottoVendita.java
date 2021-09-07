package it.unicam.C3.negozio;

public class ProdottoVendita extends Prodotto {
	
	private int quantita;

	public ProdottoVendita(int ID, String nome, float prezzo, int ivaOrdinaria) {
		super(ID, nome, prezzo, ivaOrdinaria);
		// TODO Auto-generated constructor stub
	}
	

	public void setQuantita(int qta) {
		this.quantita = qta;
	}
	
	public float getQuantita() {
		return quantita;
	}	

}
