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
import model.Clubs;
import model.Players;
import model.Transfer;

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
        transfer = em.merge(transfer);
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
        System.out.println("TransferDAO findbyclub clubname: " + clubname);
        Clubs club  = clubDAO.findByName(clubname);
        System.out.println(club.getIdclubs());
        return em.createQuery("SELECT t FROM Transfer t WHERE t.clubid.idclubs = ?1")
                .setParameter(1, club.getIdclubs()).getResultList();
    }
    
    public List<Transfer> findByPlayer(Players player) {
        return em.createQuery("SELECT t FROM Transfer t WHERE t.playerid = ?1")
                .setParameter(1, player).getResultList();
    }

}
