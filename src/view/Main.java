package view;

import DAO.StudentDAO;
import connections.ConnectionFactory;
import facade.FacadeFrontend;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Login;
import model.Student;
import util.Settings;

/**
 *
 * @author Uellington Conceição
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Connection connection = ConnectionFactory.getConnection();

            StudentDAO sd = new StudentDAO();

//            for (int i = 0; i < 10; i++) {
//
//                Student s = new Student();
//                s.setCourse(Settings.Course.ECOMP);
//                s.setInstitution(Settings.Instituition.UEFS);
//                s.setFirstName("César");
//                s.setLastName("Lindão");
//                s.setAddress("Rua do bobo nº 0");
//                s.setCpf("00000" + i);
//                Login login = new Login("gtg@gmail.com", "gtg@gmail.com", "4654df546f");
//                s.setLogin(login);
//                s.setRegistration("1464afs546");
//                s.setGenere(Settings.Genere.MASCULINO);
//
//                sd.create(s);
//            }
            
            Student s2 = new Student();
            LinkedList students = sd.read();

            System.out.println(students);
            
            sd.delete((Student) students.get(2));

            FacadeFrontend.getInstance().initialize(primaryStage, Settings.Scenes.MAIN);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);
    }

}
