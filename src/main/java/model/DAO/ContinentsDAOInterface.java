/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.DAO;

import java.util.List;
import model.entities.Continents;

/**
 *
 * @author gerasim
 */
public interface ContinentsDAOInterface {
    public void addContinent(Continents continent);
    public void removeContinent(Continents continent);
    public Continents findByID(long id);
    public List<Continents> findAll();    
}
