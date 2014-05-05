/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.Clubs;

/**
 *
 * @author Semir
 */
@Named("menu")
@SessionScoped
public class MenuBean implements Serializable{

    @EJB
    Controller controller;
    
    private String club;

    public String getClub() {
        return club;
    }

    public void setClub(String club) throws IOException {
        System.out.println("Menu bean club: " + club);
        this.club = club;
        FacesContext.getCurrentInstance().getExternalContext().redirect("clubView.jsf");
    }

    public List<Clubs> getAllClubs() {
        System.out.println("getting all clubs");
        return controller.getAllClubs();
    }
    
    public void test() {
        System.out.println("Test metod: " + club);
    }

}
