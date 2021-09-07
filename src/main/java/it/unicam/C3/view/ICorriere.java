package it.unicam.C3.view;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import it.unicam.C3.commercio.GestoreCommercio;
import it.unicam.C3.commercio.Pacco;
import it.unicam.C3.trasporto.GestoreLocker;
import it.unicam.C3.utente.Corriere;
import it.unicam.C3.utente.GestoreUtenti;

public class ICorriere {
	
	private GestoreCommercio gCommercio = GestoreCommercio.getInstance();
	private GestoreUtenti gUtenti = GestoreUtenti.getInstance();
	private GestoreLocker gLocker= GestoreLocker.getInstance();
	
	private ICorriere() {
		this.gCommercio = GestoreCommercio.getInstance();
		this.gUtenti = GestoreUtenti.getInstance();
		this.gLocker = GestoreLocker.getInstance();
	}
	
	public static ICorriere createICorriere() {
		ICorriere corriere = new ICorriere();
		return corriere;
	}		

	//CASO D'USO SCARICA_PACCHI
	public void scaricaPacchi(Scanner reader)
	{
		/* l'id del corrire lo prendo dall'utente corrente 
		System.out.println("[INPUT] Inserisci l'identificativo del corriere:");
		int idCorriere = Integer.parseInt(reader.nextLine());
		*/
		int idCorriere = ((Corriere) gUtenti.getUtenteCorrente().getUtenteCorrente()).getId();		
		String userName = ((Corriere) gUtenti.getUtenteCorrente().getUtenteCorrente()).getUserName();

		System.out.println("[INFO] CASO D'USO SCARICA_PACCHI (Corriere " + userName + ") ***");
		
		System.out.println("ID Corriere" + idCorriere);

		System.out.println("[INPUT] Inserisci l'identificativo del locker:");
		System.out.println(gLocker.listaLocker());
		int idLocker = Integer.parseInt(reader.nextLine());		
		
		List<Pacco> pacchiDaConsegnare = gCommercio.getPacchiDaConsegnare(idLocker,idCorriere);
			
		Iterator<Pacco> iter = pacchiDaConsegnare.iterator();
		
		while(iter.hasNext()) {
			Pacco pacco = iter.next();
			
			int cellaLibera = gLocker.primaCellaLibera(idLocker);
			
			if (cellaLibera>0)
			{
				System.out.println("[INPUT] Inserire pacco  " + pacco.getIdVendita() + " nella cella " + cellaLibera + ". Premere 1 quando attività è stata eseguita 0 per annullare ");
				
				if(reader.nextLine().equals("1"))
				{
					gCommercio.inserisciPaccoToLocker(pacco.getIdVendita(), cellaLibera);
					System.out.println("[INFO] Stato Locker " + gLocker.gestisciCella(idLocker, pacco.getIdVendita(), cellaLibera, true) );
					gUtenti.inviaNotifica(pacco.getIdCliente(), "Il pacco " + pacco.getIdVendita() + " è stato depositato al locker scelto");
				}
			}
			else
			{
				System.out.println("[INFO] Non ci sono celle libere nel locker");
				break;
			}			
			
		}		
		
	}	
	
}
