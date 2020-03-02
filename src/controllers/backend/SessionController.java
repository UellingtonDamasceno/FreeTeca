package controllers.backend;

import model.LogablePerson;

/**
 *
 * @author Uellington Conceição
 */
public class SessionController {
    private static SessionController session;
    
    private LogablePerson user;
    
    private SessionController(){}
    
    public static synchronized SessionController getInstance(){
        return (session == null) ? session = new SessionController() : session;
    }
    
    public void setUser(LogablePerson user){
        this.user = user;
    }
    
    public LogablePerson getUser(){
        return this.user;
    }
    
    public void closeSession(){
        this.user = null;
    }
}
