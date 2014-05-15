/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import controller.Controller;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.entities.Transfer;

/**
 *
 * @author gerasim
 */
@Named("worldBean")
@ViewScoped
public class WorldBean {
    
    @EJB
    Controller controller;
    
    @Inject
    MenuBean mb;
    
    public List<Transfer> getHotTransfersWorld() {
        return controller.getHotTransfersWorld();
    } 
}
