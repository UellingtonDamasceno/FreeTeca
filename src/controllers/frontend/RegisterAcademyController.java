/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.frontend;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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
    
}
