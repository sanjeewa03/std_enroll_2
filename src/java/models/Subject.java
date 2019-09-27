/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author sanjeewa_s
 */
public class Subject {
    private String sub_code;
    private String sub_name;
    private int credit;

    /**
     * @return the sub_code
     */
    public String getSub_code() {
        return sub_code;
    }

    /**
     * @param sub_code the sub_code to set
     */
    public void setSub_code(String sub_code) {
        this.sub_code = sub_code;
    }

    /**
     * @return the sub_name
     */
    public String getSub_name() {
        return sub_name;
    }

    /**
     * @param sub_name the sub_name to set
     */
    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    /**
     * @return the credit
     */
    public int getCredit() {
        return credit;
    }

    /**
     * @param credit the credit to set
     */
    public void setCredit(int credit) {
        this.credit = credit;
    }
}
