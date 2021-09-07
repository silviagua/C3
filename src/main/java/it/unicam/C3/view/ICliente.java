package it.unicam.C3.view;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import it.unicam.C3.commercio.GestoreCommercio;
import it.unicam.C3.commercio.Pacco;
import it.unicam.C3.trasporto.GestoreLocker;
import it.unicam.C3.utente.Cliente;
import it.unicam.C3.utente.GestoreUtenti;

public class ICliente {
	private GestoreCommercio gCommercio = GestoreCommercio.getInstance();
	private GestoreUtenti gUtenti = GestoreUtenti.getInstance();
	private GestoreLocker gLocker= GestoreLocker.getInstance();
	
	private ICliente() {
		this.gCommercio = GestoreCommercio.getInstance();
		this.gUtenti = GestoreUtenti.getInstance();
		this.gLocker = GestoreLocker.getInstance();
	}
	
	public static ICliente createICliente() {
		ICliente cliente = new ICliente();
		return cliente;
	}		
	
	//CASO D'USO RITIRA_PACCO
	
	public void ritiraPacco(Scanner reader)
	{
		System.out.println("[INPUT] Inserisci l'id del pacco da ritirare");
		String idVendita=reader.nextLine();
		
		System.out.println("[INPUT] Inserisci id del locker di ritiro");
		int idLocker= Integer.parseInt(reader.nextLine());	
		
		Pacco pacco = gCommercio.getPaccoDaRitirare(idVendita);
		if (pacco==null)
		{
			System.out.println("[ERR] Errore idPacco inesistente");
		}
		else
		{
			List<Pacco> pacchiDaRitirare = gCommercio.getPacchiDaRitirare(pacco.getIdCliente(), idLocker);
			
			if (pacchiDaRitirare==null)
			{
				System.out.println("[ERR] Errore non ci sono pacchi da ritirare nel Locker " + idLocker);
			}
			else
			{				
				Iterator<Pacco> iter = pacchiDaRitirare.iterator();
				
				while(iter.hasNext()) {
					Pacco paccoCurr = iter.next();
					
					int cellaPacco = paccoCurr.getIdCella();
					int idLockerPacco = paccoCurr.getIdLocker();
					System.out.println("[INFO] Premi 1 per ritirare il pacco " + paccoCurr.getIdVendita() + " nella cella " + cellaPacco + " - Premi 0 per annullare");
					
					if(reader.nextLine().equals("1"))
					{						
						gCommercio.prelevaPacco(paccoCurr.getIdVendita());
						String celle = gLocker.gestisciCella(idLockerPacco, 
								paccoCurr.getIdVendita(), 
								cellaPacco, 
								false);
						System.out.println("[INFO] Gestione Celle del locker " + celle );
						
						gUtenti.inviaNotifica(paccoCurr.getIdCliente(), "Il pacco " + pacco.getIdVendita() + " è stato ritirato dal locker " + idLockerPacco);
					}
				}
							
			}
		}
	}

}
