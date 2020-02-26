/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author VíctorCésar
 */
public class ConnectionFactory {
    
    private static final String DRIVER = "com.mysql.jdbc.DRIVER";
    private static final String URL = " ";
    private static final String USER = "u970457530_freeteca";
    private static final String PASS = "freeteca";
    
    public static Connection getConnection(){
    
        try {
            Class.forName(DRIVER);
            
            return DriverManager.getConnection(URL, USER, PASS);
            
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("NOT Conexão", ex);
        }
    
    }
    
    public static void closeConnection(Connection ex){
        
        if(ex != null){
            try {
                ex.close();
            } catch (SQLException ex1) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    
    }
}
