package it.unicam.C3.interactionManager;

import java.util.Scanner;

import it.unicam.C3.view.ICommesso;
import it.unicam.C3.view.IUtenteC3;

public class InteractionManagerCommesso {
	
	private ICommesso commesso;
	private IUtenteC3 utenteC3;
	
	public InteractionManagerCommesso()
	{
		commesso = ICommesso.createICommesso();	
		utenteC3 = IUtenteC3.createIUtenteC3();
	}
	
	public void view(Scanner reader)
	{
		String richiesta;
		while (true) {
			System.out.println("[INFO] MENU COMMESSO ***");
			System.out.println("[INPUT] Commesso - Scegli 1 per elaborare una vendita");
			System.out.println("[INPUT] Commesso - Scegli 2 per generare un pacco");
			System.out.println("[INPUT] Commesso - Scegli 3 per scegliere un corriere");
			System.out.println("[INPUT] Commesso - Scegli 4 per consegnare il pacco al corriere");
			System.out.println("[INPUT] Commesso - Scegli 8 per visualizzare lo stato delle vendite/pacchi");
			System.out.println("[INPUT] Commesso - Scegli 9 per logout");
			
			System.out.println("[INPUT] Scegli 0 per uscire");
			richiesta = reader.nextLine();
			if (richiesta.equals("0"))
				break;
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
				commesso.affidaPacchi(reader);
				continue;
			}
			
			if(richiesta.equals("8")) {
				utenteC3.visualizzaVendite(reader);
				continue;
			}
			
			if(richiesta.equals("9")) {
				utenteC3.logout(reader);
				
				//Vista Utente Anonimo
				InteractionManagerUtente IM = new InteractionManagerUtente();
				IM.view(reader);				
				continue;
			}
			else {
				continue;
			}			
		}
	}

}
