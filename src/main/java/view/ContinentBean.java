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
@Named("continentBean")
@ViewScoped
public class ContinentBean {
    
    @EJB
    Controller controller;

    @Inject
    MenuBean mb;
    
    String selectedContinent;
    
    @PostConstruct
    public void init() {
        selectedContinent = mb.getContinent();
    }
    
    public List<Transfer> getHotTransfersToContinent() {
        return controller.getHotTransfersToContinent(selectedContinent);
    }
    
    public List<Transfer> getHotTransfersFromContinent() {
        return controller.getHotTransfersFromContinent(selectedContinent);
    }
}
