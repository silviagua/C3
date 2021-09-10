package it.unicam.C3.view;

import java.util.Scanner;

import it.unicam.C3.utente.GestoreUtenti;
import it.unicam.C3.utente.Ruolo;
import it.unicam.C3.utente.UtenteCorrente;

public class IUtente {
	private GestoreUtenti gUtenti = GestoreUtenti.getInstance();

	private IUtente() {
		this.gUtenti = GestoreUtenti.getInstance();
	}
	
	public static IUtente createIUtente() {
		IUtente utente = new IUtente();
		return utente;
	}		

	//CASO D'USO Iscrizione
	public void iscrizione(Scanner reader) 
	{
		System.out.println("[INPUT] Inserisci il ruolo del tuo utente (2: GESTORE NEGOZIO - 3: COMMESSO - 4: CLIENTE - 5: CORRIERE)");
		
		Ruolo ruolo = Ruolo.fromInteger(Integer.parseInt(reader.nextLine()));
		
		if (ruolo==Ruolo.admin)
		{
			return;
		}
		
		System.out.println("[INPUT] Inserisci il tuo nome");
		String nome = reader.nextLine();
		
		System.out.println("[INPUT] Inserisci il tuo cognome");
		String cognome = reader.nextLine();
		
		System.out.println("[INPUT] Inserisci la pwd");
		String pwd = reader.nextLine();
		
		System.out.println("[INPUT] Inserisci la mail");
		String mail= reader.nextLine();		
		
		System.out.println("[INPUT] Inserisci la userName");
		String userName= reader.nextLine();
		int idNegozio;
		if (gUtenti.checkUtente(nome, cognome, pwd, mail, userName))
		{
			switch(ruolo)
	        {
	        	case user:
	        		gUtenti.inserisciCliente(nome, cognome, pwd, mail, userName);
	        		break;
	            case corr:
	            	gUtenti.inserisciCorriere(nome, cognome, pwd, mail, userName);
	                break;
	            case comm:
	            	System.out.println("Inserisci ID del negozio");
	            	idNegozio = Integer.parseInt(reader.nextLine());
	            	gUtenti.inserisciCommesso(nome, cognome, pwd, mail, userName, idNegozio);
	                break;
	            case gestn:
	            	System.out.println("Inserisci ID del negozio");
	            	idNegozio = Integer.parseInt(reader.nextLine());
	            	gUtenti.inserisciCommerciante(nome, cognome, pwd, mail, userName, idNegozio);

	            	break;
	            default:
	                System.out.println("no match");
	        }			
		}
		else
		{
			System.out.println("[ERR] user o mail già utilizzati dal sistema");
		}		
	}
	
	//CASO D'USO LOGIN
	public UtenteCorrente login(Scanner reader) 
	{
		System.out.println("[INPUT] Inserisci la userName");
		String userName= reader.nextLine();	

		System.out.println("[INPUT] Inserisci la password");
		String password= reader.nextLine();		
		
		return gUtenti.login(userName, password);
		
	}
	

	
}
