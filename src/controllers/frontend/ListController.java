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
    private List<Student> students;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.initialize();
        this.students = new LinkedList();
    }

    private void initialize() {
        new Thread(() -> {
            StudentDAO studentDAO = new StudentDAO();
            int[] i = new int[1];

            try {
                List<Student> stTemp = studentDAO.read();
                System.out.println(stTemp);
                stTemp.forEach((student) -> {
                    try {
                        this.add(student, 0);
                    } catch (IOException ex) {
                        Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            } catch (ClassNotFoundException ex) {
                NotificationsController.getInstance().errorNotification("Aconteceu um erro inesperado!", "Você nunca deveria ter visto esse erro!");
            } catch (SQLException ex) {
                NotificationsController.getInstance().errorNotification("Você está conectado?", "Verifique sua rede e tente novamente!");
            }
        }).start();

    }

    private void add(Student student, int pos) throws IOException {
        FXMLLoader loader = FacadeFrontend.getInstance().getLoaderScreen(Scenes.ITEM_LIST);
        Parent loadedScreen = loader.load();
        ItemListController itemController = loader.getController();
        students.add(pos, student);
        itemController.loadStudent(student);
        Platform.runLater(() -> {
            vboxList.getChildren().add(pos, loadedScreen);
        });
    }

    public void update(Student student) {
        int value = students.indexOf(student);
        System.out.println(student);
        System.out.println(value);
        this.students.remove(student);
        Platform.runLater(() -> {
            vboxList.getChildren().remove(value);
        });

        try {
            this.add(student, value);
        } catch (IOException ex) {
            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeItem(Parent root) {
        Platform.runLater(() -> {
            this.vboxList.getChildren().remove(root);
        });
    }

}
