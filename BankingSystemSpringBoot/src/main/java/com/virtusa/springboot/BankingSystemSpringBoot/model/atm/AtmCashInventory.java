package com.virtusa.springboot.BankingSystemSpringBoot.model.atm;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class AtmCashInventory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "inventory_id")
	private Long inventoryId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "atm_id")
	private Atm atm;

	@Column(name = "currency_denomination")
	private Integer currencyDenomination;

	private Integer quantity;	
	
}
