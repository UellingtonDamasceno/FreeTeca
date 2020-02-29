/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.frontend;

import DAO.StudentDAO;
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
import javafx.scene.control.Separator;
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

    private List<Parent> itemLists;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        itemLists = new LinkedList();
        this.initialize();
    }

    private void initialize() {
        StudentDAO studentDAO = new StudentDAO();

        try {
            List<Student> students = studentDAO.read();
            for (Student student : students) {
                FXMLLoader loader = FacadeFrontend.getInstance().getLoaderScreen(Scenes.ITEM_LIST);
                try {
                    Parent loadedScreen = loader.load();
                    ItemListController itemController = loader.getController();//To ouvindo a musica 
                    itemController.loadStudent(student);
                    itemLists.add(loadedScreen);
//                    Platform.runLater(() -> {
                        vboxList.getChildren().add(loadedScreen);
//                    }
                } catch (IOException ex) {
                    Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
