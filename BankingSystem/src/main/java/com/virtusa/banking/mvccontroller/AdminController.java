package com.virtusa.banking.mvccontroller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.virtusa.banking.dto.AddBranchDto;
import com.virtusa.banking.dto.AdminLoginDto;
import com.virtusa.banking.dto.RegisterBankDto;
import com.virtusa.banking.dto.UpdateBranchDto;
import com.virtusa.banking.model.Admin;
import com.virtusa.banking.model.Bank;
import com.virtusa.banking.model.User;
import com.virtusa.banking.service.AdminService;
import com.virtusa.banking.service.AuthenticateService;
import com.virtusa.banking.service.BankService;
import com.virtusa.banking.service.BranchService;
import com.virtusa.banking.service.UserService;

@Controller
@SessionAttributes({ "userId", "bankName", "userType" })
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AuthenticateService authenticateService;

	@Autowired
	private AdminService adminService;

	@Autowired
	private BankService bankService;

	@Autowired
	private BranchService branchService;

	@Autowired
	private UserService userService;

	@GetMapping("/adminlogin")
	public String adminLogin(Model model) {

		if (!model.containsAttribute("adminlogin")) {
			model.addAttribute("adminlogin", new AdminLoginDto());
		}

		return "admin/admin-login";
	}

	@PostMapping(value = "/adminlogin")
	public String loginSuccess(@Valid @ModelAttribute("adminlogin") AdminLoginDto adminlogin, BindingResult result,
			RedirectAttributes rdrctAttr, Model model, HttpSession session) {
		if (result.hasErrors()) {
			rdrctAttr.addFlashAttribute("org.springframework.validation.BindingResult.adminlogin", result);
			rdrctAttr.addFlashAttribute("adminlogin", adminlogin);
			return "redirect:adminlogin";
		}
		Admin admin = adminService.getAdminByAdminId(adminlogin.getAdminId());
		if (admin != null && !admin.getUserType().equalsIgnoreCase("admin")) {
			model.addAttribute("errorMessage", "user lack privilages for this request, user is not an admin");
			return "error-page";
		}
		int success = authenticateService.adminLogin(adminlogin.getAdminId(), adminlogin.getPassword());
		if (success != 0) {
			model.addAttribute("userId", adminlogin.getAdminId());
			model.addAttribute("userType", "admin");
			return "redirect:home";
		} else {
			rdrctAttr.addFlashAttribute("alert", "Invalid Credentials...!");
			rdrctAttr.addFlashAttribute("color", "danger");
			return "redirect:adminlogin";
		}
	}

	@GetMapping("/home")
	public String adminHome(HttpSession session, Model model) {
		if (!isUserAdmin(session)) {
			model.addAttribute("errorMessage", "User lack privilages for this request, user is not an admin");
			return "error-page";
		}
		return "admin/admin-home";
	}

	@GetMapping(value = "/bank/enterBankId")
	public String serveEnterBankIdPage(HttpSession session, Model model) {
		if (!isUserAdmin(session)) {
			model.addAttribute("errorMessage", "User lack privileges for this request, user is not an admin");
			return "error-page";
		}
		return "admin/enter-bank-id";
	}

	@GetMapping(value = "/bank/findByBankId/{bankId}")
	public String getBankById(@PathVariable("bankId") Long bankId, HttpSession session, Model model) {

		if (!isUserAdmin(session)) {
			model.addAttribute("errorMessage", "User lack privilages for this request, user is not an admin");
			return "error-page";
		}
		Bank bank = bankService.getBankById(bankId);
		if (bank == null) {
			model.addAttribute("errorMessage", "No bank with bank id = " + bankId + " found.");
			return "error-page";
		}
		model.addAttribute("bank", bank);
		return "admin/single-bank";
	}

	@GetMapping(value = "/banks")
	public String getAllBanks(HttpSession session, Model model) {
		if (!isUserAdmin(session)) {
			model.addAttribute("errorMessage", "user lack privilages for this request, user is not an admin");
			return "error-page";
		}
		List<Bank> banks = bankService.getAllBanks();
		if (banks.isEmpty()) {
			model.addAttribute("errorMessage", "There is no bank in database.");
			return "error-page";
		}
		model.addAttribute("banks", banks);
		return "admin/all-bank";
	}

	@GetMapping(value = "/banks/findByName")
	public String serveFindBankByNamePage(HttpSession session, Model model) {
		if (!isUserAdmin(session)) {
			model.addAttribute("errorMessage", "user lack privileges for this request, user is not an admin");
			return "error-page";
		}

		if (!model.containsAttribute("bankName")) {
			model.addAttribute("bankName", new String(""));
		}

		return "admin/get-bank-by-name";
	}

	@GetMapping(value = "/banks/findByName/{bankName}")
	public String getBankByName(@PathVariable("bankName") String bankName, HttpSession session, Model model) {
		if (!isUserAdmin(session)) {
			model.addAttribute("errorMessage", "user lack privilages for this request, user is not an admin");
			return "error-page";
		}

		Bank bank = bankService.findBankByName(bankName);

		if (bank == null) {
			model.addAttribute("errorMessage", "No bank with bank name = " + bankName + " found.");
			return "error-page";
		}
		model.addAttribute("bank", bank);
		return "admin/single-bank";
	}

	@GetMapping(value = "/registerBank")
	public String serveCreateBankPage(HttpSession session, Model model) {

		if (!isUserAdmin(session)) {
			model.addAttribute("errorMessage", "user lack privilages for this request, user is not an admin");
			return "error-page";
		}

		if (!model.containsAttribute("registerBankDto")) {
			model.addAttribute("registerBankDto", new RegisterBankDto());
		}

		return "admin/create-bank";
	}

	@PostMapping(value = "/createBank")
	public String createBank(@Valid @ModelAttribute("registerBankDto") RegisterBankDto registerBankDto,
			BindingResult result, RedirectAttributes rdAttr, HttpSession session, Model model) {

		if (!isUserAdmin(session)) {
			model.addAttribute("errorMessage", "user lack privilages for this request, user is not an admin");
			return "error-page";
		}

		if (result.hasErrors()) {
			rdAttr.addFlashAttribute("org.springframework.validation.BindingResult.registerBankDto", result);
			rdAttr.addFlashAttribute("registerBankDto", registerBankDto);

			return "redirect:registerBank";
		}

		boolean created = bankService.createBank(registerBankDto.getBankName());

		if (!created) {
			model.addAttribute("errorMessage", "Bank creation failed , Something went wrong");
			return "error-page";
		}
		return "redirect:createBank/success";
	}

	@GetMapping("/createBank/success")
	public String bankCreated(HttpSession session, Model model) {
		if (!isUserAdmin(session)) {
			model.addAttribute("errorMessage", "user lack privilages for this request, user is not an admin");
			return "error-page";
		}
		return "admin/bank-created-page";
	}

	@GetMapping("/verifyAccount")
	public String verifyAccount(Model m) {

		String userType = (String) m.getAttribute("userType");
		if (m.containsAttribute("userType") && !userType.isEmpty() && userType.equalsIgnoreCase("admin")) {

			List<User> users = userService.getInactiveUsers();

			if (users.isEmpty()) {
				m.addAttribute("NoUser", "No Pending request found !");
			}

			m.addAttribute("users", users);

			return "verifyAccount";
		}
		m.addAttribute("errorMessage", "UnAuthorised Access!");
		return "error-page";
	}

	@PostMapping(value = "/approveUser")
	@ResponseBody
	public String approveUser(@RequestParam("userId") int userId) {

		User user = userService.getUserById(userId);

		user.setStatus("Active");

		boolean update = userService.updateUser(user);

		return update ? "success" : "failure";
	}

	@GetMapping(value = "/bank/addBranch")
	public String serveAddBranchPage(HttpSession session, Model model) {

		if (!isUserAdmin(session)) {
			model.addAttribute("errorMessage", "user lack privilages for this request, user is not an admin");
			return "error-page";
		}

		if (!model.containsAttribute("addBranchDto")) {
			model.addAttribute("addBranchDto", new AddBranchDto());
		}

		List<Bank> allBanks = bankService.getAllBanks();
		model.addAttribute("allBanks", allBanks);

		return "admin/add-branch-page";
	}

	@PostMapping(value = "/bank/addBranch")
	public String addBranch(@Valid @ModelAttribute("addBranchDto") AddBranchDto addBranchDto, BindingResult result,
			RedirectAttributes rdAttr, HttpSession session, Model model) {

		if (!isUserAdmin(session)) {
			model.addAttribute("errorMessage", "user lack privilages for this request, user is not an admin");
			return "error-page";
		}
		if (result.hasErrors()) {
			rdAttr.addFlashAttribute("org.springframework.validation.BindingResult.addBranchDto", result);
			rdAttr.addFlashAttribute("addBranchDto", addBranchDto);

			return "redirect:addBranch";
		}
		branchService.addBranch(addBranchDto);

		return "admin/add-branch-success";
	}

	@GetMapping(value = "/bank/updateBranch")
	public String serveUpdateBranchPage(HttpSession session, Model model) {

		if (!isUserAdmin(session)) {
			model.addAttribute("errorMessage", "user lack privilages for this request, user is not an admin");
			return "error-page";
		}

		if (!model.containsAttribute("updateBranchDto")) {
			model.addAttribute("updateBranchDto", new UpdateBranchDto());
		}

		List<Bank> allBanks = bankService.getAllBanks();
		model.addAttribute("allBanks", allBanks);

		return "admin/branch-update-page";
	}	

	@PostMapping(value = "bank/updateBranch")
	public String updateBranch(@Valid @ModelAttribute("updateBranchDto") UpdateBranchDto updateBranchDto,
			BindingResult result, RedirectAttributes rdAttr, HttpSession session, Model model) {

		if (!isUserAdmin(session)) {
			model.addAttribute("errorMessage", "user lack privilages for this request, user is not an admin");
			return "error-page";
		}

		if (result.hasErrors()) {
			rdAttr.addFlashAttribute("org.springframework.validation.BindingResult.updateBranchDto", result);
			rdAttr.addFlashAttribute("updateBranchDto", updateBranchDto);

			return "redirect:bank/updateBranch";
		}

		branchService.updateBranch(updateBranchDto);

		return "admin/branch-update-success";
	}

	@GetMapping(value = "bank/deleteBranchForm")
	public String serveDeleteBranch(HttpSession session, Model model) {

		if (!isUserAdmin(session)) {
			model.addAttribute("errorMessage", "user lack privilages for this request, user is not an admin");
			return "error-page";
		}
		return "admin/branch-delete-page";
	}

	@PostMapping(value = "bank/deleteBranch")
	public String deleteBranch(@RequestParam("branchIfsc") String branchIfsc, HttpSession session, Model model) {

		if (!isUserAdmin(session)) {
			model.addAttribute("errorMessage", "user lack privilages for this request, user is not an admin");
			return "error-page";
		}
		branchService.deleteBranch(branchIfsc);

		return "admin/branch-delete-success";
	}

	private boolean isUserAdmin(HttpSession session) {
		String userType = (String) session.getAttribute("userType");
		boolean res = true;
		if (userType == null || !userType.equalsIgnoreCase("admin"))
			res = false;
		return res;
	}

	@GetMapping("/logout")
	public String logout(RedirectAttributes rad, HttpSession session, SessionStatus status) {

		rad.addFlashAttribute("alert", "Logout Successfull");
		rad.addFlashAttribute("color", "success");
		session.invalidate();
		status.setComplete();

		return "redirect:adminlogin";
	}
}
