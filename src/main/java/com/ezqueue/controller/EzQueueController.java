package com.ezqueue.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezqueue.service.EzQueueService;
import com.ezqueue.util.EzQueueConstants;

@Controller
@RequestMapping(value = "/ezqueue")    
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
	
	@RequestMapping(value = "/createQueue/{userId}", method = RequestMethod.GET)
	public String createQueue(Model model, @PathVariable String userId) throws Exception{
		model.addAttribute("RESPONSE_MAP", ezQueueService.createQueue(userId));
        return "create";
	}
	
	@RequestMapping(value = "/myQueues/{userId}", method = RequestMethod.GET)
	public String getMyQueues(Model model, @PathVariable String userId) throws Exception{
		model.addAttribute("RESPONSE_MAP", ezQueueService.getMyQueues(userId, 0, EzQueueConstants.PAGE_SIZE));
        return "queue";
	}
	
	@RequestMapping(value = "/promotion/{userId}", method = RequestMethod.GET)
	public String getPromotionsQueues(Model model, @PathVariable String userId) throws Exception{
		model.addAttribute("RESPONSE_MAP", ezQueueService.getPromotionQueues(userId, 0, EzQueueConstants.PAGE_SIZE));
        return "queue";
	}
	
	@RequestMapping(value = "/queuing/{userId}", method = RequestMethod.GET)
	public String getQueuing(Model model, @PathVariable String userId) throws Exception{
		model.addAttribute("RESPONSE_MAP", ezQueueService.getQueuingQueues(userId, 0, EzQueueConstants.PAGE_SIZE));
        return "queue";
	}
	
	@RequestMapping(value = "/favorite/{userId}", method = RequestMethod.GET)
	public String getFavorite(Model model, @PathVariable String userId) throws Exception{
		model.addAttribute("RESPONSE_MAP", ezQueueService.getFavoriteQueues(userId, 0, EzQueueConstants.PAGE_SIZE));
        return "queue";
	}
}
