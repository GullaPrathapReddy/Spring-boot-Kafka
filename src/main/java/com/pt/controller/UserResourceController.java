package com.pt.controller;

import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pt.model.UserResource;

@RestController
public class UserResourceController {
		@Autowired
		private KafkaTemplate<String, String> kafkatemplate;
		
		@Value("${spring.kafka.producer.topic}")
		private String topic;
		
		@RequestMapping("/producer/{name}")
		public String SendData(@PathVariable("name")String name) throws JsonProcessingException {
				UserResource user=new UserResource();
				user.setName(name);
				user.setSno(1234);
				user.setDate(new Date());
				ObjectMapper mapper=new ObjectMapper();
				System.out.println(topic);
				kafkatemplate.send(topic,mapper.writeValueAsString(user));
			
			return "Published Successfully  USerssdsjvhsf";
			
		}
		
}
