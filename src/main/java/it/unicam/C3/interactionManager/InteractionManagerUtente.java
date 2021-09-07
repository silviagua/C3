package it.unicam.C3.interactionManager;


import java.util.Scanner;

import it.unicam.C3.utente.UtenteCorrente;
import it.unicam.C3.view.IUtente;

public class InteractionManagerUtente {

	private IUtente utente;
	
	
	public InteractionManagerUtente() {
		utente = IUtente.createIUtente();
	}

	public void view(Scanner reader) {
		String richiesta;
		while (true) {
			System.out.println("[INFO] Benvenuti nel sistema C3");
			System.out.println("[INFO] MENU UTENTE ANONIMO ***");
			/*
			System.out.println("[INPUT] Commesso - Scegli 1 per elaborare una vendita");
			System.out.println("[INPUT] Commesso - Scegli 2 per generare un pacco");
			System.out.println("[INPUT] Commesso - Scegli 3 per scegliere un corriere");
			System.out.println("[INPUT] Commesso - Scegli 4 per consegnare il pacco al corriere");
			System.out.println("[INPUT] Corriere - Scegli 5 per scaricare i pacchi del corriere al locker");			
			System.out.println("[INPUT] Cliente - Scegli 6 per Ritirare pacco dal locker");
			*/
			System.out.println("[INPUT] Utente - Scegli 7 per iscriverti al sistema");
			System.out.println("[INPUT] Utente - Scegli 8 per fare il login");
			
			System.out.println("[INPUT] Scegli 0 per uscire");
			richiesta = reader.nextLine();
			
			if (richiesta.equals("0"))
				break;
			/*
			if (richiesta.equals("1")) {
				commesso.elaboraVendita(reader);
				continue;
			}
			if (richiesta.equals("2")) {
				commesso.generaPacco(reader);
				continue;
			} 
			if (richiesta.equals("3")) {
				commesso.selezionaCorriere(reader);
				continue;
			}
			if(richiesta.equals("4")) {
				commesso.affidaPacco(reader);
				continue;
			}
			if(richiesta.equals("5")) {
				corriere.scaricaPacchi(reader);
				continue;
			}			
			if(richiesta.equals("6")) {
				cliente.ritiraPacco(reader);
				continue;
			}
			*/
			if(richiesta.equals("7")) {
				utente.iscrizione(reader);
				continue;
			}		
			if(richiesta.equals("8")) {
				UtenteCorrente utenteCorrente = utente.login(reader);
				
				String utenteClasse ="";
				if (utenteCorrente.getUtenteCorrente()!= null) {
					utenteClasse = utenteCorrente.getUtenteCorrente().getClass().toString();
				}
				else
				{
					System.out.println("[ERR] Utente corrente trovato");
					continue;
				}
				
				if (utenteClasse.equals("class it.unicam.C3.utente.Commesso"))
				{
					//Vista Commesso
					InteractionManagerCommesso IM = new InteractionManagerCommesso();
					IM.view(reader);						
					continue;
				}

				if (utenteClasse.equals("class it.unicam.C3.utente.Corriere"))
				{
					//Vista Corriere
					InteractionManagerCorriere IM = new InteractionManagerCorriere();
					IM.view(reader);						
					continue;
				}

				if (utenteClasse.equals("class it.unicam.C3.utente.Cliente"))
				{
					//Vista Cliente
					InteractionManagerCliente IM = new InteractionManagerCliente();
					IM.view(reader);						
					continue;
				}


			}			
			
			else {
				continue;
			}
						
		}
		reader.close();
	}

}
