/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.paypal.exception.ClientActionRequiredException;
import com.paypal.exception.HttpErrorException;
import com.paypal.exception.InvalidCredentialException;
import com.paypal.exception.InvalidResponseDataException;
import com.paypal.exception.MissingCredentialException;
import com.paypal.exception.SSLConfigurationException;
import com.paypal.sdk.exceptions.OAuthException;
import com.paypal.svcs.services.AdaptivePaymentsService;
import com.paypal.svcs.types.ap.PayRequest;
import com.paypal.svcs.types.ap.PayResponse;
import com.paypal.svcs.types.ap.PreapprovalRequest;
import com.paypal.svcs.types.ap.PreapprovalResponse;
import com.paypal.svcs.types.ap.Receiver;
import com.paypal.svcs.types.ap.ReceiverList;
import com.paypal.svcs.types.common.RequestEnvelope;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author gerasim
 */
@Named("preBean")
@ApplicationScoped
public class PreaprovalBean {

    @Inject
    ClubBean club;

    private PreapprovalRequest preRequest;
    private PreapprovalResponse preResponse;

    private PayRequest payRequest;
    private PayResponse payResponse;

    private Receiver primary;
    private Receiver secondary;

    public void preapprove() throws SSLConfigurationException, InvalidCredentialException, IOException,
            UnsupportedEncodingException, HttpErrorException, InvalidResponseDataException,
            ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException {
        preRequest = new PreapprovalRequest();

        RequestEnvelope requestEnvelope = new RequestEnvelope("en_US");
        preRequest.setRequestEnvelope(requestEnvelope);
        preRequest.setCancelUrl("https://devtools-paypal.com/guide/ap_chained_payment?cancel=true");
        preRequest.setReturnUrl("http://localhost:8080/sdktest/clubView.jsf");
        preRequest.setCurrencyCode("USD");
        preRequest.setIpnNotificationUrl("http://replaceIpnUrl.com");
        preRequest.setStartingDate("2014-04-30");
        preRequest.setEndingDate("2014-05-01");
        preRequest.setMaxNumberOfPayments(1);
        preRequest.setMaxTotalAmountOfAllPayments(club.getAmount());
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

        System.out.println(preResponse.getPreapprovalKey());
        FacesContext.getCurrentInstance().getExternalContext()
                .redirect("https://www.sandbox.paypal.com/webscr?cmd=_ap-preapproval&preapprovalkey=" + preResponse.getPreapprovalKey());

    }

    public void executePayment() throws Exception {
        payRequest = new PayRequest();

        List<Receiver> receivers = new ArrayList<>();
        primary = new Receiver();
        primary.setAmount(club.getAmount());
        primary.setEmail("miketa.bogdanovic-facilitator@gmail.com");
        primary.setPrimary(Boolean.TRUE);
        receivers.add(primary);

        secondary = new Receiver();
        Double d = (club.getAmount() * 0.95);
        secondary.setAmount((double)(Math.round(d * 100) / 100));
        secondary.setEmail("club21@club.com");
        receivers.add(secondary);
        
        ReceiverList receiverList = new ReceiverList(receivers);

        payRequest.setReceiverList(receiverList);
        payRequest.setPreapprovalKey(preResponse.getPreapprovalKey());
        RequestEnvelope requestEnvelope = new RequestEnvelope("en_US");
        payRequest.setRequestEnvelope(requestEnvelope);
        payRequest.setActionType("PAY");
        payRequest.setCancelUrl("https://devtools-paypal.com/guide/ap_preapprove_payment?cancel=true");
        payRequest.setReturnUrl("https://devtools-paypal.com/guide/ap_preapprove_payment?success=true");
        payRequest.setCurrencyCode("USD");
        payRequest.setIpnNotificationUrl("http://replaceIpnUrl.com");
        payRequest.setFeesPayer("SECONDARYONLY");

        Map<String, String> sdkConfig = new HashMap<String, String>();
        sdkConfig.put("mode", "sandbox");
        sdkConfig.put("acct1.UserName", "miketa.bogdanovic-facilitator_api1.gmail.com");
        sdkConfig.put("acct1.Password", "1398705931 ");
        sdkConfig.put("acct1.Signature", "AiPC9BjkCyDFQXbSkoZcgqH3hpacAwld1cXNDk56uvbJmYoTVW8wxwcl");
        sdkConfig.put("acct1.AppId", "APP-80W284485P519543T");

        AdaptivePaymentsService adaptivePaymentsService = new AdaptivePaymentsService(sdkConfig);
        payResponse = adaptivePaymentsService.pay(payRequest);
    }
}
