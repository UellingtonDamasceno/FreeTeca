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
