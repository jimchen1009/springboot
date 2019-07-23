package com.ximuyi.demo.form.submisson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/form/submission")
public class GreetingController {

	private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

	/**
	 * The greetingForm() method uses a Model object to expose a new Greeting to the view template.
	 * The Greeting object in the following code contains fields such as id and content that correspond to the form fields in the greeting view,
	 * and will be used to capture the information from the form.
	 * @param model
	 * @return
	 */
	@GetMapping("/greeting")
	public String greetingForm(Model model) {
		model.addAttribute("greeting", new Greeting());
		return "/form/submission/greeting";
	}

	/***
	 * The greetingSubmit() method receives the Greeting object that was populated by the form.
	 * The Greeting is a @ModelAttribute so it is bound to the incoming form content,
	 * and also the submitted data can be rendered in the result view by referring to it by name
	 * (the name of the method parameter by default, so "greeting" in this case).
	 * The id is rendered in the <p th:text="'id: ' + ${greeting.id}" /> expression. Likewise the content is rendered in the <p th:text="'content: ' + ${greeting.content}" /> expression.
	 * @param greeting
	 * @return
	 */
	@PostMapping("/greeting")
	public String greetingSubmit(@ModelAttribute Greeting greeting) {
		logger.info("greeting: {}", greeting);
		return "/form/submission/result";
	}
}
