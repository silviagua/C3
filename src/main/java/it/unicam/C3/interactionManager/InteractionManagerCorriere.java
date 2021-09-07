package it.unicam.C3.interactionManager;

import java.util.Scanner;

import it.unicam.C3.view.ICorriere;
import it.unicam.C3.view.IUtenteC3;

public class InteractionManagerCorriere {
	private ICorriere corriere;
	private IUtenteC3 utenteC3;

	
	public InteractionManagerCorriere()
	{
		corriere = ICorriere.createICorriere();	
		utenteC3 = IUtenteC3.createIUtenteC3();
	}
	
	public void view(Scanner reader)
	{
		String richiesta;
		while (true) {
			System.out.println("[INFO] MENU CORRIERE ***");
			
			System.out.println("[INPUT] Corriere - Scegli 5 per scaricare i pacchi del corriere al locker");			
			System.out.println("[INPUT] Corriere - Scegli 9 per logout");			
						
			System.out.println("[INPUT] Scegli 0 per uscire");
			richiesta = reader.nextLine();
			
			if (richiesta.equals("0"))
				break;
			
			if(richiesta.equals("5")) {
				corriere.scaricaPacchi(reader);
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
