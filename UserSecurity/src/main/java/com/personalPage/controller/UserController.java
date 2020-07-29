package com.personalPage.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.personalPage.model.ConfirmationToken;
import com.personalPage.model.User;
import com.personalPage.repository.TokenRepository;
import com.personalPage.service.EmailSenderService;
import com.personalPage.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private TokenRepository tokenRepository;

	@Autowired
	private EmailSenderService emailSenderService;

	@ModelAttribute("user")
	public User userRegistrationDto() {
		return new User();
	}

	public String showRegistrationForm(Model model) {
		User user = new User();
		model.addAttribute("user",user);
		return "registration";
	}

	@PostMapping("/registration")
	public String registerUserAccount(@ModelAttribute("user") Model modelAndView, @Valid User userDto,
			BindingResult result) {

		User existing = userService.findByEmail(userDto.getEmail());
		if (existing != null) {
			result.rejectValue("email", null, "There is already an account registered with that email");
		}

		if (result.hasErrors()) {
			return "registration";
		}

		userService.save(userDto);

		ConfirmationToken confirmationToken = new ConfirmationToken(userDto);

		tokenRepository.save(confirmationToken);

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(userDto.getEmail());
		mailMessage.setSubject("Complete Registration!");
		mailMessage.setFrom("chand312902@gmail.com");
		mailMessage.setText("To confirm your account, please click here : "
				+ "http://localhost:8080/confirm-account?token=" + confirmationToken.getConfirmationToken());

		emailSenderService.sendEmail(mailMessage);
		
		modelAndView.addAttribute("user",userDto);
//		modelAndView.addObject("emailId", userDto.getEmail());
//
//		modelAndView.setViewName("successfulRegisteration");

		return "redirect:/registration?success";
	}

//	@RequestMapping(value = "/confirm-account", method = { RequestMethod.GET, RequestMethod.POST })
//	public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token") String confirmationToken) {
//		ConfirmationToken token = tokenRepository.findByConfirmationToken(confirmationToken);
//
//		if (token != null) {
//			User user = userService.findByEmail(token.getUser().getEmail());
//			user.setEnabled(true);
//			userService.save(user);
//			modelAndView.setViewName("accountVerified");
//		} else {
//			modelAndView.addObject("message", "The link is invalid or broken!");
//			modelAndView.setViewName("error");
//		}
//
//		return modelAndView;
//	}

	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable(value = "id") long id) {

		userService.deleteUserById(id);
		return "redirect:/";

	}

}
