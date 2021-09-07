package it.unicam.C3.negozio;

public class Prodotto {
	private int ID;
	private String nome;
	private float prezzo;
	private int ivaOrdinaria;
	
	public Prodotto (int ID, String nome, float prezzo, int ivaOrdinaria)
	{
		this.ID = ID;
		this.nome = nome;
		this.prezzo = prezzo;
		this.ivaOrdinaria = ivaOrdinaria;
	}
	
	public int getID()
	{
		return ID;
	}
	
	public String getNome()
	{
		return nome;
	}
	
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	
	public float getPrezzo() {
		return prezzo;
	}
	
	public void setIvaOrdinaria(int ivaOrdinaria) {
		this.ivaOrdinaria = ivaOrdinaria;
	}
	
	public int getIvaOrdinaria() {
		return ivaOrdinaria;
	}
	
}
