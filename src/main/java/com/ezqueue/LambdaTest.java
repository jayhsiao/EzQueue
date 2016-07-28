package com.ezqueue;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LambdaTest {
	
	public static void main(String[] args) throws Exception {
		
		URL url = new URL("https://android.googleapis.com/gcm/send");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "key=AIzaSyCCwKx0jbRJVtAyVrAZBNGwbuSRMXMrj9E");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);
        
        List<String> regIds = new ArrayList<>();
        regIds.add("cfjZOqzryPc:APA91bGpCialom_DStK1pbBRYtcwaiolAggzcMhPWgjEXukpXefQxONAUjBb_rGG9JhnSkjS4p7LDXpyZ1WSI082AAMqeLP_4Zob05FhkQv-L8uVjRtUvP8o-tGAgDQemCRIVIQMVgsE");
        
        Map<String, String> map = new HashMap<>();
        map.put("message", "安安安安安安安安安安安安安安安安安安安安");
        map.put("title", "安安安安安安安安安安安安安安安安安安安安");
        map.put("description", "安安安安安安安安安安安安安安安安安安安安");
        
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("registration_ids", regIds);
        resultMap.put("data", map);
        
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(resultMap);
        System.out.println("jsonString = "+jsonString);
        
        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
        out.write(jsonString.getBytes());
        out.flush();
        out.close();
        
        System.out.println("conn.getResponseCode() = "+conn.getResponseCode());
	}
	
}
