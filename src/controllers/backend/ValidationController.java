/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.backend;

import DAO.StudentDAO;
import java.sql.SQLException;
import model.Login;
import model.Student;
import model.exceptions.MissingValuesException;
import model.exceptions.PasswordWrongException;
import util.Settings;

/**
 *
 * @author acmne
 */
public class ValidationController {

    private static ValidationController validation;
    private Student temp;

    private ValidationController() {
        this.temp = new Student();
    }

    public static synchronized ValidationController getInstance() {
        return (validation == null) ? validation = new ValidationController() : validation;
    }

    public void registerPerson(String firstName, String lastName, String birth, String gender, String cpf, String adress) throws MissingValuesException {

        if (firstName == null || lastName == null || birth == null || gender == null || cpf == null || adress == null) {
            throw new MissingValuesException("Nem todos os campos foram preenchidos!");
        } else {
            temp.setFirstName(firstName);
            temp.setLastName(lastName);
            temp.setAddress(adress);
            temp.setBirth(birth);
            temp.setGenere(Settings.Genere.valueOf(gender));
            
            cpf = cpf.replaceAll(".", "");
            cpf = cpf.replaceAll("-", "");
            temp.setCpf(cpf);
        }
    }

    public void registerLogin(String email, String password, String confPassword, String recoveryEmail) throws MissingValuesException, PasswordWrongException {
        if (email == null || password == null || confPassword == null || recoveryEmail == null) {
            throw new MissingValuesException("Nem todos os campos foram preenchidos!");
        } else {
            if (!password.equals(confPassword)) {
                throw new PasswordWrongException("Senhas não conferem!");
            } else {
                Login lg = new Login(email, recoveryEmail, password);

                temp.setLogin(lg);

            }
        }
    }

    public void registerAcademy(String institute, String course, String id) throws MissingValuesException {
        if (institute == null || course == null || id == null) {
            throw new MissingValuesException("Nem todos os campos foram preenchidos!");
        } else {
            temp.setInstitution(Settings.Instituition.valueOf(institute));
            temp.setRegistration(id);
            temp.setCourse(Settings.Course.getCourse(course));
        }
    }

    public void save() throws ClassNotFoundException, SQLException {
        StudentDAO ex = new StudentDAO();
        
        ex.create(temp);


    }

}
