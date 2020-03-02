package facade;

import controllers.frontend.DashBoardController;
import controllers.frontend.EditFormsController;
import controllers.frontend.ListController;
import controllers.frontend.MainController;
import controllers.frontend.ScreensController;
import controllers.frontend.StageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import model.Person;
import model.Student;
import util.Settings.Scenes;

/**
 *
 * @author Uellington Damasceno
 */
public class FacadeFrontend {

    private static FacadeFrontend facade;

    private ScreensController screensController;
    private StageController stageController;
    private MainController mainController;
    private ListController listController;
    private DashBoardController dashboadController;
    private EditFormsController editFormController;
    
    private FacadeFrontend() {
        this.screensController = new ScreensController();
    }

    public static synchronized FacadeFrontend getInstance() {
        return (facade == null) ? facade = new FacadeFrontend() : facade;
    }

    public void initialize(Stage stage, Scenes scene) throws Exception {
        FXMLLoader loaderFXML = this.screensController.getLoaderFXML(scene);
        Parent loadedScreen = loaderFXML.load();
        this.mainController = loaderFXML.getController();
        this.stageController = new StageController(stage);
        this.stageController.changeMainStage(scene.getTitle(), loadedScreen);
    }

    public void changeScreean(Scenes scene) throws Exception {
        Parent loadedScreen = this.screensController.loadScreen(scene);
        this.stageController.changeMainStage(scene.getTitle(), loadedScreen);
    }

    public void setListController(Object controller) {
        this.listController = (ListController) controller;
    }

    public void setDashBoardController(Object controller) {
        this.dashboadController = (DashBoardController) controller;
    }
    
    public void setEditFormController(Object controller){
        this.editFormController = (EditFormsController) controller;
    }

    public void setUser(Person person) {
        this.dashboadController.loadUser(person);
    }

    public Parent getScreen(Scenes scene) throws Exception {
        return this.screensController.loadScreen(scene);
    }

    public double getStageHeigth() {
        return this.stageController.getStageY();
    }

    public double getStageWidth() {
        return this.stageController.getStageX();
    }

    public void showContentAuxStage(Scenes scene, String name) throws Exception {
        Parent content = this.screensController.loadScreen(scene);
        this.stageController.changeStageContent(name, scene.getTitle(), content);
    }

    public void updateScreen(Scenes scene, Parent parent){
        this.screensController.updateScreen(scene, parent);
    }
    
    public void addScreen(Scenes scene, Parent parent) {
        this.screensController.addScreen(scene, parent);
    }

    public void loadStudent(Student student){
        this.editFormController.load(student);
    }
    
    public void changeSideBar(Scenes scene) {
        this.mainController.changeSideBar(scene);
    }

    public void changeDashboardSideBar(Scenes scene) {
        this.dashboadController.changeSide(scene);
    }

    public void changeDashBoardCenter(Scenes scene) {
        this.dashboadController.changeCenter(scene);
    }

    public FXMLLoader getLoaderScreen(Scenes scene) {
        return this.screensController.getLoaderFXML(scene);
    }

    public void removeItemList(Parent root) {
        this.listController.removeItem(root);
    }

    public void updateItemList(Student student) {
        this.listController.update(student);
    }
}
