package controllers.frontend;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import util.Settings;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    private void playAudio2(KeyEvent event) {
        if(tgleBtnAudio.selectedProperty().get()){
            String character = event.getText();
            System.out.println("/resources/audio/"+character+".wav");
            AudioClip audio = new  AudioClip(this.getClass().getResource("/resources/audio/"+character+".wav").toString());
            audio.play();
        }else{
            event.consume();
        }
    }

    @FXML
    private void playAudio(ActionEvent event) {
    }
    
}
