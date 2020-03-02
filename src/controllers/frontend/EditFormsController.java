/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.frontend;

import DAO.StudentDAO;
import controllers.backend.AudioController;
import controllers.backend.NotificationsController;
import controllers.backend.ValidationController;
import facade.FacadeFrontend;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Student;
import model.exceptions.MissingValuesException;
import model.exceptions.PasswordWrongException;
import util.MaskFieldUtil;
import util.Settings;
import util.Settings.Course;
import util.Settings.Genere;
import util.Settings.Instituition;

/**
 * FXML Controller class
 *
 * @author csacl
 */
public class EditFormsController implements Initializable {

    @FXML
    private ComboBox<Settings.Instituition> comboAcademy;
    @FXML
    private ComboBox<Settings.Course> comboCourse;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtRecoveryEmail;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private ComboBox<Genere> cbGenere;
    @FXML
    private TextField txtAddress;
    @FXML
    private Button btnNext;

    private Student student;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.comboAcademy.setItems(FXCollections.observableArrayList(Settings.Instituition.values()));
        this.comboCourse.setItems(FXCollections.observableArrayList(Settings.Course.values()));
        this.comboCourse.getSelectionModel().selectFirst();
        this.comboAcademy.getSelectionModel().selectFirst();
        MaskFieldUtil.reproducer(this.txtEmail);
        MaskFieldUtil.reproducer(this.txtRecoveryEmail);
        comboCourse.setOnAction((event) -> {
            Course newValue = comboCourse.getSelectionModel().getSelectedItem();
            AudioController.getInstance().playAudio(newValue.getCurso());
        });

        comboAcademy.setOnAction((event) -> {
            Instituition temp = comboAcademy.getSelectionModel().getSelectedItem();
            AudioController.getInstance().playAudio(temp.getAudio());
        });
        MaskFieldUtil.reproducer(txtID);

        this.cbGenere.setItems(FXCollections.observableArrayList(Settings.Genere.values()));
        this.cbGenere.getSelectionModel().selectFirst();

        MaskFieldUtil.reproducer(txtFirstName);
        MaskFieldUtil.reproducer(txtLastName);
        MaskFieldUtil.reproducer(txtAddress);

        cbGenere.setOnAction((event) -> {
            Settings.Genere temp = cbGenere.getSelectionModel().getSelectedItem();
            AudioController.getInstance().playAudio(temp.getGenere());
        });
    }

    @FXML
    private void infoAcademy(MouseEvent event) {
    }

    @FXML
    private void lblAcademyMouseEntered(MouseEvent event) {
    }

    @FXML
    private void lblCursoMouseEntered(MouseEvent event) {
    }

    @FXML
    private void lblMatriculaMouseEntered(MouseEvent event) {
    }

    @FXML
    private void txtFieldMatriculaMouseEntered(MouseEvent event) {
    }

    @FXML
    private void lblInfoLoginOnMouseEntered(MouseEvent event) {
    }

    @FXML
    private void lblEmailOnMouseEntered(MouseEvent event) {
    }

    @FXML
    private void lblEmailRECOnMouseEntered(MouseEvent event) {
    }

    @FXML
    private void lblInfoPersonOnMouseEntered(MouseEvent event) {
    }

    @FXML
    private void lblPrimeiroNomeOnMouseEntered(MouseEvent event) {
    }

    @FXML
    private void lblUltimoNomeOnMouseEntered(MouseEvent event) {
    }

    @FXML
    private void lblGeneroOnMouseEntered(MouseEvent event) {
    }

    @FXML
    private void lblEnderecoOnMouseEntered(MouseEvent event) {
    }

    @FXML
    private void btnAvancarOnMouseEntered(MouseEvent event) {
    }

    @FXML
    private void next(ActionEvent event) {
        new Thread(() -> {
            Platform.runLater(() -> {
                this.btnNext.setDisable(true);
            });
            try {
                ValidationController.getInstance().registerAcademy(comboAcademy.getValue().name(), comboCourse.getValue().getName(), txtID.getText());
                ValidationController.getInstance().registerLogin(txtEmail.getText(), student.getLogin().getPassword(), student.getLogin().getPassword(), txtRecoveryEmail.getText());
                ValidationController.getInstance().registerPerson(txtFirstName.getText(), txtLastName.getText(), cbGenere.getValue().getGenere(), student.getCpf(), txtAddress.getText());
                StudentDAO corno = new StudentDAO();
                corno.update(ValidationController.getInstance().getStudent());
                FacadeFrontend.getInstance().updateItemList(ValidationController.getInstance().getStudent());
                NotificationsController.getInstance().sucessNotification("Sucesso!", "Aluno: " + student + " Foi atualizado com sucesso!");
            } catch (MissingValuesException ex) {
                Platform.runLater(() -> {
                    this.btnNext.setDisable(false);
                });
                NotificationsController.getInstance().errorNotification("Campo vazio!", ex.getMessage());
            } catch (PasswordWrongException ex) {
                Platform.runLater(() -> {
                    this.btnNext.setDisable(false);
                });
                NotificationsController.getInstance().infoNotification("Senhas diferentes", ex.getMessage());
            } catch (ClassNotFoundException ex) {
                Platform.runLater(() -> {
                    this.btnNext.setDisable(false);
                });
                NotificationsController.getInstance().errorNotification("Erro de se fudê!", "Acredite, vc não deveria tá vendo isso!");
            } catch (SQLException ex) {
                Platform.runLater(() -> {
                    this.btnNext.setDisable(false);
                });
                NotificationsController.getInstance().errorNotification("Conexão falida!", "Conecte-se com a internet e tente novamente!");
            }
            Platform.runLater(()->{
                this.btnNext.setDisable(false);
            });
        }).start();
    }

    public void load(Student student) {
        this.student = student;
        comboAcademy.getSelectionModel().select(student.getInstitution());
        comboCourse.getSelectionModel().select(student.getCourse());
        txtID.setText(student.getRegistration());
        txtFirstName.setText(student.getFirstName());
        txtLastName.setText(student.getLastName());
        txtAddress.setText(student.getAddress());
        txtEmail.setText(student.getLogin().getEmail());
        txtRecoveryEmail.setText(student.getLogin().getRecoveryEmail());
        cbGenere.getSelectionModel().select(student.getGenere());
    }
}
