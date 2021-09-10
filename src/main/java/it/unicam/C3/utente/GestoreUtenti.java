package it.unicam.C3.utente;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import it.unicam.C3.service.*;


public class GestoreUtenti {
    private static GestoreUtenti instance;
    private List<Corriere> corrieri;
    private List<Cliente> clienti;
    private List<Commesso> commessi;
    private UtenteCorrente<?> utenteCorrente;
    
    
    private GestoreUtenti() {
        clienti = new LinkedList<>();
        corrieri = new LinkedList<>();
        commessi = new LinkedList<>();
    }

    private GestoreUtenti(List<Cliente> clienti, List<Corriere> corrieri, List<Commesso> commessi) {
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

    public static GestoreUtenti getInstance(List<Cliente> clienti, List<Corriere> corrieri, List<Commesso> commesso) {
        if (instance == null) {
            instance = new GestoreUtenti(clienti, corrieri, commesso);
        }
        return instance;
    }
    
    public void resetGestoreUtenti()
    {
        clienti = new LinkedList<>();
        corrieri = new LinkedList<>();
        commessi = new LinkedList<>();    	
    }
    
    public void setClienti(List<Cliente> clienti)
    {
    	this.clienti = clienti;
    }
    
    public void setCorrieri(List<Corriere> corrieri)
    {
    	this.corrieri = corrieri;
    }
    
    public void setCommessi(List<Commesso> commessi)
    {
    	this.commessi = commessi;
    }
    
    private void addCliente(Cliente cliente)
    {
    	clienti.add(cliente);
    }    
    
    
    public void addCliente(int id, String nome, String cognome, String email, String password, String userName) {
        
        Cliente newCliente = new Cliente(id, nome, cognome, email, password, userName);
        clienti.add(newCliente);
    }    

    private void addCorriere(Corriere corriere)
    {
    	corrieri.add(corriere);
    }    
    
    public void addCorriere(int id, String nome, String cognome, String email, String password, String userName){
        
        Corriere newCorriere = new Corriere(id, nome, cognome, email, password, userName);
        corrieri.add(newCorriere);
    }    

    private void addCommesso(Commesso commesso)
    {
    	commessi.add(commesso);
    }
    
    
    public void addCommesso(int id, String nome, String cognome, String email, String password, String userName, int idNegozio) {
        
        Commesso newCommesso = new Commesso(id, nome, cognome, email, password, userName, idNegozio);
        commessi.add(newCommesso);
    }    
    
    
    public void inviaNotifica(int idUtente, String Messaggio)
    {
    	//implementare il metodo con invio di una mail
    	System.out.println("[NOTIFY] Utente " + idUtente + " - " + Messaggio);
    	
    }
    
    
    public String listaCorrieri()
    {
    	String info = "[INFO] CORRIERI: ";
    	Iterator<Corriere> iter = this.corrieri.iterator();

	    while (iter.hasNext	()) {
	    	
	    	Corriere corriere = iter.next();	    	
	    	info +=  corriere.toString();
	    	
		    }    	
	    return info;
    }

    public String listaClienti()
    {
    	String info = "[INFO] CLIENTI: ";
    	Iterator<Cliente> iter = this.clienti.iterator();

	    while (iter.hasNext	()) {
	    	Cliente cliente  = iter.next();
	    	
	    	info += cliente.toString();
		    }    	
	    return info;
    }
    
    
    public boolean checkUtente(String nome, String cognome, String pwd, String mail, String username) 
    {
    	//controllo se nel database possono essere inserite le informazioni in input
    	try
    	{
	    	DBUtenti dbUtenti = DBUtenti.getInstance();
	    	
	    	if (dbUtenti.cercaUtentebyUserName(username)) return false;
	    	
	    	if (dbUtenti.cercaUtentebyMail(mail)) return false;
    		
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
    
    public UtenteCorrente<?> login(String username, String password)
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
    
    public UtenteCorrente<?> getUtenteCorrente()
    {
    	return utenteCorrente;
    }
    
    
    public void logout()
    {
    	utenteCorrente = null;
    }
    
    public int getIdNegozioCommesso(int idCommesso )
    {
    	Iterator<Commesso> iter = this.commessi.iterator();

	    while (iter.hasNext	()) {
	    	Commesso commesso  = iter.next();
	    	if (commesso.getId() == idCommesso)
	    	{
	    		return commesso.getNegozio();
	    	}

	    }    	
	    return 0;
    	
    }
    
    
}