package util;

/**
 *
 * @author Uellington Conceição
 *
 * Classe responsável por armazenar configurações estáticas da aplicação.
 */
public class Settings {
    
    /*
    Responsável por informar de forma estatica quais telas podem ser
    Utilizadas.
     */
    public enum Scenes {
        MAIN("Main.fxml", "Inicio", false);

        private final String name;
        private final String title;
        private final boolean cache;

        private Scenes(String value, String title, boolean cache) {
            this.name = "/view/" + value;
            this.title = title;
            this.cache = cache;
        }

        public String getTitle() {
            return this.title;
        }

        public boolean isCache() {
            return this.cache;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    public enum Icons{
        BOOKS("books");
        
        private final String name;
        private final String ORIGIN = "/resources/icons/";
        private final String EXTENSION = ".png";
        
        private Icons(String name){
            this.name = name;
        }
        
        public String getIconPath(){
            return this.ORIGIN + this.name + this.EXTENSION;
        }
    }
}
