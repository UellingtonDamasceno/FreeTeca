package controllers.frontend;

import facade.FacadeFrontend;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
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
    private Button btnManager;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnList;
    @FXML
    private Button btnLeave;
    @FXML
    private VBox vboxRoot;
    @FXML
    private VBox vboxSide;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.initialize();
    }

    private void initialize() {
        try {
            Parent loadedScreen = FacadeFrontend.getInstance().getScreen(Scenes.LIST);
            this.vboxRoot.getChildren().add(loadedScreen);
        } catch (Exception ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
