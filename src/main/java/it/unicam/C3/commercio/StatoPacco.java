package it.unicam.C3.commercio;

import it.unicam.C3.utente.Ruolo;

public enum StatoPacco {
	creato,
	daconsegnare,
	inconsegna,
	ritirato,
	conlocker;
	
	
	public static int toInt(StatoPacco sp) {
        switch(sp) {
        case creato:
            return 1;
        case daconsegnare:
            return 2;
        case inconsegna:
        	return 3;
        case ritirato:
        	return 4;
        case conlocker:
        	return 5;
        }
        return 0;
    }		
	
	public static StatoPacco fromInt(int x) {
        switch(x) {
        case 1:
            return creato;
        case 2:
            return daconsegnare;
        case 3:
        	return inconsegna;
        case 4:
        	return ritirato;
        case 5:
        	return conlocker;
        }
        return null;
    }		
	
	public static String toString(StatoPacco sp) {
		switch(sp) {
		case creato:
			return "CREATO";
		case daconsegnare:
			return "DA CONSEGNARE";
		case inconsegna:
			return "IN CONSEGNA";
		case ritirato:
			return "RITIRATO";
		case conlocker:
			return "CONSEGNATO AL LOCKER";
		}
		return "";
	}
}
