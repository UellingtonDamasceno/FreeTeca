/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.frontend;

import controllers.backend.AudioController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author acmne
 */
public class MainController implements Initializable {

    @FXML
    private ImageView imageViewSlider;
    @FXML
    private StackPane stackPane;
    @FXML
    private ToggleButton btnAccessbility;
    @FXML
    private Button btnSettings;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label lblInfo;
    @FXML
    private Label btnRegister;
    @FXML
    private Button btnEntry;

    private AudioController audioController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.audioController = new AudioController();
        this.initialize();
    }

    private void initialize() {
        this.txtEmail.textProperty().addListener((observable, oldValue, newValue) -> {
            this.playAudio(newValue.toCharArray()[newValue.length() - 1]+"");
        });
    }

    private void playAudio(String character) {
        if (this.btnAccessbility.selectedProperty().get()) {
            audioController.playAudio(character + "");
        }
    }

    @FXML
    private void openSettings(ActionEvent event) {
    }

    @FXML
    private void openRegister(MouseEvent event) {
    }

    @FXML
    private void login(ActionEvent event) {
    }

}
