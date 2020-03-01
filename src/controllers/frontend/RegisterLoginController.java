package controllers.frontend;

import controllers.backend.NotificationsController;
import controllers.backend.ValidationController;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Login;
import model.Student;
import model.exceptions.MissingValuesException;
import model.exceptions.PasswordWrongException;
import util.MaskFieldUtil;
import util.Settings;
import util.Settings.Icons;

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
    private TextField txtRecoveryEmail;
    @FXML
    private PasswordField txtPassword1;
    @FXML
    private ImageView imgEyeConf;
    @FXML
    private ImageView imgEye;

    private boolean eye, eyeConf;
    @FXML
    private Button btnEdit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MaskFieldUtil.reproducer(this.txtEmail);
        MaskFieldUtil.reproducer(this.txtRecoveryEmail);

        this.eye = true;
        this.eyeConf = true;

        this.setEyeEffect();
    }

    private void setEyeEffect() {
        imgEyeConf.setOnMouseEntered((event) -> {
            if (eyeConf) {
                this.imgEyeConf.setImage(new Image(Icons.SLEEPY_EYE.getIconPath()));
            }
        });
        imgEyeConf.setOnMouseExited((event) -> {
            if (eyeConf) {
                this.imgEyeConf.setImage(new Image(Icons.CLOSED_EYE.getIconPath()));
            }
        });

        imgEyeConf.setOnMouseClicked((event) -> {
            if (eyeConf) {
                this.imgEyeConf.setImage(new Image(Icons.EYE.getIconPath()));
            } else {
                new Thread(() -> {
                    this.imgEyeConf.setImage(new Image(Icons.SLEEPY_EYE.getIconPath()));
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(RegisterLoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    this.imgEyeConf.setImage(new Image(Icons.CLOSED_EYE.getIconPath()));
                }).start();
            }
            this.eyeConf = !this.eyeConf;
        });
        ///======================================================
        imgEye.setOnMouseEntered((event) -> {
            if (eye) {
                this.imgEye.setImage(new Image(Icons.SLEEPY_EYE.getIconPath()));
            }
        });
        imgEye.setOnMouseExited((event) -> {
            if (eye) {
                this.imgEye.setImage(new Image(Icons.CLOSED_EYE.getIconPath()));
            }
        });

        imgEye.setOnMouseClicked((event) -> {
            if (eye) {
                this.imgEye.setImage(new Image(Icons.EYE.getIconPath()));
            } else {
                new Thread(() -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(RegisterLoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    this.imgEye.setImage(new Image(Icons.CLOSED_EYE.getIconPath()));
                }).start();
            }
            this.eye = !this.eye;
        });
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
        try {
            try {
                ValidationController.getInstance().registerLogin(txtEmail.getText(), txtPassword.getText(), txtPassword1.getText(), txtRecoveryEmail.getText());
                FacadeFrontend.getInstance().changeSideBar(Settings.Scenes.REGISTER_ACADEMY);
            } catch (PasswordWrongException ex) {
                NotificationsController.getInstance().infoNotification("Senhas diferentes", ex.getMessage());
            }
        } catch (MissingValuesException ex) {
            NotificationsController.getInstance().errorNotification("Campo vazio!", ex.getMessage());
        }
    }
    
    public void load(Student a){
        txtEmail.setText(a.getLogin().getEmail());
        txtRecoveryEmail.setText(a.getLogin().getRecoveryEmail());
        txtPassword.setText(a.getLogin().getPassword());
        txtPassword1.setText(a.getLogin().getPassword());
        
        btnEdit.setVisible(true);
    }
}
