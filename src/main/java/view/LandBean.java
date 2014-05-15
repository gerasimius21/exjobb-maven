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
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.entities.Leagues;
import model.entities.Transfer;

/**
 *
 * @author gerasim
 */
@Named("landBean")
@ViewScoped
public class LandBean {

    @EJB
    Controller controller;
    
    @Inject
    MenuBean mb;

    String selectedLand;
    Leagues league;
    
    @PostConstruct
    public void init() {
        System.out.println("Land Bean :" + selectedLand);
        selectedLand = mb.getLand();
    }

    public List<Transfer> getHotTransfersToLand() {
        return controller.getHotTransfersToLeague(selectedLand);
    }

    public List<Transfer> getHotTransfersFromLand() {
        return controller.getHotTransfersFromLeague(selectedLand);
    }

}
