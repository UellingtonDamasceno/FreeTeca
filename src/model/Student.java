package model;

import java.util.List;
import java.util.Objects;
import util.Settings;
import util.Settings.Course;
import util.Settings.Instituition;

/**
 *
 * @author Uellington Conceição
 */
public class Student extends LogablePerson{

    private List<String> phone;
    
    private Instituition institution;
    private String registration;
    private Course course;

    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }

    public Login getLogin() {
        return this.login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    
    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Instituition getInstitution() {
        return institution;
    }

    public void setInstitution(Instituition institution) {
        this.institution = institution;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.cpf);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Student) {
            Student another = (Student) obj;
            return this.hashCode() == another.hashCode();
        }
        return false;
    }
    
    @Override
    public String toString(){
        return this.cpf + " <> " + this.firstName;
    }
}
