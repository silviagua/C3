package it.unicam.C3.view;


import it.unicam.C3.utente.Commesso;
import it.unicam.C3.utente.GestoreUtenti;
import it.unicam.C3.commercio.GestoreCommercio;
import it.unicam.C3.commercio.Pacco;
import it.unicam.C3.commercio.StatoPacco;
import it.unicam.C3.commercio.TipoDestinazione;
import it.unicam.C3.commercio.Vendita;
import it.unicam.C3.negozio.GestoreNegozi;
import it.unicam.C3.trasporto.GestoreLocker;

import java.util.Scanner;

public class ICommesso {
	private GestoreCommercio gCommercio = GestoreCommercio.getInstance();
	private GestoreUtenti gUtenti = GestoreUtenti.getInstance();
	private GestoreNegozi gNegozi = GestoreNegozi.getInstance();
	private GestoreLocker gLocker = GestoreLocker.getInstance(); 
	private String userName;
	private int idNegozio;
	
	public void setUserName(String userName)
	{
		 this.userName = userName;
	}
	
	public String getUserName()
	{
		return this.userName;
	}
	
	public void setIdNegozio(int idNegozio)
	{
		this.idNegozio = idNegozio;
	}
	
	public int getIdNegozio()
	{
		return this.idNegozio;
	}
	
	private ICommesso() {
		this.gCommercio = GestoreCommercio.getInstance();
		this.gUtenti = GestoreUtenti.getInstance();
		this.gLocker = GestoreLocker.getInstance();
		
		setUserName(((Commesso) gUtenti.getUtenteCorrente().getUtenteCorrente()).getUserName());
		this.setIdNegozio(((Commesso) gUtenti.getUtenteCorrente().getUtenteCorrente()).getNegozio());
	}
	
	public static ICommesso createICommesso() {
		ICommesso commesso = new ICommesso();
		return commesso;
	}	
	
	
	//CASO D'USO ELABORA_VENDITA
	public void elaboraVendita(Scanner reader)
	{
		Vendita vendita;
		
		System.out.println("[INFO] CASO D'USO ELABORA_VENDITA (Commesso " + this.getUserName() + " - negozio " + this.getIdNegozio() + ") ***");
						
		System.out.println("[INPUT] Inserisci l'identificativo del cliente:");
		System.out.println(gUtenti.listaClienti());
		int idCliente = Integer.parseInt(reader.nextLine());		
		
		vendita = gCommercio.iniziaVendita(idCliente, this.getIdNegozio());
		
		//inizio ciclo per inserimento prodotti acquistati
		boolean fineVendita=false;
		
		System.out.println(gNegozi.listaProdotti(this.idNegozio));
		
		while (!fineVendita)
		{
			int idProdotto=0;
			int qta=0;
			System.out.println("[INPUT] Inserisci l'identificativo del prodotto acquistato o 0 per fine vendita:");
			
			idProdotto = Integer.parseInt(reader.nextLine());		
			if (idProdotto==0)
			{
				fineVendita= true;
			}
			else
			{
				System.out.println("[INPUT] Inserisci la quantit?? di prodotti acquistati");
				qta = Integer.parseInt(reader.nextLine());		
				System.out.println(vendita.addProdottoVendita(idProdotto, qta));
			}			
			
		}
		
		System.out.println(String.valueOf( "[OUTPUT] Importo parziale:??? " + vendita.getImporto() + " - IVA:??? " + vendita.getIva() + " Totale importo parziale:??? " + vendita.getTotale() ));
		
		System.out.println("[INPUT] Inserisci 0 per annullare l'acquisto e 1 per indicare che l'acquisto ?? terminato ed ?? stato pagato");
		
		String confermaAttivita = reader.nextLine();
		if(confermaAttivita.equals("1"))
		{
			gCommercio.fineVendita(vendita);
			System.out.println("[OUTPUT] E' stata conclusa con successo la vendita con ID "+ vendita.getIdVendita());
			System.out.println("[OUTPUT] " + gCommercio.getInfo());
		}
		else
		{
			System.out.println("[INFO] vendita annullata");
		}
		

	}
	
	
	//CASO D'USO GENERA_PACCO
	public void generaPacco(Scanner reader)
	{
		System.out.println("[INFO] CASO D'USO GENERA_PACCO (Commesso " + this.getUserName() + " - negozio " + this.getIdNegozio() + ") ***");

		System.out.println("[INFO] " + gCommercio.getInfo());
		Pacco pacco = null;
		
		System.out.println("[INPUT] Inserisci codice vendita");
		String idVendita = reader.nextLine();
		
		pacco=gCommercio.generaPacco(idVendita);
		
		if (pacco==null)
		{
			System.out.println("[ERROR] Hai inserito un codice vendita inesistente!!!");
			return;
		}

		System.out.println("[INPUT] Inserisci 0 se tipo di destinazione ?? indirizzo, inserisci 1 se tipo destinazione ?? un locker");
		int tipoDestinazione = Integer.parseInt(reader.nextLine());
		if (tipoDestinazione==0)
		{
			System.out.println("[INPUT] Inserisci indirizzo");
			String indirizzo = reader.nextLine();	
			
			pacco.setTipoDestinazione(TipoDestinazione.indirizzo);
			pacco.setIndirizzo(indirizzo);
		}
		else
		{
			System.out.println("[INPUT] Inserisci locker");
			System.out.println(gLocker.listaLocker());
			
			int idLocker = Integer.parseInt(reader.nextLine());	
			
			pacco.setTipoDestinazione(TipoDestinazione.locker);
			pacco.setIdLocker(idLocker);
		}
		gCommercio.addPacco(pacco);
		gUtenti.inviaNotifica(pacco.getIdCliente(), "[OUTPUT] E' stato generato il pacco con ID " + pacco.getIdVendita() );
		
	}
	
