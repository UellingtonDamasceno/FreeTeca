package model;

/**
 *
 * @author Uellington Conceição
 */
public abstract class LogablePerson extends Person {

    private Login login;
    
    public void updateRecoveryEmail(String newEmail){
        this.login.setRecoveryEmail(newEmail);
    }
    
    public boolean authenticate(String email, String password){
        return this.login.authenticate(email, password);
    }
}
