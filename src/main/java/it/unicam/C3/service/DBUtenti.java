package it.unicam.C3.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import it.unicam.C3.utente.Ruolo;
import it.unicam.C3.utente.Utente;
import it.unicam.C3.utente.Cliente;
import it.unicam.C3.utente.Commesso;
import it.unicam.C3.utente.Corriere;
import it.unicam.C3.utente.Commerciante;



public class DBUtenti extends DBConnection {
	
	private static DBUtenti instance;
	private String sql;

	public DBUtenti() throws SQLException {
        super();
    }
	
	public static DBUtenti getInstance() throws SQLException{
		if (instance == null) {
			instance = new DBUtenti();
		}
		return instance;
	}
	
	boolean checkUtente(String nome, String cognome, String pwd, String mail, String username) {
		
		return false;
	}
	
	/*
	public List<Cliente> setUtenti(Ruolo ruolo) throws SQLException  {
		sql = "SELECT ID AS id, Nome, Cognome, Mail, Password, Telefono, UserName FROM utente  \r\n"
				+ "WHERE ID_RUOLO=" + (ruolo.ordinal() + 1);
							
		List<Cliente> clienti = new LinkedList<>();
		Cliente cliente;	
		
		setData(sql);
		while (getData().next()) {
			
			cliente = new Cliente(
					getData().getInt("ID"), 
					getData().getString("Nome"), 
					getData().getString("Cognome"), 
					getData().getString("Mail"), 
					getData().getString("Password"),
					getData().getString("UserName"));
			clienti.add(cliente);
		}
		return clienti;
	}	
	*/
	public boolean cercaUtentebyUserName(String userName) throws SQLException {
		sql = "SELECT ID AS id, Nome, Cognome, Mail, Password, Telefono, UserName FROM utente  \r\n"
				+ "WHERE userName='" + userName+"'";
							
		
		setData(sql);
		if (getData().next()) {
			return true;
		}
		else
		{
			return false;
		}			
	}
	
	public boolean cercaUtentebyMail(String mail) throws SQLException {
		sql = "SELECT ID AS id, Nome, Cognome, Mail, Password, Telefono, UserName FROM utente  \r\n"
				+ "WHERE mail='" + mail +"'";
									
		setData(sql);
		if (getData().next()) {
			return true;
		}
		else
		{
			return false;
		}			
	}
	
	
	public int insertCliente(Cliente cliente) throws SQLException {
        sql = "insert into utente(id_ruolo,mail, Nome, Cognome, Password, userName) values (?,?,?,?,SHA2(?,256),?)";
        PreparedStatement prepStat = getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        prepStat.setString(1, "4");
        prepStat.setString(2, cliente.getEmail());
        prepStat.setString(3, cliente.getNome());
        prepStat.setString(4, cliente.getCognome());
        prepStat.setString(5, cliente.getPassword());
        prepStat.setString(6, cliente.getUserName());
        prepStat.executeUpdate();
     	   
        ResultSet rs = prepStat.getGeneratedKeys();
        rs.next();
        return	rs.getInt(1);
   }	
	
	public int insertCorriere(Corriere corriere) throws SQLException {
        sql = "insert into utente(id_ruolo,mail, Nome, Cognome, Password, userName) values (?,?,?,?,SHA2(?,256),?)";
        PreparedStatement prepStat = getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        prepStat.setString(1, "5");
        prepStat.setString(2, corriere.getEmail());
        prepStat.setString(3, corriere.getNome());
        prepStat.setString(4, corriere.getCognome());
        prepStat.setString(5, corriere.getPassword());
        prepStat.setString(6, corriere.getUserName());
        prepStat.executeUpdate();
     	   
        ResultSet rs = prepStat.getGeneratedKeys();
        rs.next();
        return	rs.getInt(1);
   }	

	public int insertCommesso(Commesso commesso) throws SQLException {
        sql = "insert into utente(id_ruolo,mail, Nome, Cognome, Password, userName, id_negozio) values (?,?,?,?,SHA2(?,256),?,?)";
        PreparedStatement prepStat = getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        prepStat.setString(1, "3");
        prepStat.setString(2, commesso.getEmail());
        prepStat.setString(3, commesso.getNome());
        prepStat.setString(4, commesso.getCognome());
        prepStat.setString(5, commesso.getPassword());
        prepStat.setString(6, commesso.getUserName());
        prepStat.setString(7, Integer.toString(commesso.getNegozio()));

        prepStat.executeUpdate();
     	   
        ResultSet rs = prepStat.getGeneratedKeys();
        rs.next();
        return	rs.getInt(1);
   }		
	
