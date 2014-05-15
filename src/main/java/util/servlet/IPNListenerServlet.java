/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.servlet;

import com.paypal.ipn.IPNMessage;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gerasim
 */
public class IPNListenerServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /*
     * receiver for PayPal ipn call back.
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
// For a full list of configuration parameters refer in wiki page.
// (https://github.com/paypal/sdk-core-java/wiki/SDK-Configuration-Parameters)
        Map<String, String> mode = Configuration.getConfig();
        IPNMessage ipnlistener = new IPNMessage(request, mode);
        boolean isIpnVerified = ipnlistener.validate();
        String transactionType = ipnlistener.getTransactionType();
        Map<String, String> map = ipnlistener.getIpnMap();

        System.out.println(IPNListenerServlet.class.toString() + "******* IPN (name:value) pair : " + map + " "
                + "######### TransactionType : " + transactionType + " ======== IPN verified : " + isIpnVerified);
    }
}
