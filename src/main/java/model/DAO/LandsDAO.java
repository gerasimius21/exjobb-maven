/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.DAO;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.entities.Lands;

/**
 *
 * @author gerasim
 */
@ApplicationScoped
public class LandsDAO implements LandsDAOInterface{

    @PersistenceContext(unitName = "fansferPU")
    private EntityManager em;

    @Override
    public void addLand(Lands land) {
        em.persist(land);
    }

    @Override
    public void removeLand(Lands land) {
        em.merge(land);
        em.remove(land);
    }

    @Override
    public Lands findByID(long id) {
        return em.find(Lands.class, id);
    }
    

    @Override
    public List<Lands> findAll() {
        TypedQuery<Lands> query = em.createQuery(
                "SELECT l FROM Lands l", Lands.class);
        return query.getResultList();
    }      
    
    public List<Lands> findByContinent(String continent) {
        return em.createQuery("SELECT l FROM Lands l WHERE l.continentid.continentname = ?1")
                .setParameter(1, continent).getResultList();
    }
}
