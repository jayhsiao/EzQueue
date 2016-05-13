package com.ezqueue.controller;


import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ezqueue.model.Store;
import com.ezqueue.repository.StoreRepository;
import com.ezqueue.util.ResponseObject;
import com.ezqueue.util.RetrunCode;
import com.ezqueue.util.StringUtil;
import com.google.common.collect.Lists;

@RestController
@RequestMapping(value = "/admin/store")
public class AdminStoreController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private StoreRepository storeRepository;
	
	@RequestMapping(value = "/{storeId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getStore(@PathVariable String storeId){
		ResponseObject responseObject = new ResponseObject();
		try {
			Store store = storeRepository.findOne(storeId);
			responseObject.setReturnCode(RetrunCode.SUCCESS);
			responseObject.setReturnObject(store);
		} 
		catch (Exception e) {
			logger.error(e, e);
			responseObject.setReturnCode(RetrunCode.FAIL);
			responseObject.setReturnMessage(e.getMessage());
		}
		return this.getResponse(responseObject);
	}
	
	@RequestMapping(value = "/stores", method = RequestMethod.GET)
	public ResponseEntity<Object> getStores(){
		ResponseObject responseObject = new ResponseObject();
		try {
			List<Store> stores = Lists.newArrayList(storeRepository.findAll().iterator());
			responseObject.setReturnCode(RetrunCode.SUCCESS);
			responseObject.setReturnObject(stores);
		} 
		catch (Exception e) {
			logger.error(e, e);
			responseObject.setReturnCode(RetrunCode.FAIL);
			responseObject.setReturnMessage(e.getMessage());
		}
		return this.getResponse(responseObject);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Object> addStore(@RequestBody Map<String, Object> map){
		ResponseObject responseObject = new ResponseObject();
		try {
			Store store = new Store();
			store.setStoreId(StringUtil.getUUID());
			store.setName((String) map.get("name"));
			store.setPhone((String) map.get("phone"));
			store.setLongitude((String) map.get("longitude"));
			store.setLatitude((String) map.get("latitude"));
			store.setCountry((String) map.get("country"));
			store.setCity((String) map.get("city"));
			store.setDistrict((String) map.get("district"));
			store.setAddress((String) map.get("address"));
			
			Date now = Calendar.getInstance().getTime();
			store.setCreateDate(now);
			store.setCreateUser((String) map.get("userId"));
			store.setUpdateDate(now);
			store.setUpdateUser((String) map.get("userId"));
			
			storeRepository.save(store);
			responseObject.setReturnCode(RetrunCode.SUCCESS);
		} 
		catch (Exception e) {
			logger.error(e, e);
			responseObject.setReturnCode(RetrunCode.FAIL);
			responseObject.setReturnMessage(e.getMessage());
		}
		return this.getResponse(responseObject);
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.DELETE)
	public ResponseEntity<Object> removeStore(@RequestBody Map<String, Object> map){
		ResponseObject responseObject = new ResponseObject();
		try {
			storeRepository.delete((String) map.get("storeId"));
			responseObject.setReturnCode(RetrunCode.SUCCESS);
		} 
		catch (Exception e) {
			logger.error(e, e);
			responseObject.setReturnCode(RetrunCode.FAIL);
			responseObject.setReturnMessage(e.getMessage());
		}
		return this.getResponse(responseObject);
	}
	
}
