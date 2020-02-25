package facade;

import controllers.frontend.ScreensController;
import controllers.frontend.StageController;
import javafx.scene.Parent;
import javafx.stage.Stage;
import util.Settings.Scenes;

/**
 *
 * @author Uellington Damasceno
 */
public class FacadeFrontend {

    private static FacadeFrontend facade;

    private ScreensController screensController;
    private StageController stageController;
    
    private FacadeFrontend() {
        this.screensController = new ScreensController();
    }

    public static synchronized FacadeFrontend getInstance() {
        return (facade == null) ? facade = new FacadeFrontend() : facade;
    }

    public void initialize(Stage stage, Scenes scene) throws Exception {
        Parent loadedScreen = this.screensController.loadScreen(scene);

        this.stageController = new StageController(stage);
        this.stageController.changeMainStage(scene.getTitle(), loadedScreen);
    }

    public void changeScreean(Scenes scene) throws Exception {
        Parent loadedScreen = this.screensController.loadScreen(scene);
        this.stageController.changeMainStage(scene.getTitle(), loadedScreen);
    }

//    public void changeScreenAndSetController(Scenes scene) throws IOException{
//        FXMLLoader loaderFXML = this.screensController.getLoaderFXML(scene);
//        Parent loadedScreen = loaderFXML.load();
//        this.stageController.changeMainStage(scene.getTitle(), loadedScreen);
//    }
    
    public double getStageHeigth(){
        return this.stageController.getStageY();
    }
    
    public double getStageWidth(){
        return this.stageController.getStageX();
    }
    
    public void showContentAuxStage(Scenes scene, String name) throws Exception {
        Parent content = this.screensController.loadScreen(scene);
        this.stageController.changeStageContent(name, scene.getTitle(), content);
    }
    
}
