/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.backend;

import controllers.frontend.RegisterPersonController;
import facade.FacadeFrontend;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import util.Settings;

/**
 * FXML Controller class
 *
 * @author acmne
 */
public class RegisterLoginController implements Initializable {

    @FXML
    private Button btnNext;
    @FXML
    private TextField txtEmail;
    @FXML
    private Label lblInfo;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtConfirPassword;
    @FXML
    private TextField txtRecoveryEmail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void previous(ActionEvent event) {
        try {
            FacadeFrontend.getInstance().changeSideBar(Settings.Scenes.REGISTER_PERSON);
        } catch (Exception ex) {
            Logger.getLogger(RegisterPersonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void next(ActionEvent event) {
    }
    
}
