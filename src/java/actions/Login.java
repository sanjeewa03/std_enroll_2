/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.Timestamp;
import java.util.Map;
import javax.servlet.http.HttpSession;
import models.UserModel;
import org.apache.struts2.ServletActionContext;
import services.Authentication;

/**
 *
 * @author sanjeewa_s
 */
public class Login extends ActionSupport {
    
    private UserModel user;
    private Map<String, Object> userSession;
    
    public Login() {
    }
    
    @Override
    public String execute() throws Exception{
        
        Authentication auth = new Authentication();
        UserModel orginalUser = auth.authenticate(user);
        String role = orginalUser.getRole();
        this.user.setMessage(orginalUser.getMessage());
        String result = "";
        Timestamp time = new Timestamp(System.currentTimeMillis());
        if("userAuthenticated".equals(orginalUser.getMessage())){
            switch(role){
                case "student":
                    this.makeSession(orginalUser.getUsername(),this.user.getPassword(), orginalUser.getRole(),time.getTime() );
                    result = "student";
                    break;
                case "teacher":
                    this.makeSession(orginalUser.getUsername(),this.user.getPassword(), orginalUser.getRole(),time.getTime() );
                    result = "teacher";
                    break;
                default:
                    result = "error";
                    break;
            }
        }
        else {
            result = "error";
        }
        
        return result;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserModel user) {
        this.user = user;
    }

    public UserModel getUser() {
        return user;
    }
    
    private HttpSession makeSession(String userName,String password, String role, long timestamp) {
        HttpSession newSession =  ServletActionContext.getRequest().getSession();
        newSession.setAttribute("userName",userName );
        newSession.setAttribute("password", password);
        newSession.setAttribute("role", role);
        newSession.setAttribute("timestamp", timestamp);
        newSession.setMaxInactiveInterval(30);
        return newSession;
    }
    
    
}
