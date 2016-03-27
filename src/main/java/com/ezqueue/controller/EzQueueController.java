package com.ezqueue.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezqueue.model.User;
import com.ezqueue.service.EzQueueService;

@Controller
@RequestMapping(value = "/ezQueue")
public class EzQueueController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private EzQueueService ezQueueService;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home() throws Exception{
        return "home";
	}
	
	@RequestMapping(value = "/init/{userId}", method = RequestMethod.GET)
	public String init(Model model, @PathVariable String userId) throws Exception{
		Map<String, Object> responseMap = ezQueueService.init(userId);
		model.addAttribute("RESPONSE_MAP", responseMap);
		return "main";
	}
	
	@RequestMapping(value = "/createQueue", method = RequestMethod.GET)
	public String createQueue(Model model) throws Exception{
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("isCreate", true);
		model.addAttribute("RESPONSE_MAP", responseMap);
        return "queue";
	}
	
	@RequestMapping(value = "/myQueues/{id}", method = RequestMethod.GET)
	public String getMyQueues(Model model, @PathVariable String userId) throws Exception{
		User user = new User();
		user.setUserId(userId);
		
		Map<String, Object> responseMap = ezQueueService.getMyQueues(user);
		responseMap.put("isMyQueues", true);
		model.addAttribute("RESPONSE_MAP", responseMap);
        return "queue";
	}
	
	@RequestMapping(value = "/promotionQueues", method = RequestMethod.GET)
	public String getPromotionsQueues(Model model) throws Exception{
		Map<String, Object> responseMap = ezQueueService.getPromotionQueues();
		responseMap.put("isPromotion", true);
		model.addAttribute("RESPONSE_MAP", responseMap);
        return "queue";
	}
}
