package com.virtusa.springboot.BankingSystemSpringBoot.model.account;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.virtusa.springboot.BankingSystemSpringBoot.model.branch.Branch;
import com.virtusa.springboot.BankingSystemSpringBoot.model.card.DebitCard;
import com.virtusa.springboot.BankingSystemSpringBoot.model.customer.Customer;
import com.virtusa.springboot.BankingSystemSpringBoot.model.transaction.Transaction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity(name = "Accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    // @OneToOne
    @ManyToOne
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "account_type_id")
    private AccountType accountType;

    @Column(name = "account_number")
    private Long accountNumber;

    private Double balance;

    @Column(name = "account_status")
    private String accountStatus = "Active";

    private Integer tpin;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    @OneToOne(mappedBy = "account")
    private DebitCard debitCard;
}
