package com.ximuyi.demo.jsp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/webjsp")
public class WebJspController {

	private static final Logger logger = LoggerFactory.getLogger(WebJspController.class);

	@RequestMapping(value = "/index")
	public String index(ModelMap map) {
		map.put("title", "JSP Controller.");
		map.put("content", "@RestController注解相当于@ResponseBody＋@Controller合在一起的作用.");
		return "index";
	}
}

