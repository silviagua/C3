package it.unicam.C3.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.LinkedList;
import java.util.List;
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
	        sql = "insert into vendita(etichetta,data, id_negozio, id_utente, importo, iva, pagato,ispacco) values (?,?,?,?,?,?,?,0)";
	        
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
	
	public void updateVendita(Vendita vendita) throws SQLException{
		Connection conn = null;
		conn= getConnection();

        sql = "Update vendita set isPacco=1 where etichetta=?";
        
        PreparedStatement prepStat = conn.prepareStatement(sql);
        
        prepStat.setString(1, vendita.getIdVendita());

        System.out.println(prepStat);
        prepStat.executeUpdate();        	
	}
	
	
	public void updateVendita(String idVendita) throws SQLException{
		Connection conn = null;
		conn= getConnection();

        sql = "Update vendita set isPacco=1 where etichetta=?";
        
        PreparedStatement prepStat = conn.prepareStatement(sql);
        
        prepStat.setString(1, idVendita);

        prepStat.executeUpdate();        	
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
        sql = "insert into pacco(etichetta,id_corriere, id_locker, id_stato_pacco, id_tipo_destinazione, " +
		"id_vendita, indirizzo, cella, NumConsegneFallite) values (?,?,?,?,?,?,?,?,?)";
        
        PreparedStatement prepStat = conn.prepareStatement(sql);
        
        prepStat.setString(1, pacco.getIdVendita());
        
        //se corriere è 0 o null inserisco NULL
        if(Optional.ofNullable(pacco.getIdCorriere()).orElse(0) != 0) 
        {
        	prepStat.setInt (2, pacco.getIdCorriere());
        }
        else
        {        	
        	prepStat.setNull(2, java.sql.Types.INTEGER);        	
        }
        //se locker è 0 o null inserire null
        if(Optional.ofNullable(pacco.getIdLocker()).orElse(0)!= 0)
        {
            prepStat.setInt(3, pacco.getIdLocker());        	
        }
        else
        {
        	prepStat.setNull(3, java.sql.Types.INTEGER);
        }

        prepStat.setInt(4, StatoPacco.toInt(pacco.getStatoPacco())  );
        prepStat.setInt(5,TipoDestinazione.toInt(pacco.getTipoDestinazione()) );
        prepStat.setInt(6,  pacco.getIdVenditaDB());
        prepStat.setString(7, pacco.getIndidizzo());
        prepStat.setInt(8, pacco.getCella());
        prepStat.setInt(9, pacco.getNumConsegneFallite());
        prepStat.executeUpdate();        

   }		

	
	public void updatePacco(Pacco pacco) throws SQLException {
		Connection conn = null;
		conn= getConnection();

        sql = "Update pacco set id_Stato_Pacco=?, id_corriere=?, DataConsegna=?, NumConsegneFallite=? where etichetta=?";
        
        PreparedStatement prepStat = conn.prepareStatement(sql);
        
        prepStat.setInt(1, StatoPacco.toInt(pacco.getStatoPacco())  );
        prepStat.setInt(2, pacco.getIdCorriere() );
        
        if (pacco.getDataConsegna()!=null)
        {
        	prepStat.setDate(3, java.sql.Date.valueOf( pacco.getDataConsegna() ));
        }
        else
        {
        	prepStat.setNull(3,java.sql.Types.DATE);
        }
        prepStat.setInt(4, pacco.getNumConsegneFallite());
        prepStat.setString(5,  pacco.getIdVendita());

        
        prepStat.executeUpdate();        
                
	}
	
	
	public List<Vendita> setVendite() throws SQLException {
		String sql = "SELECT ID, Etichetta, Data, Importo, Iva, pagato, ID_Utente, ID_Negozio, IsPacco FROM `vendita`";
		
		//System.out.println(SQL);						
		List<Vendita> vendite = new LinkedList<>();
		Vendita vendita;		

		setData(sql);
		while (getData().next()) {
			
			vendita = new Vendita(
					getData().getString("Etichetta"), 
					getData().getInt("ID"),
					getData().getDate("Data").toLocalDate(),
					getData().getFloat("Importo"),
					getData().getFloat("Iva"),
					getData().getInt("ID_Negozio"),
					getData().getInt("ID_Utente"),
					getData().getBoolean("pagato"),
					getData().getBoolean("IsPacco"));
			vendite.add(vendita);

		}

		return vendite;
	}		


	public List<Pacco> setPacchi() throws SQLException {
		String sql = "SELECT p.ID as pID, p.Etichetta as pEtichetta," 
				+ "				p.ID_Tipo_Destinazione as ptipoDes, p.ID_Stato_Pacco as pSp,\r\n"
				+ "				p.Indirizzo as pInd, p.ID_Locker as pIDLocker, "
				+"				p.ID_VENDITA as pIDVendita, p.ID_CORRIERE as pIDCorriere, p.Cella as pCella,\r\n"
				+ "             v.ID_Utente as vIDUtente, v.ID_Negozio as vIDNegozio, \r\n"
				+ "				p.NumConsegneFallite as pNConFall, p.DataConsegna as pDataConsegna \r\n"
				+ "FROM pacco p inner join vendita  v \r\n"
				+ "on v.id=p.ID_VENDITA";
		
						
		List<Pacco> pacchi = new LinkedList<>();
		Pacco pacco;		

		setData(sql);
		while (getData().next()) {
			pacco = new Pacco(
					TipoDestinazione.fromInt(getData().getInt("ptipoDes")),
					getData().getString("pInd"),
							getData().getInt("pIDLocker"),
							getData().getInt("pCella"),
							getData().getInt("vIDUtente"),
							getData().getInt("pIDCorriere"),					
							getData().getInt("vIDNegozio"),					
							getData().getString("pEtichetta"),					
							getData().getInt("pIDVendita"),
							StatoPacco.fromInt(getData().getInt("pSp")),
							getData().getInt("pNConFall"),
							(getData().getDate("pDataConsegna")==null? null: getData().getDate("pDataConsegna").toLocalDate()));
			pacchi.add(pacco);
		}

		return pacchi;
	}		
	
}
