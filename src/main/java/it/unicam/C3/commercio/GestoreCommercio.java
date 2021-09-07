package it.unicam.C3.commercio;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import it.unicam.C3.service.DBUtenti;
import it.unicam.C3.service.DBCommercio;

public class GestoreCommercio {
	private static GestoreCommercio instance;
	 
	private List<Vendita> vendite;
	private List<Pacco> pacchi;
	
	private GestoreCommercio() {
        vendite = new LinkedList<>();
        pacchi = new LinkedList<>();
    }	
	
	private GestoreCommercio(List<Vendita> vendite) {
        this.vendite = vendite;
    }

    public static GestoreCommercio getInstance() {
        if (instance == null) {
            instance = new GestoreCommercio();
        }
        return instance;
    }

    public static GestoreCommercio getInstance(List<Vendita> vendite) {
        if (instance == null) {
            instance = new GestoreCommercio(vendite);
        }
        return instance;
    }
    
	
	public Vendita iniziaVendita(int IDCliente, int IDNegozio)
	{
		Vendita vendita = new Vendita(IDNegozio, IDCliente);
		return vendita;
	}
	
	public void fineVendita(Vendita vendita)
	{
		int idVendita=0;
		//salvo vendita su db
		try
    	{
			DBCommercio dbVendita = DBCommercio.getInstance();
			idVendita=dbVendita.insertVendita(vendita);
	    	if (idVendita==0)    	
	    	{
	    		return;
	    	}
    	}
    	catch(Exception ex)
    	{
    		System.out.println("[ERR] " + ex.getMessage());
    		return;
    	}		
		
		vendita.setPagato(true);
		vendita.setId(idVendita);
		vendite.add(vendita);
		
	}
	
	public String getInfo()
	{
		return "Nro Vendite: " + vendite.size() + " / Nro pacchi " + pacchi.size(); 
	}
	
	public Pacco generaPacco(String idVendita)
	{
		Pacco pacco = null;
		//controllo l'esistenza della vendita e genero una classe di tipo pacco
		Vendita vendita = cercaVendita(idVendita);
		if (vendita != null)
		{
			pacco = new Pacco(idVendita, vendita.getIdCliente(), vendita.getIdNegozio(), vendita.getId());
		}
		
		return pacco;
	}
	
	public void addPacco(Pacco pacco)
	{
		//salvo vendita su db
		try
    	{
			pacco.setStatoPacco(StatoPacco.creato);
			
			DBCommercio dbVendita = DBCommercio.getInstance();
			dbVendita.insertPacco(pacco);
			
			pacchi.add(pacco);
					
    	}
    	catch(Exception ex)
    	{
    		System.out.println("[ERR] " + ex.getMessage());
    	}
	}
	
	private Vendita cercaVendita(String idVendita)
	{
		Iterator<Vendita> iter = this.vendite.iterator();

	    while (iter.hasNext	()) {
	    	Vendita vendita = iter.next();
	        if ( vendita.getIdVendita().equals(idVendita)) {
	        	return vendita;
	        }
	    }
	    return null;   			
	}
	
	public void selezionaCorriere(String idVendita, int idCorriere)
	{
		Iterator<Pacco> iter = this.pacchi.iterator();

	    while (iter.hasNext	()) {
	    	Pacco pacco = iter.next();
	        if ( pacco.getIdVendita().equals(idVendita)) {
	        	pacco.setIdCorriere(idCorriere);
	        	pacco.setStatoPacco(StatoPacco.daconsegnare);
	        	try
	        	{
	        		DBCommercio dbVendita = DBCommercio.getInstance();
	    			dbVendita.updatePacco(pacco);       		
	        	}
	        	catch(Exception ex)
	        	{
	        		System.out.println("[ERR] " + ex.getMessage());
	        	}	        	
	        }
	    }   
	}
	
	
	public List<Pacco> getPacchiDaConsegnare(int idLocker, int idCorriere)
	{
		List<Pacco> pacchiDaConsegnare = new LinkedList<>();
		Iterator<Pacco> iter = this.pacchi.iterator();
		
		while(iter.hasNext()) {
			Pacco pacco = iter.next();
			if (pacco.getIdLocker() == idLocker &&
				pacco.getIdCorriere() == idCorriere &&
				pacco.getStatoPacco() == StatoPacco.inconsegna)
			{
				pacchiDaConsegnare.add(pacco);
			}
		}
		return pacchiDaConsegnare;
		
	}
	
