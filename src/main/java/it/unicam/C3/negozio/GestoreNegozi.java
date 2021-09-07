package it.unicam.C3.negozio;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


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
    
    
    
}
