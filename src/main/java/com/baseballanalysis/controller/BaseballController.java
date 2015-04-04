package com.baseballanalysis.controller;

import javax.servlet.http.HttpServletResponse;

public class BaseballController {
	
	public void setResposeObject(HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods",
				"GET, POST, DELETE, PUT");
		response.setHeader("Access-Control-Allow-Headers",
				"Content-Type, Accept");
	}

}
