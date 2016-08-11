package com.ezqueue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezqueue.service.EzQueueService;

@Controller
@RequestMapping(value = "/ezqueue")
public class EzQueueController extends BaseController {
	
	@Autowired
	private EzQueueService ezQueueService;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model) throws Exception{
		model.addAttribute("RESPONSE_MAP", ezQueueService.init());
        return "main";
	}
	
	@RequestMapping(value = "/home/{queueId}", method = RequestMethod.GET)
	public String queueDetailHome(Model model, @PathVariable String queueId) throws Exception{
		model.addAttribute("QUEUE_DETAIL_ID", queueId);
        return this.home(model);
	}
	
	@RequestMapping(value = "/me/{userId}", method = RequestMethod.GET)
	public String getMyQueues(Model model, 
			@PathVariable String userId, 
			@RequestParam(value = "limit", required = false, defaultValue = "limit") int limit,
			@RequestParam(value = "offset", required = false, defaultValue = "offset") int offset) {
		model.addAttribute("RESPONSE_LIST", ezQueueService.getMyQueues(userId, limit, offset));
        return "queue_list";
	}
	
	@RequestMapping(value = "/type/{queueTypeId}", method = RequestMethod.GET)
	public String getAllQueues(Model model, @PathVariable String queueTypeId,
			@RequestParam(value = "limit", required = false, defaultValue = "limit") int limit,
			@RequestParam(value = "offset", required = false, defaultValue = "offset") int offset) {
		model.addAttribute("RESPONSE_LIST", ezQueueService.getTypeQueues(queueTypeId, limit, offset));
        return "queue_list";
	}
	
	@RequestMapping(value = "/promotion", method = RequestMethod.GET)
	public String getPromotionsQueues(Model model, 
			@RequestParam(value = "limit", required = false, defaultValue = "limit") int limit,
			@RequestParam(value = "offset", required = false, defaultValue = "offset") int offset) {
		model.addAttribute("RESPONSE_LIST", ezQueueService.getPromotionQueues(limit, offset));
        return "queue_list";
	}
	
	@RequestMapping(value = "/favorite/{userId}", method = RequestMethod.GET)
	public String getFavorite(Model model, 
			@PathVariable String userId, 
			@RequestParam(value = "limit", required = false, defaultValue = "limit") int limit,
			@RequestParam(value = "offset", required = false, defaultValue = "offset") int offset) {
		model.addAttribute("RESPONSE_LIST", ezQueueService.getFavoriteQueues(userId, limit, offset));
        return "queue_list";
	}
	
	@RequestMapping(value = "/queuing/{userId}", method = RequestMethod.GET)
	public String getQueuing(Model model, 
			@PathVariable String userId, 
			@RequestParam(value = "limit", required = false, defaultValue = "limit") int limit,
			@RequestParam(value = "offset", required = false, defaultValue = "offset") int offset) {
		model.addAttribute("RESPONSE_LIST", ezQueueService.getQueuingQueues(userId, limit, offset));
        return "queue_list";
	}
	
	@RequestMapping(value = "/search/{text}", method = RequestMethod.GET)
	public String getSearch(Model model, @PathVariable String text,
			@RequestParam(value = "limit", required = false, defaultValue = "limit") int limit,
			@RequestParam(value = "offset", required = false, defaultValue = "offset") int offset) {
		model.addAttribute("RESPONSE_LIST", ezQueueService.getSearchQueues(text, limit, offset));
        return "queue_list";
	}
	
	@RequestMapping(value = "/create/{userId}", method = RequestMethod.GET)
	public String createQueue(Model model, @PathVariable String userId) {
		model.addAttribute("RESPONSE_LIST", ezQueueService.createQueue(userId));
        return "create";
	}
	
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
	public String getUserQueues(Model model, @PathVariable String userId, 
			@RequestParam(value = "limit", required = false, defaultValue = "limit") int limit,
			@RequestParam(value = "offset", required = false, defaultValue = "offset") int offset) {
		model.addAttribute("RESPONSE_LIST", ezQueueService.getUserQueues(userId, limit, offset));
        return "queue_list";
	}
	
	@RequestMapping(value = "/queue/detail", method = RequestMethod.GET)
	public String getQueueDetail(Model model, 
			@RequestParam(value = "userId", required = false) String userId, 
			@RequestParam(value = "queueId") String queueId) {
		model.addAttribute("RESPONSE_MAP", ezQueueService.getQueueDetail(userId, queueId));
        return "queue_detail";
	}
	
}
