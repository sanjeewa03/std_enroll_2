/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interceptor;


import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import services.Authentication;
import services.DBConnection;

/**
 *
 * @author sanjeewa_s
 */
public class Authorization implements Interceptor {
    private String result = "";
    public Authorization() {
    }
    
    public void destroy() {
        
    }
    
    public void init() {
        
    }
    
    public String intercept(ActionInvocation actionInvocation) throws Exception {
       Connection con = null;
       boolean isAllowed = false;
       HttpSession session = ServletActionContext.getRequest().getSession();
       if(session.getAttribute("userName")!=null){
        String role = (String)session.getAttribute("role");
        String action = actionInvocation.getAction().getClass().getName();
        String permissionQ = "SELECT `action_id`, `user_role`, `permission` FROM `permissions` WHERE `action_id` = ? AND `user_role` = ?";
        
        try{
            con = DBConnection.connect();
            PreparedStatement stm = con.prepareStatement(permissionQ);
            stm.setString(1,action);
            stm.setString(2,role);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
               if(rs.getString("permission").equals("1")){
                   isAllowed = true;
               }
               else{
                   isAllowed = false;
               }
            }
        }
        catch(SQLException ex){
            Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            con.close();
        }
       
        if(isAllowed){
            if(action.equals("actions.Login")){
               switch (role) {
                case "student":
                    System.out.println("student from AUTHORIZATION INTERCEPTOR"+" is allaowd "+ isAllowed);
                    result = "student";
                    break;
                case "teacher":
                    System.out.println("teacher from AUTHORIZATION INTERCEPTOR"+" is allaowd "+ isAllowed);
                    result = "teacher";
                    break;
               }
           }
            else{
                System.out.println("isallowed is "+isAllowed+" from AUTHORIZATION INTERCEPTOR"+" is allaowd "+ isAllowed);
                result = actionInvocation.invoke();
            }
        }
        else{
            switch (role) {
                case "student":
                    System.out.println(role+" from AUTHORIZATION INTERCEPTOR"+" is allaowd "+ isAllowed);
                    result = "student";
                    break;
                case "teacher":
                    System.out.println("teacher from AUTHORIZATION INTERCEPTOR"+" is allaowd "+ isAllowed);
                    result = "teacher";
                    break;
                default:
                    System.out.println("error2 from AUTHORIZATION INTERCEPTOR"+" is allaowd "+ isAllowed);
                    result = "error";
                    break;
            }
        }
       }
       else{
           String action = actionInvocation.getAction().getClass().getName();
           if(action.equals("actions.Login")){
               System.out.println("action login  from AUTHORIZATION INTERCEPTOR"+" is allaowd "+ isAllowed);
               result = actionInvocation.invoke();
           }
           else{
            System.out.println("error2 from AUTHORIZATION INTERCEPTOR"+" is allaowd "+ isAllowed);
            result = "error";
           }
       }
        System.out.println("****"+result+ "from AUTHORIZATION INTERCEPTOR");
        return result;
    }
    
}
