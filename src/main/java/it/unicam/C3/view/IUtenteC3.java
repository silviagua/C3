package it.unicam.C3.view;

import java.util.Scanner;

import it.unicam.C3.utente.GestoreUtenti;

public class IUtenteC3 {
	private GestoreUtenti gUtenti = GestoreUtenti.getInstance();

	private IUtenteC3() {
		this.gUtenti = GestoreUtenti.getInstance();
	}
	
	public static IUtenteC3 createIUtenteC3() {
		IUtenteC3 utente = new IUtenteC3();
		return utente;
	}
	
	//CASO D'USO Logout
	public void logout(Scanner reader) 
	{
		gUtenti.logout();
	}
}
