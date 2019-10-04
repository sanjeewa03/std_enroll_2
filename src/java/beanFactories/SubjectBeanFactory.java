/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanFactories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import models.Subject;
import services.DBConnection;

/**
 *
 * @author sanjeewa_s
 */
public class SubjectBeanFactory {
    public static List<Subject> getSubjectBeans(){
        List<Subject> allbeans = new LinkedList<>();
        try {
            Connection con = DBConnection.connect();
            String q = "SELECT * FROM `subjects`";
            PreparedStatement stm = con.prepareStatement(q);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Subject s = new Subject();
                s.setSub_code(rs.getString("sub_code"));
                s.setSub_name(rs.getString("sub_name"));
                s.setCredit(rs.getInt("credit"));
                allbeans.add(s);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return allbeans;
    }
}
