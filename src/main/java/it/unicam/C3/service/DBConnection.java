package it.unicam.C3.service;

import java.sql.*;

import it.unicam.C3.Config;

public abstract class DBConnection {

	
    private static Connection connection;
    private static Statement command;
    private static ResultSet data;


    public DBConnection() throws SQLException {
        if(connection==null) {
            connection = DriverManager.getConnection(
            									Config.getValore("connectionstring"), 
            									Config.getValore("user"), 
            									Config.getValore("password"));
            command = connection.createStatement();
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public Statement getCommand(){
        return command;
    }

    public ResultSet getData(){
        return data;
    }

    public void setData(String sql) throws SQLException {
        data=command.executeQuery(sql);
    }

    public void close() throws SQLException {
        command.close();
        connection.close();
    }

}
