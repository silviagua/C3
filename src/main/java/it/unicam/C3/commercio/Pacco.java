package it.unicam.C3.commercio;

public class Pacco {
	TipoDestinazione tipoDestinazione;
	String indirizzo;
	int idLocker;
	int idCliente;
	int idCorriere;
	String idVendita;
	StatoPacco statoPacco;
	
	public Pacco(String idVendita, int idCliente)
	{
		this.idVendita = idVendita;
		this.idCliente = idCliente;
	}
	
	public Pacco(TipoDestinazione tipoDestinazione, String idVendita) {
		this.tipoDestinazione=tipoDestinazione;
		this.idVendita = idVendita;
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
	
	
}
