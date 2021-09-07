package it.unicam.C3.trasporto;

public class Locker {
	private int idLocker;
	private String nome;
	private String indirizzo;
	private String celle;
	private int numCelle;
	
	public Locker (int idLocker, String nome, String indirizzo, int numCelle)
	{
		this.idLocker=idLocker;
		this.nome=nome;
		this.indirizzo=indirizzo;
		this.numCelle=numCelle;		
		
		this.celle = new String(new char[numCelle]).replace('\0', '0');
	}

	
	public void occupaCella(int numCella)
	{	
		char[] cellChar = this.celle.toCharArray();
		cellChar[numCella-1] = '1';
		this.celle = String.valueOf(cellChar);
	}
	
	public void liberaCella(int numCella)
	{
		char[] cellChar = this.celle.toCharArray();
		cellChar[numCella-1] = '0';
		this.celle = String.valueOf(cellChar);
	}
	
	public int primaCellaLibera()
	{
		return celle.indexOf('0') +1;
	}
	
	public int getIdLocker()
	{
		return this.idLocker;
	}
	
	public String getNome()
	{
		return this.nome;
	}

	public String getIndirizzo()
	{
		return this.indirizzo;
	}	
	 
	public String getCelle()
	{
		return this.celle;
	}
	

}
