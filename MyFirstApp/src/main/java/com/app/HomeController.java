package com.app;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.app.model.LoginResponse;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("login");

		logger.info("/login request mapping.");

		return model;

	}
	
	
	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response) {


		ModelAndView model = new ModelAndView("login");

		String frCode = request.getParameter("uname");
		String frPassword = request.getParameter("pass");

		// fr login
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("frCode", frCode);
		map.add("frPasswordKey", frPassword);

		RestTemplate restTemplate = new RestTemplate();
     LoginResponse loginResponse = restTemplate.postForObject("http://localhost:8077/loginFr", map,
				LoginResponse.class);

		System.out.println("Login Response " + loginResponse.toString());

		if (loginResponse.getErrorMessage().getError()) {
			
			model = new ModelAndView("errorPage");
			model.addObject("message", loginResponse.getErrorMessage().getMessage());

		} else {
			
			model = new ModelAndView("success");
			model.addObject("message", loginResponse.getErrorMessage().getMessage());

		}
	return model;
}
}
