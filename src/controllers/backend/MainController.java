///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controllers.backend;
//
//import facade.FacadeFrontend;
//import java.net.URL;
//import java.util.ResourceBundle;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.application.Platform;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
//import javafx.scene.control.ToggleButton;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.VBox;
//import util.Settings.Scenes;
//import util.Settings.Slider;
//
///**
// * FXML Controller class
// *
// * @author acmne
// */
//public class MainController implements Initializable {
//
//    @FXML
//    private ImageView imageViewSlider;
//    @FXML
//    private ToggleButton btnAccessbility;
//    @FXML
//    private Button btnSettings;
//    @FXML
//    private TextField txtEmail;
//    @FXML
//    private PasswordField txtPassword;
//    @FXML
//    private Label lblInfo;
//    @FXML
//    private Label btnRegister;
//    @FXML
//    private Button btnEntry;
//
//    private AudioController audioController;
//
//    @FXML
//    private VBox homePanel;
//    @FXML
//    private HBox hBoxGit;
//    @FXML
//    private Pane paneRoot;
//
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        this.audioController = new AudioController();
//        this.imageViewSlider.setImage(new Image(Slider.FIRST.getImagePath()));
//        this.initialize();
//    }
//
//    private void initialize() {
//        this.txtEmail.textProperty().addListener((observable, oldValue, newValue) -> {
//            this.playAudio(newValue.toCharArray()[newValue.length() - 1] + "");
//        });
//
////        LinkedList<Image> list = new LinkedList();
////        list.add(new Image(Slider.values()[0].getImagePath()));
////        list.add(new Image(Slider.values()[1].getImagePath()));
////        list.add(new Image(Slider.values()[2].getImagePath()));
////
////        while (this.activated) {
////            for (Image image : list) {
////                Platform.runLater(() -> {
////                    this.imageViewSlider.setImage(image);
////                });
////
////                System.out.println("Explodiu");
////                try {
////                    Thread.sleep(200);
////                } catch (InterruptedException ex) {
////                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
////                }
////            }
////            System.out.println("Bomba");
////
////        }
//    }
//
////    private void changeTop() {
////        ObservableList<Node> childs = this.stackPane.getChildren();
////
////        if (childs.size() > 1) {
////            
////            Node topNode = childs.get(childs.size() - 1);
////            
////            Node newTopNode = childs.get(childs.size() - 2);
////
////            topNode.setVisible(false);
////            topNode.toBack();
////
////            newTopNode.setVisible(true);
////        }
////    }
//    private void playAudio(String character) {
//        if (this.btnAccessbility.selectedProperty().get()) {
//            audioController.playAudio(character + "");
//        }
//    }
//
//    @FXML
//    private void openSettings(ActionEvent event) {
//    }
//
//    @FXML
//    private void openRegister(MouseEvent event) {
//        this.changeSideBar(Scenes.REGISTER_PERSON);
//    }
//
//    public void changeSideBar(Scenes scene) {
//        try {
//            Parent loadedScreen = FacadeFrontend.getInstance().getScreen(scene);
//            Parent removedScreen = (Parent) this.paneRoot.getChildren().remove(0);
//            FacadeFrontend.getInstance().addScreen(Scenes.HOME_SIDE, removedScreen);
//            this.paneRoot.getChildren().add(loadedScreen);
//        } catch (Exception ex) {
//            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    @FXML
//    private void hide(MouseEvent event) {
//        Platform.runLater(() -> {
//            this.hBoxGit.setVisible(false);
//        });
//    }
//
//    @FXML
//    private void show(MouseEvent event) {
//        Platform.runLater(() -> {
//            this.hBoxGit.setVisible(true);
//        });
//    }
//
//}
