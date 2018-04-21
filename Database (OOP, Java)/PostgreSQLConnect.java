/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import inputOutput.ConnectionData;
import java.sql.*;

/**
 *
 * @author Aaron
 */
public class PostgreSQLConnect {
    
    Connection connect = null;
    
    public PostgreSQLConnect(ConnectionData cd){
        try{
            Class.forName(cd.getType());
            connect = DriverManager.getConnection(cd.toString(), cd.getLogin(), cd.getPassword()); //we set the connection
        }catch(Exception ex){ //if we are not able to connect to the database
            ex.printStackTrace();
            System.out.print(ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(0);
        }
            System.out.printf("Database connection was successful");
    }
    
    public Connection makeConnection(){
        return connect;
    }
}
