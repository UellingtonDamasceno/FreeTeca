package DAO;

import connections.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Administrator;
import model.Login;
import model.Student;
import util.Settings;

/**
 *
 * @author Uellington Conceição
 */
public class AdministratorDAO {
    
     public boolean create(Administrator a) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO administradores (firstName, lastName, cpf, andress, sexo, email, recoveryEmail, password)"
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            System.out.println(stmt.toString());
            stmt.setString(1, a.getFirstName());
            stmt.setString(2, a.getLastName());
            stmt.setString(3, a.getCpf());
            stmt.setString(4, a.getAddress());
            stmt.setString(5, String.valueOf(a.getGenere()));
           
           
            stmt.setString(6, a.getLogin().getEmail());
            stmt.setString(7, a.getLogin().getRecoveryEmail());
            stmt.setString(8, a.getLogin().getPassword());
            
            System.out.println(stmt.toString());
            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            System.out.println("Entrou aki no SQLException: " + ex.getMessage());
            return false;
        } finally {
            System.out.println("Finalizou a conexão");
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean update(Administrator a) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            //name, cpf, sexo, andress, income, institution, course, half, registration, status
            stmt = con.prepareStatement("UPDATE administratores SET firstName = ?, lastName = ?, cpf = ?, andress = ?,"
                    + " sexo = ?, email = ?, recoveryemail = ? password = ? ");
            System.out.println(stmt.toString());
            stmt.setString(1, a.getFirstName());
            stmt.setString(2, a.getLastName());
            stmt.setString(3, a.getCpf());
            stmt.setString(4, a.getAddress());
            stmt.setString(5, String.valueOf(a.getGenere()));
          
            stmt.setString(6, a.getLogin().getEmail());
            stmt.setString(7, a.getLogin().getRecoveryEmail());
            stmt.setString(8, a.getLogin().getPassword());
            System.out.println(stmt.toString());
            stmt.executeUpdate();
            System.out.println("Atualizado com sucesso!");
            return true;
        } catch (SQLException ex) {
            System.out.println("Entrou aki no SQLException: " + ex.getMessage());
            System.out.println("Falha ao atualizar");
            return false;
        } finally {
            System.out.println("Finalizou a conexão");
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    // chave primaria/ pesquisa é o cpf
    public boolean delete(Administrator a) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM administratores WHERE cpf = ?");
            System.out.println(stmt.toString());
            stmt.setString(1, a.getCpf());
            stmt.executeUpdate();
            System.out.println("Deletado com sucesso");
            return true;
        } catch (SQLException ex) {
            System.out.println("Entrou aki no SQLException: " + ex.getMessage());
            return false;
        } finally {
            System.out.println("Finalizou a conexão");
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public LinkedList<Administrator> read() throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM administratores");
        ResultSet rs = stmt.executeQuery();
        LinkedList administratores = new LinkedList();
       
        while (rs.next()) {
            
            String firstName = rs.getString("firsName");
            String lastName = rs.getString("lastName");
            
            String CPF = rs.getString("cpf");
            Settings.Genere genero = (rs.getString("sexo").charAt(0) == 'M') ? Settings.Genere.M : Settings.Genere.F;
            String endereco = rs.getString("andress");
            
            
            String email = rs.getString("email");
            
            String recoveryemail = rs.getString("recoveryemail");
            
            String password = rs.getString("password");
            
            
            
            Administrator admin= new Administrator();
            
            admin.setFirstName(firstName);
            admin.setLastName(lastName);
            admin.setAddress(endereco);
            
            
           Login ls = new Login(email, recoveryemail, password);
           
           admin.setLogin(ls);
            
            
            
            administratores.add(admin);
        }
        ConnectionFactory.closeConnection(con, stmt, rs);
        return administratores;
    }

    public Administrator readAdminForCPF(String cpf) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Administrator admin = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM administratores WHERE cpf = ?");
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();
          
            if (rs.next()) {
                String firstName = rs.getString("firsName");
                String lastName = rs.getString("lastName");

                String CPF = rs.getString("cpf");
                Settings.Genere genero = (rs.getString("sexo").charAt(0) == 'M') ? Settings.Genere.M : Settings.Genere.F;
                String endereco = rs.getString("andress");

            

                String email = rs.getString("email");

                String recoveryemail = rs.getString("recoveryemail");

                String password = rs.getString("password");
                
                admin= new Administrator();
            
                admin.setFirstName(firstName);
                admin.setLastName(lastName);
                admin.setAddress(endereco);


               Login ls = new Login(email, recoveryemail, password);

               admin.setLogin(ls);

                
            } else {
                throw new RuntimeException("A pesquisa não retronou nenhum resultado!");
            }
            return admin;
        } catch (SQLException ex) {
            Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return admin;
    }
    
}
