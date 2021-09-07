package it.unicam.C3.negozio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;


public class Negozio {
	private int ID;
	private String nome;
	private String oraApertura;
	private String oraChiusura;
	private String classeMerceologica;
	
	private ArrayList<Prodotto> prodotti;
	
	public Negozio(int ID, String nome)
	{
		this.ID = ID;
		this.nome=nome;
		this.prodotti  = new ArrayList<Prodotto> ();
	}
	
	
	public Negozio(int ID, String nome, String oraApertura, String oraChiusura, String classeMerceologica) {
		this.ID = ID;
		this.nome = nome;
		this.oraApertura = oraApertura;
		this.oraChiusura = oraChiusura;
		this.classeMerceologica = classeMerceologica;
		this.prodotti = new ArrayList<Prodotto>();
	}
	
	public void addProdotto(int ID, String nome, float prezzo)
	{	
		addProdotto(ID, nome, prezzo, 22);
	}
	
	public void addProdotto(int ID, String nome, float prezzo, int iva)
	{	
		Prodotto prod = new Prodotto(ID, nome,prezzo, iva);
		this.prodotti.add(prod);		
	}
	
	public void addProdotto(Prodotto prod)
	{
		this.prodotti.add(prod);
	}
	
	public Prodotto getProdotto(int ID)
	{
		
        Iterator<Prodotto> iter
            = this.prodotti.iterator();
  
        while (iter.hasNext()) {
        	Prodotto prod = iter.next();
            if ( prod.getID() == ID) {
                return prod;
            }
        }
        return null;
	}
	
	
	public int getID() {
		return ID;
	}

	public String getNome() {
		return nome;	
	}
	
	public String getOraApertura() {
		 return oraApertura; 
	}
	
	public String getOraChiusura() {
		 return oraChiusura; 
	}
	
	public String getClasseMerceologica() {
		 return classeMerceologica; 
	}

}
