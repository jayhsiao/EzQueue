package com.ezqueue.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezqueue.model.User;
import com.ezqueue.service.EzQueueService;
import com.ezqueue.util.StringUtil;

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
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup() throws Exception{
        return "signup";
	}
	
	@RequestMapping(value = "/doSignup", method = RequestMethod.POST)
	public String doSignup(Model model, @RequestBody Map<String, Object> map) throws Exception{
		User user = new User();
		user.setId(StringUtil.getUUID());
		user.setUserId((String) map.get("userId"));
		user.setName((String) map.get("name"));
		user.setPassword((String) map.get("password"));
		
		Map<String, Object> responseMap = ezQueueService.signup(user);
		model.addAttribute("RESPONSE_MAP", responseMap);
        return "main";
	}
	
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String signin() throws Exception{
		return "signin";
	}
	
	@RequestMapping(value = "/doSignin", method = RequestMethod.POST)
	public String doSignin(Model model, @RequestBody User user) throws Exception{
		Map<String, Object> responseMap = ezQueueService.signin(user.getUserId(), user.getPassword());
		model.addAttribute("RESPONSE_MAP", responseMap);
        return "main";
	}
	
	@RequestMapping(value = "/createQueue", method = RequestMethod.GET)
	public String createQueue(Model model) throws Exception{
        return "createQueue";
	}
	
	@RequestMapping(value = "/myQueues/{userId}", method = RequestMethod.GET)
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
