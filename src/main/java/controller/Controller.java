/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import model.DAO.ClubsDAO;
import model.DAO.ContinentsDAO;
import model.DAO.DonationsDAO;
import model.DAO.LandsDAO;
import model.DAO.LeaguesDAO;
import model.DAO.PlayersDAO;
import model.DAO.TransfersDAO;
import model.DTO.DonationDTO;
import model.DTO.TransferDTO;
import model.entities.Clubs;
import model.entities.Donation;
import model.entities.Players;
import model.entities.Transfer;
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

    @Inject
    ContinentsDAO continentDAO;

    @Inject
    LandsDAO landDAO;

    @Inject
    LeaguesDAO leagueDAO;

    public PlayersDAO getPlayers() {
        return playerDAO;
    }

    public List<Clubs> getAllClubs() {
        return clubDAO.findAll();
    }

    public List getAllClubsPerLeague(String league) {
        return clubDAO.findByLeague(league);
    }

    public List getAllContinents() {
        return continentDAO.findAll();
    }

    public List getAllLandsPerContinent(String continent) {
        return landDAO.findByContinent(continent);
    }

    public List getAllLeaguesPerLand(String land) {
        return leagueDAO.findByLand(land);
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
        return sortTransfers(transfers);
    }

    public List<Transfer> getHotTransfersFromClub(String clubname) {
        List<Transfer> transfers = transferDAO.findTransfersFromClub(clubname);
        return sortTransfers(transfers);
    }

    public List<Transfer> getHotTransfersToLeague(String leaguename) {
        List<Transfer> transfers = transferDAO.findTransfersToLeague(leaguename);
        return sortTransfers(transfers);
    }

    public List<Transfer> getHotTransfersFromLeague(String leaguename) {
        List<Transfer> transfers = transferDAO.findTransfersFromLeague(leaguename);
        return sortTransfers(transfers);
    }

    public List<Transfer> getHotTransfersToLand(String landname) {
        List<Transfer> transfers = transferDAO.findTransfersToLand(landname);
        return sortTransfers(transfers);
    }
    
    public List<Transfer> getHotTransfersFromLand(String landname) {
        List<Transfer> transfers = transferDAO.findTransfersFromLand(landname);
        return sortTransfers(transfers);
    }
    
    public List<Transfer> getHotTransfersToContinent(String continentname) {
        List<Transfer> transfers = transferDAO.findTransfersToContinent(continentname);
        return sortTransfers(transfers);
    }
    
    public List<Transfer> getHotTransfersFromContinent(String continentname) {
        List<Transfer> transfers = transferDAO.findTransfersFromContinent(continentname);
        return sortTransfers(transfers);        
    }
    
    public List<Transfer> getHotTransfersWorld() {
        return sortTransfers(getAllTransfers());
    }


    public Players getPlayer(String player) {
        return playerDAO.findByName(player);
    }

    public List<Transfer> getHotTranfersPerPlayer(Players player) {
        return transferDAO.findByPlayer(player);
    }

    private List<Transfer> sortTransfers(List<Transfer> transfers) {
        Collections.sort(transfers, new DonationComparator());
        Collections.reverse(transfers);
        if (transfers.size() > 5) {
            transfers = transfers.subList(0, 4);
        }
        return transfers;
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
