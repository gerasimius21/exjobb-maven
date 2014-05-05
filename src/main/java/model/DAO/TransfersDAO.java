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
import model.Clubs;
import model.Players;
import model.Transfer;
import model.DAO.ClubsDAO;

/**
 *
 * @author gerasim
 */
@ApplicationScoped
public class TransfersDAO implements TransfersDAOInterface {

    @PersistenceContext(unitName = "fansferPU")
    private EntityManager em;

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
        return (Transfer)em.createQuery("SELECT t FROM Transfer t WHERE t.clubid.idclubs = ?1 AND t.playerid.idplayers = ?2")
                .setParameter(1, club.getIdclubs())
                .setParameter(2, player.getIdplayers())
                .getSingleResult();
    }
    
    public List<Transfer> findByClub(String clubname) {
        ClubsDAO clubsDAO = new ClubsDAO();
        Clubs club  = clubsDAO.findByName(clubname);
        return em.createQuery("SELECT t FROM Transfer t WHERE t.clubid = ?1")
                .setParameter("clubid", club.getIdclubs()).getResultList();
    }

}
