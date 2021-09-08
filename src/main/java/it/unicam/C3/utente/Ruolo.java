package it.unicam.C3.utente;

import it.unicam.C3.commercio.StatoPacco;

public enum Ruolo {
	admin,
	gestn,
	comm,
	user,
	corr;
	
	public static Ruolo fromInteger(int x) {
        switch(x) {
        case 1:
            return admin;
        case 2:
            return gestn;
        case 3:
        	return comm;
        case 4:
        	return user;
        case 5:
        	return corr;
        }
        return null;
    }	
	
	
	public static int toInt(Ruolo ruolo) {
        switch(ruolo) {
        case admin:
            return 1;
        case gestn:
            return 2;
        case comm:
        	return 3;
        case user:
        	return 4;
        case corr:
        	return 5;
        }
        return 0;
    }			
}
