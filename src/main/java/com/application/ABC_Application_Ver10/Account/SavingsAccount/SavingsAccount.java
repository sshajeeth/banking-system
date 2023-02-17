package com.application.ABC_Application_Ver10.Account.SavingsAccount;

import com.application.ABC_Application_Ver10.Account.Account;
import javax.persistence.*;

@Entity
@DiscriminatorValue("Savings Account")
@DiscriminatorColumn(name = "account_type", discriminatorType = DiscriminatorType.STRING)
public class SavingsAccount extends Account {
    private double interest = 3.0;

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }
}
