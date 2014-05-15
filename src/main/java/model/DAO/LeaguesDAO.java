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
import model.entities.Leagues;

/**
 *
 * @author gerasim
 */
@ApplicationScoped
public class LeaguesDAO implements LeaguesDAOInterface {

    @PersistenceContext(unitName = "fansferPU")
    private EntityManager em;

    @Override
    public void addLeague(Leagues league) {
        em.persist(league);
    }

    @Override
    public void removeLeague(Leagues league) {
        em.merge(league);
        em.remove(league);
    }

    @Override
    public Leagues findByID(long id) {
        return em.find(Leagues.class, id);
    }
    

    @Override
    public List<Leagues> findAll() {
        TypedQuery<Leagues> query = em.createQuery(
                "SELECT l FROM Leagues l", Leagues.class);
        return query.getResultList();
    } 
    
    public List<Leagues> findByLand(String land) {
        return em.createQuery("SELECT l FROM Leagues l WHERE l.landid.landname = ?1")
                .setParameter(1, land).getResultList();
    }
}
