/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.util.List;
import model.entities.Players;

/**
 *
 * @author gerasim
 */
public interface PlayersDAOInterface {

    public void addPlayer(Players player);
    public void removePlayer(Players player);
    public Players findByID(int id);
    public List<Players> findByClub(String club);
    public List<Players> findAll();

}
