/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import model.entities.Transfer;

@Named("transfBean")
@ApplicationScoped
public class TransferBean implements Serializable {

    @EJB
    private Controller controller;

    private List<Transfer> transfers;
    private Transfer transfer;

    private List<Transfer> selectedTransfers;

    @PostConstruct
    public void init() {
        System.out.println("Post construct invoked");
        transfers = controller.getAllTransfers();
    }

    public List<Transfer> getTransfers() {
        return transfers;
    }

    public void setTransfers(List<Transfer> transfers) {
        this.transfers = transfers;
    }

    public Transfer getTransfer() {
        return transfer;
    }

    public void setTransfer(Transfer transfer) {
        this.transfer = transfer;
    }

    public List<Transfer> getSelectedTransfers() {
        return selectedTransfers;
    }

    public void setSelectedTransfers(List<Transfer> selectedTransfers) {
        this.selectedTransfers = selectedTransfers;
    }

}
