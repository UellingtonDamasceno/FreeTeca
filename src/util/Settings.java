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
    
    public enum Instituition{
        UEFS("Universidade Estaudal de Feira de Santana"),
        UNEB("Universidade do Estado da Baiha"),
        UNEF("Universidade de Ensino Superior de Feira de Santana"),
        FAT("Faculdade Anisio Texeira");
        
        private String name;
        
        private Instituition(String name){
            this.name = name;
        }
        
        private String getName(){
            return this.name;
        }
        
        public Instituition getIntuition(String instituicao ){
            
            switch(instituicao){
            
                case "UEFS":
                    return Instituition.UEFS;
                case "UNEB":
                    return Instituition.UNEB;
                case "UNEF":
                    return Instituition.UNEF;
                case "FAT":
                    return Instituition.FAT;
                default:
                    return null;
            }
        }
    }
    
    //Complementar com cursos
    public enum Course{
        ECOMP("Engenharia de Computação"),
        ECIVIL("Engenharia Civil"),
        EALIM("Engenharia Alimentos");
        
        private String name;
        
        private Course(String name){
            this.name = name;
        }
        
        public String getName(){
            return this.name;
        }
        
        
    }
    
    public enum Genere{
        M("Masculino"),
        F("Femenino");
        
        private final String genere;
        
        private Genere(String genere){
            this.genere = genere;
        }
        
        public String getGenere(){
            return this.genere;
        }
    }
}
