package it.unicam.C3.utente;



public class Commesso extends Utente {

	private int idNegozio;
	
	public Commesso(int id, String nome, String cognome, String email, String password, String userName, int idNegozio) {
		super(id, nome, cognome, email, password, userName);
        
        this.idNegozio = idNegozio;
    }	
	
	public int getNegozio()
	{
		return this.idNegozio;
	}
}
