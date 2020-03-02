package controllers.frontend;

import controllers.backend.SessionController;
import facade.FacadeFrontend;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import model.Person;
import util.Settings.Admin;
import util.Settings.Genere;
import util.Settings.Icons;
import util.Settings.Scenes;

/**
 * FXML Controller class
 *
 * @author csacl
 */
public class DashBoardController implements Initializable {

    @FXML
    private ImageView imageViewUser;
    @FXML
    private Label lblName;
    @FXML
    private Button btnList;
    @FXML
    private Button btnLeave;
    @FXML
    private VBox vboxRoot;
    @FXML
    private VBox vboxSide;
    @FXML
    private VBox vboxLeft;
    @FXML
    private Button btnEdit;

    private boolean isIntialized;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.loadUser(SessionController.getInstance().getUser());
        this.isIntialized = false;
        this.btnList.setOnAction((event) -> {
            if (!this.isIntialized) {
                this.listStudents();
                this.isIntialized = true;
            }
        });
        this.initialize();
    }

    private void initialize() {
        try {
            FXMLLoader loader = FacadeFrontend.getInstance().getLoaderScreen(Scenes.EDIT_FORM);
            Parent parent = loader.load();
            Object controller = loader.getController();
            FacadeFrontend.getInstance().setEditFormController(controller);
            FacadeFrontend.getInstance().updateScreen(Scenes.EDIT_FORM, parent);
            this.changeSide(Scenes.EDIT_FORM);
        } catch (IOException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listStudents() {
        try {
            FXMLLoader loader = FacadeFrontend.getInstance().getLoaderScreen(Scenes.LIST);
            Parent loadedScreen = loader.load();
            FacadeFrontend.getInstance().setListController(loader.getController());
            this.vboxRoot.getChildren().add(loadedScreen);
        } catch (Exception ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadUser(Person person) {
        String name;
        String imagePath;
        if (person == null) {
            name = Admin.NAME.getValue();
            imagePath = Admin.IMAGE.getValue();
            Platform.runLater(() -> {
                this.vboxLeft.getChildren().remove(this.btnEdit);
            });
        } else {
            Platform.runLater(() -> {
                this.vboxLeft.getChildren().remove(this.btnList);
            });
            name = person.getFirstName();
            imagePath = (person.getGenere() == Genere.MASCULINO)
                    ? Icons.USER_MALE.getIconPath()
                    : Icons.USER_FEMALE.getIconPath();
        }
        this.lblName.setText("Bem-vindo, " + name);
        this.imageViewUser.setImage(new Image(imagePath));
    }

    public void changeCenter(Scenes scene) {
        this.changeContent(this.vboxRoot, scene);
    }

    public void changeSide(Scenes scene) {
        this.changeContent(this.vboxSide, scene);
    }

    private void changeContent(VBox vbox, Scenes scene) {
        try {
            Parent loadedScreen = FacadeFrontend.getInstance().getScreen(scene);
            vbox.getChildren().clear();
            vbox.getChildren().add(loadedScreen);
        } catch (Exception ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void previous(ActionEvent event) {
        try {
            FacadeFrontend.getInstance().changeScreean(Scenes.MAIN);
        } catch (Exception ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
