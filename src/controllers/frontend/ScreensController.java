package controllers.frontend;

import java.io.IOException;
import java.util.HashMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import util.Settings.Scenes;

/**
 *
 * @author Uellington Damasceno
 */
public class ScreensController {

    private final HashMap<Scenes, Parent> allScreens;

    private Scenes lastScreenLoaded;

    public ScreensController() {
        this.lastScreenLoaded = null;
        this.allScreens = new HashMap();
    }

    public Parent loadScreen(Scenes scene) throws Exception {
        return (scene.isCache()) ? loadScreenInCache(scene) : loadFXML(scene);
    }

    private Parent loadScreenInCache(Scenes scene) throws Exception {
        if (!(lastScreenLoaded == scene) && !allScreens.containsKey(scene)) {
            Parent screenLoaded = loadFXML(scene);
            this.addScreen(scene, screenLoaded);
        }
        this.lastScreenLoaded = scene;
        return this.getScreen(scene);
    }

    public void addScreen(Scenes scene, Parent content) throws Exception {
        if (scene != null && content != null) {
            allScreens.put(scene, content);
        } else {
            throw new Exception("Id ou Conteudo nulo");
        }
    }

    public Parent getScreen(Scenes scene) throws Exception {
        if (scene != null) {
            if (allScreens.containsKey(scene)) {
                return allScreens.get(scene);
            } else {
                throw new Exception("Não existe tela com o id: " + scene.toString());
            }
        } else {
            throw new Exception("Id da tela é nulo");
        }
    }

    public FXMLLoader getLoaderFXML(Scenes scene) {
        return new FXMLLoader(getClass().getResource(scene.toString()));
    }

    public Object getSceneController(Scenes scene) {
        return getLoaderFXML(scene).getController();
    }

    private Parent loadFXML(Scenes scene) throws IOException {
        return getLoaderFXML(scene).load();
    }

}
