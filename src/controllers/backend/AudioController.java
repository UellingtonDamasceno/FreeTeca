package controllers.backend;

import model.exceptions.NotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.AudioClip;

/**
 *
 * @author Uellington Conceição
 */
public class AudioController {

    private static AudioController controller;

    private static final String ORIGIN = "/resources/audio/";
    private static final String EXTENSION = ".wav";
    private static final String INVALID = ORIGIN + "INVALIDO" + EXTENSION;

    private final Map<String, AudioClip> audios;
    private final Set<String> invalidAudios;
    private final AudioClip invalid;

    private boolean canReproduce;

    private AudioController() {
        this.audios = new HashMap();
        this.invalidAudios = new HashSet();
        this.invalid = this.loadAudio(INVALID);
        this.canReproduce = false;
    }

    public static synchronized AudioController getInstance() {
        return (controller == null) ? controller = new AudioController() : controller;
    }

    public void setCanReproduce(boolean canReproduce) {
        this.canReproduce = canReproduce;
    }

    public boolean getCanReproduce() {
        return this.canReproduce;
    }

    private String getDirectoryFile(String character) {
        return ORIGIN + character + EXTENSION;
    }

    public void playAudio(char id) {
        this.playAudio(String.valueOf(id));
    }

    public void playAudio(String id) {
        if (canReproduce) {
//            new Thread(() -> {
//                canReproduce = false;
//                try {
//                    Thread.sleep(100000);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(AudioController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }).start();
            id = id.toUpperCase();
            try {
                this.getAudio(id).play();
            } catch (NotFoundException ex) {
                this.addAudio(id).play();
            }
//            canReproduce = true;
        }
    }

    public AudioClip getAudio(String id) throws NotFoundException {
        id = id.toUpperCase();
        if (this.audios.containsKey(id)) {
            return this.audios.get(id);
        }
        throw new NotFoundException();
    }

    public AudioClip addAudio(String id) {
        id = id.toUpperCase();
        if (this.invalidAudios.contains(id)) {
            return this.invalid;
        } else {
            String path = this.getDirectoryFile(id);
            AudioClip audio = this.loadAudio(path);
            this.audios.put(id, audio);
            return audio;
        }
    }

    private AudioClip loadAudio(String id) {
        try {
            String path = this.getClass().getResource(id).toString();
            AudioClip audio = new AudioClip(path);
            return audio;
        } catch (NullPointerException e) {
            this.invalidAudios.add(id);
            return this.invalid;
        }
    }

}
