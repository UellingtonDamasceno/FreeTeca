package controllers.frontend;

import DAO.StudentDAO;
import controllers.backend.NotificationsController;
import facade.FacadeFrontend;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import model.Student;
import util.Settings.Scenes;

/**
 * FXML Controller class
 *
 * @author acmne
 */
public class ListController implements Initializable {

    @FXML
    private VBox vboxList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.initialize();
    }

    private void initialize() {
        StudentDAO studentDAO = new StudentDAO();
        try {
            List<Student> students = studentDAO.read();
            students.forEach((student) -> {
                FXMLLoader loader = FacadeFrontend.getInstance().getLoaderScreen(Scenes.ITEM_LIST);
                try {
                    Parent loadedScreen = loader.load();
                    ItemListController itemController = loader.getController();
                    itemController.loadStudent(student);
                    Platform.runLater(() -> {
                        vboxList.getChildren().add(loadedScreen);
                    });
                } catch (IOException ex) {
                    Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (ClassNotFoundException ex) {
            NotificationsController.getInstance().errorNotification("Aconteceu um erro inesperado!", "Você nunca deveria ter visto esse erro!");
        } catch (SQLException ex) {
            NotificationsController.getInstance().errorNotification("Você está conectado?", "Verifique sua rede e tente novamente!");
        }
    }

    public void removeItem(Parent root) {
        Platform.runLater(() -> {
            this.vboxList.getChildren().remove(root);
        });
    }

}
