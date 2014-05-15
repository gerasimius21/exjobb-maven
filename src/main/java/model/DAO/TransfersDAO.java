/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.entities.Clubs;
import model.entities.Players;
import model.entities.Transfer;

/**
 *
 * @author gerasim
 */
@ApplicationScoped
public class TransfersDAO implements TransfersDAOInterface {

    @PersistenceContext(unitName = "fansferPU")
    private EntityManager em;
    
    @Inject
    ClubsDAO clubDAO;
    
    @Inject
    PlayersDAO playerDAO;

    @Override
    public void addTransfer(Transfer transfer) {
        em.persist(transfer);
    }

    @Override
    public void removeTransfer(Transfer transfer) {
        em.merge(transfer);
        em.remove(transfer);
    }

    @Override
    public Transfer findByID(long id) {
        return em.find(Transfer.class, id);
    }

    @Override
    public List<Transfer> findAll() {
        TypedQuery<Transfer> query = em.createQuery(
                "SELECT g FROM Transfer g", Transfer.class);
        return query.getResultList();
    }
    
    public Transfer findByClubAndPlayer(Clubs club, Players player) {
        return (Transfer)em.createQuery("SELECT t FROM Transfer t WHERE t.clubid = ?1 AND t.playerid = ?2")
                .setParameter(1, club)
                .setParameter(2, player)
                .getSingleResult();
    }
    
    public List<Transfer> findByClub(String clubname) {
        return em.createQuery("SELECT t FROM Transfer t WHERE t.clubid.clubname = ?1")
                .setParameter(1, clubname).getResultList();
    }
    
    public List<Transfer> findByPlayer(Players player) {
        return em.createQuery("SELECT t FROM Transfer t WHERE t.playerid = ?1")
                .setParameter(1, player).getResultList();
    }
    
    public List<Transfer> findByLeague(String leaguename) {
        return em.createQuery("SELECT t FROM Transfer t WHERE t.clubid.leagueid.leaguename = ?1")
                .setParameter(1, leaguename).getResultList();
    }
    
    public List<Transfer> findByLand(String landname) {
        return em.createQuery("SELECT t FROM Transfer t WHERE t.clubid.leagueid.landid.landname = ?1")
                .setParameter(1, landname).getResultList();
    }
    
    public List<Transfer> findByContinent(String continent) {
        return em.createQuery("SELECT t FROM Transfer t Where t.clubid.leagueid.landid.continentid = ?1")
                .setParameter(1, continent).getResultList();
    }
    
    public List<Transfer> findTransfersFromClub(String clubname) {
        return em.createQuery("SELECT t FROM Transfer t WHERE t.playerid.clubid.clubname = ?1")
                .setParameter(1, clubname).getResultList();
    }
        
    public List<Transfer> findTransfersFromLeague(String leaguename) {
        return em.createQuery("SELECT t FROM Transfer t WHERE t.playerid.clubid.leagueid.leaguename = ?1 AND t.clubid.leagueid.leaguename != ?2")
                .setParameter(1, leaguename).setParameter(2, leaguename).getResultList();
    }
    
    public List<Transfer> findTransfersToLeague(String leaguename) {
        return em.createQuery("SELECT t FROM Transfer t WHERE t.playerid.clubid.leagueid.leaguename != ?1 AND t.clubid.leagueid.leaguename = ?2")
                .setParameter(1, leaguename).setParameter(2, leaguename).getResultList();
    }
    
    public List<Transfer> findTransfersFromLand(String landname) {
        return em.createQuery("SELECT t FROM Transfer t WHERE t.playerid.clubid.leagueid.landid.landname = ?1 AND t.clubid.leagueid.landid.landname != ?2")
                .setParameter(1, landname).setParameter(2, landname).getResultList();
    }
    
    public List<Transfer> findTransfersToLand(String landname) {
        return em.createQuery("SELECT t FROM Transfer t WHERE t.playerid.clubid.leagueid.landid.landname != ?1 AND t.clubid.leagueid.landid.landname = ?2")
                .setParameter(1, landname).setParameter(2, landname).getResultList();
    }
    
    public List<Transfer> findTransfersFromContinent(String continentname) {
        return em.createQuery("SELECT t FROM Transfer t WHERE t.playerid.clubid.leagueid.landid.continentid.continentname = ?1 AND t.clubid.leagueid.landid.continentid.continentname != ?2")
                .setParameter(1, continentname).setParameter(2, continentname).getResultList();
    }
    
    public List<Transfer> findTransfersToContinent(String continentname) {
        return em.createQuery("SELECT t FROM Transfer t WHERE t.playerid.clubid.leagueid.landid.continentid.continentname != ?1 AND t.clubid.leagueid.landid.continentid.continentname = ?2")
                .setParameter(1, continentname).setParameter(2, continentname).getResultList();
    }

}
