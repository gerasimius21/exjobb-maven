/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Inject;
import model.DAO.ClubsDAO;
import model.DAO.DonationsDAO;
import model.DAO.PlayersDAO;
import model.DAO.TransfersDAO;
import model.DTO.DonationDTO;
import model.DTO.TransferDTO;
import model.Donation;
import model.Players;
import model.Transfer;
import util.comparator.DonationComparator;

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

    public PlayersDAO getPlayers() {
        return playerDAO;
    }

    public ClubsDAO getClubs() {
        return clubDAO;
    }

    public List getAllClubs() {
        return clubDAO.findAll();
    }

    public void saveTransaction(TransferDTO transferDTO, DonationDTO donationDTO) {
        Transfer transfer = new Transfer();
        List<Transfer> transfers = transferDAO.findAll();
        if (transfers.isEmpty()) {
            transfer = null;
        }
        for (Transfer t : transfers) {
            System.out.println("DATABASE clubid = " + t.getClubid() + " playerid = " + t.getPlayerid());
            System.out.println("TRANSFER clubid = " + transferDTO.getClub().getIdclubs() + " playerid = " + transferDTO.getPlayer().getIdplayers());
            if ((t.getClubid().getIdclubs() == transferDTO.getClub().getIdclubs())
                    && (t.getPlayerid().getIdplayers() == transferDTO.getPlayer().getIdplayers())) {
                System.out.println((t.getClubid().getIdclubs() == transferDTO.getClub().getIdclubs()));
                System.out.println((t.getPlayerid().getIdplayers() == transferDTO.getPlayer().getIdplayers()));
                transfer = t;
                break;
            } else {
                transfer = null;
            }
        }
        if (transfer == null) {
            transfer = new Transfer();
            transfer.setClubid(transferDTO.getClub());
            transfer.setPlayerid(transferDTO.getPlayer());
            transfer.setAllDonations((float) donationDTO.getAmount());
            transferDAO.addTransfer(transfer);
            System.out.println("Transfer==null saving donation" + transfer);
            transfer = transferDAO.findByClubAndPlayer(transferDTO.getClub(), transferDTO.getPlayer());
            saveDonation(donationDTO, transfer);
        } else {
            System.out.println("Transfer exists saving donation" + transfer);
            transfer = transferDAO.findByClubAndPlayer(transferDTO.getClub(), transferDTO.getPlayer());
            float sum = transfer.getAllDonations() + (float) donationDTO.getAmount();
            transfer.setAllDonations(sum);
            saveDonation(donationDTO, transfer);
        }

    }

    private void saveDonation(DonationDTO donationDTO, Transfer transfer) {
        System.out.println(donationDTO.getAmount() + donationDTO.getPreApprovalKey() + transfer.getIdtransfer());
        Donation donation = new Donation();
        donation.setAmount((float) donationDTO.getAmount());
        donation.setPpkey(donationDTO.getPreApprovalKey());
        donation.setTransferid(transfer);
        donationDAO.addDonation(donation);
    }

    public List<Transfer> getAllTransfers() {
        System.out.println("From controller number of transfers: " + transferDAO.findAll().size());
        return transferDAO.findAll();
    }

    public List<Transfer> getHotTransfersToClub(String clubname) {
        List<Transfer> transfers = transferDAO.findByClub(clubname);
        Collections.sort(transfers, new DonationComparator());
        Collections.reverse(transfers);
        List<Transfer> hotTransfers = new ArrayList<>();
        if (transfers.size() > 5) {
            for (int i = 0; i < 5; i++) {
                hotTransfers.add(transfers.get(i));
            }
        }
        return hotTransfers;
    }

    public List<Transfer> getHotTransfersFromClub(String clubname) {
        List<Players> players = playerDAO.findByClub(clubname);
        List<Transfer> transfers = transferDAO.findAll();
        List<Transfer> hotTransfers = new ArrayList<>();

        for (Transfer t : transfers) {
            for (Players p : players) {
                if (t.getPlayerid().getIdplayers() == p.getIdplayers()) {
                    hotTransfers.add(t);
                }
            }
        }
        return hotTransfers;
    }

    public Players getPlayer(String player) {
        return playerDAO.findByName(player);
    }

    public List<Transfer> getHotTranfersPerPlayer(Players player) {
        return transferDAO.findByPlayer(player);
    }

    public void addPlayerToDB(Players player) {
        playerDAO.addPlayer(player);
    }

    public void removeDonations(String ppk) {
        donationDAO.removeDonation(donationDAO.findByPPK(ppk));
    }
    
    public void removeTransfer(Transfer t){
        transferDAO.removeTransfer(t);
    }
}
