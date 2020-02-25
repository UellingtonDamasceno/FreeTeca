package util;

/**
 *
 * @author Uellington Conceição
 *
 * Classe responsável por armazenar configurações estáticas da aplicação.
 */
public class Settings {

    public static class Audio {

        public enum Alphabet {
            A("A", "A.WAV");

            private final String letter;
            private final String fileName;
            private final String origin = "/resources/audio/";

            private Alphabet(String letter, String fileName) {
                this.letter = letter;
                this.fileName = origin + fileName;
            }
            
            public String getFileName(){
                return this.fileName;
            }
            
        }

        public enum Number {
            ZERO(0, "0.WAV"),
            ONE(1, "1.WAV");
            
            private final int number;
            private final String fileName;
            private final String origin = "/resources/audio/";
            
            private Number(int number, String fileName){
                this.number = number;
                this.fileName = origin + fileName;
            }
            
            public String getFileName(){
                return this.fileName;
            }
        }
    }

    /*
    Responsável por informar de forma estatica quais telas podem ser
    Utilizadas.
     */
    public enum Scenes {
        MAIN("<NOME_DA_TELA>.fxml", "Menu", false);

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
}
