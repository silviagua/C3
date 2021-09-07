package it.unicam.C3.commercio;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import it.unicam.C3.negozio.Negozio;
import it.unicam.C3.utente.Cliente;
import it.unicam.C3.utente.Corriere;
import it.unicam.C3.utente.GestoreUtenti;

public class GestoreCommercio {
	private static GestoreCommercio instance;
	 
	private List<Vendita> vendite;
	private List<Pacco> pacchi;
	
	private GestoreCommercio() {
        vendite = new LinkedList<>();
        pacchi = new LinkedList<>();
    }	
	
	private GestoreCommercio(List<Vendita> vendite) {
        this.vendite = vendite;
    }

    public static GestoreCommercio getInstance() {
        if (instance == null) {
            instance = new GestoreCommercio();
        }
        return instance;
    }

    public static GestoreCommercio getInstance(List<Vendita> vendite) {
        if (instance == null) {
            instance = new GestoreCommercio(vendite);
        }
        return instance;
    }
    
	
	public Vendita iniziaVendita(int IDCliente, int IDNegozio)
	{
		Vendita vendita = new Vendita(IDCliente, IDNegozio);
		return vendita;
	}
	
	public void fineVendita(Vendita vendita)
	{
		vendita.setPagato(true);
		vendite.add(vendita);
		
	}
	
	public String getInfo()
	{
		return "Nro Vendite: " + vendite.size() + " / Nro pacchi " + pacchi.size(); 
	}
	
	public Pacco generaPacco(String idVendita)
	{
		Pacco pacco = null;
		//controllo l'esistenza della vendita e genero una classe di tipo pacco
		Vendita vendita = CercaVendita(idVendita);
		if (vendita != null)
		{
			pacco = new Pacco(idVendita, vendita.getIdCliente());
		}
		
		return pacco;
	}
	
	public void addPacco(Pacco pacco)
	{
		pacco.setStatoPacco(StatoPacco.creato);
		pacchi.add(pacco);
	}
	
	private Vendita CercaVendita(String IDVendita)
	{
		Iterator<Vendita> iter = this.vendite.iterator();

	    while (iter.hasNext	()) {
	    	Vendita vendita = iter.next();
	        if ( vendita.getID().equals(IDVendita)) {
	        	return vendita;
	        }
	    }
	    return null;   			
	}
	
	public void selezionaCorriere(String idVendita, int idCorriere)
	{
		Iterator<Pacco> iter = this.pacchi.iterator();

	    while (iter.hasNext	()) {
	    	Pacco pacco = iter.next();
	        if ( pacco.getIdVendita() == idVendita) {
	        	pacco.setIdCorriere(idCorriere);
	        	pacco.setStatoPacco(StatoPacco.daconsegnare);
	        }
	    }
	    
		
	}
	
	/*
	public void SetPagato(String IDVendita)
	{
		Iterator<Vendita> iter = this.vendite.iterator();

	    while (iter.hasNext	()) {
	    	Vendita vendita = iter.next();
	        if ( vendita.getID() == IDVendita) {
	        	vendita.setPagato(true);
	        	return;
	        }
	    }
	    return;   		
		
	}
	*/
}