	//CASO D'USO SELEZIONA_CORRIERE
	public void selezionaCorriere(Scanner reader)
	{
		
		System.out.println("[INFO] CASO D'USO SELEZIONA_CORRIERE (Commesso " + this.getUserName() +" - negozio " + this.getIdNegozio() + ") ***");

		System.out.println("[INPUT] Inserire l'ID del pacco");
		String idPacco = reader.nextLine();
		System.out.println("[INPUT] Inserisci l'ID del corriere");
		System.out.println(gUtenti.listaCorrieri());
		
		int idCorriere = Integer.parseInt(reader.nextLine());
		gCommercio.selezionaCorriere(idPacco, idCorriere);
		gUtenti.inviaNotifica(idCorriere, "[OUTPUT] E' stato assegnato il pacco con ID " + idPacco + " per la consegna" );
	}
	
	//CASO D'USO AFFIDA PACCHI
	public void affidaPacchi(Scanner reader)
	{
		System.out.println("[INFO] CASO D'USO AFFIDA_PACCO (Commesso " + this.getUserName() + " - negozio " + this.getIdNegozio() + ") ***");

		System.out.println("");
		System.out.println("[OUTPUT] Pacchi da affidare: " + gCommercio.getPacchiDaNegozio(this.getIdNegozio(), StatoPacco.daconsegnare) ); 
		System.out.println("[OUTPUT] Corrieri da sccegliere: " + gUtenti.listaCorrieri());
		boolean fineAttivita=false;
		
		while (!fineAttivita)
		{
			System.out.println("[INPUT] Inserire 0 se non ci sono pacchi da affidare a Corriere o IDPACCO");			
		
			String idPacco = reader.nextLine();		
			if (idPacco.equals("0"))
			{
				fineAttivita= true;
			}
			else
			{
				System.out.println("[INPUT] Inserire l'ID del corriere ");
				int idCorriere = Integer.parseInt(reader.nextLine());
				int idCliente = gCommercio.affidaPacco(idPacco, idCorriere);
				
				switch(idCliente)
				{
					case 0:
						System.out.println("[INPUT] Vuoi procedere con il cambio Corriere? (0=NO - 1 = SI)");
						String confermaAttivita = reader.nextLine();
						if (confermaAttivita.equals("1"))
						{
							this.selezionaCorriere(reader);
						}
						
						break;
					case -1:
						break;
					default:
						gUtenti.inviaNotifica(idCliente, "Pacco " + idPacco + " affidato al corriere " + idCorriere);
				}
			}
		}	
	}
	
	
}
