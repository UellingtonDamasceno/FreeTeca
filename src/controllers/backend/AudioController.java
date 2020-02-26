package controllers.backend;

import model.exceptions.NotFoundException;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.media.AudioClip;

/**
 *
 * @author Uellington Conceição
 */
public class AudioController {
    private Map<String, AudioClip> audios;
    
    public AudioController(){
        this.audios = new HashMap();
    }
    
    public AudioClip getAudio(String id) throws NotFoundException{
        if(this.audios.containsKey(id)){
            return this.audios.get(id);
        }
        throw new NotFoundException();
    }
    
    public void addAudio(String id, String audioName){
        String path = this.getClass().getResource(audioName).toString();
        AudioClip audio = new AudioClip(path);
        this.addAudio(id, audio);
    }
    
    public void addAudio(String id, AudioClip audio){
        this.audios.put(id, audio);
    }
}