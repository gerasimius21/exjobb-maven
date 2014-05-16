/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import javax.ejb.EJB;
import javax.inject.Named;
import model.entities.Clubs;
import model.entities.Userinformation;

/**
 *
 * @author Semir
 */
@Named("firtTimeBean")
public class firstTimeLogin {
    
    @EJB
    Controller controller;

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

    public void addUserToDB() {
        Userinformation uinfo = new Userinformation();
        uinfo.setAge(age);
        uinfo.setCountry(country);
        uinfo.setFavoriteTeam(favoriteTeam);
        uinfo.setName(name);
        uinfo.setEmail(email);
        controller.addUserToDB(uinfo);
    }

}