	public int insertCommerciante(Commerciante gestoreNegozio) throws SQLException {
		sql = "insert into utente(id_ruolo,mail, Nome, Cognome, Password, userName, id_negozio) values (?,?,?,?,SHA2(?,256),?,?)";
        PreparedStatement prepStat = getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        prepStat.setString(1, "2");
        prepStat.setString(2, gestoreNegozio.getEmail());
        prepStat.setString(3, gestoreNegozio.getNome());
        prepStat.setString(4, gestoreNegozio.getCognome());
        prepStat.setString(5, gestoreNegozio.getPassword());
        prepStat.setString(6, gestoreNegozio.getUserName());
        prepStat.setString(7, Integer.toString(gestoreNegozio.getNegozio()));

        prepStat.executeUpdate();
     	   
        ResultSet rs = prepStat.getGeneratedKeys();
        rs.next();
        return	rs.getInt(1);		
	}
	
	
	public <T extends Utente> T getUtente(String username, String password) throws SQLException{
    	sql = "select ID, Nome, Cognome, Mail, ID_Ruolo, ID_Negozio, Password, username from utente where userName='" + username + "' and password=SHA2('" + password +"',256)";

		setData(sql);
		if (getData().next()) {

			//ReportTypeEnum value = ReportTypeEnum.values()[ordinal];
			Ruolo ruoloUtente = Ruolo.values()[ getData().getInt("ID_Ruolo") -1];
			switch(ruoloUtente)
			{
				case user:
					Cliente cliente = new Cliente(
							getData().getInt("ID"),
							getData().getString("Nome"),
							getData().getString("Cognome"),
							getData().getString("Mail"), 
							getData().getString("Password"),
							getData().getString("UserName") );
					return (T) cliente;
				case corr:
					Corriere corriere = new Corriere(
							getData().getInt("ID"),
							getData().getString("Nome"),
							getData().getString("Cognome"),
							getData().getString("Mail"), 
							getData().getString("Password"),
							getData().getString("UserName") );
					return (T) corriere;
				case comm:
					Commesso commesso = new Commesso(
							getData().getInt("ID"),
							getData().getString("Nome"),
							getData().getString("Cognome"),
							getData().getString("Mail"), 
							getData().getString("Password"),
							getData().getString("UserName"),
							getData().getInt("ID_Negozio")
							);
					return (T) commesso;
				default:
					return null;
			}
		}    	
    	return null;		   	
	}
	
	public List<Cliente> setClienti() throws SQLException  {
		sql = "SELECT ID AS id, Nome, Cognome, Mail, Password, Telefono, UserName FROM utente  \r\n"
				+ "WHERE ID_RUOLO=" + Ruolo.toInt(Ruolo.user) +"";
							
		List<Cliente> clienti = new LinkedList<>();
		Cliente cliente;	

		setData(sql);
		while (getData().next()) {

				cliente = new Cliente(
						getData().getInt("ID"), 
						getData().getString("Nome"), 
						getData().getString("Cognome"), 
						getData().getString("Mail"), 
						getData().getString("Password"),
						getData().getString("UserName"));
				
				clienti.add(cliente);
		}

		return clienti;
	}		
	

	public List<Corriere> setCorrieri() throws SQLException  {
		sql = "SELECT ID AS id, Nome, Cognome, Mail, Password, Telefono, UserName FROM utente  \r\n"
			+ "WHERE ID_RUOLO=" + Ruolo.toInt(Ruolo.corr) +"";
							
		List<Corriere> corrieri = new LinkedList<>();
		Corriere corriere;	

		setData(sql);
		while(getData().next()) {

				corriere = new Corriere(
						getData().getInt("ID"), 
						getData().getString("Nome"), 
						getData().getString("Cognome"), 
						getData().getString("Mail"), 
						getData().getString("Password"),
						getData().getString("UserName"));
				
				corrieri.add(corriere);
				
		}
		

		return corrieri;
	}		
	
	
	
	
}
