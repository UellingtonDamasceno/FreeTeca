package controllers.frontend;

import controllers.backend.AudioController;
import controllers.backend.NotificationsController;
import controllers.backend.ValidationController;
import facade.FacadeFrontend;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.input.MouseEvent;
import model.exceptions.MissingValuesException;
import util.MaskFieldUtil;
import util.Settings;
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
        this.dataPiker.setValue(LocalDate.now());
        this.cbGenere.setItems(FXCollections.observableArrayList(Genere.values()));
        this.cbGenere.getSelectionModel().selectFirst();
        MaskFieldUtil.reproducer(txtCpf);
        MaskFieldUtil.reproducer(dataPiker.getEditor());
        MaskFieldUtil.reproducer(txtFirstName);
        MaskFieldUtil.reproducer(txtLastName);
        MaskFieldUtil.reproducer(txtAddress);

        MaskFieldUtil.cpfField(txtCpf);
        MaskFieldUtil.dateField(dataPiker.getEditor());
        
        cbGenere.setOnAction((event)->{
            Genere temp = cbGenere.getSelectionModel().getSelectedItem();
            AudioController.getInstance().playAudio(temp.getGenere());
        });
    }

    @FXML
    private void previous(ActionEvent event) {
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
            try {
                FacadeFrontend.getInstance().changeSideBar(Scenes.REGISTER_LOGIN);
            } catch (Exception ex) {
                Logger.getLogger(RegisterPersonController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (MissingValuesException ex) {
            NotificationsController.getInstance().errorNotification("Campo vazio!", ex.getMessage());
        }

    }

    @FXML
    private void lblInfoPersonOnMouseEntered(MouseEvent event) {
        AudioController.getInstance().playAudio(Settings.Phrase.INFOPERSONAL.getPhrase());
    }

    @FXML
    private void btnRetornarOnMouseEntered(MouseEvent event) {
        AudioController.getInstance().playAudio(Settings.Phrase.RETORNAR.getPhrase());
    }

    @FXML
    private void btnAvancarOnMouseEntered(MouseEvent event) {
        AudioController.getInstance().playAudio(Settings.Phrase.AVANCAR.getPhrase());
    }

    @FXML
    private void lblPrimeiroNomeOnMouseEntered(MouseEvent event) {
        AudioController.getInstance().playAudio(Settings.Phrase.PRIMEIRO_NOME.getPhrase());
    }

    @FXML
    private void lblUltimoNomeOnMouseEntered(MouseEvent event) {
        AudioController.getInstance().playAudio(Settings.Phrase.SEGUNDO_NOME.getPhrase());
    }

    @FXML
    private void lblNascimentoOnMouseEntered(MouseEvent event) {
        AudioController.getInstance().playAudio(Settings.Phrase.NASCIMENTO.getPhrase());
    }

    @FXML
    private void lblGeneroOnMouseEntered(MouseEvent event) {
        AudioController.getInstance().playAudio(Settings.Phrase.SEXO.getPhrase());
    }

    @FXML
    private void lblEnderecoOnMouseEntered(MouseEvent event) {
        AudioController.getInstance().playAudio(Settings.Phrase.ENDERECO.getPhrase());
    }

    @FXML
    private void lblCPFOnMouseEntered(MouseEvent event) {
        AudioController.getInstance().playAudio(Settings.Phrase.CPF.getPhrase());
    }

}
