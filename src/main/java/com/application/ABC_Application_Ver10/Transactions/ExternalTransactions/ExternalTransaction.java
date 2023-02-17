package com.application.ABC_Application_Ver10.Transactions.ExternalTransactions;

import com.application.ABC_Application_Ver10.Transactions.Transaction;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("External Transaction")
@DiscriminatorColumn(name = "transaction_type", discriminatorType = DiscriminatorType.STRING)
public class ExternalTransaction extends Transaction {

    private double amount;
    private String branchName;
    private String branchCode;
    private String branchAddress;
    private String postCode;

    public ExternalTransaction() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}
