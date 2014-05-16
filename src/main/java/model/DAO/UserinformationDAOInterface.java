/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.util.List;
import model.entities.Userinformation;

/**
 *
 * @author Semir
 */
public interface UserinformationDAOInterface {

    public void addUserinformation(Userinformation userinfo);
    public void removeClub(Userinformation userinfo);
    public Userinformation findByID(long id);
    public List<Userinformation> findAll();
    public Userinformation findByEmail(String email);
    
}
