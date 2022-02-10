package com.otsi.retail.authservice.responceModel;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Response {
	
	
	private int statusCode;
    private String body;
    private Map<String, String> headers;
    private  String errorDescription;
    private Map<String, String> authResponce;
	
	
	
	
	
}
