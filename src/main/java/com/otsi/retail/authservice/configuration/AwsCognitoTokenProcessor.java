package com.otsi.retail.authservice.configuration;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;

import net.minidev.json.JSONArray;
import sun.text.normalizer.ICUBinary.Authenticate;

@Component
public class AwsCognitoTokenProcessor {
	 
	@Autowired
	private ConfigurableJWTProcessor configurableJWTProcessor;


	public JWTClaimsSet getCliamsFromToken(String token) throws ParseException, BadJOSEException, JOSEException {
		return configurableJWTProcessor.process(getBearerToken(token), null);
	}

	private String getBearerToken(String token) {
		return token.startsWith("Bearer ") ? token.substring("Bearer ".length()) : token;

	}
}
