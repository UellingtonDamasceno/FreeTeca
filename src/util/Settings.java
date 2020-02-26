package util;

/**
 *
 * @author Uellington Conceição
 *
 * Classe responsável por armazenar configurações estáticas da aplicação.
 */
public class Settings {

    public static class Audio {

        private static final String ORIGIN = "/resources/audio/";
        private static final String EXTENSION = ".wav";

        private Audio() {
        }

        public enum Alphabet {
            A("A"),
            B("B"),
            C("C"),
            D("D"),
            E("E"),
            F("F"),
            G("G"),
            H("H"),
            I("I"),
            J("J"),
            K("K"),
            L("L"),
            M("M"),
            N("N"),
            O("O"),
            P("P"),
            Q("Q"),
            R("R"),
            S("S"),
            T("T"),
            U("U"),
            V("V"),
            W("W"),
            X("X"),
            Y("Y"),
            Z("Z");
            private final String letter;

            private Alphabet(String letter) {
                this.letter = letter;
            }

            public String getLetter() {
                return this.letter;
            }

            public String getFileDirectory() {
                return ORIGIN + letter + EXTENSION;
            }

            public String getFileName() {
                return letter + EXTENSION;
            }

        }

        public enum Number {
            ZERO("0"),
            ONE("1"),
            TWO("2"), 
            THREE("3"),
            FOUR("4"),
            FIVE("5"),
            SIX("6"),
            SEVEN("7"),
            EIGHT("8"),
            NINE("9");

            private final String stringNumber;
            private final int integerNumber;
            
            private Number(String number) {
                this.stringNumber = number;
                this.integerNumber = Integer.parseInt(stringNumber);
            }

            public int getIntegerNumber() {
                return this.integerNumber;
            }
            
            public String getStringNumber(){
                return this.stringNumber;
            }

            public String getFileDirectory() {
                return ORIGIN + stringNumber + EXTENSION;
            }

            public String getFileName() {
                return this.stringNumber + EXTENSION;
            }
        }
    }

    /*
    Responsável por informar de forma estatica quais telas podem ser
    Utilizadas.
     */
    public enum Scenes {
        MAIN("Main.fxml", "Teste", false);

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
