/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inputOutput;

/**
 *
 * @author Aaron
 */
public class ConnectionData {

    private String type, url, ipaddress, port, database, login, password;

    public void setType(String str) {
        type = str;
    }

    public void setUrl(String str) {
        url = str;
    }

    public void setIpaddress(String str) {
        ipaddress = str;
    }

    public void setPort(String str) {
        port = str;
    }

    public void setDatabase(String str) {
        database = str;
    }

    public void setLogin(String str) {
        login = str;
    }

    public void setPassword(String str) {
        password = str;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public String getPort() {
        return port;
    }

    public String getDatabase() {
        return database;
    }
    
    public String getLogin() {
        return login;
    }
    
    public String getPassword() {
        return password;
    }

    public String toString() {
        return url + "://" + ipaddress + ":" + port + "/" + database;
    }
}
