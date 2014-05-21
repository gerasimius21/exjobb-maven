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

/**
 *
 * @author Semir
 */
@Named("addCountryBean")
@ViewScoped
public class AddCountryBean {

    @EJB
    Controller controller;
    
    private Lands lands;

    private String landname;
    private Continents continent;

    public Lands getLands() {
        return lands;
    }

    public void setLands(Lands lands) {
        this.lands = lands;
    }

    public String getLandname() {
        return landname;
    }

    public void setLandname(String landname) {
        this.landname = landname;
    }

    public Continents getContinent() {
        return continent;
    }

    public void setContinent(Continents continent) {
        this.continent = continent;
    }
    
    public void addCountryToDB() throws IOException{
        lands = new Lands();
        lands.setLandname(landname);
        lands.setContinentid(continent);
        lands.setLeaguesCollection(new ArrayList<Leagues>());
        controller.addCountryToDB(lands);
        FacesContext.getCurrentInstance().getExternalContext().redirect("successAddCountry.jsf");
    }
    
    public List<Continents> getAllContinents(){
        return controller.getAllContinents();
    }

}
