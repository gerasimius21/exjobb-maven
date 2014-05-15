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
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.entities.Clubs;
import model.entities.Continents;
import model.entities.Lands;
import model.entities.Leagues;

/**
 *
 * @author Semir
 */
@Named("menu")
@SessionScoped
public class MenuBean implements Serializable {

    @EJB
    Controller controller;

    private String club;

    private String land;

    private String continent;

    private String league;
    
    
//    @PostConstruct
//    public void init() {
//        continents = controller.getAllContinents();
//    }
    
    public List<Continents> getAllContinents() {
        return controller.getAllContinents();
    }

    public String getClub() {
        return club;
    }

    public String getContinent() {
        return continent;
    }

    public String getLand() {
        return land;
    }

    public String getLeague() {
        return league;
    }
    
    public void setClub(String club) throws IOException {
        System.out.println("Menu bean club: " + club);
        this.club = club;
        FacesContext.getCurrentInstance().getExternalContext().redirect("clubView.jsf");
    }

    public void setContinent(String continent) throws IOException {
        System.out.println("Menu bean continent: " + continent);
        this.continent = continent;
        FacesContext.getCurrentInstance().getExternalContext().redirect("continentView.jsf");
    }

    public void setLand(String land) throws IOException {
        System.out.println("Menu bean land: " + land);
        this.land = land;
        FacesContext.getCurrentInstance().getExternalContext().redirect("landView.jsf");
    }

    public void setLeague(String league) throws IOException {
        System.out.println("Menu bean league: " + league);
        this.league = league;
        FacesContext.getCurrentInstance().getExternalContext().redirect("leagueView.jsf");
    }

    public List<Clubs> getAllClubs() {
        System.out.println("getting all clubs");
        return controller.getAllClubsPerLeague(league);
    }

    public List<Lands> getAllLands() {
        System.out.println("getting all lands");
        return controller.getAllLandsPerContinent(continent);
    }

    public List<Leagues> getAllLeagues() {
        System.out.println("getting all leagues");
        return controller.getAllLeaguesPerLand(land);
    }
}
