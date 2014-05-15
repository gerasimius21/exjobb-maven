/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.DAO;

import java.util.List;
import model.entities.Lands;

/**
 *
 * @author gerasim
 */
public interface LandsDAOInterface {
    public void addLand(Lands club);
    public void removeLand(Lands club);
    public Lands findByID(long id);
    public List<Lands> findAll();    
}
