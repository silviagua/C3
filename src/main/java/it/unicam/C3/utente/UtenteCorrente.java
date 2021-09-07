package it.unicam.C3.utente;

//classe generica per salvare utente corrente di tipo Cliente/Commesso/Corriere

public class UtenteCorrente<T> {

	private T utente;
	
	public UtenteCorrente(T t)
	{
		utente = t;
	}
	
	public T getUtenteCorrente() {
		return utente;
	}
}
