package com.otsi.rpd.authservice.configuration;

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
	 private static final Log logger = LogFactory.getLog(AwsCognitoTokenProcessor.class);

	@Value("${Cognito.aws.idTokenPoolUrl}")
	private String ID_TOKEN_URL;
	@Autowired
	private ConfigurableJWTProcessor configurableJWTProcessor;

	public Authentication authenticate(HttpServletRequest request) throws Exception {

		String token = request.getHeader("Authorization");
		
		if (token == null || token.equals(null) ) {
			logger.error("Token missed");
			throw new Exception("Token missed");
		}
			JWTClaimsSet claims = getCliamsFromToken(token);
			validateIssuer(claims);
			verifyIfIdToken(claims);
			String username = getUserNameFrom(claims);
			if (username != null) {
				List<GrantedAuthority> grantedAuthorities = getAuthoritiesFromCliams(claims.getClaims());
				User user = new User(username, "", grantedAuthorities);
				return new JwtAuthentication(user, claims, grantedAuthorities);
			}

		
		return null;

	}

	private List<GrantedAuthority> getAuthoritiesFromCliams(Map<String, Object> claims) {
		JSONArray roles =  (JSONArray) claims.get("cognito:groups");
		List<GrantedAuthority> authorities = new ArrayList<>();
		roles.stream().forEach(a->{
			authorities.add(new SimpleGrantedAuthority((String) a));
		});
			
		

		return authorities;
	}

	private String getUserNameFrom(JWTClaimsSet claims) {
		return claims.getClaims().get("cognito:username").toString();
	}

	private void verifyIfIdToken(JWTClaimsSet claims) throws Exception {
		if (!claims.getIssuer().equals(ID_TOKEN_URL)) {
			logger.error("JWT Token is not an ID Token");
			throw new Exception("JWT Token is not an ID Token");
		}

	}

	private void validateIssuer(JWTClaimsSet claims) throws Exception {
		if (!claims.getIssuer().equals(ID_TOKEN_URL)) {
			logger.error("Issuer %s does not match cognito idp %s");
			throw new Exception(
					String.format("Issuer %s does not match cognito idp %s", claims.getIssuer(), ID_TOKEN_URL));
		}
	}

	public JWTClaimsSet getCliamsFromToken(String token) throws ParseException, BadJOSEException, JOSEException {
		return configurableJWTProcessor.process(getBearerToken(token), null);
	}

	private String getBearerToken(String token) {
		return token.startsWith("Bearer ") ? token.substring("Bearer ".length()) : token;

	}
}
