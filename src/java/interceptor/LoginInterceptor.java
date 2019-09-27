/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;


/**
 *
 * @author sanjeewa_s
 */
public class LoginInterceptor implements Interceptor {
    
    public LoginInterceptor() {
    }
    
    public void destroy() {
    }
    
    public void init() {
    }
    
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        HttpSession session = ServletActionContext.getRequest().getSession();
        String action = actionInvocation.getAction().getClass().getName();
        String result = "";
        if(session.getAttribute("userName")==null){
            if(action.equals("actions.Login")){
                result = actionInvocation.invoke();
            }else{
                result = "login";
            }
        }
        else{
            result = actionInvocation.invoke();
        }
        return result;
    }
    
}
