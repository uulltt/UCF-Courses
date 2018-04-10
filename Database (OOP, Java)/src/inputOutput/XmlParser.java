/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inputOutput;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 *
 * @author Aaron
 */
public class XmlParser {

    ConnectionData connectionData;
    Document document;

    public ConnectionData getConnectionData() {
        return connectionData;
    }

    public XmlParser(String file) throws ParserConfigurationException {
        ParseXmlFile(file);
    }

    public void ParseXmlFile(String f) throws ParserConfigurationException {
        connectionData = new ConnectionData();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();;
        DocumentBuilder builder = factory.newDocumentBuilder();
        try {
            document = builder.parse(ClassLoader.getSystemResourceAsStream(f)); //we try to parse the document
        } catch (SAXException ex) {//if we get either of these errors
            Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex); //do this
        } catch (IOException ex) {
            Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        NodeList nl = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) { //going through each node in the nodelist
            Node n = nl.item(i);
            if (n instanceof Element) {
                String type = ((Element) n).getAttributes().getNamedItem("type").getNodeValue(); //we get the type of the element
                connectionData.setType(type); //we set the type to the connectiondata
                        connectionData.setUrl(n.getTextContent().split("\n")[1].trim()); //we set each thing within the text content, each being split by a newline and then trimmed  
                        connectionData.setIpaddress(n.getTextContent().split("\n")[2].trim());                      
                        connectionData.setPort(n.getTextContent().split("\n")[3].trim());                        
                        connectionData.setDatabase(n.getTextContent().split("\n")[4].trim());                       
                        connectionData.setLogin(n.getTextContent().split("\n")[5].trim());
                        connectionData.setPassword(n.getTextContent().split("\n")[6].trim());
                        //System.out.print(connectionData.toString());
                }
            }
        }
    }

