package model;

import java.util.Objects;

/**
 *
 * @author Uellington Conceição
 */
public class Login {
    private String email;
    private String recoveryEmail;
    private String password;

    public Login(String email, String recoveryEmail, String password) {
        this.email = email;
        this.recoveryEmail = recoveryEmail;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRecoveryEmail() {
        return recoveryEmail;
    }

    public void setRecoveryEmail(String recoveryEmail) {
        this.recoveryEmail = recoveryEmail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean authenticate(String email, String password){
        return this.email.equals(email) && this.password.equals(password);
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.email);
        hash = 67 * hash + Objects.hashCode(this.recoveryEmail);
        hash = 67 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Login){
            Login another = (Login) obj;
            return this.hashCode() == another.hashCode();
        }
        return false;
    }
    
}
