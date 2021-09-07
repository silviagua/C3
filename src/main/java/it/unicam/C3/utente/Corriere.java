package it.unicam.C3.utente;



public class Corriere extends Utente {
	public Corriere(){}

    public Corriere(int id, String nome, String cognome, String email, String password) {
    	this.setId(id);
        this.setNome(nome);
        this.setCognome(cognome);
        this.setEmail(email);
        this.setPassword(password);

    }

}
