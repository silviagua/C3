package it.unicam.C3.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import it.unicam.C3.commercio.Pacco;
import it.unicam.C3.commercio.StatoPacco;
import it.unicam.C3.commercio.TipoDestinazione;
import it.unicam.C3.commercio.Vendita;
import it.unicam.C3.negozio.ProdottoVendita;


public class DBCommercio extends DBConnection {

	private static DBCommercio instance;
	private String sql;
	
	public DBCommercio() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static DBCommercio getInstance() throws SQLException{
		if (instance == null) {
			instance = new DBCommercio();
		}
		return instance;
	}
	
	public int insertVendita(Vendita vendita) throws SQLException {
		Connection conn = null;
		try
		{
			conn= getConnection();
	        sql = "insert into vendita(etichetta,data, id_negozio, id_utente, importo, iva, pagato) values (?,?,?,?,?,?,?)";
	        
	        PreparedStatement prepStat = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
	        
	        prepStat.setString(1, vendita.getIdVendita());
	        prepStat.setObject (2, vendita.getData());
	        prepStat.setInt(3, vendita.getIdNegozio());
	        prepStat.setInt(4, vendita.getIdCliente());
	        prepStat.setFloat(5, vendita.getImporto());
	        prepStat.setFloat(6,  vendita.getIva());
	        prepStat.setBoolean(7,true);
	        prepStat.executeUpdate();
	
	        ResultSet rs = prepStat.getGeneratedKeys();
	        rs.next();
	        int idVendita =rs.getInt(1);        
	        
	        this.insertProdottoVendita(vendita, idVendita);
	        return idVendita;
		}
		catch(Exception ex)
		{
			return 0;
		}
   }		
	
	private void insertProdottoVendita(Vendita vendita, int idVenditaDB) throws SQLException {
        sql="insert into Prodotto_vendita(Id_Prodotto,Id_Vendita,Quantita) values (?,?,?)";
        PreparedStatement prepStat = getConnection().prepareStatement(sql);
        for(ProdottoVendita p:vendita.getProdotti() ) {
            prepStat.setInt(1, p.getId());
            prepStat.setInt(2, idVenditaDB);
            prepStat.setInt(3, p.getQuantita());
            
            prepStat.executeUpdate();
        }
    }
	
	
	public void insertPacco(Pacco pacco) throws SQLException {
		Connection conn = null;
		conn= getConnection();
        sql = "insert into pacco(etichetta,id_corriere, id_locker, id_stato_pacco, id_tipo_destinazione, id_vendita, indirizzo) values (?,?,?,?,?,?,?)";
        
        PreparedStatement prepStat = conn.prepareStatement(sql);
        
        prepStat.setString(1, pacco.getIdVendita());
        if(Optional.ofNullable(pacco.getIdCorriere()).orElse(0) != 0) 
        {
        	prepStat.setInt (2, pacco.getIdCorriere());
        }
        else
        {        	
        	prepStat.setNull(2, java.sql.Types.INTEGER);        	
        }
        
        prepStat.setInt(3, pacco.getIdLocker());
        prepStat.setInt(4, StatoPacco.toInt(pacco.getStatoPacco())  );
        prepStat.setInt(5,TipoDestinazione.toInt(pacco.getTipoDestinazione()) );
        prepStat.setInt(6,  pacco.getIdVenditaDB());
        prepStat.setString(7, pacco.getIndidizzo());
        prepStat.executeUpdate();        

   }		

	
	public void updatePacco(Pacco pacco) throws SQLException {
		Connection conn = null;
		conn= getConnection();

        sql = "Update pacco set id_Stato_Pacco=?, id_corriere=? where etichetta=?";
        
        PreparedStatement prepStat = conn.prepareStatement(sql);
        
        prepStat.setInt(1, StatoPacco.toInt(pacco.getStatoPacco())  );
        prepStat.setInt(2, pacco.getIdCorriere() );
        prepStat.setString(3,  pacco.getIdVendita());

        System.out.println(prepStat);
        prepStat.executeUpdate();        
        
        
		
	
	}
	
}
