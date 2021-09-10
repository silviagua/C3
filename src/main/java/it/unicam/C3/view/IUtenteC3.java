package it.unicam.C3.view;

import java.util.Scanner;

import it.unicam.C3.commercio.GestoreCommercio;
import it.unicam.C3.utente.Cliente;
import it.unicam.C3.utente.GestoreUtenti;
import it.unicam.C3.utente.Ruolo;
import it.unicam.C3.utente.UtenteCorrente;

public class IUtenteC3<T> {
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
	
	//CASO D'USO VISUALIZZA_VENDITE		
	public void visualizzaVendite(Scanner reader)
	{
		GestoreCommercio gCommercio=GestoreCommercio.getInstance();
		UtenteCorrente utenteCorrente=gUtenti.getUtenteCorrente();
		int idUtente=0;
		String utenteClasse ="";
		String info="";
		
		if (utenteCorrente.getUtenteCorrente()!= null) {
			utenteClasse = utenteCorrente.getUtenteCorrente().getClass().toString();
			idUtente = (utenteCorrente.getUtenteCorrente().getId());
		}
		Ruolo ruolo=null;
		if (utenteClasse.equals("class it.unicam.C3.utente.Commesso"))
		{			
			ruolo=Ruolo.comm;
		}
		
		if (utenteClasse.equals("class it.unicam.C3.utente.Corriere"))
		{
			ruolo=Ruolo.corr;
		}
		
		if (utenteClasse.equals("class it.unicam.C3.utente.Cliente"))
		{
			ruolo=Ruolo.user;
		}
		
		if (ruolo!=null) info=gCommercio.visualizzaInfo(idUtente, ruolo);
		System.out.println("[INFO]\n" +info);
	
	}	
}
