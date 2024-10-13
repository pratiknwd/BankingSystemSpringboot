package com.virtusa.banking.mvccontroller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.virtusa.banking.dto.CreditCardApplicationDto;
import com.virtusa.banking.model.CardDetails;
import com.virtusa.banking.model.User;
import com.virtusa.banking.service.AccountServiceImpl;
import com.virtusa.banking.service.UserService;

@Controller
public class FinancialController {

	@Autowired
	private UserService userService;

	@Autowired
	private AccountServiceImpl acl;

	@RequestMapping("/creditCards")
	public String creditCards() {
		return "creditCards";
	}

	@RequestMapping("/sipcalculator")
	public String sipCalculator() {
		return "sipcalculator";
	}

	@RequestMapping(value = "/apply4creditcard", method = RequestMethod.GET)
	public String applyForCreditCard(@RequestParam("id") int id, Model m, HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");
		String cardType = "";
		if (id == 1) {
			cardType = "PLATINUM";
		} else {
			cardType = "MasterCard";
		}
		if (userId != null) {
			User user = userService.getUserById(userId);

			m.addAttribute("user", new CreditCardApplicationDto(user.getName(), user.getEmail(), user.getPhoneNo(),
					user.getAddress(), cardType, user.getuIdType(), user.getuId()));
		}

		if (!m.containsAttribute("user")) {
			m.addAttribute("user", new CreditCardApplicationDto());
		}

		m.addAttribute("cardId", id);
		m.addAttribute("cardType", cardType);
		return "creditCardForm";
	}

	@RequestMapping(value = "/apply4creditcard", method = RequestMethod.POST)
	public String applyForCreditCard(@Valid @ModelAttribute("user") CreditCardApplicationDto user, BindingResult result,
			@RequestParam("id") int id, RedirectAttributes rad, Model m, HttpSession session) {

		if (result.hasErrors()) {
			rad.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
			rad.addFlashAttribute("user", user);
			rad.addAttribute("id", id);
			return "redirect:apply4creditcard";
		}

		CardDetails creditCards = acl.applyForCreditCards((Integer) session.getAttribute("userId"),user.getCardType());

		m.addAttribute("number", creditCards.getAccount().getAccountNumber());
		m.addAttribute("name", user.getName());

		return "creditCardApplicationUnderProgress";
	}
}
