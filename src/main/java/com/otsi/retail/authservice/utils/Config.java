package com.otsi.retail.authservice.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Config {
	@Value("${RazoryPay.key_Id}")
	private String Key;
	
	@Value("${RazorPay.key_Secret}")
	private String secert;
}
