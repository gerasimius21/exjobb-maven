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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import model.DTO.DonationDTO;
import model.DTO.TransferDTO;

/**
 *
 * @author gerasim
 */
@Named("preBean")
@ApplicationScoped
public class PreaprovalBean {

    @Inject
    MoveTextBean mtb;

    @EJB
    Controller controller;

    private PreapprovalRequest preRequest;
    private PreapprovalResponse preResponse;

    private TransferDTO transferDTO;
    private DonationDTO donationDTO;

    private Double amount;

    public void preapprove() throws Exception {
        preRequest = new PreapprovalRequest();

        RequestEnvelope requestEnvelope = new RequestEnvelope("en_US");
        preRequest.setRequestEnvelope(requestEnvelope);
        preRequest.setCancelUrl("https://devtools-paypal.com/guide/ap_chained_payment?cancel=true");
        preRequest.setReturnUrl("http://localhost:8080/sdktest/test2.jsf");
        preRequest.setCurrencyCode("EUR");
        preRequest.setIpnNotificationUrl("http://replaceIpnUrl.com");
        preRequest.setStartingDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        preRequest.setEndingDate("2014-06-30");
        preRequest.setMaxNumberOfPayments(1);

        System.out.println(mtb.getSelectedClub().getClubname());
        System.out.println(amount);

        preRequest.setMaxTotalAmountOfAllPayments(amount);
        preRequest.setDisplayMaxTotalAmount(Boolean.TRUE);
        preRequest.setFeesPayer("SECONDARYONLY");

        Map<String, String> sdkConfig = new HashMap<>();
        sdkConfig.put("mode", "sandbox");
        sdkConfig.put("acct1.UserName", "miketa.bogdanovic-facilitator_api1.gmail.com");
        sdkConfig.put("acct1.Password", "1398705931");
        sdkConfig.put("acct1.Signature", "AiPC9BjkCyDFQXbSkoZcgqH3hpacAwld1cXNDk56uvbJmYoTVW8wxwcl");
        sdkConfig.put("acct1.AppId", "APP-80W284485P519543T");

        AdaptivePaymentsService adaptivePaymentsService = new AdaptivePaymentsService(sdkConfig);
        preResponse = adaptivePaymentsService.preapproval(preRequest);

        if (preResponse.getResponseEnvelope().getAck().equals(AckCode.SUCCESS)) {
            System.out.println("Saving donation preapproval!");
            saveTransaction();
        } else {
            System.out.println("PreApproval Unsuccessful!!!");
        }

        FacesContext.getCurrentInstance().getExternalContext()
                .redirect("https://www.sandbox.paypal.com/webscr?cmd=_ap-preapproval&preapprovalkey=" + preResponse.getPreapprovalKey());

    }

//    public void executePayment() throws Exception {
//        payRequest = new PayRequest();
//        List<Receiver> receivers = new ArrayList<>();
//
//        primary = new Receiver();
//        primary.setAmount(amount);
//        primary.setEmail("miketa.bogdanovic-facilitator@gmail.com");
//        primary.setPrimary(Boolean.TRUE);
//        receivers.add(primary);
//
//        secondary = new Receiver();
//        Double d = (amount * 0.95);
//        secondary.setAmount((double) (Math.round(d * 100) / 100));
//        secondary.setEmail("club21@club.com");
//        receivers.add(secondary);
//
//        ReceiverList receiverList = new ReceiverList(receivers);
//
//        payRequest.setReceiverList(receiverList);
//        payRequest.setPreapprovalKey(preResponse.getPreapprovalKey());
//        RequestEnvelope requestEnvelope = new RequestEnvelope("en_US");
//        payRequest.setRequestEnvelope(requestEnvelope);
//        payRequest.setActionType("PAY");
//        payRequest.setCancelUrl("https://devtools-paypal.com/guide/ap_preapprove_payment?cancel=true");
//        payRequest.setReturnUrl("http://localhost:8080/sdktest/clubView.jsf");
//        payRequest.setCurrencyCode("EUR");
//        payRequest.setIpnNotificationUrl("http://replaceIpnUrl.com");
//        payRequest.setFeesPayer("SECONDARYONLY");
//
//        Map<String, String> sdkConfig = new HashMap<>();
//        sdkConfig.put("mode", "sandbox");
//        sdkConfig.put("acct1.UserName", "miketa.bogdanovic-facilitator_api1.gmail.com");
//        sdkConfig.put("acct1.Password", "1398705931 ");
//        sdkConfig.put("acct1.Signature", "AiPC9BjkCyDFQXbSkoZcgqH3hpacAwld1cXNDk56uvbJmYoTVW8wxwcl");
//        sdkConfig.put("acct1.AppId", "APP-80W284485P519543T");
//
//        AdaptivePaymentsService adaptivePaymentsService = new AdaptivePaymentsService(sdkConfig);
//        payResponse = adaptivePaymentsService.pay(payRequest);
//    }

    private void saveTransaction() {
        transferDTO = new TransferDTO(mtb.getSelectedClub(), mtb.getSelectedPlayer());
        donationDTO = new DonationDTO(amount, preResponse.getPreapprovalKey());

        controller.saveTransaction(transferDTO, donationDTO);
    }

    public void setAmount(Double amount) {
        System.out.println("clubBean setAmount" + amount);
        this.amount = amount;
    }

    public Double getAmount() {
        System.out.println("clubBean getAmount" + amount);
        return amount;
    }
}
