package it.unicam.C3.trasporto;

public class Locker {
	int idLocker;
	String nome;
	String indirizzo;
	
	public Locker (int idLocker, String nome, String indirizzo)
	{
		this.idLocker=idLocker;
		this.nome=nome;
		this.indirizzo=indirizzo;
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
}
