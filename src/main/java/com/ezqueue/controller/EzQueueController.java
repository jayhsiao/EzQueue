package com.ezqueue.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezqueue.service.EzQueueService;
import com.ezqueue.util.EzQueueConstants;

@Controller
@RequestMapping(value = "/ezqueue")
public class EzQueueController extends BaseController {
	
	@Value("${env.url}")
	private String envUrl;
	
	@Autowired
	private EzQueueService ezQueueService;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("RESPONSE", ezQueueService.init());
        return EzQueueConstants.PAGE_MAIN;
	}
	
	@RequestMapping(value = "/home/{queueId}", method = RequestMethod.GET)
	public String queueDetailHome(Model model, @PathVariable String queueId) {
		model.addAttribute("QUEUE_DETAIL_ID", queueId);
        return this.home(model);
	}
	
	@RequestMapping(value = "/type/{queueTypeId}", method = RequestMethod.GET)
	public String getAllQueues(Model model, @PathVariable String queueTypeId,
			@RequestParam(value = "limit", required = false, defaultValue = "limit") int limit,
			@RequestParam(value = "offset", required = false, defaultValue = "offset") int offset) {
		List<Map<String, Object>> typeQueues = ezQueueService.getTypeQueues(queueTypeId, limit, offset);
		model.addAttribute("QUEUES", typeQueues);
		model.addAttribute("QUEUES_SIZE", typeQueues.size());
        return EzQueueConstants.PAGE_QUEUE_LIST;
	}
	
	@RequestMapping(value = "/promotion/{queueTypeId}", method = RequestMethod.GET)
	public String getPromotionsQueues(Model model, 
			@RequestParam(value = "limit", required = false, defaultValue = "limit") int limit,
			@RequestParam(value = "offset", required = false, defaultValue = "offset") int offset) {
		List<Map<String, Object>> promotionQueues = ezQueueService.getPromotionQueues(limit, offset);
		model.addAttribute("QUEUES", promotionQueues);
		model.addAttribute("QUEUES_SIZE", promotionQueues.size());
        return EzQueueConstants.PAGE_QUEUE_LIST;
	}
	
	@RequestMapping(value = "/me/{userId}", method = RequestMethod.GET)
	public String getMyQueues(Model model, 
			@PathVariable String userId, 
			@RequestParam(value = "limit", required = false, defaultValue = "limit") int limit,
			@RequestParam(value = "offset", required = false, defaultValue = "offset") int offset) {
		List<Map<String, Object>> myQueues = ezQueueService.getMyQueues(userId, limit, offset);
		model.addAttribute("QUEUES", myQueues);
		model.addAttribute("QUEUES_SIZE", myQueues.size());
        return EzQueueConstants.PAGE_QUEUE_LIST;
	}
	
	@RequestMapping(value = "/favorite/{userId}", method = RequestMethod.GET)
	public String getFavorite(Model model, 
			@PathVariable String userId, 
			@RequestParam(value = "limit", required = false, defaultValue = "limit") int limit,
			@RequestParam(value = "offset", required = false, defaultValue = "offset") int offset) {
		List<Map<String, Object>> favoriteQueues = ezQueueService.getFavoriteQueues(userId, limit, offset);
		model.addAttribute("QUEUES", favoriteQueues);
		model.addAttribute("QUEUES_SIZE", favoriteQueues.size());
        return EzQueueConstants.PAGE_QUEUE_LIST;
	}
	
	@RequestMapping(value = "/queuing/{userId}", method = RequestMethod.GET)
	public String getQueuing(Model model, 
			@PathVariable String userId, 
			@RequestParam(value = "limit", required = false, defaultValue = "limit") int limit,
			@RequestParam(value = "offset", required = false, defaultValue = "offset") int offset) {
		List<Map<String, Object>> queuingQueues = ezQueueService.getQueuingQueues(userId, limit, offset);
		model.addAttribute("QUEUES", queuingQueues);
		model.addAttribute("QUEUES_SIZE", queuingQueues.size());
        return EzQueueConstants.PAGE_QUEUE_LIST;
	}
	
	@RequestMapping(value = "/create/{userId}", method = RequestMethod.GET)
	public String createQueue(Model model, @PathVariable String userId) {
		model.addAttribute("CREATE_QUEUE", ezQueueService.createQueue(userId));
        return EzQueueConstants.PAGE_CREATE;
	}
	
	@RequestMapping(value = "/search/{text}", method = RequestMethod.GET)
	public String getSearch(Model model, @PathVariable String text,
			@RequestParam(value = "limit", required = false, defaultValue = "limit") int limit,
			@RequestParam(value = "offset", required = false, defaultValue = "offset") int offset) {
		List<Map<String, Object>> searchQueues = ezQueueService.getSearchQueues(text, limit, offset);
		model.addAttribute("QUEUES", searchQueues);
		model.addAttribute("QUEUES_SIZE", searchQueues.size());
        return EzQueueConstants.PAGE_QUEUE_LIST;
	}
	
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
	public String getUserQueues(Model model, @PathVariable String userId, 
			@RequestParam(value = "limit", required = false, defaultValue = "limit") int limit,
			@RequestParam(value = "offset", required = false, defaultValue = "offset") int offset) {
		List<Map<String, Object>> userQueues = ezQueueService.getUserQueues(userId, limit, offset);
		model.addAttribute("QUEUES", userQueues);
		model.addAttribute("QUEUES_SIZE", userQueues.size());
        return EzQueueConstants.PAGE_QUEUE_LIST;
	}
	
	@RequestMapping(value = "/queue/detail", method = RequestMethod.GET)
	public String getQueueDetail(Model model, 
			@RequestParam(value = "userId", required = false) String userId, 
			@RequestParam(value = "queueId") String queueId) {
		model.addAttribute("ENV_URL", envUrl + "/ezqueue/home/");
		model.addAttribute("QUEUE_DETAIL", ezQueueService.getQueueDetail(userId, queueId));
        return EzQueueConstants.PAGE_QUEUE_DETAIL;
	}
	
}
