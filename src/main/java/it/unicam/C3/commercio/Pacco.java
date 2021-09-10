package it.unicam.C3.commercio;

import java.time.LocalDate;

public class Pacco {
	private TipoDestinazione tipoDestinazione;
	private String indirizzo;
	private int idLocker;
	private int cella;
	private int idCliente;
	private int idCorriere;
	private int idNegozio;
	private String idVendita;
	private int idVenditaDB;
	private StatoPacco statoPacco;
	private int numConsegneFallite;
	private LocalDate dataConsegna;
	
	
	public Pacco(String idVendita, int idCliente, int idNegozio, int idVenditaDB)
	{
		this.idVendita = idVendita;
		this.idCliente = idCliente;
		this.idNegozio = idNegozio;
		this.idVenditaDB = idVenditaDB;
		this.statoPacco=StatoPacco.creato;
		this.cella=0;
		this.numConsegneFallite=0;
	}

	public Pacco(TipoDestinazione tipoDestinazione, String indirizzo, int idLocker, int cella,
			int idCliente,int idCorriere, int idNegozio, String idVendita, 
			int idVenditaDB, StatoPacco sp, int numConsegneFallite, LocalDate dataConsegna) {
		this.tipoDestinazione=tipoDestinazione;
		this.indirizzo=indirizzo;
		this.idLocker=idLocker;
		this.cella=cella;
		this.idCliente=idCliente;
		this.idCorriere=idCorriere;
		this.idNegozio=idNegozio;
		this.idVendita = idVendita;
		this.idVenditaDB=idVenditaDB;
		this.statoPacco=sp;
		this.numConsegneFallite=numConsegneFallite;
		if (dataConsegna!= null)
		{
			this.dataConsegna=dataConsegna;
		}
	}
	
	
	

	public int getNumConsegneFallite()
	{
		return this.numConsegneFallite;
	}
	
	
	public void addConsegnaFallita()
	{
		this.numConsegneFallite++;
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
	
	public LocalDate getDataConsegna() {
		return this.dataConsegna;
	}
	
	public void setDataConsegna(LocalDate dataConsegna) {
		this.dataConsegna= dataConsegna;
	}
	
	public void setCella(int idCella)
	{
		this.cella=idCella;
	}
	
	public int getCella()
	{
		return this.cella;
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
	
	public String toString()
	{
		if (statoPacco!=StatoPacco.inconsegna)
		{
			return "(" + this.idVendita + ") Negozio:" + this.idNegozio + " - Stato:" + StatoPacco.toString(statoPacco) +  " Cliente:" + this.idCliente;
		}
		else
		{
			if (this.tipoDestinazione==TipoDestinazione.indirizzo)
			{
				return "(" + this.idVendita + ") Negozio:" + this.idNegozio + " - Stato:" + StatoPacco.toString(statoPacco) + " presso Indirizzo " + this.indirizzo + " - con Corriere " + this.idCorriere + " - num consegne fallite " + this.numConsegneFallite + " Cliente:" + this.idCliente; 
			}
			else
			{
				return "(" + this.idVendita + ") Negozio:" + this.idNegozio + " - Stato:" + StatoPacco.toString(statoPacco) + " presso Locker " + this.idLocker + " - con Corriere " + this.idCorriere + " - num consegne fallite " + this.numConsegneFallite + " Cliente:" + this.idCliente;
			}				
		}
	}
}
