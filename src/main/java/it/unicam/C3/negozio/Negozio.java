package it.unicam.C3.negozio;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import it.unicam.C3.utente.Utente;


public class Negozio {
	private int id;
	private String nome;
	private String oraApertura;
	private String oraChiusura;
	private String classeMerceologica;
	
	private List<Prodotto> prodotti;
	
	public Negozio(int id, String nome)
	{
		this.id = id;
		this.nome=nome;
		this.prodotti  = new LinkedList<>();
	}
	
	
	public String listaProdotti()
    {
    	String info = "[INFO] Prodotti: ";
    	Iterator<Prodotto> iter = this.prodotti.iterator();

	    while (iter.hasNext	()) {
	    	Prodotto prodotto = iter.next();
	    	info += "(" + prodotto.getId() + ") " + prodotto.getNome() + "-€" + prodotto.getPrezzo() + "-IVA %" + prodotto.getIvaOrdinaria()+"; ";
		    }    	
	    return info;
    }	
	
	public Negozio(int id, String nome, String oraApertura, String oraChiusura, String classeMerceologica) {
		this.id = id;
		this.nome = nome;
		this.oraApertura = oraApertura;
		this.oraChiusura = oraChiusura;
		this.classeMerceologica = classeMerceologica;
		this.prodotti = new LinkedList<>();
	}
	
	public void addProdotto(int id, String nome, float prezzo)
	{	
		addProdotto(id, nome, prezzo, 22);
	}
	
	public void addProdotto(int id, String nome, float prezzo, int iva)
	{	
		Prodotto prod = new Prodotto(id, nome,prezzo, iva);
		this.prodotti.add(prod);		
	}
	
	public void setProdotti(List<Prodotto> prodotti)
	{
		this.prodotti = prodotti;
	}
	
	
	public void addProdotto(Prodotto prod)
	{
		this.prodotti.add(prod);
	}
	
	public Prodotto getProdotto(int id)
	{		
        Iterator<Prodotto> iter
            = this.prodotti.iterator();
  
        while (iter.hasNext()) {
        	Prodotto prod = iter.next();
            if ( prod.getId() == id) {
                return prod;
            }
        }
        return null;
	}
	
	
	public int getID() {
		return id;
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
