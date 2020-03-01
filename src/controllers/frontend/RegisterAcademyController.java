/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.frontend;

import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException;
import controllers.backend.NotificationsController;
import controllers.backend.ValidationController;
import facade.FacadeFrontend;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.exceptions.MissingValuesException;
import util.MaskFieldUtil;
import util.Settings;

/**
 * FXML Controller class
 *
 * @author acmne
 */
public class RegisterAcademyController implements Initializable {

    @FXML
    private ComboBox<Settings.Instituition> comboAcademy;
    @FXML
    private ComboBox<Settings.Course> comboCourse;
    @FXML
    private TextField txtID;
    @FXML
    private Button btnReturn;
    @FXML
    private Button btnSave;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.comboAcademy.setItems(FXCollections.observableArrayList(Settings.Instituition.values()));
        this.comboCourse.setItems(FXCollections.observableArrayList(Settings.Course.values()));
        this.comboCourse.getSelectionModel().selectFirst();
        this.comboAcademy.getSelectionModel().selectFirst();

        MaskFieldUtil.reproducer(txtID);
    }

    @FXML
    private void returning(ActionEvent event) {
        try {
            FacadeFrontend.getInstance().changeSideBar(Settings.Scenes.REGISTER_LOGIN);
        } catch (Exception ex) {
            Logger.getLogger(RegisterPersonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void save(ActionEvent event) {
        try {
            ValidationController.getInstance().registerAcademy(comboAcademy.getValue().name(), comboCourse.getValue().getName(), txtID.getText());
        } catch (MissingValuesException ex) {
            NotificationsController.getInstance().errorNotification("Campo vazio!", ex.getMessage());
        }

        try {
            ValidationController.getInstance().save();
            FacadeFrontend.getInstance().changeSideBar(Settings.Scenes.HOME_SIDE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterAcademyController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            NotificationsController.getInstance().errorNotification("Conexão falida!", "Conecte-se com a internet e tente novamente!");
        }

    }

}
