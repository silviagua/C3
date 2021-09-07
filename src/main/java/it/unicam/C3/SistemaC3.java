package it.unicam.C3;

import java.util.Scanner;
import java.util.ArrayList;

import it.unicam.C3.view.InteractionManager;

import it.unicam.C3.negozio.*;
import it.unicam.C3.service.DBManager;
import it.unicam.C3.trasporto.GestoreLocker;
import it.unicam.C3.utente.*;


public class SistemaC3 {
	//Queste informazioni andrebbero specificate all'interno di un file di configurazione iniziale.
	static String citta = "Ascoli Piceno";
	static String descrizione ="WAP";
	static String email = "info@comune.ascolipiceno.ap.it";
	static String tel= "0736";
	
	static String user= "root";
	static String password = "";
	static String host ="localhost";
	static String database = "C3";
	static String connectionstring ="jdbc:mysql://localhost:3306/C3";	
	
	//static ArrayList<Negozio> negoziCentro;
	static GestoreUtenti gUtenti;
	static GestoreNegozi gNegozi;
	static GestoreLocker gLocker;


	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		setSistemaC3(reader);
		
		//Viene avviata l'applicazione che permette interazione da linea di comando
		InteractionManager IM = new InteractionManager();
		IM.startSistemaC3(reader);
	}

	public static void setSistemaC3(Scanner reader) {
		try {
			//carica dati da db
			System.out.println("[INFO] Carico i dati da DB");
			
			DBManager dbManager = DBManager.getInstance();
			dbManager.setDBManager(connectionstring, user, password);
			
			
			gUtenti.setClienti(dbManager.setUtenti(4));
			gUtenti.setCorrieri(dbManager.setUtenti(3));
			
			
			gNegozi.setNegozi(dbManager.setNegozi());
			

			gLocker.setLockers(dbManager.setLocker());			
		
		}		
		catch(Exception ex)
		{
		
			System.out.println("[INFO]: Avvio il sistema C3 - città " + SistemaC3.citta);
	
			//aggiunti tutti i componenti per gestire il sistemaC3
		
			//Add NEGOZI E PRODOTTI
			gNegozi=  GestoreNegozi.getInstance();
			
			
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
			
			Prodotto p = gNegozi.getProdotto(2, 8);
			
	
			//ADD UTENTI (CLIENTI E CORRIERI)
			gUtenti = GestoreUtenti.getInstance();
			gUtenti.addCliente(1,"silvia","gualdesi","silvia@gualdesi.it","password","utente1");
			gUtenti.addCliente(2,"Riccardo", "Pet", "ricc.pet@gmail.com", "password","utente2");
	
			gUtenti.addCorriere(3,"Mario","Rossi","mario@rossi.it","password","corriere1");
			gUtenti.addCorriere(4,"Giuseppe","Verdi","g.verdi@corrierispa.it","password","corriere2");
	
			//ADD LOCKER
			gLocker = GestoreLocker.getInstance();
			gLocker.addLocker(1, "Locker1", "Via Roma,1",5);
			gLocker.addLocker(2, "Locker2", "Via Verdi 2",10);
		}		
	}
}