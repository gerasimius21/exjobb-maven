/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import model.DAO.ClubsDAO;
import model.DAO.DonationsDAO;
import model.DAO.PlayersDAO;
import model.DAO.TransfersDAO;
import model.DTO.DonationDTO;
import model.DTO.TransferDTO;
import model.Donation;
import model.Transfer;

/**
 *
 * @author gerasim
 */
@Stateless
public class Controller {

    @Inject
    PlayersDAO playerDAO;

    @Inject
    ClubsDAO clubDAO;

    @Inject
    TransfersDAO transferDAO;

    @Inject
    DonationsDAO donationDAO;

    Transfer transfer;
    Donation donation;

    public PlayersDAO getPlayers() {
        return playerDAO;
    }

    public ClubsDAO getClubs() {
        return clubDAO;

    }

    public List getAllClubs() {
        return clubDAO.findAll();
    }

    public void saveDonation(TransferDTO transferDTO, DonationDTO donationDTO) {
        if (transferDAO.findByClubAndPlayer(transferDTO.getClub(), transferDTO.getPlayer()) == null) {
            transfer.setClubid(transferDTO.getClub());
            transfer.setPlayerid(transferDTO.getPlayer());
            transferDAO.addTransfer(transfer);

            donation.setAmount((float) donationDTO.getAmount());
            donation.setTransferid(transferDAO.findByClubAndPlayer(transferDTO.getClub(), transferDTO.getPlayer()));
            donation.setPpkey(donationDTO.getPreApprovalKey());

            donationDAO.addDonation(donation);
        } else {
            donation.setAmount((float) donationDTO.getAmount());
            donation.setPpkey(donationDTO.getPreApprovalKey());
            donation.setTransferid(transferDAO.findByClubAndPlayer(transferDTO.getClub(), transferDTO.getPlayer()));
            
            donationDAO.addDonation(donation);
        }
    }
}
