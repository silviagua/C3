package it.unicam.C3.view;

import java.util.Scanner;

public class InteractionManager {

	private ICommesso commesso;
	
	/*
	private ICameriere cameriere;
	private IAddettoSala addettoSala;
	private IGestore gestore;
*/	
	public InteractionManager() {
	
		commesso = ICommesso.createICommesso();
	}

	public void startSistemaC3(Scanner reader) {
		String richiesta;
		while (true) {
			System.out.println("[INFO] Benvenuti nel sistema C3");
			System.out.println("[INFO] Scegli cosa vuoi fare:");
			System.out.println("[INPUT] Scegli 1 per elaborare una vendita");
			System.out.println("[INPUT] Scegli 2 per generare un pacco");
			System.out.println("[INPUT] Scegli 3 per scegliere un corriere");

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
			else {
				continue;
			}
			
		}
		reader.close();
	}

}
