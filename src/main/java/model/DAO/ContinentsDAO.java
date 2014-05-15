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
import model.entities.Continents;

/**
 *
 * @author gerasim
 */
@ApplicationScoped
public class ContinentsDAO implements ContinentsDAOInterface {

    @PersistenceContext(unitName = "fansferPU")
    private EntityManager em;

    @Override
    public void addContinent(Continents continent) {
        em.persist(continent);
    }

    @Override
    public void removeContinent(Continents continent) {
        em.merge(continent);
        em.remove(continent);
    }

    @Override
    public Continents findByID(long id) {
        return em.find(Continents.class, id);
    }
    

    @Override
    public List<Continents> findAll() {
        TypedQuery<Continents> query = em.createQuery(
                "SELECT c FROM Continents c", Continents.class);
        return query.getResultList();
    }    
}
