/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import model.entities.Clubs;
import model.entities.Continents;
import model.entities.Lands;
import model.entities.Leagues;
import model.entities.Players;
import model.entities.Transfer;
import model.entities.Userinformation;

/**
 *
 * @author Semir
 */
@Named("addClubBean")
@ViewScoped
public class AddClubBean {

    @EJB
    Controller controller;
    
    private Clubs club;

    private String clubname;
    private String email;
    private Leagues league;

    public String getClubname() {
        return clubname;
    }

    public void setClubname(String clubname) {
        this.clubname = clubname;
    }

    public Leagues getLeague() {
        return league;
    }

    public void setLeague(Leagues league) {
        this.league = league;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void addClubToDB() throws IOException{
        club = new Clubs();
        club.setClubname(clubname);
        club.setEmail(email);
        club.setLeagueid(league);
        club.setPlayersCollection(new ArrayList<Players>());
        club.setTransferCollection(new ArrayList<Transfer>());
        club.setUserinformationCollection(new ArrayList<Userinformation>());
        controller.addClubToDB(club);
        FacesContext.getCurrentInstance().getExternalContext().redirect("successAddClub.jsf");
    }
    
    public List<Leagues> getAllLeagues(){
        return controller.getAllLeagues();
    }

}
