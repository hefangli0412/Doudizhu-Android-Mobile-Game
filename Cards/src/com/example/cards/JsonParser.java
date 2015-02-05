package com.example.cards;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonParser {
	Object pojo = null;
	public JsonParser(Object o) {
		pojo = o;
	}
	public JsonParser() {
		pojo = null;
	}
	public String createJson(){
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(pojo);	 
		} catch (JsonGenerationException e) {
	 
			e.printStackTrace();
	 
		} catch (JsonMappingException e) {	 
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonString;
	}
	public String createJson(Object o){
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(o);	 
		} catch (JsonGenerationException e) {
	 
			e.printStackTrace();
	 
		} catch (JsonMappingException e) {	 
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonString;
	}
}
