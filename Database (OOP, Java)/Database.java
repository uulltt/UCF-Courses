/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import inputOutput.*;
import javax.xml.parsers.ParserConfigurationException;
//Aaron Varkonyi, COP-3330, July 31, 2016
/**
 *
 * @author Aaron
 */
public class Database {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParserConfigurationException {
        XmlParser p = new XmlParser("inputOutput/properties.xml");
        ConnectionData cd = p.getConnectionData();
        //System.out.println();
        PostgreSQLConnect c = new PostgreSQLConnect(cd);
        c.makeConnection();
    }
    
}
