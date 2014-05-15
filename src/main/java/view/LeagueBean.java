/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.entities.Transfer;

/**
 *
 * @author gerasim
 */
@Named("leagueBean")
@ViewScoped
public class LeagueBean {

    @EJB
    Controller controller;
    
    @Inject
    MenuBean mb;        
    
    String selectedLeague;
    
    @PostConstruct
    public void init() {
        selectedLeague = mb.getLeague();
    }

    public List<Transfer> getHotTransfersToLeague() {
        return controller.getHotTransfersToLeague(selectedLeague);
    }

    public List<Transfer> getHotTransfersFromLeague() {
        return controller.getHotTransfersFromLeague(selectedLeague);
    }

}
