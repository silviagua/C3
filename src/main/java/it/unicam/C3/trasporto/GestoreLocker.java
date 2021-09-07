package it.unicam.C3.trasporto;

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

    public void addLocker(int id, String nome, String descrizione) {
    	Locker locker = new Locker(id, nome, descrizione);
    	lockers.add(locker);
    }

}
