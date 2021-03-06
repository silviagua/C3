package it.unicam.C3.negozio;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import it.unicam.C3.utente.Utente;


public class GestoreNegozi {
	private static GestoreNegozi instance;
    private List<Negozio> negozi;

    private GestoreNegozi() {
    	negozi = new LinkedList<>();
    }    
    
    public static GestoreNegozi getInstance() {
        if (instance == null) {
            instance = new GestoreNegozi();
        }
        return instance;
    }
    
    public void resetGestoreNegozi()
    {
    	negozi = new LinkedList<>();
    }

    public void addNegozio(int id, String nome) {
        Negozio negozio = new Negozio(id, nome);
        negozi.add(negozio);
    }

    public void addNegozio(Negozio negozio) {
        
        negozi.add(negozio);
    }    
    
    public Negozio getNegozio(int id) {
    	Iterator<Negozio> iter = this.negozi.iterator();

	    while (iter.hasNext	()) {
	    	Negozio negozio = iter.next();
	        if ( negozio.getID() == id) {
	            return negozio;
	        }
	    }
	    return null;    	
    }
    
    public String listaProdotti(int idNegozio)
    {
    	
    	Negozio negozio= this.getNegozio(idNegozio);
    	return negozio.listaProdotti();
    	
    }    
    
    public Prodotto getProdotto(int idNegozio, int idProdotto)
    {
    	Iterator<Negozio> iter = this.negozi.iterator();

	    while (iter.hasNext	()) {
	    	Negozio negozio = iter.next();
	        if ( negozio.getID() == idNegozio) {
	            return negozio.getProdotto(idProdotto);
	        }
	    }
	    return null;      	
    }
    
    public void setNegozi(List <Negozio> negozi)
    {
    	this.negozi = negozi;
    }
    
}
