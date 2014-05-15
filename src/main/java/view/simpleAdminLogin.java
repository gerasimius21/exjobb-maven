/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Semir
 */
@Named("adminLogin")
@ApplicationScoped
public class simpleAdminLogin {

    private String username;
    private String password;

    private String rightuser = "mastermilos";
    private String rightpass = "ballin";

    boolean login = false;

    public boolean isLogin() {
        return login;
    }

    public String testLogin() {
        System.out.println("username = " + username + "\t" + "rightuser" + rightuser);
        System.out.println("password = " + password + "\t" + "rightpass" + rightpass);
        if ((username.equals(rightuser)) && (password.equals(rightpass))) {
            login = true;
            System.out.println("Login TRUE");
        } else {
            login = false;
            System.out.println("Loing FALSE");
        }
        return "adminView";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
