package it.unicam.C3;

import java.util.Scanner;

import it.unicam.C3.service.DBLocker;
import it.unicam.C3.service.DBNegozi;
import it.unicam.C3.service.DBUtenti;
import it.unicam.C3.interactionManager.InteractionManagerUtente;
import it.unicam.C3.negozio.*;
import it.unicam.C3.trasporto.GestoreLocker;
import it.unicam.C3.utente.*;


public class SistemaC3 {
	
	static String citta;
	static String descrizione;
	static String mail;
	static String telefono;
	
	//static ArrayList<Negozio> negoziCentro;
	static GestoreUtenti gUtenti;
	static GestoreNegozi gNegozi;
	static GestoreLocker gLocker;

	public static void main(String[] args)  {
		Scanner reader = new Scanner(System.in);
		setSistemaC3(reader);
		
		//Viene avviata l'applicazione che permette interazione da linea di comando
		InteractionManagerUtente IM = new InteractionManagerUtente();
		IM.view(reader);
	}

	public static void setSistemaC3(Scanner reader) {

		gNegozi=  GestoreNegozi.getInstance();
		gUtenti = GestoreUtenti.getInstance();
		gLocker = GestoreLocker.getInstance();

		citta = Config.getValore("citta");
		descrizione = Config.getValore("descrizione");
		mail = Config.getValore("mail");
		telefono = Config.getValore("telefono");
		
		System.out.println("[INFO] AVVIO SISTEMA C3 - CITTA' " + citta );

		
		try {
			//carica dati da db
			System.out.println("[INFO] Carico i dati da DB");
			
			DBUtenti dbUtenti = DBUtenti.getInstance();
			gUtenti.setClienti(dbUtenti.setUtenti(Ruolo.user));
			gUtenti.setCorrieri(dbUtenti.setUtenti(Ruolo.corr));
			
			DBNegozi dbNegozi = DBNegozi.getInstance();
			gNegozi.setNegozi(dbNegozi.setNegozi());
			
			DBLocker dbLocker = DBLocker.getInstance();
			gLocker.setLockers(dbLocker.setLocker());			
		
		}		
		catch(Exception ex)
		{
			System.out.println(ex.getLocalizedMessage());
			System.out.println("[INFO] Carico i dati da CODICE");
			//aggiunti tutti i componenti per gestire il sistemaC3 senza gestione del database
		
			//Add NEGOZI E PRODOTTI
			
			Negozio negozio1 = new Negozio (1,"negozio1");
			
			negozio1.addProdotto(1,"prodotto1",10);
			negozio1.addProdotto(2,"prodotto2",1.5f);
			negozio1.addProdotto(3,"prodotto3",6);
			negozio1.addProdotto(4,"prodotto4",2);
			
			gNegozi.addNegozio(negozio1);
			
			negozio1 = new Negozio (2,"negozio2");
			
			negozio1.addProdotto(5,"prodotto21",10);
			negozio1.addProdotto(6,"prodotto22",4);
			negozio1.addProdotto(7,"prodotto23",2.2f);
			negozio1.addProdotto(8,"prodotto24",1);	
			
			gNegozi.addNegozio(negozio1);
						
	
			//ADD UTENTI (CLIENTI E CORRIERI)
			
			gUtenti.addCliente(1,"silvia","gualdesi","silvia@gualdesi.it","password","silvia");
			gUtenti.addCliente(2,"Riccardo", "Pet", "ricc.pet@gmail.com", "password","riccardo");
	
			gUtenti.addCorriere(3,"Mario","Rossi","mario@rossi.it","password","mario");
			gUtenti.addCorriere(4,"Giuseppe","Verdi","g.verdi@corrierispa.it","password","giuseppe");
	
			//ADD LOCKER
			gLocker = GestoreLocker.getInstance();
			gLocker.addLocker(1, "Locker1", "Via Roma,1",5);
			gLocker.addLocker(2, "Locker2", "Via Verdi 2",10);
						
		}
		
	}
}