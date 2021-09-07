package it.unicam.C3.negozio;

public class Prodotto {
	private int id;
	private String nome;
	private float prezzo;
	private int ivaOrdinaria;
	
	public Prodotto (int id, String nome, float prezzo, int ivaOrdinaria)
	{
		this.id = id;
		this.nome = nome;
		this.prezzo = prezzo;
		this.ivaOrdinaria = ivaOrdinaria;
	}
	
	public int getId()
	{
		return id;
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
