package it.unicam.C3.commercio;

public enum TipoDestinazione {
		indirizzo,
		locker;



	public static int toInt(TipoDestinazione td) {
	    switch(td) {
	    case indirizzo:
	        return 1;
	    case locker:
	        return 2;
	    }
	    return 0;
	}		
	
	public static TipoDestinazione fromInt(int x) {
	    switch(x) {
	    case 1:
	        return indirizzo;
	    case 2:
	        return locker;
	    }
	    return null;
	}

}
