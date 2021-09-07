package it.unicam.C3.utente;



public class Commesso extends Utente {

	private int idNegozio;
	
	public Commesso(int id, String nome, String cognome, String email, String password, int idNegozio) {
		this.setId(id);
        this.setNome(nome);
        this.setCognome(cognome);
        this.setEmail(email);
        this.setPassword(password);
        this.setEmail(email);
        
        this.idNegozio = idNegozio;
    }	
}
