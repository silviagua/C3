package it.unicam.C3.service;


import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.unicam.C3.negozio.Negozio;
import it.unicam.C3.negozio.Prodotto;

public class DBNegozi extends DBConnection {
	
	private static DBNegozi instance;

	public DBNegozi() throws SQLException {
        super();
    }
	
	public static DBNegozi getInstance() throws SQLException{
		if (instance == null) {
			instance = new DBNegozi();
		}
		return instance;
	}
	
	
	public List<Negozio> setNegozi() throws SQLException {
		String sql = "SELECT n.id ID, n.Nome Nome  FROM negozio as n;";
						
		List<Negozio> negozi = new LinkedList<>();
		Negozio negozio;		

		setData(sql);
		while (getData().next()) {
			negozio = new Negozio(getData().getInt("ID"), getData().getString("Nome"));
			negozio.setProdotti(this.getProdotti(getData().getInt("ID")));
			
			negozi.add(negozio);				
		}
		return negozi;
	}	
	
	
	public List<Prodotto> getProdotti(int idNegozio) throws SQLException {
		String sql = "select ID as ID, Nome as Nome, Descrizione as Descrzione, IVA as IVA, Prezzo as Prezzo\r\n"
				+ "from prodotto where id_Negozio = " + idNegozio;
						
		List<Prodotto> prodotti = new LinkedList<>();
		Prodotto prodotto;
		
		setData(sql);
			while (getData().next()) {

				prodotto = new Prodotto(getData().getInt("ID"),
						getData().getString("Nome"),						
						getData().getFloat("Prezzo"),
						getData().getInt("IVA"));
				prodotti.add(prodotto);
			}

		return prodotti;
	}

}
