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
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import model.entities.Clubs;

/**
 *
 * @author Semir
 */
@Named("clubListBean")
@SessionScoped
public class ClubListBean implements Serializable {

    @EJB
    Controller controller;

    private List<Clubs> clubList;
    private Clubs selectedClub;

    public Clubs getSelectedClub() {
        return selectedClub;
    }

    public void setClubList(List<Clubs> clubList) {
        this.clubList = clubList;
    }

    public void setSelectedClub(Clubs selectedClub) {
        this.selectedClub = selectedClub;
    }

    @PostConstruct
    public void init(){
        clubList = controller.getAllClubs();
    }
    
    public List<Clubs> getClubList() {

        return clubList;
    }

}
