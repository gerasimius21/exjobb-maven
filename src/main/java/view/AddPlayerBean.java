/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import model.Clubs;
import model.Players;
import model.Transfer;

/**
 *
 * @author Semir
 */
@Named("addPlayerBean")
@ViewScoped
public class AddPlayerBean {

    @EJB
    Controller controller;
    
    Players player;

    private String playername;
    private String position;
    private Integer jerseynumber;
    private Integer goalsscored;
    private Integer yellowcards;
    private Integer redcards;
    private Integer gamesplayed;
    private Collection<Transfer> transferCollection;
    private Clubs clubid;
    
    

    public Players getPlayer() {
        return player;
    }

    public void setPlayer(Players player) {
        this.player = player;
    }

    public String getPlayername() {
        return playername;
    }

    public void setPlayername(String playername) {
        this.playername = playername;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getJerseynumber() {
        return jerseynumber;
    }

    public void setJerseynumber(Integer jerseynumber) {
        this.jerseynumber = jerseynumber;
    }

    public Integer getGoalsscored() {
        return goalsscored;
    }

    public void setGoalsscored(Integer goalsscored) {
        this.goalsscored = goalsscored;
    }

    public Integer getYellowcards() {
        return yellowcards;
    }

    public void setYellowcards(Integer yellowcards) {
        this.yellowcards = yellowcards;
    }

    public Integer getRedcards() {
        return redcards;
    }

    public void setRedcards(Integer redcards) {
        this.redcards = redcards;
    }

    public Integer getGamesplayed() {
        return gamesplayed;
    }

    public void setGamesplayed(Integer gamesplayed) {
        this.gamesplayed = gamesplayed;
    }

    public Collection<Transfer> getTransferCollection() {
        return transferCollection;
    }

    public void setTransferCollection(Collection<Transfer> transferCollection) {
        this.transferCollection = transferCollection;
    }

    public Clubs getClubid() {
        return clubid;
    }

    public void setClubid(Clubs clubid) {
        this.clubid = clubid;
    }
    
    public void addPlayerToDB(){
        
        this.player = new Players();
        this.player.setClubid(clubid);
        this.player.setGamesplayed(gamesplayed);
        this.player.setGoalsscored(goalsscored);
        this.player.setJerseynumber(jerseynumber);
        this.player.setPlayername(playername);
        this.player.setPosition(position);
        this.player.setRedcards(redcards);
        this.player.setYellowcards(yellowcards);
        this.player.setTransferCollection(new ArrayList<Transfer>());
        System.out.println(toString());
        controller.addPlayerToDB(player);
    }

    @Override
    public String toString() {
        return "AddPlayerBean{" + "controller=" + controller + ", player=" + player + ", playername=" + playername + ", position=" + position + ", jerseynumber=" + jerseynumber + ", goalsscored=" + goalsscored + ", yellowcards=" + yellowcards + ", redcards=" + redcards + ", gamesplayed=" + gamesplayed + ", transferCollection=" + transferCollection + ", clubid=" + clubid + '}';
    }

}
