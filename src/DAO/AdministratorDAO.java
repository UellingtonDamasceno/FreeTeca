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
import model.exceptions.NotFoundException;
import util.Settings;
import util.Settings.Genere;

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
            stmt = con.prepareStatement("UPDATE adminstradores SET firstName = ?, lastName = ?, cpf = ?, andress = ?,"
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

    public boolean delete(Administrator a) throws ClassNotFoundException, SQLException{
        return delete(a.getCpf());
    }
    
// chave primaria/ pesquisa é o cpf
    public boolean delete(String cpf) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM administradores WHERE cpf = ?");
            
            stmt.setString(1, cpf);
            System.out.println(stmt.toString());
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
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM administradores");
        ResultSet rs = stmt.executeQuery();
        LinkedList adminstradores = new LinkedList();
       
        while (rs.next()) {
            
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            
            String CPF = rs.getString("cpf");
            Settings.Genere genero = (rs.getString("sexo").charAt(0) == 'M') ? Genere.MASCULINO : Genere.FEMININO;
            String endereco = rs.getString("andress");
            
            
            String email = rs.getString("email");
            
            String recoveryemail = rs.getString("recoveryemail");
            
            String password = rs.getString("password");
            
            
            
            Administrator admin= new Administrator();
            
            admin.setFirstName(firstName);
            admin.setLastName(lastName);
            admin.setCpf(CPF);
            admin.setGenere(genero);
            admin.setAddress(endereco);
            
            
           Login ls = new Login(email, recoveryemail, password);
           
           admin.setLogin(ls);
            
            
            
            adminstradores.add(admin);
        }
        ConnectionFactory.closeConnection(con, stmt, rs);
        return adminstradores;
    }

    public Administrator readAdminForEmail(String email) throws ClassNotFoundException, SQLException, NotFoundException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Administrator admin = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM adminstradores WHERE email = ?");
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            if (rs.next()) {
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");

                String CPF = rs.getString("cpf");
                Settings.Genere genero = (rs.getString("sexo").charAt(0) == 'M') ? Genere.MASCULINO :Genere.FEMININO;
                String endereco = rs.getString("andress");

                String recoveryemail = rs.getString("recoveryemail");
                String password = rs.getString("password");
                admin= new Administrator();

                admin.setFirstName(firstName);
                admin.setLastName(lastName);
                admin.setCpf(CPF);
                admin.setGenere(genero);
                admin.setAddress(endereco);

               Login ls = new Login(email, recoveryemail, password);

               admin.setLogin(ls);

            } else {

                throw new NotFoundException();
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