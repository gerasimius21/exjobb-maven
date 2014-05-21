/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import model.entities.Clubs;
import model.entities.Userinformation;

/**
 *
 * @author Semir
 */
@Named("firtTimeBean")
@SessionScoped
public class firstTimeLogin implements Serializable{
    
    @EJB
    Controller controller;
    
    @Inject
    LoginPageCode lp;
    
    private String name;

    private Integer age;

    private String country;

    private Clubs favoriteTeam;
    
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Clubs getFavoriteTeam() {
        return favoriteTeam;
    }

    public void setFavoriteTeam(Clubs favoriteTeam) {
        this.favoriteTeam = favoriteTeam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addUserToDB() throws IOException {
        Userinformation uinfo = new Userinformation();
        uinfo.setAge(age);
        uinfo.setCountry(country);
        uinfo.setFavoriteTeam(favoriteTeam);
        uinfo.setName(name);
        uinfo.setEmail(email);
        controller.addUserToDB(uinfo);
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
    }
    
    @PostConstruct
    public void infoExists(){
        
        setEmail(lp.getUserFromSessionEmail());
        
        if(controller.infoExists(email)){
            
        }else{
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("firstTimeLogin.jsf");
            } catch (IOException ex) {
                Logger.getLogger(firstTimeLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

}
