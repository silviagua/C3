package it.unicam.C3.utente;

public class Commerciante extends Utente {

	private int idNegozio;
	
	public Commerciante(int id, String nome, String cognome, String email, String password, String userName, int idNegozio) {
		super(id, nome, cognome, email, password, userName);
        
        this.idNegozio = idNegozio;
    }	
	
	public int getNegozio()
	{
		return this.idNegozio;
	}
}
