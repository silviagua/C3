package it.unicam.C3.trasporto;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import it.unicam.C3.service.DBLocker;
import it.unicam.C3.utente.Utente;

public class GestoreLocker {
	
	private static GestoreLocker instance;
    private List<Locker> lockers;

    private GestoreLocker() {
    	lockers = new LinkedList<>();
    }    
    
    public static GestoreLocker getInstance() {
        if (instance == null) {
            instance = new GestoreLocker();
        }
        return instance;
    }
    
    public void resetGestoreLocker()
    {
    	lockers = new LinkedList<>();    	
    }

    public void addLocker(int id, String nome, String descrizione, int numCelle) {
    	Locker locker = new Locker(id, nome, descrizione, numCelle);
    	lockers.add(locker);
    }

    public Locker getLocker(int id) {
    	Iterator<Locker> iter = this.lockers.iterator();

	    while (iter.hasNext	()) {
	    	Locker locker = iter.next();
	        if ( locker.getIdLocker() == id) {
	            return locker;
	        }
	    }
	    return null;    	
    }
    
    public int primaCellaLibera(int idLocker)
    {
    	Locker locker = this.getLocker(idLocker);
    	
    	if (locker.equals(null)) return 0;
    	return locker.primaCellaLibera();     
    }
    
    public String listaLocker()
    {
    	String info = "[INFO] Locker: ";
    	Iterator<Locker> iter = this.lockers.iterator();

	    while (iter.hasNext	()) {
	    	Locker locker = iter.next();
	    	
	    	info += locker.toString();
		    }    	
	    return info;
    	
    }    
    /*
    public String occupaCella(int idLocker, String idVendita, int idCella)
    {
    	Iterator<Locker> iter = this.lockers.iterator();

	    while (iter.hasNext	()) {
	    	Locker locker = iter.next();
	        if (locker.getIdLocker() == idLocker) {
	        	locker.occupaCella(idCella);
	        	return locker.getCelle();
	        }
	    }	       	
    	return null;
    }
    */
    
    public String gestisciCella(int idLocker, String idVendita, int idCella, boolean occupa)
    {
    	Iterator<Locker> iter = this.lockers.iterator();

	    while (iter.hasNext	()) {
	    	Locker locker = iter.next();
	        if (locker.getIdLocker() == idLocker) {
	        	if (occupa)
	        	{
	        		locker.occupaCella(idCella);
	        	}
	        	else
	        	{
	        		locker.liberaCella(idCella);
	        	}
	        	try
	        	{
	        		DBLocker dbLocker = DBLocker.getInstance();
	        		dbLocker.updateLocker(locker);
	        	}
	        	catch(Exception ex)
	        	{
	        		System.out.println("[ERR] " + ex.getMessage());
	        	}
	        	
	        	return locker.getCelle();
	        }
	    }	       	
    	return null;
    	
    }
    public void setLockers(List<Locker> lockers)
    {
    	this.lockers = lockers;
    }    
        
}
