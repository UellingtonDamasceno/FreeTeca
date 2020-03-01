package DAO;

import connections.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Login;
import model.Student;
import model.exceptions.NotFoundException;
import util.Settings.Course;
import util.Settings.Genere;
import util.Settings.Instituition;

/**
 *
 * @author Uellington Damasceno
 */
public class StudentDAO {

    //criacao de um estudande no banco de dados
    public boolean create(Student a) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO alunos (firstName, lastName, cpf, andress, sexo, institution, course, registration, email, recoveryEmail, password)"
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            System.out.println(a.getCpf());
            stmt.setString(1, a.getFirstName());
            stmt.setString(2, a.getLastName());
            stmt.setString(3, a.getCpf());
            stmt.setString(4, a.getAddress());
            stmt.setString(5, String.valueOf(a.getGenere()));
            stmt.setString(6, String.valueOf(a.getInstitution()));
            stmt.setString(7, String.valueOf(a.getCourse()));
            stmt.setString(8, a.getRegistration());
            stmt.setString(9, a.getLogin().getEmail());
            stmt.setString(10, a.getLogin().getRecoveryEmail());
            stmt.setString(11, a.getLogin().getPassword());

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

    public boolean update(Student a) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            //name, cpf, sexo, andress, income, institution, course, half, registration, status
            stmt = con.prepareStatement("UPDATE alunos SET firstName = ?, lastName = ?, cpf = ?, andress = ?,"
                    + " sexo = ?, institution = ?, course = ?, registration = ?, email = ?, recoveryemail = ? password = ? ");
            System.out.println(stmt.toString());
            stmt.setString(1, a.getFirstName());
            stmt.setString(2, a.getLastName());
            stmt.setString(3, a.getCpf());
            stmt.setString(4, a.getAddress());
            stmt.setString(5, String.valueOf(a.getGenere()));
            stmt.setString(6, String.valueOf(a.getInstitution()));
            stmt.setString(7, String.valueOf(a.getCourse()));
            stmt.setString(8, a.getRegistration());
            stmt.setString(9, a.getLogin().getEmail());
            stmt.setString(10, a.getLogin().getRecoveryEmail());
            stmt.setString(11, a.getLogin().getPassword());
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

    public boolean delete(Student a) throws ClassNotFoundException, SQLException {
        return delete(a.getCpf());
    }

    // chave primaria/ delete para o registro
    public boolean delete(String cpf) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM alunos WHERE cpf = ?");

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

    public LinkedList<Student> read() throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM alunos");
        ResultSet rs = stmt.executeQuery();
        LinkedList alunos = new LinkedList();

        while (rs.next()) {

            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");

            String CPF = rs.getString("cpf");
            Genere genero = (rs.getString("sexo").charAt(0) == 'M') ? Genere.MASCULINO : Genere.FEMININO;
            String endereco = rs.getString("andress");

            String instituicao = rs.getString("institution");

            String curso = rs.getString("course");

            String matricula = rs.getString("registration");
            String email = rs.getString("email");
            String recoveryemail = rs.getString("recoveryemail");
            String password = rs.getString("password");
            Student aluno = new Student();

            aluno.setFirstName(firstName);
            aluno.setLastName(lastName);
            aluno.setCpf(CPF);
            aluno.setGenere(genero);
            aluno.setAddress(endereco);

            aluno.setInstitution(Instituition.valueOf(instituicao));
            aluno.setCourse(Course.valueOf(curso));
            aluno.setRegistration(matricula);

            Login ls = new Login(email, recoveryemail, password);
            aluno.setLogin(ls);
            alunos.add(aluno);
        }
        ConnectionFactory.closeConnection(con, stmt, rs);
        return alunos;
    }

    public Student readStudentForEmail(String email) throws NotFoundException, SQLException, ClassNotFoundException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Student aluno = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM alunos WHERE email = ?");
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            if (rs.next()) {
                
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");

                String CPF = rs.getString("cpf");
                Genere genero = (rs.getString("sexo").charAt(0) == 'M') ? Genere.MASCULINO : Genere.FEMININO;
                String endereco = rs.getString("andress");

                String instituicao = rs.getString("institution");
                String curso = rs.getString("course");
                String matricula = rs.getString("registration");
                String recoveryemail = rs.getString("recoveryemail");
                String password = rs.getString("password");

                aluno = new Student();

                aluno.setFirstName(firstName);
                aluno.setLastName(lastName);
                aluno.setCpf(CPF);
                aluno.setGenere(genero);
                aluno.setAddress(endereco);

                aluno.setInstitution(Instituition.valueOf(instituicao));
                aluno.setCourse(Course.valueOf(curso));
                aluno.setRegistration(matricula);
                Login ls = new Login(email, recoveryemail, password);
                System.out.println("Login recupreado: "+ ls.getEmail() +" " + ls.getPassword());
                aluno.setLogin(ls);

            } else {
                throw new NotFoundException();
            }
            return aluno;
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return aluno;
    }
}
