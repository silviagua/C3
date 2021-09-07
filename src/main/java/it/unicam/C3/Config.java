package it.unicam.C3;

import java.io.FileInputStream;
import java.util.Properties;

public final class Config {
	final static String fileName = "config.properties";
	final static String filePath = "src\\main\\java\\it\\unicam\\C3\\";
	static Properties properties;
	
	final static String citta = "Ascoli Piceno";
	final static String descrizione ="WAP";
	final static String mail = "info@comune.ascolipiceno.ap.it";
	final static String telefono= "0736";
	
	final static String ivaordinaria = "22";
	
	final static String user= "root";
	final static String password = "";
	final static String host ="localhost";
	final static String database = "C3";
	final static String connectionstring ="jdbc:mysql://localhost:3306/C3";
	
	public static String getValore(String chiave) {		
		try
		{
			FileInputStream fis = new FileInputStream(filePath + fileName);
			properties = new Properties();
			properties.load(fis);		
			return properties.getProperty(chiave);
		}
		catch(Exception ex)
		{
			System.out.println("[ERR]" + ex.getMessage());
			
			switch(chiave)
			{
				case "citta": 
					return citta;
				case "descrizione":
					return descrizione;
				case "mail":
					return mail;
				case "telefono":
					return telefono;
				case "user":
					return user;
				case "password":
					return password;
				case "host":
					return host;
				case "database":
					return database;						
				case "connectionstring":
					return connectionstring;
				case "ivaordinaria":
					return ivaordinaria;
			}
			return null;	
		}			    
    }			
}
