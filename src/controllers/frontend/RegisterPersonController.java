package controllers.frontend;

import controllers.backend.ValidationController;
import facade.FacadeFrontend;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.exceptions.MissingValuesException;
import util.MaskFieldUtil;
import util.Settings.Genere;
import util.Settings.Scenes;

/**
 * FXML Controller class
 *
 * @author acmne
 */
public class RegisterPersonController implements Initializable {

    @FXML
    private Label lblInfo;
    @FXML
    private Button btnNext;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private DatePicker dataPiker;
    @FXML
    private ComboBox<Genere> cbGenere;
    @FXML
    private TextField txtCpf;
    @FXML
    private TextField txtAddress;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbGenere.setItems(FXCollections.observableArrayList(Genere.values()));
        MaskFieldUtil.reproducer(txtCpf);
        MaskFieldUtil.reproducer(dataPiker.getEditor());
        MaskFieldUtil.reproducer(txtFirstName);
        MaskFieldUtil.reproducer(txtLastName);
        MaskFieldUtil.reproducer(txtAddress);

        MaskFieldUtil.cpfField(txtCpf);
        MaskFieldUtil.dateField(dataPiker.getEditor());
    }

    @FXML
    private void previous(ActionEvent event){      
        try {            
            FacadeFrontend.getInstance().changeSideBar(Scenes.HOME_SIDE);
        } catch (Exception ex) {
            Logger.getLogger(RegisterPersonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void next(ActionEvent event) {
        try {
            ValidationController.getInstance().registerPerson(txtFirstName.getText(), txtLastName.getText(), dataPiker.getValue().getDayOfWeek().name(), cbGenere.getValue().getGenere(), txtCpf.getText(), txtAddress.getText());
        } catch (MissingValuesException ex) {
            Logger.getLogger(RegisterPersonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            FacadeFrontend.getInstance().changeSideBar(Scenes.REGISTER_LOGIN);
        } catch (Exception ex) {
            Logger.getLogger(RegisterPersonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
