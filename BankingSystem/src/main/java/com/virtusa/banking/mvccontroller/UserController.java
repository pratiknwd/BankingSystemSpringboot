package com.virtusa.banking.mvccontroller;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.virtusa.banking.dto.CheckAccountStatusDto;
import com.virtusa.banking.dto.InternetBankingRegistration;
import com.virtusa.banking.dto.Login;
import com.virtusa.banking.dto.ResetPassword;
import com.virtusa.banking.dto.TransferMoneyDto;
import com.virtusa.banking.dto.UserAccountRegistration;
import com.virtusa.banking.model.Account;
import com.virtusa.banking.model.AccountType;
import com.virtusa.banking.model.Bank;
import com.virtusa.banking.model.Branch;
import com.virtusa.banking.model.CardDetails;
import com.virtusa.banking.model.ModeOfTransaction;
import com.virtusa.banking.model.OnlineBankingLoginDetails;
import com.virtusa.banking.model.Transaction;
import com.virtusa.banking.model.User;
import com.virtusa.banking.model.UserType;
import com.virtusa.banking.service.AccountServiceImpl;
import com.virtusa.banking.service.AuthenticateServiceImpl;
import com.virtusa.banking.service.BankService;
import com.virtusa.banking.service.UserService;

@Controller
@SessionAttributes({ "userId", "bankName" })
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private AccountServiceImpl acl;

	@Autowired
	private BankService bankService;

	@Autowired
	AuthenticateServiceImpl authenticateServiceImpl;

	@Autowired
	private UserService userService;

	@RequestMapping("/home")
	public String home(RedirectAttributes rad, Model m) {

		if (m.getAttribute("userId") == null) {
			rad.addFlashAttribute("alert", "Login First !");
			rad.addFlashAttribute("color", "danger");
			return "redirect:login";
		}

		return "home";
	}

	// login
	@RequestMapping("/login")
	public String login(Model m) {

		if (!m.containsAttribute("login")) {
			m.addAttribute("login", new Login());
		}

		List<Bank> banks = bankService.getAllBanks();
		m.addAttribute("banks", banks);

		return "login";
	}

	@RequestMapping(value = "/loginSuccess", method = RequestMethod.POST)
	public String loginSuccess(@Valid @ModelAttribute("login") Login login, BindingResult result,
			RedirectAttributes rdrctAttr, Model m, HttpSession session) {

		if (result.hasErrors()) {
			rdrctAttr.addFlashAttribute("org.springframework.validation.BindingResult.login", result);
			rdrctAttr.addFlashAttribute("login", login);
			return "redirect:login";
		}

		int success = authenticateServiceImpl.login(login.getUserId(), login.getBank(), login.getPassword());

		if (success != 0) {
			m.addAttribute("userId", login.getUserId());
			m.addAttribute("bankName", login.getBank());
			session.setAttribute("userId", login.getUserId());

			return "redirect:home";
		} else {
			rdrctAttr.addFlashAttribute("alert", "Invalid Credentials...!");
			rdrctAttr.addFlashAttribute("color", "danger");
			return "redirect:login";
		}
	}

	@RequestMapping(value = "/checkaccountStatus", method = RequestMethod.GET)
	public String accountStuatus(Model m) {

		if (!m.containsAttribute("checkStatus")) {
			m.addAttribute("checkStatus", new CheckAccountStatusDto());
		}

		return "checkAccountStatus";
	}

	@RequestMapping(value = "/checkaccountStatus", method = RequestMethod.POST)
	public String accountStuatus(@Valid @ModelAttribute("checkStatus") CheckAccountStatusDto checkStatus,
			BindingResult result, RedirectAttributes rdAttr, Model m) {

		if (result.hasErrors()) {
			rdAttr.addFlashAttribute("org.springframework.validation.BindingResult.checkStatus", result);
			rdAttr.addFlashAttribute("checkStatus", checkStatus);

			return "redirect:checkaccountStatus";
		}

		Account account = acl.getAccountByNumber(checkStatus.getApplicationNum());

		if (account != null && checkStatus.getApplicationNum().equals(account.getAccountNumber())
				&& checkStatus.getEmail().equals(account.getUser().getEmail())) {

			User user = userService.getUserById(account.getUser().getUserId());

			if (user.getStatus().equalsIgnoreCase("Inactive")) {
				rdAttr.addFlashAttribute("name", user.getName());
				rdAttr.addFlashAttribute("account", account.getAccountNumber());
				return "redirect:accountUnderVerification";
			} else if (user.getStatus().equalsIgnoreCase("Active") && !user.isAvailedInternetBanking()) {
				rdAttr.addFlashAttribute("name", user.getName());
				rdAttr.addFlashAttribute("id", user.getUserId());
				return "redirect:accountUnderVerification";
			} else if (user.getStatus().equalsIgnoreCase("Active") && user.isAvailedInternetBanking()) {
				rdAttr.addFlashAttribute("alert", "Account Active! Login to continue!");
				rdAttr.addFlashAttribute("color", "danger");
				return "redirect:/login";
			}
		} else {
			rdAttr.addFlashAttribute("alert", "Invalid Credentials !");
			rdAttr.addFlashAttribute("color", "danger");
			return "redirect:/checkaccountStatus";
		}

		return "checkAccountStatus";
	}

	// Registering to Internet Banking
	@RequestMapping(value = "/internetBankingRegistration", method = RequestMethod.GET)
	public String internetBankingRegistration(Model m) {

		if (!m.containsAttribute("internetBankingRegistration")) {
			m.addAttribute("internetBankingRegistration", new InternetBankingRegistration());
		}

		List<Bank> banks = bankService.getAllBanks();
		m.addAttribute("banks", banks);

		return "internetBankingRegistration";
	}

	@RequestMapping(value = "/internetBankingRegistration", method = RequestMethod.POST)
	public String internetBankingRegistration(
			@Valid @ModelAttribute("internetBankingRegistration") InternetBankingRegistration internetBankingRegistration,
			BindingResult result, RedirectAttributes rdAttr, Model m) {

		if (result.hasErrors()) {
			rdAttr.addFlashAttribute("org.springframework.validation.BindingResult.internetBankingRegistration",
					result);
			rdAttr.addFlashAttribute("internetBankingRegistration", internetBankingRegistration);

			return "redirect:internetBankingRegistration";
		}

		String bankName = acl.getAccountsByUserId(internetBankingRegistration.getUserId()).get(0).getBranch().getBank()
				.getBankName();

		if (!authenticateServiceImpl.isUserPresent(internetBankingRegistration.getUserId())) {
			rdAttr.addFlashAttribute("alert", "User doesn't exists...!");
			return "redirect:internetBankingRegistration";
		} else if (authenticateServiceImpl.checkIfRegistered(internetBankingRegistration.getBank(),
				internetBankingRegistration.getUserId())) {
			rdAttr.addFlashAttribute("alert", "Already registered, please login...!");
			return "redirect:internetBankingRegistration";
		} else if (!internetBankingRegistration.getPassword()
				.equals(internetBankingRegistration.getUserId().toString())) {
			rdAttr.addFlashAttribute("alert", "Invalid Credentials!");
			return "redirect:internetBankingRegistration";
		} else if (!internetBankingRegistration.getBank().equalsIgnoreCase(bankName)) {
			rdAttr.addFlashAttribute("alert", "Invalid Credentials!");
			return "redirect:internetBankingRegistration";
		}
		m.addAttribute("userId", internetBankingRegistration.getUserId());
		m.addAttribute("bankName", internetBankingRegistration.getBank());

		return "redirect:resetPassword";
	}
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public String resetPassword(Model m) {
		if (!m.containsAttribute("resetPassword")) {
			m.addAttribute("resetPassword", new ResetPassword());
		}
		return "resetPassword";
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public String resetPassword(@Valid @ModelAttribute("resetPassword") ResetPassword resetPassword,
			BindingResult result, RedirectAttributes ra, Model m, HttpSession session, SessionStatus status) {

		if (result.hasErrors()) {
			ra.addFlashAttribute("org.springframework.validation.BindingResult.resetPassword", result);
			ra.addFlashAttribute("resetPassword", resetPassword);
			return "redirect:resetPassword";
		}

		Integer userid = (Integer) session.getAttribute("userId");
		String bankName = (String) session.getAttribute("bankName");

		if (resetPassword.getOldPassword().equals(userid.toString())
				&& resetPassword.getNewPassword().equals(resetPassword.getConfirmPassword())) {
			authenticateServiceImpl.registerForInternetBanking(userid, bankName, resetPassword);
			status.setComplete(); // will remove all the sessionAttributes
			ra.addFlashAttribute("alert", "Registration Successfull. Login to Proceed");
			ra.addFlashAttribute("color", "success");
			return "redirect:login";
		} else if (!resetPassword.getNewPassword().equals(resetPassword.getConfirmPassword())) {
			ra.addFlashAttribute("alert", "Password didn't match");
			return "redirect:resetPassword";
		}

		ra.addFlashAttribute("alert", "Something went wrong");
		return "redirect:resetPassword";
	}

	// open new account or if already have an account then opening different type of
	// account
	@RequestMapping("/register")
	public String register(Model m, HttpSession session) {

		if (!m.containsAttribute("register")) {
			m.addAttribute("register", new UserAccountRegistration());
		}

		List<Bank> banks = bankService.getAllBanks();
		m.addAttribute("banks", banks);
		m.addAttribute("title", "Registration");

		Integer userId = (Integer) session.getAttribute("userId");

		if (userId != null) {
			m.addAttribute("userId", userId);

			// if the user already have a bank then he should be able to create different
			// account type in the same bank only no other bank should be available
			List<Bank> userBank = new ArrayList<>();
			for (Bank b : banks) {
				if (b.getBankName().equalsIgnoreCase((String) m.getAttribute("bankName"))) {
					userBank.add(b);
					break;
				}
			}

			m.addAttribute("banks", userBank);

			// for fetching the account-type the user already hold in the bank
			List<Long> accountNumbersByUserId = acl.getAccountNumbersByUserId(userId);
			List<String> accType = new ArrayList<>();

			for (Long l : accountNumbersByUserId) {
				Account ac = acl.getAccountByNumber(l);
				accType.add(ac.getAccountType().toString());
			}
			m.addAttribute("accType", accType);
		}

		return "createAccount";
	}

	// get the IFSC code according to a specific bank
	@GetMapping(value = "/getIfscCodes", produces = "application/json")
	@ResponseBody
	public List<String> getIfscCodes(@RequestParam String bankName) {
		Bank bank = bankService.findBankByName(bankName);
		List<String> ifsc = new ArrayList<>();

		if (bank != null && bank.getBranches() != null) {
			for (Branch b : bank.getBranches()) {
				ifsc.add(b.getIfsc());
			}
		}

		return ifsc;
	}

	@RequestMapping(value = "/registrationSuccess", method = RequestMethod.POST)
	public String registrationSuccess(@Valid @ModelAttribute("register") UserAccountRegistration register,
			BindingResult result, Model m, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			logger.info(register.toString());
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.register", result);
			redirectAttributes.addFlashAttribute("register", register);
			return "redirect:/register";
		}

		AccountType accountType = null;
		switch (register.getAccountType()) {
		case "SAVINGS":
			accountType = AccountType.SAVINGS;
			break;
		case "INVESTMENT":
			accountType = AccountType.INVESTMENT;
			break;
		case "CHECKING":
			accountType = AccountType.CHECKING;
			break;
		default:
			accountType = AccountType.SAVINGS;
		}

		logger.info(register.toString());
		Account account = null;
		if (register.getUserId() != null) {
			account = acl.createAccount(register.getIfsc(), accountType, register.getInitialAmount(),
					register.getUserId());
			m.addAttribute("account", account);
			return "redirect:passbook";
		} else {
			account = acl.createAccount(register.getIfsc(), accountType, register.getInitialAmount(),
					register.getName(), register.getAddress(), register.getMobile(), register.getEmail(),
					UserType.Customer, register.getuIdType(), register.getuId());
		}

		redirectAttributes.addFlashAttribute("name", register.getName());
		redirectAttributes.addFlashAttribute("account", account.getAccountNumber());

		return "redirect:accountUnderVerification";
	}

	@RequestMapping("/accountUnderVerification")
	public String accountUnderVerification(Model m) {

		m.addAttribute("name", m.getAttribute("name"));
		m.addAttribute("account", m.getAttribute("account"));
		m.addAttribute("id", m.getAttribute("id"));
		return "accountUnderVerification";
	}

	@RequestMapping("/transfermoney/{userId}")
	public String transferMoney(@PathVariable("userId") Integer userId, Model model, RedirectAttributes rad) {

		if (!model.containsAttribute("userId")) {
			rad.addFlashAttribute("alert", "Login First !");
			rad.addFlashAttribute("color", "danger");
			return "redirect:/login";
		}

		if (!model.containsAttribute("transaction")) {
			model.addAttribute("transaction", new TransferMoneyDto());
		}

		List<Long> accountNumbersList = acl.getAccountNumbersByUserId(userId);
		model.addAttribute("accountNumberList", accountNumbersList);
		model.addAttribute("title", "Money Transfer");
		model.addAttribute("userID", userId);

		return "Transfer";
	}

	@RequestMapping(value = "/processtransaction", method = RequestMethod.POST)
	public String processTransaction(@Valid @ModelAttribute("transaction") TransferMoneyDto transaction,
			BindingResult result, @RequestParam("userId") int userId, RedirectAttributes rad, Model m) {

		if (result.hasErrors()) {
			rad.addFlashAttribute("org.springframework.validation.BindingResult.transaction", result);
			rad.addFlashAttribute("transaction", transaction);
			return "redirect:transfermoney/" + userId;
		}
		String bankName = (String) m.getAttribute("bankName");

		OnlineBankingLoginDetails user = authenticateServiceImpl.getUserDetailsByIdandBankName(userId, bankName);

		if (transaction.getFromAccountNumber().equals(transaction.getToAccountNumber())) {
			rad.addFlashAttribute("alert", "Sender and Receivers Cant be same");
			rad.addFlashAttribute("color", "danger");
			return "redirect:transfermoney/" + userId;
		}

		if (user.gettPin() != transaction.gettPin()) {
			rad.addFlashAttribute("alert", "Invalid Tpin");
			rad.addFlashAttribute("color", "danger");
			return "redirect:transfermoney/" + userId;
		}

		Account fromAccount = acl.getAccountByNumber(transaction.getFromAccountNumber());
		Account toAccount = acl.getAccountByNumber(transaction.getToAccountNumber());

		if (transaction.getAmount() > fromAccount.getBalance()) {
			rad.addFlashAttribute("alert", "Insufficient Balance!");
			rad.addFlashAttribute("color", "danger");
			return "redirect:transfermoney/" + userId;
		}

		acl.transferFunds(fromAccount, transaction.getIFSC(), toAccount, transaction.getAmount(),
				ModeOfTransaction.Online_Banking);

		return "redirect:transactionsuccess";
	}

	@RequestMapping("/transactionsuccess")
	public String transactionSAuccess(Model m, RedirectAttributes rad) {
		m.addAttribute("title", "Transaction Success");

		if (!m.containsAttribute("userId")) {
			rad.addFlashAttribute("alert", "Login First !");
			rad.addFlashAttribute("color", "danger");
			return "redirect:login";
		}
		return "TransactionSuccess";
	}

	@RequestMapping("/alltransactions")
	public String allTransactions(Model m) {

		Integer userId = (Integer) m.getAttribute("userId");

		if (userId != null) {
			List<Long> accountNumbersByUserId = acl.getAccountNumbersByUserId(userId);

			List<List<Transaction>> transactions = new ArrayList<>();
			for (Long l : accountNumbersByUserId) {
				transactions.add(acl.allTranscations(l));
			}

			m.addAttribute("transactions", transactions);

			logger.info(transactions.toString());

		}
		return "allTransactions";
	}

	@RequestMapping(value = "/passbook")

	public String passbook(Model m, RedirectAttributes rad) {

		Integer userId = (Integer) m.getAttribute("userId");
		String bankName = (String) m.getAttribute("bankName");

		if (userId == null) {
			rad.addFlashAttribute("alert", "Login First !");
			rad.addFlashAttribute("color", "danger");
			return "redirect:login";
		}
		List<Account> account = acl.getAccountsByUserId(userId);

		OnlineBankingLoginDetails user = authenticateServiceImpl.getUserDetailsByIdandBankName(userId, bankName);

		m.addAttribute("account", account.get(0));
		m.addAttribute("accountList", account);

		if (user != null) {
			m.addAttribute("tPin", user.gettPin());
		}

		return "passbook";
	}

	@RequestMapping("/cards")
	public String cards(Model m, RedirectAttributes rad) {

		Integer userId = (Integer) m.getAttribute("userId");

		if (userId == null) {
			rad.addFlashAttribute("alert", "Login First !");
			rad.addFlashAttribute("color", "danger");
			return "redirect:login";
		}

		List<List<CardDetails>> cardByUserId = acl.getCardByUserId(userId);

		User user = userService.getUserById(userId);

		m.addAttribute("cards", cardByUserId);
		m.addAttribute("userName", user.getName());

		return "cards";
	}

	@RequestMapping("/logout")
	public String logout(RedirectAttributes rad, HttpSession session, SessionStatus status) {

		rad.addFlashAttribute("alert", "Logout Successfull");
		rad.addFlashAttribute("color", "success");
		session.invalidate();
		status.setComplete();

		return "redirect:login";
	}
}
