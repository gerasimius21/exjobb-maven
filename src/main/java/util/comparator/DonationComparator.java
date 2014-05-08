/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util.comparator;

import java.util.Comparator;
import model.Transfer;

/**
 *
 * @author gerasim
 */
public class DonationComparator implements Comparator<Transfer> {

        @Override
        public int compare(Transfer a, Transfer b) {
            return a.getAllDonations() < b.getAllDonations() ? -1 : a.getAllDonations() == b.getAllDonations() ? 0 : 1;
        }
    }
