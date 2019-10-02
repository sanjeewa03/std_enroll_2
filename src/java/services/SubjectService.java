/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Subject;

/**
 *
 * @author sanjeewa_s
 */
public class SubjectService {
    
    public void addSubject(Subject s){
        try {
            Connection con = DBConnection.connect();
            String q = "INSERT INTO `subjects`(`sub_code`, `sub_name`, `credit`) VALUES (?,?,?)";
            PreparedStatement stm = con.prepareStatement(q);
            stm.setString(1,s.getSub_code());
            stm.setString(2,s.getSub_name());
            stm.setInt(3,s.getCredit());
            stm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public Subject[] getSubjects(int offSet, int count){
        try {
            Connection con = DBConnection.connect();
            String q = "SELECT * FROM `subjects` LIMIT ? OFFSET ?";
            PreparedStatement stm = con.prepareStatement(q);
            stm.setInt(1,count);
            stm.setInt(2,offSet);
            ResultSet rs = stm.executeQuery();
            Subject[] sub = new Subject[count];
            int i =0;
            while(rs.next()){
                Subject s = new Subject();
                s.setSub_name(rs.getString("sub_name"));
                s.setSub_code(rs.getString("sub_code"));
                s.setCredit(rs.getInt("credit"));
                sub[i]=s;
                System.out.println(sub[i].getSub_code());
                i++;
            }
            return sub;
        } catch (SQLException ex) {
            Logger.getLogger(SubjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int getSubjectCount(){
        int count = 0;
        try {
            Connection con = DBConnection.connect();
            String q = "SELECT COUNT(sub_code) FROM `subjects`";
            PreparedStatement stm = con.prepareStatement(q);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                count = rs.getInt("COUNT(sub_code)");
            }
            System.out.println(count);
            return count;
        } catch (SQLException ex) {
            Logger.getLogger(SubjectService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
}
