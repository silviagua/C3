package it.unicam.C3.utente;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import it.unicam.C3.commercio.Vendita;


public class GestoreUtenti {
    private static GestoreUtenti instance;
    private List<Corriere> corrieri;
    private List<Cliente> clienti;

    private GestoreUtenti() {
        clienti = new LinkedList<>();
        corrieri = new LinkedList<>();
    }

    private GestoreUtenti(List<Cliente> clienti, List<Corriere> corrieri) {
        this.clienti = clienti;
        this.corrieri = corrieri;
    }

    public static GestoreUtenti getInstance() {
        if (instance == null) {
            instance = new GestoreUtenti();
        }
        return instance;
    }

    public static GestoreUtenti getInstance(List<Cliente> clienti, List<Corriere> corrieri) {
        if (instance == null) {
            instance = new GestoreUtenti(clienti, corrieri);
        }
        return instance;
    }
    
    public void addCliente(int id, String nome, String cognome, String email, String password) {
        
        Cliente newCliente = new Cliente(id, nome, cognome, email, password);
        clienti.add(newCliente);
    }    
    
    public void addCorriere(int id, String nome, String cognome, String email, String password){
        
        Corriere newCorriere = new Corriere(id, nome, cognome, email, password);
        corrieri.add(newCorriere);
    }    
    
    public void inviaNotifica(int idUtente, String Messaggio)
    {
    	//implementare il metodo con invio di una mail
    	
    	
    }
    
    public String listaCorrieri()
    {
    	String info = "";
    	Iterator<Cliente> iter = this.clienti.iterator();

	    while (iter.hasNext	()) {
	    	Cliente cliente = iter.next();
	    	info += cliente.getId() + " " + cliente.getNome() + " " + cliente.getCognome();
		    }    	
	    return info;
    }

}