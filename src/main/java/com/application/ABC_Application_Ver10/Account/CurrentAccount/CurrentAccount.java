package com.application.ABC_Application_Ver10.Account.CurrentAccount;

import com.application.ABC_Application_Ver10.Account.Account;
import javax.persistence.*;

@Entity
@DiscriminatorValue("Current Account")
@DiscriminatorColumn(name = "account_type", discriminatorType = DiscriminatorType.STRING)
public class CurrentAccount extends Account {
    private double overDraftAmount;

    public double getOverDraftAmount() {
        return overDraftAmount;
    }

    public void setOverDraftAmount(double overDraftAmount) {
        this.overDraftAmount = overDraftAmount;
    }
}
