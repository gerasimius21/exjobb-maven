/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.paypal.svcs.services.AdaptivePaymentsService;
import com.paypal.svcs.types.ap.PayRequest;
import com.paypal.svcs.types.ap.PayResponse;
import com.paypal.svcs.types.ap.PreapprovalRequest;
import com.paypal.svcs.types.ap.PreapprovalResponse;
import com.paypal.svcs.types.ap.Receiver;
import com.paypal.svcs.types.ap.ReceiverList;
import com.paypal.svcs.types.common.AckCode;
import com.paypal.svcs.types.common.RequestEnvelope;
import controller.Controller;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Clubs;
import model.DTO.DonationDTO;
import model.DTO.TransferDTO;
import model.Donation;
import model.Players;
import model.Transfer;
import org.eclipse.persistence.descriptors.SelectedFieldsLockingPolicy;

@Named("transfBean")
@ViewScoped
public class TransferBean implements Serializable {

    @EJB
    private Controller controller;

    private List<Transfer> transfers;
    private Transfer transfer;

    private List<Transfer> selectedTransfers;

    private PayRequest payRequest;
    private PayResponse payResponse;

    private Receiver primary;
    private Receiver secondary;

    private Double amount;

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

    public void compleatTransactions() throws Exception {

        for (int i = 0; i < this.selectedTransfers.size(); i++) {
            Collection<Donation> c = this.selectedTransfers.get(i).getDonationCollection();
            System.out.println("Size of collection for " + selectedTransfers.get(i).getPlayerid().getPlayername() + ": " + c.size());
            for (Donation d : c) {
                executePayment(d.getPpkey(), d.getAmount());
                System.out.println("Compleating transaction for ppk: " + d.getPpkey());
            }
            System.out.println("Removing transfer with player: " + selectedTransfers.get(i).getPlayerid().getPlayername() 
                               + "and club: " + selectedTransfers.get(i).getClubid().getClubname());
            controller.removeTransfer(selectedTransfers.get(i));
        }

    }

    public void executePayment(String ppk, double amount) throws Exception {
        payRequest = new PayRequest();
        List<Receiver> receivers = new ArrayList<>();

        primary = new Receiver();
        primary.setAmount(amount);
        primary.setEmail("miketa.bogdanovic-facilitator@gmail.com");
        primary.setPrimary(Boolean.TRUE);
        receivers.add(primary);

        secondary = new Receiver();
        Double d = (amount * 0.95);
        secondary.setAmount(roundByTwo(d));
        secondary.setEmail("club21@club.com");
        receivers.add(secondary);

        ReceiverList receiverList = new ReceiverList(receivers);

        payRequest.setReceiverList(receiverList);
        payRequest.setPreapprovalKey(ppk);
        RequestEnvelope requestEnvelope = new RequestEnvelope("en_US");
        payRequest.setRequestEnvelope(requestEnvelope);
        payRequest.setActionType("PAY");
        payRequest.setCancelUrl("https://devtools-paypal.com/guide/ap_preapprove_payment?cancel=true");
        payRequest.setReturnUrl("http://localhost:8080/sdktest/clubView.jsf");
        payRequest.setCurrencyCode("EUR");
        payRequest.setIpnNotificationUrl("http://replaceIpnUrl.com");
        payRequest.setFeesPayer("SECONDARYONLY");

        Map<String, String> sdkConfig = new HashMap<>();
        sdkConfig.put("mode", "sandbox");
        sdkConfig.put("acct1.UserName", "miketa.bogdanovic-facilitator_api1.gmail.com");
        sdkConfig.put("acct1.Password", "1398705931 ");
        sdkConfig.put("acct1.Signature", "AiPC9BjkCyDFQXbSkoZcgqH3hpacAwld1cXNDk56uvbJmYoTVW8wxwcl");
        sdkConfig.put("acct1.AppId", "APP-80W284485P519543T");

        AdaptivePaymentsService adaptivePaymentsService = new AdaptivePaymentsService(sdkConfig);
        payResponse = adaptivePaymentsService.pay(payRequest);
        
        System.out.println("success ? " + (payResponse.getResponseEnvelope().getAck() == AckCode.SUCCESS));
        System.out.println("success with warning ? " + (payResponse.getResponseEnvelope().getAck() == AckCode.SUCCESSWITHWARNING));
        if((payResponse.getResponseEnvelope().getAck() == AckCode.SUCCESS) || (payResponse.getResponseEnvelope().getAck() == AckCode.SUCCESSWITHWARNING)){
            System.out.println("Removing donation with ppk : " + ppk);
            controller.removeDonations(ppk);
        }
    }
    
    public double roundByTwo(double d){
        double ret = Math.round(d*100.0)/100.0;
        return ret;
    }

}
