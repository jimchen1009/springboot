package com.ximuyi.demo.form.validation;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@Controller()
@RequestMapping(value = "/form/validation")
public class WebController implements WebMvcConfigurer {

	private static final String Location = "form/validation/";

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController(addLoaction("results")).setViewName(addLoaction("results"));
	}

	@GetMapping("/")
	public String showForm(PersonForm personForm) {
		return addLoaction("validation");
	}

	@PostMapping("/")
	public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return addLoaction("validation");
		}
		else {
			return  "redirect:/" + addLoaction("results");
		}
	}

	private String addLoaction(String filename){
		return Location + filename;
	}
}
