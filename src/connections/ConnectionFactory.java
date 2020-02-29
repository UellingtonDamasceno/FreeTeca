package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConnectionFactory {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String UNICODE = "?autoReconnect=true&useUnicode=yes&characterEncoding=UTF-8";
    private static final String URL = "jdbc:mysql://sql130.main-hosting.eu:3306/u970457530_freeteca";
    private static final String USER = "u970457530_freeteca";
    private static final String PASS = "freeteca";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL+UNICODE, USER, PASS); 
    }
    
    public static void closeConnection(Connection con) throws SQLException{
        if(con != null){
            con.close();
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt) throws SQLException{
        closeConnection(con);
        if(stmt != null){
            stmt.close();
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet r) throws SQLException{
        closeConnection(con, stmt);
        if(r != null){
            r.close();
        }
        
    }
}
