package it.unicam.C3.service;

import java.util.Map;

import it.unicam.C3.negozio.Negozio;
import it.unicam.C3.negozio.Prodotto;
import it.unicam.C3.trasporto.Locker;
import it.unicam.C3.utente.Cliente;
import it.unicam.C3.utente.Corriere;
import it.unicam.C3.utente.Ruolo;
import it.unicam.C3.utente.Utente;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class DBManager {
	private static DBManager instance;
	private String url;
	private String user;
	private String pwd;
	private Connection conn = null;

	private DBManager() {
	}

	public void setDBManager(String url, String user, String pwd) {
		this.url = url;
		this.user = user;
		this.pwd = pwd;
	}

	public static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	private void connect() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your PostgreSQL JDBC Driver? " + "Include in your library path!");
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url, user, pwd);
			System.out.println("Database connected, ready to go!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Problems in opening a connection to the DB");
			e.printStackTrace();
		}
	}

	private void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Problems in closing the connection to the DB");
			e.printStackTrace();
		}
	}

	public boolean DBtest() {
		Boolean result = true;
		try {
			if (conn == null || !conn.isClosed()) {
				connect();
				result = false;
			}
			DatabaseMetaData data = conn.getMetaData();
			System.out.println("Details on DBMS - " + data.getDatabaseProductName() + "\n" + "  version:  "
					+ data.getDriverMajorVersion() + "\n" + "  catalogs: " + data.getCatalogs().getCursorName() + "\n"
					+ "  schemas:  " + data.getSchemas().getRow() + "\n");
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	

	public List<Locker> setLocker() throws SQLException {
		String sql = "SELECT ID, Nome, Descrizione, Indirizzo, numCelle, Celle FROM locker";
		
		//System.out.println(SQL);						
		List<Locker> lockers = new LinkedList<>();
		Locker locker;		
		
		try (Connection conn = DriverManager.getConnection(url, user, pwd);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {

			while (rs.next()) {

				locker = new Locker(
						rs.getInt("ID"), 
						rs.getString("Nome"),
						rs.getString("Indirizzo"),
						rs.getInt("numCelle"));
				lockers.add(locker);
				
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return lockers;
		
	}	
	
	public List<Negozio> setNegozi() throws SQLException {
		String sql = "SELECT n.id ID, n.Nome Nome  FROM negozio as n;";
						
		List<Negozio> negozi = new LinkedList<>();
		Negozio negozio;		

		try (Connection conn = DriverManager.getConnection(url, user, pwd);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {

			while (rs.next()) {

				negozio = new Negozio(
						rs.getInt("ID"), 
						rs.getString("Nome"));
				
				negozio.setProdotti(this.getProdotti(rs.getInt("ID")));
				negozi.add(negozio);
				
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return negozi;
	}	
	
	
	public List<Prodotto> getProdotti(int idNegozio) throws SQLException {
		String sql = "select ID as ID, Nome as Nome, Descrizione as Descrzione, IVA as IVA, Prezzo as Prezzo\r\n"
				+ "from prodotto where id_Negozio = " + idNegozio;
						
		List<Prodotto> prodotti = new LinkedList<>();
		Prodotto prodotto;
		
		try (Connection conn = DriverManager.getConnection(url, user, pwd);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {

			while (rs.next()) {

				prodotto = new Prodotto(
						rs.getInt("ID"), 
						rs.getString("Nome"),
						rs.getFloat("Prezzo"),
						rs.getInt("IVA"));
				
				prodotti.add(prodotto);
				
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return prodotti;
	}
	
	/*
	public List<Utente> setUtenti(int idRuolo) throws SQLException  {
		String sql = "SELECT ID AS id, Nome, Cognome, Mail, Password, Telefono, UserName FROM utente  \r\n"
				+ "WHERE ID_RUOLO=" + idRuolo +"";
							
		List<Utente> utenti = new LinkedList<>();
		Utente utente;	

		try (Connection conn = DriverManager.getConnection(url, user, pwd);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {

			while (rs.next()) {

				utente = new Utente(
						rs.getInt("ID"), 
						rs.getString("Nome"), 
						rs.getString("Cognome"), 
						rs.getString("Mail"), 
						rs.getString("Password"),
						rs.getString("UserName"));
				
				utenti.add(utente);
				
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return utenti;
	}		
	*/
	
	
	
}
