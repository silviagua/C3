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
	private int idVenditaDB;
	private StatoPacco statoPacco;
	
	
	public Pacco(String idVendita, int idCliente, int idNegozio, int idVenditaDB)
	{
		this.idVendita = idVendita;
		this.idCliente = idCliente;
		this.idNegozio = idNegozio;
		this.idVenditaDB = idVenditaDB;
	}
	
	public Pacco(TipoDestinazione tipoDestinazione, String idVendita, int idNegozio, int idVenditaDB) {
		this.tipoDestinazione=tipoDestinazione;
		this.idVendita = idVendita;
		this.idNegozio = idNegozio;
		this.idVenditaDB = idVenditaDB;
	}
	
	public int getIdVenditaDB()
	{
		return this.idVenditaDB;
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
	
	public int getIdLocker()
	{
		return this.idLocker;
	}
	
	public String getIndidizzo()
	{
		return this.indirizzo;
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
	
	public int getIdCorriere()
	{
		return this.idCorriere;
	}	
	
	public int getIdNegozio() {
		return this.idNegozio;
	}
	
	public void setIdCella(int idCella)
	{
		this.idCella=idCella;
	}
	
	public int getIdCella()
	{
		return this.idCella;
	}
	
	public boolean checkCorriere (int idCorriere)
	{
		if (this.idCorriere== idCorriere)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
