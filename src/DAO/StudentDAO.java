package DAO;


import connections.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Person;
import model.Student;
import util.Settings.Course;
import util.Settings.Genere;
import util.Settings.Instituition;

/**
 *
 * @author Uellington Damasceno
 */
public class StudentDAO {
<<<<<<< HEAD

    //criacao de um estudande no banco de dados
    public boolean create(Student a) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO alunos (firstName, lastName, cpf, andress, sexo, institution, course, registration, email, recoveryEmail, password)"
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
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

    // chave primaria/ pesquisa para o registro
    public boolean delete(Student a) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM alunos WHERE registration = ?");
            System.out.println(stmt.toString());
            stmt.setString(1, String.valueOf(a.getInstitution()));
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
            
            String firstName = rs.getString("firsName");
            String lastName = rs.getString("lastName");
            
            String CPF = rs.getString("cpf");
            Genere genero = (rs.getString("sexo").charAt(0) == 'M') ? Genere.M : Genere.F;
            String endereco = rs.getString("andress");
            
            Object instituicao = rs.getString("institution");
            
                    
            Object curso = rs.getString("course");
            
            Object matricula =  rs.getString("registration");
            
            String email = rs.getString("email");
            
            String recoveryemail = rs.getString("recoveryemail");
            
            String password = rs.getString("password");
            
            
            
            Student aluno = new Student();
            
            aluno.setFirstName(firstName);
            aluno.setLastName(lastName);
            aluno.setAddress(endereco);
            
            aluno.setInstitution((Instituition) instituicao);
            
            aluno.setCourse((Course) curso);
            
            aluno.setRegistration((String) matricula);
            
            aluno.setLogin(email, recoveryemail, password);
            
            
            
            alunos.add(aluno);
        }
        ConnectionFactory.closeConnection(con, stmt, rs);
        return alunos;
    }

    public Student readAlunoForCPF(String cpf) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Student aluno = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM alunos WHERE cpf = ?");
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();
          
            if (rs.next()) {
               String firstName = rs.getString("firsName");
            String lastName = rs.getString("lastName");
            
            String CPF = rs.getString("cpf");
            Genere genero = (rs.getString("sexo").charAt(0) == 'M') ? Genere.M : Genere.F;
            String endereco = rs.getString("andress");
            
            Object instituicao = rs.getString("institution");
            
                    
            Object curso = rs.getString("course");
            
            Object matricula =  rs.getString("registration");
            
            String email = rs.getString("email");
            
            String recoveryemail = rs.getString("recoveryemail");
            
            String password = rs.getString("password");
            
            
            
            aluno = new Student();
            
            aluno.setFirstName(firstName);
            aluno.setLastName(lastName);
            aluno.setAddress(endereco);
            
            aluno.setInstitution((Instituition) instituicao);
            
            aluno.setCourse((Course) curso);
            
            aluno.setRegistration((String) matricula);
            
            aluno.setLogin(email, recoveryemail, password);
                
            } else {
                throw new RuntimeException("A pesquisa não retronou nenhum resultado!");
            }
            return aluno;
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return aluno;
    }
=======
	
	/*private int matricula;
	private String nome;
	private String email;
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}*/
    
>>>>>>> ef1494589d7b81d046e41573f7c968344f492083
}
