package com.virtusa.banking.mvccontroller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.virtusa.banking.model.Branch;
import com.virtusa.banking.service.AccountServiceImpl;
import com.virtusa.banking.service.BranchService;

@Controller
public class TestAccountController {

	@Autowired
	public AccountServiceImpl accountServiceImpl;

	@Autowired
	public BranchService branchServiceImpl;

	@RequestMapping("/accountBalance/{userId}")
	public String availaBalance(@PathVariable("userId") int userId, Model model) {
		List<Long> accountNumbers = accountServiceImpl.getAccountNumbersByUserId(userId);
		model.addAttribute("AccountNumbers", accountNumbers);
		model.addAttribute("title", "CheckBalance");
		return "availableBalance";
	}

	@PostMapping("/balance")
	@ResponseBody
	public String getBalance(@RequestParam("selectedAccount") long accountNumber) {
		BigDecimal totalBalance = BigDecimal.valueOf(accountServiceImpl.checkBalanceByAccountNumber(accountNumber));

		return totalBalance.toString();
	}

	@RequestMapping("/")
	public String homePage(Model m) {
		return "landingPage";
	}

	@RequestMapping("/FinancialTools")
	public String financialTool() {
		return "financialTools";
	}

	@RequestMapping("/ppfcalculator")
	public String pfCalculator() {
		return "pfCalculator";
	}

	@RequestMapping("/compound-interest-calculator")
	public String compoundCalculator() {
		return "compoundInterest";
	}

	@RequestMapping("/gst-calculator")
	public String gstCalculator() {
		return "gstCalculator";
	}

	@RequestMapping("/connect-with-us")
	public String connectWithUs() {
		return "connectWithUs";
	}

	@RequestMapping("/getAllBranches")
	public String getAllBranches(Model model) {
		List<Branch> branches = branchServiceImpl.getAllBranches();
		model.addAttribute("branchNames", branches);
		return "branchLocations";
	}

	@RequestMapping("/navbaar")
	public String navbaar() {
		return "navbaar";
	}
}
