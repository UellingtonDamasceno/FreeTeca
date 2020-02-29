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
        MAIN("Main.fxml", "Inicio", false),
        HOME_SIDE("123", "", true),
        REGISTER_PERSON("RegisterPerson.fxml", "Registro", true),
        REGISTER_LOGIN("RegisterLogin.fxml", "Login", true),
        DASHBOARD("DashBoard.fxml","Dash", false),
        ITEM_LIST("ItemList.fxml", "Item", true),
        LIST("List.fxml", "List", true);
        
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

    public enum Icons {
        BOOKS("books");

        private final String name;
        private final String ORIGIN = "/resources/icons/";
        private final String EXTENSION = ".png";

        private Icons(String name) {
            this.name = name;
        }

        public String getIconPath() {
            return this.ORIGIN + this.name + this.EXTENSION;
        }
    }

    public enum Slider {
        FIRST("1"),
        SECOND("2"),
        THIRD("3");

        private final String name;
        private final String ORIGIN = "/resources/images/";
        private final String EXTENSION = ".jpg";

        private Slider(String name) {
            this.name = name;
        }

        public String getImagePath() {
            return this.ORIGIN + this.name + this.EXTENSION;

        }
    }

    public enum Instituition {
        UEFS("Universidade Estaudal de Feira de Santana"),
        UNEB("Universidade do Estado da Baiha"),
        UNEF("Universidade de Ensino Superior de Feira de Santana"),
        FAT("Faculdade Anisio Texeira");

        private String name;

        private Instituition(String name) {
            this.name = name;
        }

        private String getName() {
            return this.name;
        }
             
    }

    //Complementar com cursos
    public enum Course {
        ECOMP("Engenharia de Computação"),
        ECIVIL("Engenharia Civil"),
        EALIM("Engenharia Alimentos");


        private String name;

        private Course(String name) {
            this.name = name;
        }
        
        public String getName(){
            return this.name;
        }

    }

    public enum Genere {
        MASCULINO("M"),
        FEMENINO("F"),
        OUTRO("O");

        private final String genere;
        private Genere(String genere) {
            this.genere = genere;
        }

        public String getGenere() {
            return this.genere;
        }
    }
}
