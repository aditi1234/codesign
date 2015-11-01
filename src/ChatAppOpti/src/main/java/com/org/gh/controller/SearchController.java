package com.org.gh.controller;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.org.gh.daoImpl.MongoDaoImpl;

@RestController
public class SearchController {

	@RequestMapping("/sendlocation")
	public ModelAndView getLocation(@ModelAttribute String selectedLoc, ServletRequest req, ServletResponse res) {
		
		String wordCloudData = MongoDaoImpl.getInstance().getWordCloud(selectedLoc);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("wordCloudData", wordCloudData);
		modelAndView.addObject("dashboard");
		return modelAndView;
		
	}
	
	@RequestMapping(value="/getList/{key}", produces={MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.GET)
	public ModelAndView getHistory(@PathVariable String key) {
		
		final String uri="http://localhost:8080/springtestexample/"+key;
		
		RestTemplate restTemplate = new RestTemplate();
		String history = restTemplate.getForObject(uri, String.class);
		
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("history", history);
		modelAndView.addObject("dashboard");
		
		return modelAndView;
	}
	
}
