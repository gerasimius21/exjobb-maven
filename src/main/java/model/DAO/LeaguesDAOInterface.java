/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.DAO;

import java.util.List;
import model.entities.Leagues;

/**
 *
 * @author gerasim
 */
public interface LeaguesDAOInterface {
    public void addLeague(Leagues league);
    public void removeLeague(Leagues league);
    public Leagues findByID(long id);
    public List<Leagues> findAll();   
}
