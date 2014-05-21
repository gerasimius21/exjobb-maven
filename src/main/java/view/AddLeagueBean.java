/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import model.entities.Clubs;
import model.entities.Continents;
import model.entities.Lands;
import model.entities.Leagues;

/**
 *
 * @author Semir
 */
@Named("addLeagueBean")
@ViewScoped
public class AddLeagueBean {

    @EJB
    Controller controller;

    Leagues league;

    private String leaguename;
    private Lands land;

    public String getLeaguename() {
        return leaguename;
    }

    public void setLeaguename(String leaguename) {
        this.leaguename = leaguename;
    }

    public Lands getLand() {
        return land;
    }

    public void setLand(Lands land) {
        this.land = land;
    }

    public void addLeagueToDB() throws IOException {
        league = new Leagues();
        league.setLandid(land);
        league.setLeaguename(leaguename);
        league.setClubsCollection(new ArrayList<Clubs>());
        controller.addLeagueToDB(league);
        FacesContext.getCurrentInstance().getExternalContext().redirect("successAddLeague.jsf");
    }


    public List<Lands> getAllCountries() {
        return controller.getAllCountries();
    }

}
