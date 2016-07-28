package com.ezqueue.controller.admin;


import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ezqueue.controller.BaseController;
import com.ezqueue.model.Store;
import com.ezqueue.repository.StoreRepository;
import com.ezqueue.util.StringUtil;
import com.google.common.collect.Lists;

@RestController
@RequestMapping(value = "/admin/stores")
public class AdminStoreController extends BaseController {
	
	@Autowired
	private StoreRepository storeRepository;
	
	@RequestMapping(value = "/{storeId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getStore(@PathVariable String storeId){
		return this.getResponse(storeRepository.findOne(storeId));
	}
	
	@RequestMapping(value = "/stores", method = RequestMethod.GET)
	public ResponseEntity<Object> getStores(){
		return this.getResponse(Lists.newArrayList(storeRepository.findAll().iterator()));
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Object> addStore(@RequestBody Map<String, Object> map){
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
		return this.getResponse();
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.DELETE)
	public ResponseEntity<Object> removeStore(@RequestBody Map<String, Object> map){
		storeRepository.delete((String) map.get("storeId"));
		return this.getResponse();
	}
	
}
