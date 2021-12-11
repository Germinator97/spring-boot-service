package com.cinetpay.billing.application.response;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
    
    public static ResponseEntity<Object> generateResponse(Integer code, boolean status, String message, Object data, HttpStatus http) {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("code", code);
        map.put("status", status);
        map.put("message", message);
        map.put("data", data);

        return new ResponseEntity<Object>(map, http);
    }
    
}
