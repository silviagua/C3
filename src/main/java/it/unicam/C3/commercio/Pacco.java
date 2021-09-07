package it.unicam.C3.commercio;

public class Pacco {
	private TipoDestinazione tipoDestinazione;
	private String indirizzo;
	private int idLocker;
	private int idCella;
	private int idCliente;
	private int idCorriere;
	private int idNegozio;
	private String idVendita;
	private StatoPacco statoPacco;

	
	public Pacco(String idVendita, int idCliente, int idNegozio)
	{
		this.idVendita = idVendita;
		this.idCliente = idCliente;
		this.idNegozio = idNegozio;

	}
	
	public Pacco(TipoDestinazione tipoDestinazione, String idVendita, int idNegozio) {
		this.tipoDestinazione=tipoDestinazione;
		this.idVendita = idVendita;
		this.idNegozio = idNegozio;

	}
	
	public void setIndirizzo(String indirizzo) {
		if (this.tipoDestinazione==TipoDestinazione.indirizzo)
		{
			this.indirizzo = indirizzo;
		}
		else
		{
			this.indirizzo = "";
		}
	}
	
	public void setIdLocker(int idLocker)
	{
		if (this.tipoDestinazione==TipoDestinazione.locker)
		{
			this.idLocker=idLocker;
		}
		else
		{
			this.idLocker=0;
		}
		
	}
	
	public void setTipoDestinazione(TipoDestinazione tipoDestinazione) {
		this.tipoDestinazione=tipoDestinazione;
	}		
	
	public TipoDestinazione getTipoDestinazione() {
		return this.tipoDestinazione;
	}		

	public void setStatoPacco(StatoPacco statoPacco) {
		this.statoPacco=statoPacco;
	}
	
	public StatoPacco getStatoPacco() {
		return this.statoPacco;
	}
	
	public int getIdCliente() {
		return this.idCliente;
	}	
	
	public String getIdVendita() {
		return this.idVendita;
	}
	
	public void setIdCorriere(int idCorriere)
	{
		this.idCorriere = idCorriere;
	}

	public int getIdNegozio() {
		// DA AGGIUNGER!!!!
		return 0;
		
	}

	public boolean checkCorriere(int idCorriere) {
		// TODO Auto-generated method stub
		if (this.idCorriere== idCorriere)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public int getIdLocker() {
		return this.idLocker;
	}

	public int getIdCella() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setIdCella(int idCella) {
		this.idCella = idCella;
	}

	public int getIdCorriere() {
		// TODO Auto-generated method stub
		return this.idCorriere;
	}
	
	
}
