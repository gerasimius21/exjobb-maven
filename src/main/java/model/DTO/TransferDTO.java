/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.DTO;

import model.Clubs;
import model.Players;

/**
 *
 * @author gerasim
 */
public class TransferDTO {
    private Players player;
    private Clubs club;
    
    public TransferDTO(Clubs club, Players player) {
        this.club = club;
        this.player = player;
    }

    public Players getPlayer() {
        return player;
    }

    public void setPlayer(Players player) {
        this.player = player;
    }

    public Clubs getClub() {
        return club;
    }

    public void setClub(Clubs club) {
        this.club = club;
    }
    
}
