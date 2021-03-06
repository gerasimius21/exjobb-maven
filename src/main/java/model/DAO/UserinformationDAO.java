/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.sql.SQLException;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.entities.Userinformation;

/**
 *
 * @author Semir
 */
@ApplicationScoped
public class UserinformationDAO implements UserinformationDAOInterface {

    @PersistenceContext(unitName = "fansferPU")
    private EntityManager em;

    @Override
    public void addUserinformation(Userinformation userinfo) {
        em.persist(userinfo);
    }

    @Override
    public void removeClub(Userinformation userinfo) {
        em.merge(userinfo);
        em.remove(userinfo);
    }

    @Override
    public Userinformation findByID(long id) {
        return em.find(Userinformation.class, id);
    }

    @Override
    public List<Userinformation> findAll() {
        TypedQuery<Userinformation> query = em.createQuery(
                "SELECT g FROM Userinformation g", Userinformation.class);
        return query.getResultList();
    }

    @Override
    public Userinformation findByEmail(String email) {
        Userinformation uinfo;
        try {
            uinfo = (Userinformation) em.createQuery("SELECT d FROM Userinformation d WHERE d.email = ?1").setParameter(1, email).getSingleResult();
        } catch (NoResultException e) {
            uinfo = null;
            return uinfo;
        };
        return uinfo;
    }

}
