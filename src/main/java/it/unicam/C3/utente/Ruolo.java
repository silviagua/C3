package it.unicam.C3.utente;

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
}
