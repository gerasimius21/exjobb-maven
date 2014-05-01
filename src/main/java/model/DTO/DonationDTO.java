/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.DTO;

/**
 *
 * @author gerasim
 */
public class DonationDTO {

    private double amount;
    private String preApprovalKey;

    
    public DonationDTO(double amount, String key) {
        this.amount = amount;
        this.preApprovalKey = key;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPreApprovalKey() {
        return preApprovalKey;
    }

    public void setPreApprovalKey(String preApprovalKey) {
        this.preApprovalKey = preApprovalKey;
    }
    
}
