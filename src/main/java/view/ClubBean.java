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
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Clubs;
import model.Players;
import model.Transfer;
import org.primefaces.event.SelectEvent;

@Named("clubBean")
@ViewScoped
public class ClubBean implements Serializable {

    @EJB
    Controller controller;
    private List<Players> clubPlayers;
    private Players selectedPlayer;

    private List<Clubs> clubs;
    private Clubs selectedClub;

//    private List clubHotPlayers;
    @Inject
    MenuBean mb;

    @Inject
    MoveTextBean mtb;

    @Inject
    ClubListBean clb;
    
    @PostConstruct
    public void init() {
        System.out.println("Club: " + mb.getClub());
        clubs = controller.getClubs().findAll();
        clubPlayers = controller.getPlayers().findByClub(mb.getClub());
        
    }

    public void onRowSelect(SelectEvent event) throws IOException {
        mtb.setPlayerName(selectedPlayer.getPlayername());
        clb.getClubList().remove(selectedPlayer.getClubid());
        FacesContext.getCurrentInstance().getExternalContext().redirect("playerView.jsf?selectedPlayer=" + selectedPlayer.getPlayername());
        
    }

    public List<Clubs> getClubs() {
        return clubs;
    }

    public List<Players> getPlayers() {
        return clubPlayers;
    }

    public List<Transfer> getHotTranfersToClub() {
        return controller.getHotTransfersToClub(mb.getClub());
    }

    public List<Transfer> getHotTransfersFromClub() {
        return controller.getHotTransfersFromClub(mb.getClub());
    }

    public Clubs getSelectedClub() {
        return selectedClub;
    }

    public void setSelectedClub(Clubs selectedClub) {
        this.selectedClub = selectedClub;
    }

    public Players getSelectedPlayer() {
        return selectedPlayer;
    }

    public void setSelectedPlayer(Players selectedPlayer) {
        this.selectedPlayer = selectedPlayer;
        System.out.println("setSelectedPlayer clubBean: " + this.selectedPlayer);
        clubs.remove(this.selectedPlayer.getClubid());
    }

}
