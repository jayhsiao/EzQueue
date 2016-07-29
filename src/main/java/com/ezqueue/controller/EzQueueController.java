package com.ezqueue.controller;

import java.util.Map;

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
	
	@RequestMapping(value = "/createQueue/{userId}", method = RequestMethod.GET)
	public String createQueue(Model model, @PathVariable String userId) {
		model.addAttribute("RESPONSE_MAP", ezQueueService.createQueue(userId));
        return "create";
	}
	
	@RequestMapping(value = "/all/{userId}", method = RequestMethod.GET)
	public String getAllQueues(Model model, @PathVariable String userId, 
			@RequestParam(value = "queueTypeId") String queueTypeId,
			@RequestParam(value = "limit", required = false, defaultValue = "limit") int limit,
			@RequestParam(value = "offset", required = false, defaultValue = "offset") int offset) {
		model.addAttribute("RESPONSE_MAP", ezQueueService.getAllQueues(userId, queueTypeId, limit, offset));
        return "queue_list";
	}
	
	@RequestMapping(value = "/myQueues/{userId}", method = RequestMethod.GET)
	public String getMyQueues(Model model, @PathVariable String userId, 
			@RequestParam(value = "limit", required = false, defaultValue = "limit") int limit,
			@RequestParam(value = "offset", required = false, defaultValue = "offset") int offset) {
		model.addAttribute("RESPONSE_MAP", ezQueueService.getMyQueues(userId, limit, offset));
        return "queue_list";
	}
	
	@RequestMapping(value = "/promotion/{userId}", method = RequestMethod.GET)
	public String getPromotionsQueues(Model model, @PathVariable String userId, 
			@RequestParam(value = "limit", required = false, defaultValue = "limit") int limit,
			@RequestParam(value = "offset", required = false, defaultValue = "offset") int offset) {
		model.addAttribute("RESPONSE_MAP", ezQueueService.getPromotionQueues(userId, limit, offset));
        return "queue_list";
	}
	
	@RequestMapping(value = "/queuing/{userId}", method = RequestMethod.GET)
	public String getQueuing(Model model, @PathVariable String userId, 
			@RequestParam(value = "limit", required = false, defaultValue = "limit") int limit,
			@RequestParam(value = "offset", required = false, defaultValue = "offset") int offset) {
		model.addAttribute("RESPONSE_MAP", ezQueueService.getQueuingQueues(userId, limit, offset));
        return "queue_list";
	}
	
	@RequestMapping(value = "/favorite/{userId}", method = RequestMethod.GET)
	public String getFavorite(Model model, @PathVariable String userId, 
			@RequestParam(value = "limit", required = false, defaultValue = "limit") int limit,
			@RequestParam(value = "offset", required = false, defaultValue = "offset") int offset) {
		model.addAttribute("RESPONSE_MAP", ezQueueService.getFavoriteQueues(userId, limit, offset));
        return "queue_list";
	}
	
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
	public String getUserQueues(Model model, @PathVariable String userId, 
			@RequestParam(value = "limit", required = false, defaultValue = "limit") int limit,
			@RequestParam(value = "offset", required = false, defaultValue = "offset") int offset) {
		model.addAttribute("RESPONSE_MAP", ezQueueService.getUserQueues(userId, limit, offset));
        return "queue_list";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String getSearch(Model model, @RequestParam Map<String, Object> map, 
			@RequestParam(value = "userId") String userId,
			@RequestParam(value = "text") String text,
			@RequestParam(value = "limit", required = false, defaultValue = "limit") int limit,
			@RequestParam(value = "offset", required = false, defaultValue = "offset") int offset) {
		model.addAttribute("RESPONSE_MAP", ezQueueService.getSearchQueues(userId, text, limit, offset));
        return "queue_list";
	}
	
	@RequestMapping(value = "/queue", method = RequestMethod.GET)
	public String getQueueDetail(Model model, 
			@RequestParam(value = "userId") String userId, 
			@RequestParam(value = "queueId") String queueId, 
			@RequestParam(value = "canEdit") boolean canEdit) {
		model.addAttribute("RESPONSE_MAP", ezQueueService.getQueueDetail(userId, queueId, canEdit));
        return "queue_detail";
	}
	
}
