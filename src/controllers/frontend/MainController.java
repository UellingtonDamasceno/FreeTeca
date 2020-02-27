package controllers.frontend;

import controllers.backend.AudioController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;

/**
 * FXML Controller class
 *
 * @author Uellington Conceição
 */
public class MainController implements Initializable {

    @FXML
    private TextField txtNumber;
    @FXML
    private ToggleButton tgleBtnAudio;

    private AudioController audio;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.audio = new AudioController();
    }
    
    @FXML
    private void playAudio2(KeyEvent event) {
        if(tgleBtnAudio.selectedProperty().get()){
            String character = event.getText();
            audio.playAudio(character);
        }else{
            event.consume();
        }
    }

    @FXML
    private void playAudio(ActionEvent event) {
    }
    
}
