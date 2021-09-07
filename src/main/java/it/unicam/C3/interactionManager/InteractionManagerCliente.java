package it.unicam.C3.interactionManager;

import java.util.Scanner;

import it.unicam.C3.view.ICliente;
import it.unicam.C3.view.IUtenteC3;

public class InteractionManagerCliente {

	private ICliente cliente;
	private IUtenteC3 utenteC3;
	
	public InteractionManagerCliente()
	{
		cliente = ICliente.createICliente();	
		utenteC3 = IUtenteC3.createIUtenteC3();
	}
	
	public void view(Scanner reader)
	{
		String richiesta;
		while (true) {
			System.out.println("[INFO] MENU CLIENTE ***");
			
			System.out.println("[INPUT] Cliente - Scegli 6 per Ritirare pacco dal locker");			
			System.out.println("[INPUT] Cliente - Scegli 9 per logout");			
						
			System.out.println("[INPUT] Scegli 0 per uscire");
			richiesta = reader.nextLine();
			
			if (richiesta.equals("0"))
				break;
			
			if(richiesta.equals("6")) {
				cliente.ritiraPacco(reader);
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
