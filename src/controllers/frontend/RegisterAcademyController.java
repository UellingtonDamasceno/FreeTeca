/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.frontend;

import controllers.backend.AudioController;
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
import javafx.scene.input.MouseEvent;
import model.exceptions.MissingValuesException;
import util.MaskFieldUtil;
import util.Settings;
import util.Settings.Course;

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

        comboCourse.setOnAction((event) -> {
            Course newValue = comboCourse.getSelectionModel().getSelectedItem();
            AudioController.getInstance().playAudio(newValue.getCurso());
            System.out.println("O resultado:" + newValue.getCurso());
        });

        MaskFieldUtil.reproducer(txtID);
    }
        
    @FXML
    private void infoAcademy(MouseEvent event) {
        AudioController.getInstance().playAudio(Settings.Phrase.ACADEMYINFO.getPhrase());
    }

    @FXML
    private void lblAcademyMouseEntered(MouseEvent event) {
        AudioController.getInstance().playAudio(Settings.Phrase.INSTITUICAOENSINO.getPhrase());
    }

    @FXML
    private void lblCursoMouseEntered(MouseEvent event) {
        AudioController.getInstance().playAudio(Settings.Phrase.CURSO.getPhrase());
    }

    @FXML
    private void lblMatriculaMouseEntered(MouseEvent event) {
        AudioController.getInstance().playAudio(Settings.Phrase.MATRICULAA.getPhrase());
    }

    @FXML
    private void txtFieldMatriculaMouseEntered(MouseEvent event) {
        AudioController.getInstance().playAudio(Settings.Phrase.INSERIRMATRICULA.getPhrase());
    }

    @FXML
    private void btnRetornarMouseEntered(MouseEvent event) {
        AudioController.getInstance().playAudio(Settings.Phrase.RETORNAR.getPhrase());
    }

    @FXML
    private void btnSalvarMouseEntered(MouseEvent event) {
        AudioController.getInstance().playAudio(Settings.Phrase.SALVAR.getPhrase());
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
            Logger.getLogger(RegisterAcademyController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ValidationController.getInstance().save();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterAcademyController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RegisterAcademyController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            FacadeFrontend.getInstance().changeSideBar(Settings.Scenes.HOME_SIDE);
        } catch (Exception ex) {
            Logger.getLogger(RegisterPersonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
