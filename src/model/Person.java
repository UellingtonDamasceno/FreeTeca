package model;

import java.util.Date;
import util.Settings.Genere;

/**
 *
 * @author Uellington Conceição
 */
public abstract class Person {
    
    protected String firstName;
    protected String lastName;
    protected String birth;
    protected Genere genere;
    
    protected String cpf;
    protected String address;

    public Genere getGenere() {
        return genere;
    }

    public void setGenere(Genere genere) {
        this.genere = genere;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }
    
    @Override
    public String toString(){
        return this.getFirstName() + " " + this.getLastName();
    }
}
