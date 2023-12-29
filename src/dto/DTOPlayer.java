/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class DTOPlayer implements Serializable{
    private String userName;
    private String email;
    private String password;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DTOPlayer(String userName, String email, String password, String status, int score) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.status = status;
        this.score = score;
    }
    private int score;

    public DTOPlayer() {
    }
    
    

    public DTOPlayer(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public DTOPlayer(String userName, String email, String password, int score) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    

    public DTOPlayer(String userName, String passWord) {
        this.userName = userName;
        this.password = passWord;
    }
    
    

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    
}
