package controllers.frontend;

import controllers.backend.AudioController;
import controllers.backend.NotificationsController;
import controllers.backend.SessionController;
import controllers.backend.ValidationController;
import facade.FacadeFrontend;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.LogablePerson;
import model.Person;
import model.exceptions.MissingValuesException;
import model.exceptions.NotFoundException;
import util.MaskFieldUtil;
import util.Settings;
import util.Settings.Admin;
import util.Settings.Scenes;
import util.Settings.Slider;

/**
 * FXML Controller class
 *
 * @author acmne
 */
public class MainController implements Initializable {

    @FXML
    private ImageView imageViewSlider;
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

    @FXML
    private VBox homePanel;
    @FXML
    private HBox hBoxGit;
    @FXML
    private Pane paneRoot;

    private boolean activated;
    @FXML
    private ImageView imgEye;

    private boolean eye;
    @FXML
    private ImageView ImageAcessibilidade;

    private LogablePerson user = null;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MaskFieldUtil.reproducer(txtEmail);
        this.activated = false;
        this.eye = true;
        this.imageViewSlider.setImage(new Image(Slider.FIRST.getImagePath()));
        this.setAllBinds();
        this.startSlide();
    }

    private void setAllBinds() {
        ImageAcessibilidade.setOnMouseEntered((event) -> {
            if (!this.btnAccessbility.isSelected()) {
                AudioController.getInstance().playAudio(Settings.Phrase.LIGAR_ACESSIBILIDADE.getPhrase());
            } else {
                AudioController.getInstance().playAudio(Settings.Phrase.DESLIGAR_ACESSIBILIDADE.getPhrase());
            }

        });
        this.btnAccessbility.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                AudioController.getInstance().setCanReproduce(newValue);
            }
        });
        this.btnAccessbility.setOnMouseEntered((event) -> {
            AudioController.getInstance().setCanReproduce(true);
            if (btnAccessbility.isSelected()) {
                AudioController.getInstance().playAudio(Settings.Phrase.DESLIGAR_ACESSIBILIDADE.getPhrase());
            } else {
                AudioController.getInstance().playAudio(Settings.Phrase.LIGAR_ACESSIBILIDADE.getPhrase());
                AudioController.getInstance().setCanReproduce(false);
            }
        });

        imgEye.setOnMouseEntered((event) -> {
            if (eye) {
                this.imgEye.setImage(new Image(Settings.Icons.SLEEPY_EYE.getIconPath()));
            }
        });
        imgEye.setOnMouseExited((event) -> {
            if (eye) {
                this.imgEye.setImage(new Image(Settings.Icons.CLOSED_EYE.getIconPath()));
            }
        });

        imgEye.setOnMouseClicked((event) -> {
            if (eye) {
                this.imgEye.setImage(new Image(Settings.Icons.EYE.getIconPath()));
            } else {
                new Thread(() -> {
                    this.imgEye.setImage(new Image(Settings.Icons.SLEEPY_EYE.getIconPath()));
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(RegisterLoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    this.imgEye.setImage(new Image(Settings.Icons.CLOSED_EYE.getIconPath()));
                }).start();
            }
            this.eye = !this.eye;
        });
    }

    private void startSlide() {
        this.activated = true;
        new Thread(() -> {
            LinkedList<Image> list = new LinkedList();
            for (Slider value : Slider.values()) {
                list.add(new Image(value.getImagePath()));
            }
            while (this.activated && this.homePanel.getScene().getWindow().isShowing()) {
                list.stream().map((image) -> {
                    Platform.runLater(() -> {
                        this.imageViewSlider.setImage(image);
                    });
                    return image;
                }).forEachOrdered((_item) -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
        }).start();
    }

    @FXML
    private void openSettings(ActionEvent event) {
        this.activated = !this.activated;
    }

    @FXML
    private void openRegister(MouseEvent event) {
        this.changeSideBar(Scenes.REGISTER_PERSON);
    }

    public void changeSideBar(Scenes scene) {
        try {
            Parent loadedScreen = FacadeFrontend.getInstance().getScreen(scene);
            Parent removedScreen = (Parent) this.paneRoot.getChildren().remove(0);
            FacadeFrontend.getInstance().addScreen(Scenes.HOME_SIDE, removedScreen);
            this.paneRoot.getChildren().add(loadedScreen);
        } catch (Exception ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void hide(MouseEvent event) {
        Platform.runLater(() -> {
            this.hBoxGit.setVisible(false);
        });
    }

    @FXML
    private void show(MouseEvent event) {
        Platform.runLater(() -> {
            this.hBoxGit.setVisible(true);
        });
    }

    @FXML
    private void login(ActionEvent event) {
        new Thread(() -> {
            try {
                if (this.txtEmail.getText().equals(Admin.EMAIL.getValue())) {
                    if (this.txtPassword.getText().equals(Admin.PASSWORD.getValue())) {
                        SessionController.getInstance().setUser(null);
                        Platform.runLater(() -> {
                            try {
                                this.activated = false;
                                FacadeFrontend.getInstance().changeScreean(Scenes.DASHBOARD);
                            } catch (Exception ex) {
                                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                    } else {
                        NotificationsController.getInstance().infoNotification("Senhas não conferem!", "A senha digitada não confere!");
                    }
                } else {
                    Platform.runLater(() -> {
                        this.lblInfo.setText("Aguarde, estamos preparando tudo!");
                        this.btnEntry.setDisable(true);
                    });
                    try {
                        user = ValidationController.getInstance().login(this.txtEmail.getText(), this.txtPassword.getText());
                    } catch (MissingValuesException ex) {
                        Platform.runLater(() -> {
                            this.btnEntry.setDisable(false);
                        });
                        NotificationsController.getInstance().errorNotification("Campo vazio.", ex.getMessage());
                    } catch (NotFoundException ex) {
                        Platform.runLater(() -> {
                            this.btnEntry.setDisable(false);
                        });
                        NotificationsController.getInstance().errorNotification("User inexistente", "Não existe um user com o email: \n" + this.txtEmail.getText());
                    } catch (SQLException ex) {
                        Platform.runLater(() -> {
                            this.btnEntry.setDisable(false);
                        });
                        NotificationsController.getInstance().errorNotification("Você está conectado?", "Verifique sua rede e tente novamente!");
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    this.lblInfo.setText("");

                    if (user.authenticate(this.txtEmail.getText(), this.txtPassword.getText())) {
                        SessionController.getInstance().setUser(user);
                        Platform.runLater(() -> {
                            try {
                                this.activated = false;
                                FacadeFrontend.getInstance().changeScreean(Scenes.DASHBOARD);
                            } catch (Exception ex) {
                                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                    } else {
                        Platform.runLater(() -> {
                            this.btnEntry.setDisable(false);
                        });
                        NotificationsController.getInstance().infoNotification("Senhas não conferem!", "A senha digitada não confere!");
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ).start();
    }

    @FXML
    private void lblEmailEntered(MouseEvent event) {
        AudioController.getInstance().playAudio(Settings.Phrase.EMAIL.getPhrase());
    }

    @FXML
    private void lblSenhaEntered(MouseEvent event) {
        AudioController.getInstance().playAudio(Settings.Phrase.SENHA.getPhrase());
    }

    @FXML
    private void btnConfigEntered(MouseEvent event) {
        AudioController.getInstance().playAudio(Settings.Phrase.CONFIGURACOES.getPhrase());
    }

    @FXML
    private void txtEmailEntered(MouseEvent event) {
        AudioController.getInstance().playAudio(Settings.Phrase.INSERIREMAIL.getPhrase());
    }

    @FXML
    private void txtSenhaEntered(MouseEvent event) {
        AudioController.getInstance().playAudio(Settings.Phrase.INSERIRSENHA.getPhrase());
    }

    @FXML
    private void lblErrorEntered(MouseEvent event) {
        if (lblInfo.isDisabled()) {
            AudioController.getInstance().playAudio(Settings.Phrase.ERROLOGIN.getPhrase());
        }
    }

    @FXML
    private void btnEntrarEntered(MouseEvent event) {
        AudioController.getInstance().playAudio(Settings.Phrase.ENTRAR.getPhrase());
    }

    @FXML
    private void lblCadastroEntered(MouseEvent event) {
        AudioController.getInstance().playAudio(Settings.Phrase.NOVO_CADASTRO.getPhrase());
    }

}
