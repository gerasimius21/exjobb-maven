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
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Clubs;
import model.Players;

@Named("clubBean")
@RequestScoped
public class ClubBean implements Serializable {

    @EJB
    private Controller controller;
    private List<Players> clubPlayers;
    private Players selectedPlayer;
    
    private List<Clubs> clubs;
    private String selectedClub;

    private Double amount;
    
    @Inject
    MenuBean mb;
      
    
    @PostConstruct
    public void init() {
        System.out.println("Club: " + mb.getClub());
        clubs = controller.getClubs().findAll();
        clubPlayers = controller.getPlayers().findByClub(mb.getClub());
    }
    
    public List<Clubs> getClubs() {
        return clubs;
    }
      
    public List<Players> getPlayers() {  
        return clubPlayers;
    } 
    
    public String getSelectedClub() {
        return selectedClub;
    }
    
    public void setSelectedClub(String selectedClub) {
        this.selectedClub = selectedClub;
    }
    
    public Players getSelectedPlayer() {
        return selectedPlayer;
    }

    public void setSelectedPlayer(Players selectedPlayer) {
        this.selectedPlayer = selectedPlayer;
        clubs.remove(this.selectedPlayer.getClubid());
    }
    
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    public Double getAmount() {
        return amount;
    }

}
