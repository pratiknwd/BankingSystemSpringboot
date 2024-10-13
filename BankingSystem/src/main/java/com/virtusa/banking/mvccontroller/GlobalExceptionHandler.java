package com.virtusa.banking.mvccontroller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.virtusa.banking.exception.CustomDatabaseException;
import com.virtusa.banking.exception.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({SQLException.class,CustomDatabaseException.class})
	public String handleSQLException(HttpServletRequest request, Exception ex, Model model) {
		logger.error("Database Exception Occured:: URL=" + request.getRequestURL(), ex);
		model.addAttribute("url", request.getRequestURL());
		model.addAttribute("exception", ex);
		return "exception/exception-page";
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ResourceNotFoundException.class)
	public String handleResourceNotFoundException(HttpServletRequest request, Exception ex, Model model) {
		logger.error("Resorce NOt Found Exception Occured:: URL=" + request.getRequestURL(), ex);
		model.addAttribute("url", request.getRequestURL());
		model.addAttribute("exception", ex);
		return "exception/exception-page";
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = NullPointerException.class)
	public String nullpointer(HttpServletRequest request, Exception ex,Model m) {
		ex.printStackTrace();
		m.addAttribute("status", HttpStatus.BAD_REQUEST.value());
		return "exception";
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = Exception.class)
	public String exception(HttpServletRequest request, Exception ex,Model m) {
		ex.printStackTrace();
		m.addAttribute("status", HttpStatus.BAD_REQUEST.value());
		return "exception";
	}
}
