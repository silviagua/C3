package it.unicam.C3.commercio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import it.unicam.C3.negozio.*;


public class Vendita {
	private LocalDate data;
	private float importo;
	private float iva;
	private boolean pagato;
	private int idNegozio;
	private int idCliente;
	private String idVendita;
	private int id;
	
	private GestoreNegozi gNegozi= GestoreNegozi.getInstance();
	
	
	
	private List<ProdottoVendita> prodotti;
	
	public void setId(int id)
	{
		this.id=id;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public void setIdVendita(String idVendita)
	{
		this.idVendita=idVendita;
	}
	
	public String getIdVendita()
	{
		return this.idVendita;
	}
	
	public void setProdotti(List<ProdottoVendita> prodotti)
	{
		this.prodotti = prodotti;
	}
	
	public List<ProdottoVendita> getProdotti()
	{
		return this.prodotti;
	}

	public void setData(LocalDate data)
	{
		this.data=data;
	}
	
	public LocalDate getData()
	{
		return this.data;
	}
	
	public void setPagato(boolean pagato) {
		this.pagato = pagato;
	}
	
	public boolean getPagato() {
		return pagato;
	}	
	
	
	public float getImporto()
	{
		return this.importo;
	}
	
	public float getIva()
	{
		return this.iva;
	}

	public float getTotale()
	{
		return this.importo + this.iva;
	}
	
	
	public int getIdCliente()
	{
		return this.idCliente;
	}
	
	public int getIdNegozio()
	{
		return this.idNegozio;
	}
	
	
	
	
	//inizio una nuova vendita impostando la data e  l'ID del negozio
	public Vendita(int idNegozio, int idCliente)
	{
		this.data = LocalDate.now();
		this.idNegozio = idNegozio;
		this.idCliente = idCliente;		
		this.importo = 0;
		this.iva = 0;
		this.idVendita = UUID.randomUUID().toString();
		
		this.prodotti = new LinkedList<>();
	}
	
	public String addProdottoVendita(int id, int qta)
	{	
		//cerco il prodotto con ID indicato
		Prodotto prod = gNegozi.getProdotto(this.idNegozio, id);
		
		ProdottoVendita prodVendita = new ProdottoVendita(
				prod.getId(),
				prod.getNome(),
				prod.getPrezzo(),
				prod.getIvaOrdinaria());
		
		prodVendita.setQuantita(qta);
		prodotti.add(prodVendita);
		
		this.importo += prod.getPrezzo() * qta;
		this.iva += this.iva + (prod.getPrezzo() * prod.getIvaOrdinaria() /100) * qta; 
		return  "[OUTPUT] Nome: " + prod.getNome() + "- Prezzo unitario: " + prod.getPrezzo() + "- Qta: " + String.valueOf(qta) + " - Tot " + this.importo + " - Iva: " + this.iva;
	}
	
}
