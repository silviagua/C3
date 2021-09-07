package it.unicam.C3.service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.unicam.C3.trasporto.Locker;

public class DBLocker extends DBConnection {
	
	private static DBLocker instance;
	private String sql;

	public DBLocker() throws SQLException {
        super();
    }
	
	public static DBLocker getInstance() throws SQLException{
		if (instance == null) {
			instance = new DBLocker();
		}
		return instance;
	}

	public List<Locker> setLocker() throws SQLException {
		String sql = "SELECT ID, Nome, Descrizione, Indirizzo, numCelle, Celle FROM locker";
		
		//System.out.println(SQL);						
		List<Locker> lockers = new LinkedList<>();
		Locker locker;		

		setData(sql);
		while (getData().next()) {
			
			locker = new Locker(
					getData().getInt("ID"), 
					getData().getString("Nome"),
					getData().getString("Indirizzo"),
					getData().getInt("numCelle"));
			lockers.add(locker);
		}

		return lockers;
	}	
			
	public void updateLocker(Locker locker) throws SQLException{
		Connection conn = null;
		conn= getConnection();

        sql = "Update locker set celle=? where id=?";
        
        PreparedStatement prepStat = conn.prepareStatement(sql);
        
        prepStat.setString(1, locker.getCelle()  );
        prepStat.setInt(2, locker.getIdLocker());
        
        System.out.println(prepStat);
        prepStat.executeUpdate();       		
	
	}
}
