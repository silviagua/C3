package it.unicam.C3.utente;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import it.unicam.C3.commercio.Vendita;
import it.unicam.C3.service.*;


public class GestoreUtenti {
    private static GestoreUtenti instance;
    private List<Utente> corrieri;
    private List<Utente> clienti;
    private List<Commesso> commessi;
    private UtenteCorrente utenteCorrente;
    
    
    private GestoreUtenti() {
        clienti = new LinkedList<>();
        corrieri = new LinkedList<>();
        commessi = new LinkedList<>();
    }

    private GestoreUtenti(List<Utente> clienti, List<Utente> corrieri, List<Commesso> commessi) {
        this.clienti = clienti;
        this.corrieri = corrieri;
        this.commessi=commessi;
    }

    public static GestoreUtenti getInstance() {
        if (instance == null) {
            instance = new GestoreUtenti();
        }
        return instance;
    }

    public static GestoreUtenti getInstance(List<Utente> clienti, List<Utente> corrieri, List<Commesso> commesso) {
        if (instance == null) {
            instance = new GestoreUtenti(clienti, corrieri, commesso);
        }
        return instance;
    }
    
    public void setClienti(List<Utente> clienti)
    {
    	this.clienti = clienti;
    }
    
    public void setCorrieri(List<Utente> corrieri)
    {
    	this.corrieri = corrieri;
    }
    
    public void setCommessi(List<Commesso> commessi)
    {
    	this.commessi = commessi;
    }
    
    public void addCliente(Cliente cliente)
    {
    	clienti.add(cliente);
    }
    
    
    public void addCliente(int id, String nome, String cognome, String email, String password, String userName) {
        
        Utente newCliente = new Utente(id, nome, cognome, email, password, userName);
        clienti.add(newCliente);
    }    

    public void addCorriere(Corriere corriere)
    {
    	clienti.add(corriere);
    }    
    
    public void addCorriere(int id, String nome, String cognome, String email, String password, String userName){
        
        Corriere newCorriere = new Corriere(id, nome, cognome, email, password, userName);
        corrieri.add(newCorriere);
    }    

    
    public void addCommesso(Commesso commesso)
    {
    	commessi.add(commesso);
    }
    
    
    public void addCommessi(int id, String nome, String cognome, String email, String password, String userName, int idNegozio) {
        
        Commesso newCommesso = new Commesso(id, nome, cognome, email, password, userName, idNegozio);
        clienti.add(newCommesso);
    }    
    
    
    public void inviaNotifica(int idUtente, String Messaggio)
    {
    	//implementare il metodo con invio di una mail
    	System.out.println("[NOTIFY] Utente " + idUtente + " - " + Messaggio);
    	
    }
    
    public String listaCorrieri()
    {
    	String info = "[INFO] CORRIERI: ";
    	Iterator<Utente> iter = this.corrieri.iterator();

	    while (iter.hasNext	()) {
	    	Utente utente = iter.next();
	    	info += "(" + utente.getId() + ") " + utente.getNome() + "-" + utente.getCognome() + "; ";
		    }    	
	    return info;
    }

    public String listaUtenti()
    {
    	String info = "[INFO] UTENTI: ";
    	Iterator<Utente> iter = this.clienti.iterator();

	    while (iter.hasNext	()) {
	    	Utente utente = iter.next();
	    	
	    	info += "(" + utente.getId() + ") " + utente.getNome() + "-" + utente.getCognome() + "; ";
		    }    	
	    return info;
    	
    }
    
    public boolean checkUtente(String nome, String cognome, String pwd, String mail, String username) 
    {
    	//controllo se nel database possono essere inserite le informazioni in input
    	try
    	{
	    	DBUtenti dbUtenti = DBUtenti.getInstance();
	    	
	    	if (dbUtenti.getUtentebyUserName(username)!=null) return false;
	    	
	    	if (dbUtenti.getUtentebyMail(mail) !=null) return false;
    		
	    	return true;   	
    	}
    	catch(Exception ex)
    	{
    		return false;
    	}
    }
    
    public void inserisciCliente (String nome, String cognome, String pwd, String mail, String username) 
    {
    	try
    	{
	    	DBUtenti dbUtenti = DBUtenti.getInstance();
	    	//aggiungo nel db e nella lista in memoria 
	    	Cliente cliente = new Cliente(0,nome, cognome, mail, pwd , username);
	    	
	    	int id = dbUtenti.insertCliente(cliente);
	    	cliente.setId(id);
	    	
	    	this.addCliente(cliente);
    	}
    	catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    	
    }
    
    public void inserisciCorriere (String nome, String cognome, String pwd, String mail, String username) 
    {
    	try
    	{
	    	DBUtenti dbUtenti = DBUtenti.getInstance();
	    	//aggiungo nel db e nella lista in memoria 
	    	Corriere corriere = new Corriere(0,nome, cognome, mail, pwd , username);
	    	
	    	int id = dbUtenti.insertCorriere(corriere);
	    	corriere.setId(id);
	    	
	    	this.addCorriere(corriere);
    	}
    	catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    	
    }
    

    public void inserisciCommesso (String nome, String cognome, String pwd, String mail, String username, int idNegozio) 
    {
    	try
    	{
	    	DBUtenti dbUtenti = DBUtenti.getInstance();
	    	//aggiungo nel db e nella lista in memoria 
	    	Commesso commesso = new Commesso(0,nome, cognome, mail, pwd , username, idNegozio);
	    	
	    	int id = dbUtenti.insertCommesso(commesso);
	    	commesso.setId(id);
	    	
	    	this.addCommesso(commesso);
    	}
    	catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    }
    
    
    public void inserisciCommerciante (String nome, String cognome, String pwd, String mail, String username, int idNegozio) 
    {
    	try
    	{
	    	DBUtenti dbUtenti = DBUtenti.getInstance();
	    	//aggiungo nel db e nella lista in memoria 
	    	Commerciante gestoreNegozio = new Commerciante(0,nome, cognome, mail, pwd , username, idNegozio);
	    	
	    	int id = dbUtenti.insertCommerciante(gestoreNegozio);
	    	gestoreNegozio.setId(id);
	    	
	    	/*
	    		this.addCommesso(commesso);
	    	*/
    	}
    	catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    	
    }    
    
    public UtenteCorrente login(String username, String password)
    {
    	try
    	{
    		DBUtenti dbUtenti = DBUtenti.getInstance();
    		utenteCorrente= new UtenteCorrente( dbUtenti.getUtente(username, password));
    		return utenteCorrente;

    	}
    	catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    		return null;
    	}
    	
    }
    
    public UtenteCorrente getUtenteCorrente()
    {
    	return utenteCorrente;
    }
    
    
    public void logout()
    {
    	utenteCorrente = null;
    }
    
}