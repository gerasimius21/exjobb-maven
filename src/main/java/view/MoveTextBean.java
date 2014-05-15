/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import controller.Controller;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import model.entities.Clubs;
import model.entities.Players;

/**
 *
 * @author Semir
 */
@Named("movetextBean")
@SessionScoped
public class MoveTextBean implements Serializable {
    
    @EJB
    Controller controller;
    private String playerName;
    private Clubs selectedClub;

    public Clubs getSelectedClub() {
        return selectedClub;
    }

    public void setSelectedClub(Clubs selectedClub) {
        this.selectedClub = selectedClub;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    
    public String reset(){
        playerName = "";
        return ""; 
    }
    
    public Players getSelectedPlayer(){
        return controller.getPlayer(playerName);
    }
    
    
}
