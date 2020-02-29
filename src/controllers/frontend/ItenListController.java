/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.frontend;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Student;
import util.Settings.Icons;

/**
 * FXML Controller class
 *
 * @author acmne
 */
public class ItenListController implements Initializable {

    @FXML
    private ImageView imgIcon;
    @FXML
    private Label lblName;
    @FXML
    private Label lblId;
    @FXML
    private Label lblInstituation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void loadStudent(Student student) {
        Image image = new Image(Icons.BOOKS.getIconPath());
        this.imgIcon.setImage(image);
        this.lblName.setText(student.getFirstName() + " " + student.getLastName());
        this.lblInstituation.setText(student.getInstitution().name());
        this.lblId.setText(student.getRegistration());
    }
    
}
