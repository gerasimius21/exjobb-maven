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
import com.paypal.svcs.types.ap.Receiver;
import com.paypal.svcs.types.ap.ReceiverList;
import com.paypal.svcs.types.common.RequestEnvelope;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
@Named("paymentBean")
@ApplicationScoped
public class PaymentBean {

    @Inject
    ClubBean club;

    private Receiver primary;
    private Receiver secondary;
    private PayRequest payRequest;

    private List<Receiver> receivers = new ArrayList<>();

    public void pay() throws SSLConfigurationException, InvalidCredentialException, IOException, 
            UnsupportedEncodingException, HttpErrorException, InvalidResponseDataException, 
            ClientActionRequiredException, MissingCredentialException, InterruptedException, OAuthException {
        payRequest = new PayRequest();
        primary = new Receiver();
        secondary = new Receiver();

        primary.setEmail("miketa.bogdanovic-facilitator@gmail.com");
        primary.setPrimary(Boolean.TRUE);
        primary.setAmount(club.getAmount());

        secondary.setEmail("club21@club.com");
        secondary.setAmount(club.getAmount() * 0.95);

        receivers.add(primary);
        receivers.add(secondary);

        ReceiverList receiverList = new ReceiverList(receivers);
        payRequest.setReceiverList(receiverList);

        RequestEnvelope requestEnvelope = new RequestEnvelope("en_US");
        payRequest.setRequestEnvelope(requestEnvelope);
        payRequest.setActionType("PAY");
        payRequest.setCancelUrl("https://devtools-paypal.com/guide/ap_chained_payment?cancel=true");
        payRequest.setReturnUrl("https://devtools-paypal.com/guide/ap_chained_payment?success=true");
        payRequest.setCurrencyCode("USD");
        payRequest.setIpnNotificationUrl("http://replaceIpnUrl.com");

        Map<String, String> sdkConfig = new HashMap<>();
        sdkConfig.put("mode", "sandbox");
        sdkConfig.put("acct1.UserName", "miketa.bogdanovic-facilitator_api1.gmail.com");
        sdkConfig.put("acct1.Password", "1398705931");
        sdkConfig.put("acct1.Signature", "AiPC9BjkCyDFQXbSkoZcgqH3hpacAwld1cXNDk56uvbJmYoTVW8wxwcl");
        sdkConfig.put("acct1.AppId", "APP-80W284485P519543T");

        AdaptivePaymentsService adaptivePaymentsService = new AdaptivePaymentsService(sdkConfig);
        PayResponse payResponse = adaptivePaymentsService.pay(payRequest);
        
        System.out.println(payResponse.getPayKey());
        FacesContext.getCurrentInstance().getExternalContext().redirect("https://www.sandbox.paypal.com/webscr?cmd=_ap-payment&paykey=" + payResponse.getPayKey());
    }
}
