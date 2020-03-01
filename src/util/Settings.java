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
        REGISTER_ACADEMY("RegisterAcademy.fxml", "Academy", true),
        DASHBOARD("DashBoard.fxml", "Dash", false),
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
        ADM("admin"),
        BOOKS("books"),
        CLOSED_EYE("closed_eye"),
        SLEEPY_EYE("sleepy_eye"),
        USER_MALE("circled_male"),
        USER_FEMALE("circled_female"),
        EYE("eye");

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
        UEFS("Universidade Estaudal de Feira de Santana", Phrase.UEFS),
        UNEB("Universidade do Estado da Baiha", Phrase.UNEB),
        UNEF("Universidade de Ensino Superior de Feira de Santana", Phrase.UNEF),
        FAT("Faculdade Anisio Texeira", Phrase.FAT);

        private String name;
        private Phrase audio;

        private Instituition(String name, Phrase audio) {
            this.name = name;
            this.audio = audio;
        }

        public String getName() {
            return this.name;
        }
        
        public String getAudio(){
            return this.audio.getPhrase();
        }

    }

    //Complementar com cursos
    public enum Course {
        ECOMP("Engenharia de Computação", Phrase.ECOMP),
        CIVIL("Engenharia Civil", Phrase.CIVIL),
        ENGAL("Engenharia de Alimentos", Phrase.ENGAL);

        private final String name;
        private final Phrase curso;

        private Course(String name, Phrase curso) {
            this.name = name;
            this.curso = curso;
        }

        public static Course getCourse(String name) {
            switch (name) {
                case "Engenharia de Computação":
                    return ECOMP;
                case "Engenharia Civil":
                    return CIVIL;
                case "Engenharia de Alimentos":
                    return ENGAL;
                default:
                    throw new AssertionError();
            }
        }

        public String getName() {
            return this.name;
        }

        public String getCurso() {
            return this.curso.getPhrase();
        }

    }

    public enum Genere {

        MASCULINO("MASCULINO", Phrase.MASCULINO),
        FEMININO("FEMININO", Phrase.FEMININO),
        OUTRO("OUTRO", Phrase.OUTRO);

        private final String genere;
        private final Phrase sexo;

        private Genere(String genere, Phrase sexo) {
            this.genere = genere;
            this.sexo = sexo;
        }

        public String getGenere() {
            return this.genere;
        }
    }

    public enum Phrase {
        AVANCAR("AVANÇAR"),
        NOVO_CADASTRO("CADASTROAQUI"),
        CONFIGURACOES("CONFIG"),
        CONFIRMAR_SENHA("CONFIRMARSENHA"),
        CPF("CPF"),
        DESLIGAR_ACESSIBILIDADE("DESLIGARACESSIBILIDADE"),
        EDITAR_INFORMACAO("EDITARINFO"),
        EMAIL_DE_RECUPERACAO("EMAILDERECUPERACAO"),
        ENDERECO("ENDERECO"),
        ENTRAR("ENTRAR"),
        EXCLUIR("EXCLUIR"),
        GERIR_ESTUDANTES("GERIRESTUDANTES"),
        LIGAR_ACESSIBILIDADE("LIGARACESSIBILIDADE"),
        LISTAR_ESTUDANTES("LISTARESTUDANTES"),
        LOGIN_INFO("LOGININFO"),
        MATRICULA("MATRICULA"),
        NASCIMENTO("NASCIMENTO"),
        PRIMEIRO_NOME("PRIMEIRONOME"),
        RECUPERACAO("RECUPERACAO"),
        RETORNAR("RETORNAR"),
        SAIR("SAIR"),
        SEGUNDO_NOME("SEGUNDONOME"),
        SENHA("SENHA"),
        SEXO("SEXO"),
        VISUALIZAR("VISUALIZAR"),
        EMAIL("EMAIL"),
        ERROLOGIN("ERROLOGIN"),
        INSERIRSENHA("INSERIRSENHA"),
        INSERIREMAIL("INSERIREMAIL"),
        ACADEMYINFO("ACADEMYINFO"),
        MATRICULAA("MATRICULAA"),
        SALVAR("SALVAR"),
        CURSO("CURSO"),
        INSTITUICAOENSINO("INSTITUICAOENSINO"),
        INSERIRMATRICULA("INSERIRMATRICULA"),
        XD("XD"),
        INFOPERSONAL("INFOPERSONAL"),
        MASCULINO("MASCULINO"),
        FEMININO("FEMININO"),
        OUTRO("OUTRO"),
        ENGAL("ENGAL"),
        CIVIL("CIVIL"),
        ECOMP("ECOMP"),
        FAT("FAT"),
        UNEF("UNEF"),
        UNEB("UNEB"),
        UEFS("UEFS");

        private final String phrase;

        private Phrase(String phrase) {

            this.phrase = phrase;
        }

        public String getPhrase() {
            return this.phrase;
        }
    }
    
    public enum Admin{
        NAME("Admin"),
        EMAIL("root"),
        PASSWORD("root"),
        IMAGE(Icons.ADM.getIconPath());
        
        private final String value;
        
        private Admin(String value){
            this.value = value;
        }
        
        public String getValue(){
            return this.value;
        }
    }
    
}