	public void inserisciPaccoToLocker(String idVendita, int cella)
	{
		Iterator<Pacco> iter = this.pacchi.iterator();
		
		while(iter.hasNext()) {
			Pacco pacco = iter.next();
			if (pacco.getIdVendita().equals(idVendita) )
			{
				
				try {
				//Modifica stato anche su DB
				DBCommercio dbCommercio = DBCommercio.getInstance();
				dbCommercio.updatePacco(pacco);
				
				pacco.setStatoPacco(StatoPacco.conlocker);
				pacco.setIdCella(cella);	
				
				}
				catch(Exception ex)
				{
					System.out.println("[ERR] " + ex.getMessage());
				}
			}
		}
				
	}
	
	
	public String getPacchiDaNegozio(int idNegozio, StatoPacco statoPacco)
	{
		String strPacchi="";
		Iterator<Pacco> iter = this.pacchi.iterator();
		
		while(iter.hasNext()) {
			Pacco pacco = iter.next();
			if (pacco.getIdNegozio() == idNegozio && 
				pacco.getStatoPacco() == statoPacco)
			{
				if (strPacchi != "") strPacchi += " - ";
				strPacchi += pacco.getIdVendita();
			}
		}
		return strPacchi;
	}
	
	//ritorna l'id del cliente utile per l'invio del messaggio di notifica
	public int affidaPacco(String idVendita, int idCorriere)
	{
		String infoOperazione="";
		boolean paccoTrovato = false;
		Iterator<Pacco> iter = this.pacchi.iterator();
		
		while(iter.hasNext()) {
			Pacco pacco = iter.next();
			if (pacco.getIdVendita().equals(idVendita))
			{				
				if (pacco.getStatoPacco()==StatoPacco.daconsegnare)
				{
					
					if(pacco.checkCorriere(idCorriere) )
					{
						pacco.setStatoPacco(StatoPacco.inconsegna);
						
						try
			        	{
			        		DBCommercio dbVendita = DBCommercio.getInstance();
			    			dbVendita.updatePacco(pacco);
			    			infoOperazione ="[INFO] Operazione avvenuta con successo";
			    			
			    			return pacco.getIdCliente();
			        	}
			        	catch(Exception ex)
			        	{
			        		System.out.println("[ERR] " + ex.getMessage());
			        		return -1;
			        	}						
						
					}
					else
					{
						System.out.println("[ERR] Corriere non congruente ");
						return 0;
					}			
				}
				else
				{
					infoOperazione ="[ERR] Errore StatoPacco non congruente";
					return -1;
				}			
			}
		}
		
		return -1;
	}
	
	
	public Pacco getPaccoDaRitirare(String idVendita)
	{
		Iterator<Pacco> iter = this.pacchi.iterator();
		
		while(iter.hasNext()) {
			Pacco pacco = iter.next();
			if (pacco.getIdVendita().equals(idVendita))
			{
				return pacco;
			}
		}
		return null;
	}	
	
	public List<Pacco> getPacchiDaRitirare(int idCliente, int idLocker)
	{
		List<Pacco> pacchiDaRitirare = new LinkedList<>();
		Iterator<Pacco> iter = this.pacchi.iterator();
		
		while(iter.hasNext()) {
			Pacco pacco = iter.next();
			if (pacco.getIdCliente()==idCliente && 
					pacco.getIdLocker()==idLocker &&
					pacco.getStatoPacco()==StatoPacco.conlocker)
			{
				pacchiDaRitirare.add(pacco);				
			}
		}
		return pacchiDaRitirare;
	}

	
	private Pacco cercaPacco(String idVendita)
	{
		Iterator<Pacco> iter = this.pacchi.iterator();

	    while (iter.hasNext	()) {
	    	Pacco pacco = iter.next();
	        if ( pacco.getIdVendita().equals(idVendita)) {
	        	return pacco;
	        }
	    }
	    return null;   			
	}
	
	public void prelevaPacco(String idVendita)
	{
		Pacco pacco = this.cercaPacco(idVendita);
		
		if (pacco!=null)
		{			
			pacco.setStatoPacco(StatoPacco.ritirato);
			pacco.setIdLocker(0);
			pacco.setIdCella(0);
			
			try {
				DBCommercio dbCommercio = DBCommercio.getInstance();
				dbCommercio.updatePacco(pacco);
			}
			catch(Exception ex)
			{
				System.out.println("[ERR] " + ex.getMessage());
			}
		}
		
		
	}
	
	/*
	public void SetPagato(String IDVendita)
	{
		Iterator<Vendita> iter = this.vendite.iterator();

	    while (iter.hasNext	()) {
	    	Vendita vendita = iter.next();
	        if ( vendita.getID() == IDVendita) {
	        	vendita.setPagato(true);
	        	return;
	        }
	    }
	    return;   		
		
	}
	*/
}
