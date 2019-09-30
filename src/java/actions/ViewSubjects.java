/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Subject;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author sanjeewa_s
 */
public class ViewSubjects extends ActionSupport {
    
    private Subject[] subjects;
    
    public ViewSubjects() {
    }
    
    @Override
    public String execute() throws Exception {
        System.out.println(this.haveSession());
        System.out.println("inside the execute");
        this.subjects = new Subject[5];
        for(Integer  i=0;i<5;i++){
            Subject s = new Subject();
            s.setSub_code(i.toString());
            s.setSub_name("sub_"+i.toString());
            s.setCredit(i);
            this.subjects[i]=s;
        }
        System.out.println(this.subjects.length);
        for(int j=0;j<this.subjects.length;j++){
            System.out.println(this.subjects[j].getSub_name());
        }
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        String referer = request.getHeader("Referer");
        System.out.println(referer);
        //response.sendRedirect(referer);
        return SUCCESS;
    }

    /**
     * @return the subjects
     */
    public Subject[] getSubjects() {
        return subjects;
    }

    /**
     * @param subjects the subjects to set
     */
    public void setSubjects(Subject[] subjects) {
        this.subjects = subjects;
    }
    
     private boolean haveSession(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        if(session.getAttribute("userName")!=null){
            return true;
        }
        else{
            return false;
        }
    }
    
}
