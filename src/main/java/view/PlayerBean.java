/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import model.entities.Players;
import model.entities.Transfer;

/**
 *
 * @author gerasim
 */
@Named("playerBean")
@ViewScoped
public class PlayerBean implements Serializable {

    @EJB
    Controller controller;

    String selectedPlayer;
    Players player;

    @PostConstruct
    public void init() {
        selectedPlayer = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("selectedPlayer");
        System.out.println("INIT " + selectedPlayer);
        player = controller.getPlayer(selectedPlayer);
    }

    public List<Transfer> getHotTransfers() {
        return controller.getHotTranfersPerPlayer(player);
    }

    public String getSelectedPlayer() {
        return selectedPlayer;
    }

    public void setSelectedPlayer(String selectedPlayer) {
        this.selectedPlayer = selectedPlayer;
    }

    public Players getPlayer() {
        return player;
    }

    public void setPlayer(Players player) {
        this.player = player;
    }

}
