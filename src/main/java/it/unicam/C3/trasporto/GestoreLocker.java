package it.unicam.C3.trasporto;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


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

    public void addLocker(int id, String nome, String descrizione, int numCelle) {
    	Locker locker = new Locker(id, nome, descrizione, numCelle);
    	lockers.add(locker);
    }

	public void setLockers(List<Locker> lockers) {
		// TODO Auto-generated method stub
		this.lockers=lockers;
	}

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
	        	
	        	return locker.getCelle();
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

}
