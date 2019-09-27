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
import java.util.logging.Level;
import java.util.logging.Logger;
import models.UserModel;

/**
 *
 * @author sanjeewa_s
 */
public class Authentication {
    
    private final String selectAllUsers = "SELECT * FROM `users`";
    private final String selectUser = "SELECT * FROM `users` WHERE `username`=?";
    
    public UserModel authenticate(UserModel user){
        UserModel orginalUser = null;
        if(!this.isExist(user.getUsername())){
            System.out.println("user not found");
            orginalUser = user;
            orginalUser.setRole(null); 
            orginalUser.setMessage("Username Not Found");
        }
        else{
            orginalUser = this.getUser(user.getUsername());
            boolean passwordMatched = BCrypt.checkpw(user.getPassword(), orginalUser.getPassword());
            if(passwordMatched){
                orginalUser.setMessage("userAuthenticated");
            }
            else{
                orginalUser.setMessage("Incorrect password");
            }
        }        
        return orginalUser;
    }
    
    
    public boolean isExist(String username){
        boolean isExist=false;
        Connection con = null;
        try{
            con = DBConnection.connect();
            PreparedStatement stm = con.prepareStatement(this.selectUser);
            stm.setString(1,username);
            ResultSet rs = stm.executeQuery();
            isExist = rs.next();
        }
        catch(SQLException ex){
            Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return isExist;
    }
    
    public UserModel getUser(String username){
        ResultSet results = null;
        Connection con=null;
        UserModel orginalUser = new UserModel();
        try{
            con = DBConnection.connect();
            PreparedStatement stm = con.prepareStatement(this.selectUser);
            stm.setString(1, username);
            results = stm.executeQuery();
            results.next();
            orginalUser.setUsername(results.getString("username"));
            orginalUser.setPassword(results.getString("password"));
            orginalUser.setRole(results.getString("role"));
        }
        catch(SQLException ex){
            Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return orginalUser;
    }
}
